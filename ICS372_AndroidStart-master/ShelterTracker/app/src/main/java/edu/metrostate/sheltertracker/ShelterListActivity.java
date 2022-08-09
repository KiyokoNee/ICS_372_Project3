package edu.metrostate.sheltertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ShelterListActivity extends AppCompatActivity {
    /**
     * Method - once button is clicked show the list of shelters
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_list);

        ListView lv = findViewById(R.id.shelter_list);
        List<Shelter> shelters = (((ShelterTrackerApplication)getApplication()).getShelterList().getShelters());

        lv.setAdapter(new ShelterAdapter(this,shelters));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShelterListActivity.this, AnimalListActivity.class);
                intent.putExtra("shelter_id", shelters.get(position).getShelterID());
                startActivity(intent);
            }
        });
    }
}