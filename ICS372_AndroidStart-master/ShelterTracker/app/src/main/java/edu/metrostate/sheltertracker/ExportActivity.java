package edu.metrostate.sheltertracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class ExportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.export_shelter);

        final Button button = (Button) findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mEdit = findViewById(R.id.fileNameInput);
                String file_name = mEdit.getText().toString();
                    ((ShelterTrackerApplication)getApplication()).writeFile(file_name);
                    ((ShelterTrackerApplication)getApplication()).saveFile(file_name);

                    mEdit.getText().clear();
                }
        });
    }
}
