package edu.metrostate.sheltertracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showShelterList(View view) {
        Intent intent = new Intent(this, ShelterListActivity.class);
        startActivity(intent);
    }

    public void showAllAnimals(View view){
        Intent intent = new Intent(this, AnimalListActivity.class);
        startActivity(intent);
    }

    public void addAnimal(View view){
        Intent intent = new Intent(this, AddAnimalActivity.class);
        startActivity(intent);
    }

    public void importJSON(View view){
        Intent intent = new Intent(this, ImportJSONActivity.class);
        startActivity(intent);
    }

    public void importXML(View view){
        Intent intent = new Intent(this, ImportXMLActivity.class);
        startActivity(intent);
    }

    public void exportShelterList(View view){
        Intent intent = new Intent(this, ExportActivity.class);
        startActivity(intent);
    }

    public void toggleReceiving(View view){
        Intent intent = new Intent(this, ReceivingActivity.class);
        startActivity(intent);
    }

    public void exit(View view){
        ((ShelterTrackerApplication)getApplication()).saveFile(null);
        finish();
    }
}