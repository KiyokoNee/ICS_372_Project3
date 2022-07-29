package edu.metrostate.sheltertracker;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AddAnimalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);

        ListView lv = findViewById(R.id.shelter_list);

        lv.setAdapter(new ShelterAdapter(this,
                (List<Shelter>) ((ShelterTrackerApplication)getApplication()).getShelterList().getShelters()));

    }

    public void onRadioButtonClicked(View view) {
    }
}
