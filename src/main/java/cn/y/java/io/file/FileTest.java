package cn.y.java.io.file;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class FileTest {

    /**
     * 构造器
     */
    @Test
    public void testConstructor() {
        /*
         相对路径创建File文件
         当前使用test方法创建File对象，相对路径就是当前模块下
         如果是使用main方法创建File对象，则相对路径就是当前工程下

         test方式
         project-name 项目名称
         │
         ├─module1 模块1
         │
         ├─module2 模块2
         │
         └─a.txt 文件

         main方式
         project-name 项目名称
         │
         ├─module1 模块1
         │  │
         │  └─a.txt 文件
         │
         └─module2 模块2
         */
        File file = new File("a.txt");
        System.out.println(file);


        // 绝对路径创建目录
        File file1 = new File("C:\\b");
        System.out.println(file1);

        /*
         绝对路径创建文件，使用指定子文件方式
         如果使用这种方式，第一个参数必须是目录，第二个参数可以是文件也可以是目录
         */
        File file2 = new File("C:\\b", "a.txt");
        System.out.println(file2);

        // 和上面类似，只不过第一个参数是File对象
        File file3 = new File(new File("C:\\b"), "b.txt");
        System.out.println(file3);
    }

    /**
     * 获取文件或目录基本信息
     */
    @Test
    public void testFileInfo() {
        // 相对路径文件信息
        File file = new File("a.txt");
        // 获取名称
        System.out.println(file.getName());
        // 获取路径
        System.out.println(file.getPath());
        // 获取绝对路径，直接返回路径字符串
        System.out.println(file.getAbsolutePath());
        // 获取绝对路径对象，返回路径File对象
        System.out.println(file.getAbsoluteFile());
        // 获取上层文件或目录
        System.out.println(file.getParent());
        // 获取文件长度，只对文件有效
        System.out.println(file.length());
        // 获取上次修改时间
        System.out.println(file.lastModified());

        System.out.println("-----------------");

        // 绝对路径目录信息
        File dir = new File("C:\\b");
        // 获取名称
        System.out.println(dir.getName());
        // 获取路径
        System.out.println(dir.getPath());
        // 获取绝对路径，直接返回路径字符串
        System.out.println(dir.getAbsolutePath());
        // 获取绝对路径对象，返回路径File对象
        System.out.println(dir.getAbsoluteFile());
        // 获取上层文件或目录
        System.out.println(dir.getParent());
        // 获取文件长度，只对文件有效
        System.out.println(dir.length());
        // 获取上次修改时间
        System.out.println(dir.lastModified());
    }

    /**
     * 列出目录的下一级
     */
    @Test
    public void testListFiles() {
        // 获取用户目录
        String home = System.getProperty("user.home");

        File file = new File(home);

        // 获取目录下一级所有文件或目录
        String[] filePathList = file.list();
        for (String filePath : filePathList) {
            System.out.println(filePath);
        }

        // 获取目录下一级所有文件或目录的File对象
        File[] fileList = file.listFiles();
        for (File f : fileList) {
            System.out.println(f);
        }
    }

    /**
     * 文件或目录重命名
     * 相同路径下文件会重命名，不同路径下文件会移动
     * 不同路径下如果目标路径不存在，则移动失败
     */
    @Test
    public void testRenameTo() {
        // 测试路径，用户桌面
        String dir = System.getProperty("user.home") + File.separator + "Desktop";
        // 原文件
        String src = dir + "\\a.txt";

        /*
         原文件存在，目标文件不存在
         路径相同，文件名称不同
         */
        // 目标文件
        String dest1 = dir + "\\b.txt";
        File file1 = new File(src);
        // 重命名成功
        // System.out.println(file1.renameTo(new File(dest1)));

        System.out.println("-----------------");
        /*
         原文件存在，目标文件不存在
         路径不同，目标路径存在
         */
        String dest2 = dir + "\\b\\a.txt";
        File file2 = new File(src);
        // 将文件移动到对应的路径上
        // System.out.println(file2.renameTo(new File(dest2)));

        System.out.println("-----------------");
        /*
         原文件存在，目标文件不存在
         路径不同，目标路径不存在
         */
        String dest3 = dir + "\\b\\a.txt";
        File file3 = new File(src);
        // 无法移动文件
        // System.out.println(file3.renameTo(new File(dest3)));
    }

    /**
     * 文件判断相关
     */
    @Test
    public void testCheck() {

        // 存在的目录，当前工程下的src目录肯定存在
        File dir = new File("src");
        // 文件或目录是否存在
        System.out.println(dir.exists());
        // 是否为目录
        System.out.println(dir.isDirectory());
        // 是否为文件
        System.out.println(dir.isFile());
        // 是否可读
        System.out.println(dir.canRead());
        // 是否可写
        System.out.println(dir.canWrite());
        // 是否是隐藏状态
        System.out.println(dir.isHidden());

        System.out.println("-----------------");
        // 不存在的文件
        File file = new File("abc.txt");
        // 文件或目录是否存在
        System.out.println(file.exists());
        // 是否为目录
        System.out.println(file.isDirectory());
        // 是否为文件
        System.out.println(file.isFile());
        // 是否可读
        System.out.println(file.canRead());
        // 是否可写
        System.out.println(file.canWrite());
        // 是否是隐藏状态
        System.out.println(file.isHidden());
    }

    /**
     * 文件的创建或删除
     */
    @Test
    public void testCreateOrDelete() throws IOException {

        // 测试路径，用户桌面
        String dir = System.getProperty("user.home") + File.separator + "Desktop";

        // 创建新文件，如果文件存在就创建，返回true，如果不存在则不创建，返回false
        File file = new File(dir, "a.txt");
        System.out.println(file.createNewFile());


        // 在测试路径下创建a\b目录，因为上层目录a不存在，所以无法创建b目录
        File file1 = new File(dir, "a\\b");
        System.out.println(file1.mkdir());

        // 在测试路径下创建a\b目录，如果上层目录不存在则依次创建
        File file2 = new File(dir, "a\\b");
        System.out.println(file2.mkdirs());

        // 删除文件
        File file3 = new File(dir, "a.txt");
        System.out.println(file3.delete());

        // 删除目录，因为a，目录下已经有b目录，需要使用递归删除的方式
        File file4 = new File(dir, "a");
        System.out.println(file4.delete());
    }

    /**
     * 递归打印文件
     */
    private void recursionPrintFile(File file){
        if (!file.exists()) return;
        if (file.isFile()){
            System.out.println(file.getName());
        }else if (file.isDirectory()){
            System.out.println(file.getName() + "-----------");
            for (File listFile : file.listFiles()) {
                recursionPrintFile(listFile);
            }
        }
    }

}
