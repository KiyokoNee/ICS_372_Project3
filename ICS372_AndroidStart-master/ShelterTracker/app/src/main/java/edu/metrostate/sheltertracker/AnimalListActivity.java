package edu.metrostate.sheltertracker;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AnimalListActivity extends AppCompatActivity {
    /**
     * Method that once button is clicked show the list of animals
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_list);

        String id = getIntent().getStringExtra("shelter_id");
        TextView label = findViewById(R.id.title_text);
        Shelter selected = null;
        ListView lv = findViewById(R.id.shelter_list);

        /**
         * If there isn't an shelter selected, display all animals.
         * Else, display animals in a certain shelter
         */
        if(id == null){
            label.setText("All Animals");
            lv.setAdapter(new AnimalAdapter(this,
                    ((ShelterTrackerApplication)getApplication()).getShelterList().getAllAnimals()));
        } else{
            List<Shelter> shelters = (((ShelterTrackerApplication)getApplication()).getShelterList().getShelters());

            for (Shelter shelter:shelters) {
                if(shelter.getShelterID().equals(id)){
                    selected = shelter;
                    break;
                }
            }

            label.setText(selected.getShelterName());
            lv.setAdapter(new AnimalAdapter(this,((ShelterTrackerApplication)getApplication()).getShelterList().getShelter(id).getAnimalList()));
        }

    }
}
