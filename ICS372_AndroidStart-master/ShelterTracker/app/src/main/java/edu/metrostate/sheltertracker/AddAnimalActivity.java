package edu.metrostate.sheltertracker;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;

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
}
