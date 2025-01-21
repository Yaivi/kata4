package software.ulpgc.kata4.model;

public class Title {
    private final String id;
    private final TitleType titleType;
    private final String primaryTitle;
    private final String originalTitle;
    private final int isAdult;
    private final int startYear;
    private final int endYear;
    private final int runtime;

    public Title(String id, TitleType titleType, String primaryTitle, String originalTitle, int isAdult, int startYear, int endYear, int runtime) {
        this.id = id;
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtime = runtime;
    }
    public enum TitleType {
        Short,
        Movie,
        TvShort,
        TvSeries,
        TvPilot,
        TvMiniSeries,
        TvSpecial,
        VideoGame,
        TvEpisode,
        TvMovie,
        Video
    }

    public String getId() {
        return id;
    }

    public TitleType getTitleType() {
        return titleType;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public int getIsAdult() {
        return isAdult;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getRuntime() {
        return runtime;
    }

    @Override
    public String toString() {
        return "Title{" +
                "id='" + id + '\'' +
                ", titleType=" + titleType +
                ", primaryTitle='" + primaryTitle + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", isAdult=" + isAdult +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", runtime=" + runtime +
                '}';
    }
}
