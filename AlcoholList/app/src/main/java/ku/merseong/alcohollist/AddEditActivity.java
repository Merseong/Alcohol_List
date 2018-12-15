package ku.merseong.alcohollist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

// 술일기를 새로 추가하는 화면에 적용되는 클래스
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

        // 술의 종류에 대해 기록할때 쓰인다.
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

    // 뒤로가기 버튼
    public void onBackButtonClicked(View v)
    {

        MainActivity.imm.hideSoftInputFromWindow(alcName.getWindowToken(), 0);
        finish();
    }

    // 저장 버튼을 눌렀을때
    public void onSaveButtonClicked(View view)
    {
        String name = alcName.getText().toString();
        String food = alcFood.getText().toString();
        int date = 0;
        String comment = alcComment.getText().toString();

        if (name.length() == 0 || food.length() == 0 || comment.length() == 0)
        {
            Toast.makeText(this, "빈 일기는 저장되지 않습니다.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        try {
            date += alcDate.getText().toString().length() == 6? Integer.parseInt(alcDate.getText().toString()) + 1000000 : 1000000;
            new Alcohol(date, name, category, food, comment);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "문제가 발생했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            finish();
        }
        Toast.makeText(this, "정상적으로 추가되었습니다.", Toast.LENGTH_SHORT).show();

        MainActivity.m_oListView.setAdapter(new ListAdapter(Alcohol.publicAlcList()));

        Alcohol.Save();

        MainActivity.imm.hideSoftInputFromWindow(alcName.getWindowToken(), 0);
        finish();
    }

    public void onEditButtonClicked(View view) {
    }
}
