package ku.merseong.alcohollist;

import java.util.Calendar;

public class DaFe
{
    public Enums.Feel feel;

    public int day;
    public int month;
    public int year;

    // 인풋방식 (기분1자리)(연도2자리)(월2자리)(일2자리)
    public DaFe(int dafe)
    {
        if (dafe == 1000000)
        {
            Calendar cal = Calendar.getInstance();
            day = cal.get(Calendar.DATE);
            month = cal.get(Calendar.MONTH) + 1;
            year = cal.get(Calendar.YEAR) - 2000;
        }
        else
        {
            day = dafe % 100;
            month = (dafe % 10000) / 100;
            year = (dafe % 1000000) / 10000;
        }
        feel = Enums.Feel.SOSO;
        for (Enums.Feel fe : Enums.Feel.values())
            if (fe.ordinal() == dafe/1000000) {feel = fe; break;}
    }

    @Override
    public boolean equals(Object obj)
    {
        DaFe tocomp = (DaFe)obj;
        return tocomp.toString().equals(this.toString());
    }

    @Override
    public String toString()
    {
        return Integer.toString(feel.ordinal()) + (year < 10 ? "0" : "") + Integer.toString(year) +
                (month < 10 ? "0" : "") + Integer.toString(month) +
                (day < 10 ? "0" : "") + Integer.toString(day);
    }
}
