package edu.metrostate.sheltertracker;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ImportJSONActivity extends AppCompatActivity {
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.import_shelter);

//        ListView lv = findViewById(R.id.shelter_list);

//        lv.setAdapter(new ShelterAdapter(this,
//                (List<Shelter>) ((ShelterTrackerApplication)getApplication()).getShelterList().getShelters()));
//
    }
}
