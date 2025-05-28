package org.example.juc.datastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentHashMapTest {
    private static final Logger log = LoggerFactory.getLogger("ConcurrentHashMapTest");

    private static final String FILE_DIR = System.getProperty("user.dir") + File.separator + "src/main/resources/chmtest";
    private static final int FILE_COUNT = 16;
    private static final String FILE_PREFIX = "letter-";
    private static final int LETTER_COUNT = 250;

    public static void main(String[] args) {
        // 生成测试文件
        generateTestFile();

        // 测试使用HashMap统计字母
        // testCountUseHashMap();

        // 测试使用ConcurrentHashMap统计字母
        testCountUseConcurrentHashMap();

        // 清理测试文件
        cleanTestFileDir();
    }

    public static void testCountUseConcurrentHashMap() {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        ConcurrentHashMap<Character, Integer> map = new ConcurrentHashMap<>();
        // ConcurrentHashMap<Character, LongAdder> map = new ConcurrentHashMap<>();
        CountDownLatch countDownLatch = new CountDownLatch(FILE_COUNT);
        for (int i = 0; i < FILE_COUNT; i++) {
            int fileSuffix = i;
            pool.submit(() -> {
                try (FileInputStream fis = new FileInputStream(new File(FILE_DIR, FILE_PREFIX + fileSuffix))) {
                    int data;
                    while ((data = fis.read()) != -1) {
                        char letter = (char) data;
                        map.compute(letter, (k, v) -> v == null ? 1 : v + 1);
                        // 使用LongAdder累加器方式
                        // LongAdder longAdder = map.computeIfAbsent(letter, k -> new LongAdder());
                        // longAdder.increment();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        pool.shutdown();
        System.out.println(map);
    }

    public static void testCountUseHashMap() {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        HashMap<Character, Integer> map = new HashMap<>();
        CountDownLatch countDownLatch = new CountDownLatch(FILE_COUNT);
        for (int i = 0; i < FILE_COUNT; i++) {
            int fileSuffix = i;
            pool.submit(() -> {
                try (FileInputStream fis = new FileInputStream(new File(FILE_DIR, FILE_PREFIX + fileSuffix))) {
                    int data;
                    while ((data = fis.read()) != -1) {
                        // synchronized (map){
                        char letter = (char) data;
                        map.compute(letter, (k, v) -> v == null ? 1 : v + 1);
                        // }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        pool.shutdown();
        System.out.println(map);
    }

    public static File createTestFileDir() {
        File file = new File(FILE_DIR);
        if (!file.exists()) {
            boolean succeed = file.mkdir();
            if (!succeed) throw new RuntimeException("create file dir error");
            log.info("create test file dir");
        }
        return file;
    }

    public static void cleanTestFileDir() {
        File file = new File(FILE_DIR);
        if (!file.exists()) return;
        File[] files = file.listFiles();
        if (files != null) {
            for (File testFile : files) {
                testFile.delete();
            }
        }
        file.delete();
    }

    public static void generateTestFile() {
        log.info("start generate test file");
        ArrayList<Byte> letterList = new ArrayList<>();
        for (int i = 0; i < LETTER_COUNT; i++) {
            for (byte letter = 65; letter < 91; letter++) {
                letterList.add(letter);
            }
        }
        Collections.shuffle(letterList);

        File file = createTestFileDir();
        int allLetterCount = LETTER_COUNT * 26;
        int fileLetterCount = (allLetterCount + FILE_COUNT - 1) / FILE_COUNT;
        log.info("start generate test file, all count: {}, file letter count: {}", allLetterCount, fileLetterCount);
        int letterIdx = 0;
        for (int i = 0; i < FILE_COUNT; i++) {
            int end = Math.min(letterList.size(), (i + 1) * fileLetterCount);
            for (; letterIdx < end; letterIdx++) {
                try (FileOutputStream fos = new FileOutputStream(new File(file, FILE_PREFIX + i), true)) {
                    fos.write(letterList.get(letterIdx));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
