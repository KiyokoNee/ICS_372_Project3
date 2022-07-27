package edu.metrostate.sheltertracker;

import android.app.Application;
import android.util.Log;

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
        //TODO: call parser to decode JSON string to list
        //TODO: add elements to list


//        for(int i = 0; i < temp_list.size(); i++) {
//            shelterList.add(new Shelter(temp_list.get(i)));
//        }
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

    public String readFile() throws IOException {
        File path = getExternalFilesDir(null);
//        File file = new File(path);
        List <String> temp_list = Files.readAllLines(path.toPath());
        return String.join("", temp_list);

    }



}
