package daniel.english.ithinkihavecovid;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class SymptomsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);
        getList();
    }

    private void getList() {
        String[] array = getResources().getStringArray(R.array.symptoms_array);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, array);

        ListView listView = (ListView) findViewById(R.id.symptoms_list);
        listView.setAdapter(adapter);
    }

}
