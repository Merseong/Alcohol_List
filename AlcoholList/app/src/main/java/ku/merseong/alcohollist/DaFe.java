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
        feel = Enums.Feel.SOSO;
        for (Enums.Feel fe : Enums.Feel.values())
            if (fe.ordinal() == dafe/1000000) {feel = fe; break;}
    }

    public DaFe(int year, int month, int day)
    {
        this.year = year;
        this.month = month;
        this.day = day;
        feel = Enums.Feel.SOSO;
    }

    @Override
    public boolean equals(Object obj)
    {
        DaFe tocomp = (DaFe)obj;
        if (tocomp.year == this.year && tocomp.month == this.month && tocomp.day == this.day) return true;
        else return false;
    }

    @Override
    public String toString()
    {
        return feel.name() + Integer.toString(year) + Integer.toString(month) + Integer.toString(day);
    }
}
