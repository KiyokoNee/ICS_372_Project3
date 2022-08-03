package edu.metrostate.sheltertracker;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AnimalListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_list);

        String id = getIntent().getStringExtra("shelter_id");
        TextView label = findViewById(R.id.title_text);
        Shelter selected = null;
        if(id == null){
            label.setText("All Animals");
        } else{
            List<Shelter> shelters = (List<Shelter>) (((ShelterTrackerApplication)getApplication()).getShelterList().getShelters());
            for (Shelter shelter:shelters) {
                if(shelter.getShelterID().equals(id)){
                    selected = shelter;
                    break;
                }
            };
            if(selected != null){
                label.setText(selected.getShelterName());
            }
        }
        ListView lv = findViewById(R.id.shelter_list);

        lv.setAdapter(new AnimalAdapter(this,
                 ((ShelterTrackerApplication)getApplication()).getShelterList().getAllAnimals()));

    }
}
