package cn.y.java.juc.thread_local;

public class Obj {

    private byte[] data;
    private String name;

    public Obj() {
        this("default");
    }

    public Obj(String name) {
        this(false);
        this.name = name;
    }

    public Obj(boolean flag) {
        if (flag){
            data = new byte[1024 * 1024];
        }
    }

    @Override
    public String toString() {
        return "Obj{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("obj " + name + " recycled");
    }
}
