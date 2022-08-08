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

public class ExportActivity extends AppCompatActivity {

    /**
     * Method will export shelter json data to a user defined file on device sdcard
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.export_shelter);

        /**
         * On submit, user defined file name is created and populated with data from applications
         * current shelter roster.
         */
        final Button button = (Button) findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mEdit = findViewById(R.id.fileNameInput);
                String file_name = mEdit.getText().toString();
                ((ShelterTrackerApplication) getApplication()).writeFile(file_name);
                String localFileLocation = getExternalFilesDir(null).getAbsolutePath() + File.separator + file_name;
                File file = new File(localFileLocation);
                if (!file.exists()) {
                    showDialogError(view);
                } else {
                    ((ShelterTrackerApplication) getApplication()).saveFile(file_name);
                    showDialogSuccess(view);
                    mEdit.getText().clear();
                }
            }
        });
    }

    public void showDialogError(View view) {
        Dialog dialog = new AlertDialog.Builder(this).setTitle("Error").setCancelable(false)
                .setMessage("File could not be created")
                .setPositiveButton( "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    public void showDialogSuccess(View view) {
        Dialog dialog = new AlertDialog.Builder(this).setTitle("Success!").setCancelable(false)
                .setMessage("File export complete")
                .setPositiveButton( "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }
}
