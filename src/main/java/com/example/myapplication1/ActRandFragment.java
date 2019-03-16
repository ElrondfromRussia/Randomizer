package com.example.myapplication1;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActRandFragment extends Fragment
{
    final String LOG_TAG = "myLogs";

    TextView tvOut1;
    Button btnGo1;
    EditText tmax1;
    int max;
    int max_buf;
    int result;
    String color;
    int size;

    List<Integer> used_nums = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if(savedInstanceState!=null)
        {
            max = savedInstanceState.getInt("myMax");
            result = savedInstanceState.getInt("myRes");
            color = savedInstanceState.getString("myColor");
            used_nums = (List<Integer>) savedInstanceState.getSerializable("list");
            size = savedInstanceState.getInt("mySize");
            max_buf = savedInstanceState.getInt("myBuf");
        }
    }

    @Override
    public void onSaveInstanceState(final Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("myColor", color);
        outState.putInt("myMax", max);
        outState.putInt("myRes", result);
        outState.putInt("mySize", size);
        outState.putInt("myBuf", max_buf);
        outState.putSerializable("list", (Serializable) used_nums);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.activepage, container, false);

        tvOut1 = (TextView) v.findViewById(R.id.tex_res1);
        btnGo1 = (Button) v.findViewById(R.id.bt_go1);
        tmax1 = (EditText) v.findViewById(R.id.et_max1);

        if(used_nums.size() == 0)
            color = "#669900";
        tvOut1.setTextColor(Color.parseColor(color));

        tvOut1.setTextSize(size);

        tvOut1.setText(String.valueOf(result));

        btnGo1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(tmax1.getText().length()>0)
                    max = (int)Double.parseDouble(tmax1.getText().toString());
                else
                    max = 0;
                if(max != max_buf)
                    ResetUsedList();
                max_buf = max;
                result = getRandom(max);
                if(used_nums.contains(result))
                {
                    color = "#ff0000";
                    tvOut1.setTextColor(Color.parseColor(color));
                }
                else
                {
                    used_nums.add(result);
                    color = "#669900";
                    tvOut1.setTextColor(Color.parseColor(color));
                }

                if(result > 99)
                    size = (int) (250 / 2);
                else
                    size = 250;
                tvOut1.setTextSize(size);
                tvOut1.setText(String.valueOf(result));

            }
        });
        return v;
    }

    public void ResetUsedList()
    {
        color = "#669900";
        if(tvOut1 != null)
            tvOut1.setTextColor(Color.parseColor("#669900"));
        if(used_nums != null)
            used_nums.clear();
    }

    public List<Integer> GetUsedList()
    {
        return used_nums;
    }

    public int getRandom(int max)
    {
        if(max != 0)
            return 1 + (int) (Math.random() * max);
        else
            return 0;
    }
}
