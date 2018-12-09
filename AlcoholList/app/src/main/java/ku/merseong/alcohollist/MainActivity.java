package ku.merseong.alcohollist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity
{
    public static ListView m_oListView = null;

    public static File cont = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cont = getFilesDir();
        m_oListView = (ListView)findViewById(R.id.listview1);
        m_oListView.setAdapter(new ListAdapter(Alcohol.publicAlcList()));
    }

    public void onAddButtonClicked(View view)
    {
        Intent intent = new Intent(this, AddEditActivity.class);
        startActivity(intent);
    }

    public void Load()
    {
        final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Alc_List" + File.separator + "Alc.txt";

        try
        {
            InputStream is = new FileInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line="";

            while((line=reader.readLine())!=null)
            {
                Log.i("MyApp", line);
                new Alcohol(line);
            }

            reader.close();
            is.close();
            Toast.makeText(this, path + " loaded", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void onSearchButtonClicked(View view)
    {
        Load();
        m_oListView.setAdapter(new ListAdapter(Alcohol.publicAlcList()));
    }
}

