package ku.merseong.alcohollist;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Alcohol
{
    private static int nextindex = 0;
    private static ArrayList<Alcohol> AlcList = new ArrayList<Alcohol>();

    public int index;
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

    // 날짜에 대한 String을 반환
    public String GetDate() { return Integer.toString(dateNfeel.year) + "년 " + Integer.toString(dateNfeel.month) + "월 " + Integer.toString(dateNfeel.day) + "일"; }

    // 코멘트에 대한 String을 길이를 조절해서 반환. 설정값 30
    public String GetShortComment()
    {
        int limit_len = 80;
        if (limit_len > comment.length())
            return comment;
        else
            return comment.substring(0, limit_len) + "...";
    }

    public String ToString()
    {
        return Integer.toString(index) + " " + dateNfeel.toString() + " " + name + " " +
                category.getName() + " " + food + " " + comment;
    }

    // index에 해당하는 기록을 삭제
    public static void delete(int index)
    {
        AlcList.remove(index);
    }

    public static void Save(Activity activity)
    {
        String FILENAME = "DATA_file";
        String tosave = "DATAS!";

        try
        {
            FileOutputStream fos = activity.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            try
            {
                osw.write(tosave);
                osw.flush();
                osw.close();
                Toast.makeText(activity.getBaseContext(), "Data saved", Toast.LENGTH_LONG).show();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void Load()
    {}

    public static void Reset()
    {}

    // AlcList를 역순으로 출력
    public static ArrayList<Alcohol> publicAlcList()
    {
        ArrayList<Alcohol> out = new ArrayList<>();
        for (int i = AlcList.size() - 1; i >= 0; i--)
        {
            out.add(AlcList.get(i));
        }
        return out;
    }

    // 이름을 입력받고, 그 이름이 포함된 이름을 가진 index들을 리턴, 없으면 빈 ArrayList를 리턴
    public ArrayList<Integer> SearchbyName(String s_name)
    {
        ArrayList<Integer> out = new ArrayList<>();

        for (Alcohol Alc : AlcList)
        {
            if (Alc.name.contains(s_name)) out.add(Alc.index);
        }

        return out;
    }

    // 술의 종류중 하나를 입력받고, 해당하는 index들을 리턴, 없으면 빈 ArrayList를 리턴
    public ArrayList<Integer> SearchbyCategory(String s_category)
    {
        ArrayList<Integer> out = new ArrayList<>();
        for (Enums.AlcCategory item : Enums.AlcCategory.values())
            if (item.getName().equals(s_category))
                for (Alcohol alc : AlcList)
                    if (alc.category.getName().equals(s_category)) out.add(alc.index);
        return out;
    }

    // 날짜를 입력받고 해당하는 index들을 리턴, 없으면 빈 ArrayList를 리턴
    public ArrayList<Integer> SearchbyDate(int s_date)
    {
        ArrayList<Integer> out = new ArrayList<>();
        if (s_date <= 0 || s_date >= 1000000) { return out; }

        int year = s_date / 10000;
        int month = (s_date % 10000) / 100;
        int day = (s_date % 100);
        if (year > 0 && month > 0 && day > 0)
            for (Alcohol item : AlcList)
            {
                if (item.dateNfeel == new DaFe(year, month, day)) out.add(item.index);
            }
        return out;
    }
}
