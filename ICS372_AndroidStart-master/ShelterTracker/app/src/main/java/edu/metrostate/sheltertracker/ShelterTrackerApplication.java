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
        String localFileLocation = getExternalFilesDir(null).getAbsolutePath()+File.separator+"seedFile.json";
        shelterList.addHashMap(ParseUtilities.loadJSON(localFileLocation));
        writeFile();
    }

    public ShelterList getShelterList() {
        return shelterList;
    }

    public void writeFile() {
        // this will put files in the /sdcard/Android/data/edu.metrostate.sheltertracker/files directory
        File externalDir = getExternalFilesDir(null);
        File outputFile = new File(externalDir, "seedFile.json");
        String localFileLocation = getExternalFilesDir(null).getAbsolutePath()+File.separator+"seedFile.json";
        File file = new File(localFileLocation);
        if(!file.exists()) {
            try {
                Files.createFile(outputFile.toPath());
                Files.write(outputFile.toPath(), "My data".getBytes());
            } catch (IOException ex) {
                Log.e("FileCreation", "Error creating file", ex);
            }
    }

    }
}
