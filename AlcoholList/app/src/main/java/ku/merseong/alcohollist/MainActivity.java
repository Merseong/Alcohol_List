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
        new Alcohol(1111111, "복순도가", Enums.AlcCategory.TAKJU, "NONE", "존나개맛있다.");
        new Alcohol(1222222, "느린마을", Enums.AlcCategory.TAKJU, "NONE", "존나맛있다.");
        new Alcohol(1111111, "복순도가", Enums.AlcCategory.TAKJU, "NONE", "존나개맛있다.");
        new Alcohol(1222222, "느린마을", Enums.AlcCategory.TAKJU, "NONE", "존나맛있다.");
        new Alcohol(1111111, "복순도가", Enums.AlcCategory.TAKJU, "NONE", "존나개맛있다.");
        new Alcohol(1222222, "느린마을", Enums.AlcCategory.TAKJU, "NONE", "존나맛있다.");
        new Alcohol(1111111, "복순도가", Enums.AlcCategory.TAKJU, "NONE", "존나개맛있다.");
        new Alcohol(1222222, "느린마을", Enums.AlcCategory.TAKJU, "NONE", "존나맛있다.");

        m_oListView = (ListView)findViewById(R.id.listview1);
        ListAdapter oAdapter = new ListAdapter(Alcohol.AlcList);
        m_oListView.setAdapter(oAdapter);
    }
}

