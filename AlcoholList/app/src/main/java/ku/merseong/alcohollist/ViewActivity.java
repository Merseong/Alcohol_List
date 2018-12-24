package ku.merseong.alcohollist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewActivity extends Activity
{
    Alcohol itemAlcohol;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_page);

        int index = getIntent().getIntExtra("index", -1);
        itemAlcohol = Alcohol.AlcList.get(Alcohol.AlcList.size() - index - 1);

        TextView alcName = (TextView)findViewById(R.id.AlcName);
        TextView alcFood = (TextView)findViewById(R.id.AlcFood);
        TextView alcCategory = (TextView)findViewById(R.id.AlcCategory);
        TextView alcComment = (TextView)findViewById(R.id.AlcComment);
        TextView alcDate = (TextView)findViewById(R.id.AlcDate);

        alcName.setText(itemAlcohol.name);
        alcFood.setText(itemAlcohol.food);
        alcCategory.setText(itemAlcohol.category.getName());
        alcComment.setText(itemAlcohol.comment);
        alcDate.setText(itemAlcohol.GetDate());
    }

    public void onEditButtonClicked(View view) {
    }

    public void onBackButtonClicked(View view) {
        finish();
    }
}
