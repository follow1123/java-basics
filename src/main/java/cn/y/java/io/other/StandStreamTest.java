package cn.y.java.io.other;

import java.io.*;

public class StandStreamTest {

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        File fileA = new File(projectPath, "src/main/resources/a.txt");
        File fileB = new File(projectPath, "src/main/resources/b.txt");
        System.out.println("输入a开头的字符保存到a.txt文件内\n输入b开头的字符保存到b.txt文件内\n输入q退出");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintStream psA = new PrintStream(fileA);
             PrintStream psB = new PrintStream(fileB)) {
            PrintStream defaultPs = System.out;

            String line;

            while (!(line = br.readLine()).equals("q")){
                if (line.startsWith("a")){
                    System.setOut(psA);
                }else if (line.startsWith("b")){
                    System.setOut(psB);
                }else {
                    System.setOut(defaultPs);
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
