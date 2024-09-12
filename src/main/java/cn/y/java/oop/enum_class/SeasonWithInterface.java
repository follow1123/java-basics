package cn.y.java.oop.enum_class;

public enum SeasonWithInterface implements Runnable{
    // 枚举实例自己实现对应的逻辑
    SPRING("春天"){
        @Override
        public void run() {
            System.out.println("春天 run");
        }
    },
    SUMMER("夏天"),
    AUTUMN("秋天"){
        @Override
        public void run() {
            System.out.println("秋天 run");
        }
    },
    WINTER("冬天");


    private final String seasonName;

    private SeasonWithInterface(String seasonName){
        this.seasonName = seasonName;
    }

    public String getSeasonName() {
        return seasonName;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                '}';
    }

    @Override
    public void run() {
        System.out.println("季节");
    }
}
