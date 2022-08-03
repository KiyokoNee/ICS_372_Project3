package edu.metrostate.sheltertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
//        Intent intent = new Intent(this, ImportJSONActivity.class);
//        startActivity(intent);
        String jsonFile = ((ShelterTrackerApplication)getApplication()).getExternalFilesDir(null).getAbsolutePath() + "/JSONInput.json";
        ParseUtilities.addIncomingJSON(jsonFile, ((ShelterTrackerApplication)getApplication()).getShelterList());

        // Alert once import JSON button is clicked, doesn't mean it is successful. More work to come!
        Dialog dialog = new AlertDialog.Builder(this).setTitle("My alert").setCancelable(false)
                .setMessage("Import of JSON file success! ")
                .setPositiveButton( "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).create();

        dialog.show();
    }

    public void importXML(View view){
//        Intent intent = new Intent(this, ImportXMLActivity.class);
//        startActivity(intent);
        String xmlFile = ((ShelterTrackerApplication)getApplication()).getExternalFilesDir(null).getAbsolutePath() + "/XMLInput.xml";
        ParseUtilities.parseIncomingXML(xmlFile, ((ShelterTrackerApplication)getApplication()).getShelterList());

        // Alert once import XML button is clicked, doesn't mean it is successful. More work to come!
        Dialog dialog = new AlertDialog.Builder(this).setTitle("My alert").setCancelable(false)
                .setMessage("Import of XML file success!")
                .setPositiveButton( "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).create();

        dialog.show();
    }

    public void exportShelterList(View view){
        Intent intent = new Intent(this, ExportActivity.class);
        startActivity(intent);
    }

    public void showDialog(View view) {

        Dialog dialog = new AlertDialog.Builder(this).setTitle("My alert").setCancelable(false)
                .setMessage("This is an alert that is shown when the button is pressed")
                .setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        }).create();

        dialog.show();
    }
    public void exit(View view){
        finish();
    }
}