package cn.y.java.demo_projects.minichat.model;

import java.io.*;
import java.util.Properties;

public class PropsModel {

    protected String dataRootDir;
    protected String filePath;
    private final Properties props;

    public PropsModel(String dataRootDir, String filename) {
        this.dataRootDir = dataRootDir;
        File file = new File(dataRootDir, filename);
        this.filePath = file.getAbsolutePath();
        createIfNotExists();

        try (FileInputStream fis = new FileInputStream(file)) {
            props = new Properties();
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PropsModel(String filename) {
        this(System.getProperty("user.dir") + File.separator + "src/main/resources/minichat/", filename);
    }

    public String getFilePath() {
        return filePath;
    }

    public String getProp(String name) {
        return props.getProperty(name);
    }

    public void setProp(String name, String value) {
        props.setProperty(name, value);
    }

    public void store() {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            props.store(fos, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createIfNotExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void delete() {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
