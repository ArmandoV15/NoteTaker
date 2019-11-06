package com.example.notetaker;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;


public class MyGridLayout extends GridLayout {
    public MyGridLayout(final Context context) {
        super(context);

        //Setting Column Count to 1 column
        setColumnCount(1);

        //This is where I create my button in the GirdLayout.
        //This button is used to get from the first activity to the Second
        Button myButton = new Button(context);
        myButton.setId(R.id.newNote);
        myButton.setText("ADD NEW NOTE");
        GridLayout.Spec rowSpec = GridLayout.spec(0, 1, 1/2);
        GridLayout.Spec colSpec = GridLayout.spec(0, 1, 1);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, colSpec);
        myButton.setLayoutParams(layoutParams);
        addView(myButton);

        //This is where I create the ListView in which the created notes go.
        ListView notesList = new ListView(context);
        notesList.setId(R.id.listView);
        GridLayout.Spec rowSpec2 = GridLayout.spec(1, 1, 1);
        GridLayout.Spec colSpec2 = GridLayout.spec(0, 1, 1);
        GridLayout.LayoutParams layoutParams2 = new GridLayout.LayoutParams(rowSpec2, colSpec2);
        layoutParams2.setGravity(Gravity.TOP);
        notesList.setLayoutParams(layoutParams2);
        addView(notesList);
    }
}
