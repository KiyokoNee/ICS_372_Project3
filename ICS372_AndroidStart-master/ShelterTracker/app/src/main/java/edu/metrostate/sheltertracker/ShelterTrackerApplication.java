package edu.metrostate.sheltertracker;

import android.app.Application;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ShelterTrackerApplication extends Application {

    private final ShelterList shelterList = new ShelterList();

    @Override
    public void onCreate() {
        super.onCreate();
        String localFileLocation = getExternalFilesDir(null).getAbsolutePath()+File.separator+"seedFile.json";
        shelterList.addHashMap(ParseUtilities.loadJSON(localFileLocation));
        writeFile(null);
    }

    public ShelterList getShelterList() {
        return shelterList;
    }

    public void writeFile(String submittedFileName) {
        // this will put files in the /sdcard/Android/data/edu.metrostate.sheltertracker/files directory
        String fileName = "seedFile.json";
        File externalDir = getExternalFilesDir(null);
        if(submittedFileName != null){
            fileName = submittedFileName;
        }
        File outputFile = new File(externalDir, fileName);
        String localFileLocation = getExternalFilesDir(null).getAbsolutePath()+File.separator+fileName;
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

    public void saveFile(String file) {
        String path = getExternalFilesDir(null).getAbsolutePath() + File.separator;
        String fileName = "seedFile.json";
        if(file != null){
            fileName = file;
        }
        FileUtilities.writeJSON(shelterList, path + fileName);
    }

}
