package ku.merseong.alcohollist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.*;
import java.util.*;

public class MainActivity extends AppCompatActivity
{
    public static ListView m_oListView = null;
    private EditText searchText;

    public static MainActivity MainContext = null;
    private boolean isSearching = false;
    private ImageButton searchButton = null;

    public static InputMethodManager imm;

    private int whatitemClicked = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Load();

        MainContext = this;
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        searchButton = (ImageButton)findViewById(R.id.search_button);
        searchText = (EditText)findViewById(R.id.Search_text);
        m_oListView = (ListView)findViewById(R.id.listview1);
        m_oListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (whatitemClicked != i)
                {
                    Toast.makeText(MainContext, Alcohol.publicAlcList().get(i).name + "에 대한 기록을 삭제하려면 한번 더 눌러주세요.", Toast.LENGTH_LONG).show();
                    whatitemClicked = i;
                }
                else {
                    Alcohol.delete(Alcohol.AlcList.size() - i - 1);
                    Alcohol.Save();
                    Load();
                    m_oListView.setAdapter(new ListAdapter(Alcohol.publicAlcList()));
                    whatitemClicked = -1;
                    Toast.makeText(MainContext, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        m_oListView.setAdapter(new ListAdapter(Alcohol.publicAlcList()));
    }

    public void onAddButtonClicked(View view)
    {
        Intent intent = new Intent(this, AddEditActivity.class);
        startActivity(intent);
    }

    public void Load()
    {
        Alcohol.Reset();
        final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Alc_List" + File.separator + "Alc.txt";

        try
        {
            InputStream is = new FileInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line="";

            while((line=reader.readLine())!=null)
            {
                new Alcohol(line);
            }

            reader.close();
            is.close();
            Toast.makeText(this, "정상적으로 불러왔습니다.", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void onSearchButtonClicked(View view)
    {
        String toSearch = searchText.getText().toString();
        if (toSearch.length() == 0 || isSearching)
        {
            m_oListView.setAdapter(new ListAdapter(Alcohol.publicAlcList()));
            isSearching = false;
            searchButton.setImageResource(R.drawable.baseline_search_white_18dp);
            searchText.setText("");
            return;
        }
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Alcohol> Alc_list = new ArrayList<>();

        list.addAll(Alcohol.Search(toSearch));
        Collections.sort(list);
        for (int i = list.size() - 1; i >= 0; i--)
        {
            Alc_list.add(Alcohol.Search(list.get(i)));
        }

        searchButton.setImageResource(R.drawable.baseline_arrow_back_ios_white_18dp);
        searchText.clearFocus();
        isSearching = true;
        m_oListView.setAdapter(new ListAdapter(Alc_list));
    }
}

