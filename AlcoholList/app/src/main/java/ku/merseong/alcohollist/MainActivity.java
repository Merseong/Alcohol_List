package ku.merseong.alcohollist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private ListView m_oListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Alcohol(1111111, "복순도가", Enums.AlcCategory.TAKJU, "NONE", "존나개맛있다.");
        new Alcohol(1222222, "느린마을", Enums.AlcCategory.TAKJU, "NONE", "존나맛있다.");
        new Alcohol(1111111, "참이슬", Enums.AlcCategory.SOJU, "NONE", "이걸 왜 돈주고먹음;");
        new Alcohol(1222222, "청하", Enums.AlcCategory.CHEONGJU, "Defense", "존나맛있다. 방어성공 ㅎㅎ 30자이상테스의ㅏㄴ러아러니ㅏ어리ㅏㅇ너ㅣㅏ리ㅏㅇ너ㅏㅣㄹ너어리ㅏㄴ런ㅇㄹㄴㅇㄹㄴㅇㄹ");
        new Alcohol(1111111, "복순도가", Enums.AlcCategory.TAKJU, "NONE", "존나개맛있다.");
        new Alcohol(1222222, "느린마을", Enums.AlcCategory.TAKJU, "NONE", "존나맛있다.");
        new Alcohol(1111111, "참이슬", Enums.AlcCategory.SOJU, "NONE", "이걸 왜 돈주고먹음;");
        new Alcohol(1222222, "청하", Enums.AlcCategory.CHEONGJU, "Defense", "존나맛있다. 방어성공 ㅎㅎ");
        new Alcohol(1111111, "복순도가", Enums.AlcCategory.TAKJU, "NONE", "존나개맛있다.");
        new Alcohol(1222222, "느린마을", Enums.AlcCategory.TAKJU, "NONE", "존나맛있다.");
        new Alcohol(1111111, "참이슬", Enums.AlcCategory.SOJU, "NONE", "이걸 왜 돈주고먹음;");
        new Alcohol(1222222, "청하", Enums.AlcCategory.CHEONGJU, "Defense", "존나맛있다. 방어성공 ㅎㅎ");

        m_oListView = (ListView)findViewById(R.id.listview1);
        ListAdapter oAdapter = new ListAdapter(Alcohol.AlcList);
        m_oListView.setAdapter(oAdapter);
    }
}

