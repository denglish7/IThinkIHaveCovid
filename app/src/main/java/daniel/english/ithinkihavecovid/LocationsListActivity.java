package daniel.english.ithinkihavecovid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class LocationsListActivity extends AppCompatActivity {

    private ArrayList<TestingLocation> locations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_list);

        Intent intent = getIntent();
        locations = intent.getParcelableArrayListExtra("key");

        Log.d("heres locations", String.valueOf(locations));
        getList();


    }

    private void getList() {

        String[] titlesArray = new String[locations.size()];

        for (int i = 0; i < locations.size(); i++) {
            titlesArray[i] = locations.get(i).getTitle();
        }

        Log.d("titles", String.valueOf(titlesArray));

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, titlesArray);

        ListView listView = (ListView) findViewById(R.id.locations_list);
        listView.setAdapter(adapter);
    }


}
