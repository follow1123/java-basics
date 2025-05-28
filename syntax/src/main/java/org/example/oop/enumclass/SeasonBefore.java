package org.example.oop.enumclass;

public class SeasonBefore {
    private final String seasonName;

    private SeasonBefore(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public static final SeasonBefore SPRING = new SeasonBefore("春天");
    public static final SeasonBefore SUMMER = new SeasonBefore("夏天");
    public static final SeasonBefore AUTUMN = new SeasonBefore("秋天");
    public static final SeasonBefore WINTER = new SeasonBefore("冬天");

    @Override
    public String toString() {
        return "SeasonBefore{" +
                "seasonName='" + seasonName + '\'' +
                '}';
    }
}
