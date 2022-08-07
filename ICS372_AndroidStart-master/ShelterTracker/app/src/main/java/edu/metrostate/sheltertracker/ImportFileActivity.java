package edu.metrostate.sheltertracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ImportFileActivity extends AppCompatActivity {
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.import_shelter);
            ShelterList shelterList = (((ShelterTrackerApplication)getApplication()).getShelterList());


        final Button button = (Button) findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mEdit = findViewById(R.id.fileNameInput);
                String file_name = mEdit.getText().toString();
                String localFileLocation = getExternalFilesDir(null).getAbsolutePath()+file_name;
                shelterList.addHashMap(ParseUtilities.loadJSON(localFileLocation));
                ((ShelterTrackerApplication)getApplication()).writeFile();
            }
        });

//        ListView lv = findViewById(R.id.shelter_list);

//        lv.setAdapter(new ShelterAdapter(this,
//                (List<Shelter>) ((ShelterTrackerApplication)getApplication()).getShelterList().getShelters()));
//
    }


}
