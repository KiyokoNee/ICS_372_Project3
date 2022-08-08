package edu.metrostate.sheltertracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class ImportJSONActivity extends AppCompatActivity {

    /**
     * Method takes user submitted XML file name, parses into hashmap of existing shelters.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.import_json);
        ShelterList shelterList = (((ShelterTrackerApplication)getApplication()).getShelterList());
        final Button button = (Button) findViewById(R.id.submit_button);

        /**
         * When submit button is clicked user submitted string for file location is passed to
         * ParseUtilities to be parsed into existing hashmap of shelters.
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mEdit = findViewById(R.id.fileNameInput);
                String file_name = mEdit.getText().toString();
                String localFileLocation = getExternalFilesDir(null).getAbsolutePath()+File.separator+file_name;
                File file = new File(localFileLocation);
                if(!file.exists()){
                    showDialogError(view);
                }else{
                shelterList.addHashMap(ParseUtilities.loadJSON(localFileLocation));
                ((ShelterTrackerApplication)getApplication()).saveFile(null);
                showDialogSuccess(view);
                mEdit.getText().clear();
                }
            }
        });
    }

    public void showDialogError(View view) {
        Dialog dialog = new AlertDialog.Builder(this).setTitle("Error").setCancelable(false)
                .setMessage("File does not exist")
                .setPositiveButton( "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    public void showDialogSuccess(View view) {
        Dialog dialog = new AlertDialog.Builder(this).setTitle("Success!").setCancelable(false)
                .setMessage("File import complete")
                .setPositiveButton( "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }
}
