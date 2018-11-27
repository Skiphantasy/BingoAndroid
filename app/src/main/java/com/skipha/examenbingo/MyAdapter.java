/**
 * Author: Skipha
 * Date: 27 Nov 2018
 */


package com.skipha.examenbingo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class MyAdapter extends ArrayAdapter {

    private Activity context;
    private String[] data;
    private TextView textNumber, textRights;
    private int[] right;
    private String line;
    private String bingo;
    private String rightNumbers;
    private String total;
    private Button button;
    private Spinner spinner;


    public MyAdapter(Activity context,  String[] data, int[] right, Button button, Spinner spinner) {
        super(context, R.layout.my_list, data);
        this.right = right;
        this.context = context;
        this.data = data;
        this.button = button;
        this.spinner = spinner;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.my_list, null);
        line = item.getResources().getString(R.string.line);
        bingo = item.getResources().getString(R.string.bingo);
        total = item.getResources().getString(R.string.total);
        rightNumbers = item.getResources().getString(R.string.right);
        textNumber = (TextView)item.findViewById(R.id.adapternumbers);
        textRights = (TextView)item.findViewById(R.id.adapterrights);
        textNumber.setText(data[position]);

        if(right[position] >= 5 && right[position] < 10) {
            textRights.setText(line + " " + rightNumbers + " " + right[position]);
        } else if (right[position] == 10 ) {
            textRights.setText(bingo + " " + rightNumbers + " " + right[position] + " " + total + " " + spinner.getCount());
            button.setEnabled(false);
        }
        else {
            textRights.setText(rightNumbers + " " + right[position]);
        }

        this.notifyDataSetChanged();
        return(item);
    }
}
