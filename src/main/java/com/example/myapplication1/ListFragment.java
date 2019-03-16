package com.example.myapplication1;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListFragment extends Fragment
{
    final String LOG_TAG = "myLogs";

    TextView used_nms;
    String text;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if(savedInstanceState!=null)
        {
            text = savedInstanceState.getString("myText");
        }
    }

    @Override
    public void onSaveInstanceState(final Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("myText", used_nms.getText().toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.used_list, container, false);

        used_nms = (TextView) v.findViewById(R.id.used_l_t);
        used_nms.setText(text);
        used_nms.setMovementMethod(new ScrollingMovementMethod());

        return v;
    }

    public void setNums(List<Integer> listnums)
    {
        Collections.sort(listnums);
        text =  "Used numbers\n";
        text += "------------\n";
        for(int k = 0; k < listnums.size(); k++)
        {
            text += String.valueOf(listnums.get(k));
            text += "\n";
        }
    }
}
