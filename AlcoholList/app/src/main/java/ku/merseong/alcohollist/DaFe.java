package ku.merseong.alcohollist;

public class DaFe
{
    public Enums.Feel feel;

    public int day;
    public int month;
    public int year;

    // 인풋방식 (기분1자리)(연도2자리)(월2자리)(일2자리)
    public DaFe(int dafe)
    {
        day = dafe % 100;
        month = (dafe % 10000) / 100;
        year = (dafe % 1000000) / 10000;
    }
}
