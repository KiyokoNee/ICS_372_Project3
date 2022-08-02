package edu.metrostate.sheltertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Collection;
import java.util.List;

public class ShelterListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_list);

        ListView lv = findViewById(R.id.shelter_list);
        List<Shelter> shelters = (((ShelterTrackerApplication)getApplication()).getShelterList().getShelters());

        lv.setAdapter(new ShelterAdapter(this,shelters));
        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ShelterListActivity.this, AnimalListActivity.class);
                intent.putExtra("shelter_id", shelters.get(i).getShelterID());
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}