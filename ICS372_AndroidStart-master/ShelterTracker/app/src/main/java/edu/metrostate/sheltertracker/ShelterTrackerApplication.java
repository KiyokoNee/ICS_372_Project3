package edu.metrostate.sheltertracker;

import android.app.Application;
import android.util.Log;

import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ShelterTrackerApplication extends Application {

    private final ShelterList shelterList = new ShelterList();


    @Override
    public void onCreate() {
        super.onCreate();
        String localFileLocation = getExternalFilesDir(null).getAbsolutePath()+"/myfile.txt";
        shelterList.addHashMap(ParseUtilities.loadJSON(localFileLocation));

        writeFile();
    }

    public ShelterList getShelterList() {
        return shelterList;
    }



    public void writeFile() {

        // this will put files in the /sdcard/Android/data/edu.metrostate.sheltertracker/files directory
        File externalDir = getExternalFilesDir(null);

        File internalDir = getFilesDir();

        File outputFile = new File(externalDir, "myfile.txt");

        try {
            Files.createFile(outputFile.toPath());
            Files.write(outputFile.toPath(), "My data".getBytes());

        } catch (IOException ex) {
            Log.e("FileCreation", "Error creating file", ex);
        }

    }

    public String readExternalFile(String fileName) throws IOException {
        File path = getExternalFilesDir(null);
        File file = new File(path, fileName);
        List <String> temp_list = Files.readAllLines(file.toPath());
        return String.join("", temp_list);



//        File path = getExternalFilesDir(null);
//        List <String> temp_list = Files.readAllLines(path.toPath());
//        String joined_string = String.join("", temp_list);
//        ParseUtilities.addIncomingJSON(joined_string, shelterList);

    }
}
