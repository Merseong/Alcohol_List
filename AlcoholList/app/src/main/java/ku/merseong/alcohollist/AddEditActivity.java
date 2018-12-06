package ku.merseong.alcohollist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddEditActivity extends Activity
{
    EditText alcName;
    EditText alcFood;
    Spinner alcCategory;
    EditText alcDate;
    EditText alcComment;

    String str_category = "기타";
    Enums.AlcCategory category = Enums.AlcCategory.OTHERS;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_page);

        alcName = (EditText)findViewById(R.id.AlcName);
        alcFood = (EditText)findViewById(R.id.AlcFood);
        alcCategory = (Spinner)findViewById(R.id.AlcCategory);
        alcDate = (EditText)findViewById(R.id.AlcDate);
        alcComment = (EditText)findViewById(R.id.AlcComment);

        alcCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_category = parent.getItemAtPosition(position).toString();
                for (Enums.AlcCategory alc : Enums.AlcCategory.values())
                    if (alc.getName().equals(str_category)) { category = alc; break;
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onBackButtonClicked(View v)
    {
        Toast.makeText(this, "onBackButtonClicked", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onSaveButtonClicked(View view)
    {
        Toast.makeText(this, "onSaveButtonClicked", Toast.LENGTH_SHORT).show();
        String name = alcName.getText().toString();
        String food = alcFood.getText().toString();
        int date = Integer.parseInt(alcDate.getText().toString()) + 1000000;
        String comment = alcComment.getText().toString();

        new Alcohol(date, name, category, food, comment);

        MainActivity.m_oListView.setAdapter(new ListAdapter(Alcohol.publicAlcList()));
        finish();
    }
}
