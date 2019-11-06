package com.example.notetaker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final int LOGIN_REQUEST_CODE = 1;
    static final String TAG = "MainActivity";

    List<Note> myNotes = new ArrayList<>();
    ArrayAdapter<Note> arrayAdapter;


    /**
     This onActivityResult allows the program to get the results from my second activity when the user presses the "Done" button.
     It also differentiates between a new note and an edited note which prevents a new note showing up in the programs ListView if it was edited.
     Otherwise if a new note was created it will create a new note and add it to the ListView
     * @param requestCode Used to hold and check the requestCode and check it is equals LOGIN_REQUEST_CODE
     * @param resultCode code returned by the second activity to validate the results before sending results back
     * @param data our call to Intent when the program wants to get anything the second activity put into putExtra
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {

            String title = data.getStringExtra("title");
            String type = data.getStringExtra("type");
            String content = data.getStringExtra("content");
            int edited = data.getIntExtra("index", -1);
            Log.d(TAG, "" + edited);
            if (edited > -1) {
                myNotes.set(edited, new Note(title, type, content));
                arrayAdapter.notifyDataSetChanged();

            } else {
                ListView notesList = findViewById(R.id.listView);
                myNotes.add(new Note(title, type, content));
                arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myNotes);
                notesList.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
                Log.d(TAG, "add new");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting my activity layout to the GridLayout I made in a separate class(MyGridLayout).
        MyGridLayout myGridLayout = new MyGridLayout(this);
        setContentView(myGridLayout);

        /**
         This setOnClickListener is used when the user wants to create a new Note.
         It passes and empty title, type, and content to the second activity which represents the new note.
         There is also an index being passed which is used to distinguish this as a new note
         */
        Button button = findViewById(R.id.newNote);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("title", "");
                intent.putExtra("type", "");
                intent.putExtra("content", "");
                intent.putExtra("index", -1);
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
            }
        });

        /**
         This setOnItemClickListener is used to tell when a note in the LIstView is being clicked.
         When the note is clicked, its title, type, and contents are sent to the second activity and are able to be edited.
         The notes position is also sent over to distinguish it as a note that is being edited.
         */
        final ListView listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                Note n1 = myNotes.get(position);
                String noteTitle = n1.getTitle();
                String type = n1.getType();
                String content = n1.getContent();
                intent.putExtra("title", noteTitle);
                intent.putExtra("type", type);
                intent.putExtra("content", content);
                intent.putExtra("index", position);
                startActivityForResult(intent, LOGIN_REQUEST_CODE);

            }
        });

        /**
         This setOnItemLongClickListener is used to create and display an alertDialog box which asks the user if they are sure they want to delete their note.
         If "yes" is pressed then the note is removed from the ListView, if "no" is pressed then the alert closes and the program
         continues normally.
         */
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                final String selection = parent.getItemAtPosition(position).toString();

                AlertDialog.Builder alertBuilder =
                        new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setTitle("Delete a Note")
                        .setMessage("Are you sure you want to delete " + selection)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               myNotes.remove(position);
                               arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null);
                alertBuilder.show();
                return true;
            }
        });
    }

}
