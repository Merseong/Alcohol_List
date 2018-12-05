package ku.merseong.alcohollist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    public static ListView m_oListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_oListView = (ListView)findViewById(R.id.listview1);
        m_oListView.setAdapter(new ListAdapter(Alcohol.publicAlcList()));
    }

    public void onAddButtonClicked(View view)
    {
        Toast.makeText(getBaseContext(), "To Add Menu", Toast.LENGTH_LONG).show();
        // test case
        Intent intent = new Intent(this, AddEditActivity.class);
        startActivity(intent);
    }
}

