package edu.metrostate.sheltertracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ExportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.export_shelter);

//        ListView lv = findViewById(R.id.shelter_list);
//
//        lv.setAdapter(new ShelterAdapter(this,
//                (List<Shelter>) ((ShelterTrackerApplication)getApplication()).getShelterList().getShelters()));

    }
}
