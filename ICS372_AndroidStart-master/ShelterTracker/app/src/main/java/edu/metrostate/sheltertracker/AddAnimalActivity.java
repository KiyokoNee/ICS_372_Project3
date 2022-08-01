package edu.metrostate.sheltertracker;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AddAnimalActivity extends AppCompatActivity {
    private RadioButton radioLB = null;
    private RadioButton radioKG = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);

        radioLB = findViewById(R.id.radio_lb);
        radioKG = findViewById(R.id.radio_kg);

    }

    public void onRadioButtonClicked(View view) {
        if(view == radioLB){

        }else if(view == radioKG){

        }
    }

    public void onSubmitClicked(View view){

    }

    public void fillSpinner(){
        Spinner spinner = (Spinner)findViewById(R.id.type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.animal_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);
    }
}
