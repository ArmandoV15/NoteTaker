package com.example.notetaker;

import android.content.Context;

import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;

public class MyNoteGridLayout extends GridLayout {
    public MyNoteGridLayout(final Context context) {
        super(context);

        //Setting Column Count to 2 columns.
        setColumnCount(2);

        //This is where I create my title EditText. This is where the user can enter
        //there desired title for their note
        final EditText editTitle = new EditText(context);
        editTitle.setId(R.id.editTitle);
        editTitle.setHint("Title");
        GridLayout.Spec rowSpec = GridLayout.spec(0, 1, 1/2);
        GridLayout.Spec colSpec = GridLayout.spec(0, 1, 20);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, colSpec);
        editTitle.setLayoutParams(layoutParams);
        addView(editTitle);

        //This is where I create the spinner which holds the note Types.
        //This is where the user can select what type of note they are writing.
        //The use can choose from "PERSONAL", "SCHOOL", "WORK", "OTHER".
        final Spinner typeSpinner = new Spinner(context);
        typeSpinner.setId(R.id.typeSpinner);
        String[] types = new String[4];
        types[0] = "PERSONAL";
        types[1] = "SCHOOL";
        types[2] = "WORK";
        types[3] = "OTHER";
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, types);
        typeSpinner.setAdapter(arrayAdapter);
        GridLayout.Spec rowSpec2 = GridLayout.spec(0, 1, 1/2);
        GridLayout.Spec colSpec2 = GridLayout.spec(1, 1, 1);
        GridLayout.LayoutParams layoutParams2 = new GridLayout.LayoutParams(rowSpec2, colSpec2);
        typeSpinner.setLayoutParams(layoutParams2);
        addView(typeSpinner);

        //This is where I create the content EditText. This is where the user enters the notes "content" or "body".
        //This is the biggest EditText and it covers the most room on the screen which was achieved with gravity and weights.
        final EditText content = new EditText(context);
        content.setId(R.id.editContent);
        content.setHint("Content");
        GridLayout.Spec rowSpec3 = GridLayout.spec(1, 1, 1);
        GridLayout.Spec colSpec3 = GridLayout.spec(0, 2, 1);
        GridLayout.LayoutParams layoutParams3 = new GridLayout.LayoutParams(rowSpec3, colSpec3);
        content.setLayoutParams(layoutParams3);
        content.setGravity(Gravity.TOP);
        addView(content);

        //This is where I created the "DONE" button used to get back to the main activity once teh user
        //is done using the second activity.
        Button doneButton = new Button(context);
        doneButton.setId(R.id.doneButton);
        doneButton.setText("DONE");
        GridLayout.Spec rowSpec4 = GridLayout.spec(2, 1, 1/2);
        GridLayout.Spec colSpec4 = GridLayout.spec(0, 2, 1);
        GridLayout.LayoutParams layoutParams4 = new GridLayout.LayoutParams(rowSpec4, colSpec4);
        doneButton.setLayoutParams(layoutParams4);
        addView(doneButton);


    }
}
