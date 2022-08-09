package edu.metrostate.sheltertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ReceivingActivity extends AppCompatActivity {

    /**
     * Method that once button is clicked, will display a list of shelters.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_list);

        ListView lv = findViewById(R.id.shelter_list);
        List<Shelter> shelters = (((ShelterTrackerApplication)getApplication()).getShelterList().getShelters());

        lv.setAdapter(new ShelterAdapter(this,shelters));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Method that once a shelter is selected, it will toggle the receiving status to false/true.
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(shelters.get(position).isReceiving()){
                    shelters.get(position).setReceiving(false);
                } else{
                    shelters.get(position).setReceiving(true);
                }

                ((ShelterTrackerApplication)getApplication()).saveFile(null);
                showDialog(view);
            }
        });
    }

    /**
     * Method that show the dialog of when receiving status has been toggled.
     * @param view
     */
    public void showDialog(View view) {

        Dialog dialog = new AlertDialog.Builder(this).setTitle("Note:").setCancelable(false)
                .setMessage("Receiving has been switched!")
                .setPositiveButton( "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).create();

        dialog.show();
    }
}
