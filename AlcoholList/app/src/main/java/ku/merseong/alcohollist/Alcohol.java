package ku.merseong.alcohollist;

import java.util.ArrayList;

public class Alcohol
{
    private static int nextindex = 0;
    public int index;
    public static ArrayList<Alcohol> AlcList = new ArrayList<Alcohol>();

    public DaFe dateNfeel;
    public String name;
    public Enums.AlcCategory category;
    public String food;
    public String comment;

    public Alcohol(int date, String name, Enums.AlcCategory cat, String food, String comment)
    {
        this.name = name;
        this.category = cat;
        this.food = food;
        this.comment = comment;
        this.dateNfeel = new DaFe(date);
        index = nextindex++;
        AlcList.add(index, this);
    }

    public String GetDate() { return Integer.toString(dateNfeel.year) + "년 " + Integer.toString(dateNfeel.month) + "월 " + Integer.toString(dateNfeel.day) + "일"; }

    public String GetShortComment()
    {
        int limit_len = 30;
        if (limit_len > comment.length())
            return comment;
        else
            return comment.substring(0, limit_len) + "...";
    }

    public static void delete(int index)
    {
        AlcList.remove(index);
    }

    public int SearchByName(String tosearch)
    {
        return -1;
    }

    public int SearchByDate(int tosearch)
    {
        if (tosearch <= 0) return -1;

        int year = tosearch / 10000;
        int month = (tosearch % 10000) / 100;
        int day = (tosearch % 100);
        if (year > 0 && month > 0 && day > 0)
            for (Alcohol item : AlcList)
            {
                if (item.dateNfeel == new DaFe(year, month, day)) return item.index;
            }
        return -1;
    }
}
