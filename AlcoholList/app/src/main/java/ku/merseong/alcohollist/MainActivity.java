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

    //private int whatitemClicked = -1; 이전버전의 제거에 사용됨

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

        // 술 일기의 삭제에 관여, 두번 터치시 삭제되도록 클릭된 item의 index를 기억해둔다.
        m_oListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                toViewActivity(i);
                /*이전 버전의 제거방식
                if (whatitemClicked != i)
                {
                    Toast.makeText(MainContext, Alcohol.publicAlcList().get(i).name + "에 대한 기록을 삭제하려면 한번 더 눌러주세요.", Toast.LENGTH_LONG).show();
                    whatitemClicked = i;
                }
                else {
                    // Alcohol의 데이터를 삭제하고, 그걸 데이터에 저장한 뒤 다시 로드한다.
                    Alcohol.delete(Alcohol.AlcList.size() - i - 1);
                    Alcohol.Save();
                    Load();
                    m_oListView.setAdapter(new ListAdapter(Alcohol.publicAlcList()));
                    whatitemClicked = -1;
                    Toast.makeText(MainContext, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        m_oListView.setAdapter(new ListAdapter(Alcohol.publicAlcList()));
    }

    // 리스트의 하나를 눌렀을때 창이 넘어가도록
    public void toViewActivity(int index)
    {
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    // 추가 창으로 넘기도록
    public void onAddButtonClicked(View view)
    {
        Intent intent = new Intent(this, AddEditActivity.class);
        startActivity(intent);
    }

    // 초기에 데이터를 불러온다.
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

    // 검색 버튼, 검색 후 아이콘이 바뀌도록
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

