package edu.metrostate.sheltertracker;

import android.app.Application;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ShelterTrackerApplication extends Application {

    private final ShelterList shelterList = new ShelterList();

    /**
     * When application is launched, attempt to load preexisting data from predefined file location into shelterList.
     * Call to writeFile
     */
    @Override
    public void onCreate() {
        super.onCreate();
        String localFileLocation = getExternalFilesDir(null).getAbsolutePath()+File.separator+"seedFile.json";
        File file = new File(localFileLocation);
        if(file.exists()) {
            shelterList.addHashMap(ParseUtilities.loadJSON(localFileLocation));
        } else {
            writeFile(null);
        }
    }

    public ShelterList getShelterList() {
        return shelterList;
    }

    /**
     * Method will write a new file to device sdcard. Method used for both initial storage of
     * shelter data and for user defined export location.
     * @param submittedFileName - user defined file name for export, else null will direct to
     *                            default seedFile.json file location
     */
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

    /**
     * Method for saving json data to defined file location. Used for both default storage and
     * user defined export file.
     * @param file - user defined file name for export, else null will direct to
     *               default seedFile.json file location
     */
    public void saveFile(String file) {
        String path = getExternalFilesDir(null).getAbsolutePath() + File.separator;
        String fileName = "seedFile.json";
        if(file != null){
            fileName = file;
        }
        FileUtilities.writeJSON(shelterList, path + fileName);
    }
}
