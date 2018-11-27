/**
 * Author: Skipha
 * Date: 27 Nov 2018
 */


package com.skipha.examenbingo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity {

    private Button button;
    private MyAdapter adapter;
    private ArrayList<Integer> generatedNumbers;
    private ArrayList<String> generatedStrings;
    private ArrayList<TextView> textViews;
    private RelativeLayout rLayout;
    private ListView listView;
    private Spinner spinner;
    private ArrayAdapter<String> dataAdapter;
    private int currentNumber;
    private ArrayList<Integer> auxiliarArray;
    private int numbersCount;
    private int players;
    private String[] names;
    private int[][] papers;
    private int[] rights;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        button = findViewById(R.id.button2);
        numbersCount = Integer.parseInt(getResources().getString(R.string.numberscount));
        players = Integer.parseInt(getResources().getString(R.string.players));
        papers = new int [players][];
        rights = new int[players];
        names = new String[players];
        generatedNumbers = new ArrayList<>();
        generatedStrings = new ArrayList<>();
        auxiliarArray = new ArrayList<>();
        spinner = findViewById(R.id.spinner);
        rLayout = findViewById(R.id.rLayout);
        textViews = new ArrayList<>();

        for (int i = 0; i < rLayout.getChildCount(); i++) {
            if(rLayout.getChildAt(i) instanceof TextView) {
                textViews.add((TextView)rLayout.getChildAt(i));
            }
        }

        for (int i = 0; i < players; i++) {
            papers[i] = fillArray();
            rights[i] = 0;
            names[i] = "";
            for (int j = 0; j < papers[i].length; j++) {
                names[i]+= papers[i][j] + " ";
            }
        }
        listView = findViewById(R.id.listview);
        adapter = new MyAdapter(this ,names, rights, button, spinner);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentNumber = generateRandom(generatedNumbers);
                generatedNumbers.add(currentNumber);
                textViews.get(textViews.size()-1).setText("" + currentNumber);
                generatedStrings.add("" + currentNumber);
                dataAdapter = new ArrayAdapter<>(v.getContext(),
                        android.R.layout.simple_spinner_item, generatedStrings);
                spinner.setAdapter(dataAdapter);
                dataAdapter.notifyDataSetChanged();

                for (int i = 0; i < players; i++) {
                    for (int j = 0; j < numbersCount; j++) {
                        if(currentNumber == papers[i][j])
                        rights[i]++;
                        adapter.notifyDataSetChanged();
                    }
                }
             adapter.notifyDataSetChanged();
            }
        });
    }

    protected int[] fillArray() {
        int[] numbers = new int[numbersCount];

        for (int i = 0; i < numbersCount; i++) {
            auxiliarArray.add(generateRandom((auxiliarArray)));
            numbers[i] = auxiliarArray.get(i);
        }

        auxiliarArray.clear();
        return numbers;
    }
    protected int generateRandom(ArrayList<Integer> numbers) {
        boolean isRepeated;
        int n;

        do {
            isRepeated = false;
            Random rand = new Random();
            n = rand.nextInt(90) + 1;
            if (numbers.size() != 0) {
                for (int i = 0; i < numbers.size(); i++) {
                    if (n == numbers.get(i)) {
                        isRepeated = true;
                    }
                }
            }
       } while (isRepeated == true);

        return n;
    }
}
