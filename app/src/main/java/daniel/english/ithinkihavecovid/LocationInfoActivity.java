package daniel.english.ithinkihavecovid;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LocationInfoActivity extends AppCompatActivity {
    private TestingLocation location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);

        location = (TestingLocation) getIntent().getSerializableExtra("location");

        Log.e("made to info", String.valueOf(location));

        TextView title;
        title = (TextView) findViewById(R.id.title);
        title.setText(location.getTitle());
    }
}
