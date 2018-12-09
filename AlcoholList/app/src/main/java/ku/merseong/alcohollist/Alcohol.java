package ku.merseong.alcohollist;

import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

public class Alcohol
{
    private static int nextindex = 0;
    public static ArrayList<Alcohol> AlcList = new ArrayList<Alcohol>();

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
        AlcList.add(this);
    }

    public Alcohol(String input)
    {
        String[] s_input = input.split("@");
        this.dateNfeel = new DaFe(Integer.parseInt(s_input[0]));
        this.name = s_input[1];
        this.category = Enums.AlcCategory.OTHERS;
        for (Enums.AlcCategory alc : Enums.AlcCategory.values()) {
            if (s_input[2].equals(alc.getName())) {
                this.category = alc;
                break;
            }
        }
        this.food = s_input[3];
        this.comment = s_input[4];
        this.index = nextindex++;
        AlcList.add(this);
    }

    // 날짜에 대한 String을 반환
    public String GetDate() { return Integer.toString(dateNfeel.year) + "년 " + Integer.toString(dateNfeel.month) + "월 " + Integer.toString(dateNfeel.day) + "일"; }

    // 코멘트에 대한 String을 길이를 조절해서 반환. 설정값 80
    public String GetShortComment()
    {
        int limit_len = 80;
        if (limit_len > comment.length())
            return comment;
        else
            return comment.substring(0, limit_len) + "...";
    }

    @Override
    // Save, Load를 위해 String 형태로 출력
    public String toString()
    {
        return dateNfeel.toString() + "@" + name + "@" + category.getName() + "@" + food + "@" + comment;
    }

    // index에 해당하는 기록을 삭제
    public static void delete(int index)
    {
        AlcList.remove(index);
    }

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

    public static Alcohol Search(int index)
    {
        for (Alcohol alc : AlcList)
        {
            if (alc.index == index) return alc;
        }
        return null;
    }

    public static HashSet<Integer> Search(String toSearch)
    {
        HashSet<Integer> out = new HashSet<>();

        out.addAll(SearchbyName(toSearch));
        out.addAll(SearchbyCategory(toSearch));
        out.addAll(SearchbyDate(toSearch));

        return out;
    }

    // 이름을 입력받고, 그 이름이 포함된 이름을 가진 index들을 리턴, 없으면 빈 ArrayList를 리턴
    private static ArrayList<Integer> SearchbyName(String s_name)
    {
        ArrayList<Integer> out = new ArrayList<>();

        for (Alcohol Alc : AlcList)
        {
            if (Alc.name.contains(s_name)) out.add(Alc.index);
        }

        return out;
    }

    // 술의 종류중 하나를 입력받고, 해당하는 index들을 리턴, 없으면 빈 ArrayList를 리턴
    private static ArrayList<Integer> SearchbyCategory(String s_category)
    {
        ArrayList<Integer> out = new ArrayList<>();
        for (Enums.AlcCategory item : Enums.AlcCategory.values())
            if (item.getName().equals(s_category))
                for (Alcohol alc : AlcList)
                    if (alc.category.getName().equals(s_category)) out.add(alc.index);
        return out;
    }

    // 날짜를 입력받고 해당하는 index들을 리턴, 없으면 빈 ArrayList를 리턴
    private static ArrayList<Integer> SearchbyDate(String s_date)
    {
        int i_date = 0;
        ArrayList<Integer> out = new ArrayList<>();

        try {
            i_date = Integer.parseInt(s_date);
        }
        catch (Exception e)
        {
            return out;
        }

        if (i_date <= 0) { return out; }

        i_date += 1000000;
        for (Alcohol item : AlcList)
        {
            if (item.dateNfeel.equals(new DaFe(i_date))) out.add(item.index);
        }
        return out;
    }

    public static void Save()
    {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Alc_List";

        File file;
        file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(path + File.separator + "Alc" + ".txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter buw = new BufferedWriter(new OutputStreamWriter(fos, "UTF8"));

            for (Alcohol alc : Alcohol.AlcList)
            {
                buw.write(alc.toString());
                buw.newLine();
            }

            buw.close();
            fos.close();
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.MainContext, "문제가 발생했습니다. 다시 시도해주세요. (권한 설정을 확인해주세요.)", Toast.LENGTH_SHORT).show();
        }
    }

    public static void Reset()
    {
        nextindex = 0;
        AlcList = new ArrayList<>();
    }
}
