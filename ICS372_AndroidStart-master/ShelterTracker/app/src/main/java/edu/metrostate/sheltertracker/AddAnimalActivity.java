package edu.metrostate.sheltertracker;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;


public class AddAnimalActivity extends AppCompatActivity {
    private RadioButton radioLB = null;
    private RadioButton radioKG = null;
    private String animal_Type;
    private String weight_unit;
    private Shelter shelter;
    private final ArrayList<Shelter> shelterArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);

        List<Shelter> shelters = (((ShelterTrackerApplication)getApplication()).getShelterList().getShelters());
        shelterArrayList.addAll(shelters);
        radioLB = findViewById(R.id.radio_lb);
        radioKG = findViewById(R.id.radio_kg);
        fillTypeSpinner();
        fillShelterSpinner();

    }

    public void onRadioButtonClicked(View view) {
        if(view == radioLB){
            weight_unit = radioLB.getText().toString();
        }else if(view == radioKG){
            weight_unit = radioKG.getText().toString();
        }
    }

    public void onSubmitClicked(View view){

        //ShelterList shelterList = (((ShelterTrackerApplication)getApplication()).getShelterList());

        EditText mEdit = findViewById(R.id.nameInput);
        String animal_Name = mEdit.getText().toString();
        mEdit = findViewById(R.id.idInput);
        String animal_ID = mEdit.getText().toString();
        mEdit = findViewById(R.id.animalWeightInput);
        double animal_weight = Double.valueOf(mEdit.getText().toString());
        mEdit = findViewById(R.id.receipt_date);
        long receipt_date = Long.valueOf(mEdit.getText().toString());

        Animal newAnimal = new Animal(animal_Type, animal_Name, animal_ID, animal_weight, weight_unit, receipt_date);
        Shelter.addUserCreatedAnimal(newAnimal, shelter.getShelterID(), (((ShelterTrackerApplication)getApplication()).getShelterList()));

    }

    public void fillTypeSpinner(){
        Spinner spinner = findViewById(R.id.type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.animal_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                animal_Type = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(AddAnimalActivity.this,"Please select an animal type", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fillShelterSpinner(){
        Spinner spinner2 = findViewById(R.id.shelter_spinner);
        spinner2.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, shelterArrayList));
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                shelter = (Shelter) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(AddAnimalActivity.this, "Please select a shelter", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
