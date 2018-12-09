package ku.merseong.alcohollist;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.*;

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

        MainActivity.imm.hideSoftInputFromWindow(alcName.getWindowToken(), 0);
        finish();
    }

    public void onSaveButtonClicked(View view)
    {
        String name = alcName.getText().toString();
        String food = alcFood.getText().toString();
        int date = Integer.parseInt(alcDate.getText().toString()) + 1000000;
        String comment = alcComment.getText().toString();

        try {
            new Alcohol(date, name, category, food, comment);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "문제가 발생했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            finish();
        }
        Toast.makeText(this, "정상적으로 추가되었습니다.", Toast.LENGTH_SHORT).show();

        MainActivity.m_oListView.setAdapter(new ListAdapter(Alcohol.publicAlcList()));
        Save();

        MainActivity.imm.hideSoftInputFromWindow(alcName.getWindowToken(), 0);
        finish();
    }

    private void Save()
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
            Toast.makeText(this, "문제가 발생했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
