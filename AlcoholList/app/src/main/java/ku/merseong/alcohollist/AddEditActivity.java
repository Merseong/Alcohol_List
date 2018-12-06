package ku.merseong.alcohollist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AddEditActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_page);
    }

    public void onBackButtonClicked(View v)
    {
        Toast.makeText(this, "onBackButtonClicked", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onSaveButtonClicked(View view)
    {
        Toast.makeText(this, "onSaveButtonClicked", Toast.LENGTH_SHORT).show();
        new Alcohol(1181225, "민들레대포", Enums.AlcCategory.CHEONGJU, "파전", "민들레캐논 마싯서");

        MainActivity.m_oListView.setAdapter(new ListAdapter(Alcohol.publicAlcList()));
    }
}
