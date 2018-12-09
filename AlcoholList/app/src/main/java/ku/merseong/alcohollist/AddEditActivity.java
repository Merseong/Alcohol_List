package ku.merseong.alcohollist;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

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
        /*
        String name = alcName.getText().toString();
        String food = alcFood.getText().toString();
        int date = Integer.parseInt(alcDate.getText().toString()) + 1000000;
        String comment = alcComment.getText().toString();

        new Alcohol(date, name, category, food, comment);

        MainActivity.m_oListView.setAdapter(new ListAdapter(Alcohol.publicAlcList()));*/
        new Alcohol(1234567, "히로유키", Enums.AlcCategory.CHEONGJU, "밥 이랑", "dhk! 엊아ㅓㅣㅏ넝ㄹ");
        new Alcohol(1234567, "히로유키", Enums.AlcCategory.CHEONGJU, "밥 이랑", "dhk! 엊아ㅓㅣㅏ넝ㄹ");
        new Alcohol(1234567, "히로유키", Enums.AlcCategory.CHEONGJU, "밥 이랑", "dhk! 엊아ㅓㅣㅏ넝ㄹ");

        Save();

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
                buw.write(alc.ToString());

            buw.close();
            fos.close();
            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
        }
    }
}
