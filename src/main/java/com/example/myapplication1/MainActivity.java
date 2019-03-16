package com.example.myapplication1;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    int state;
    Fragment.SavedState state1;
    Fragment.SavedState state2;

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        state = 1;

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);

        ActRandFragment ActFrag = new ActRandFragment();
        FragmentTransaction ft0 = getFragmentManager().beginTransaction();
        ft0.add(R.id.lay_for_frags, ActFrag);
        ft0.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        int i;
        switch(item.getItemId()){
            case  android.R.id.home:
                ActivateFragment(1);
                return true;
            case  R.id.main_lay:
                ActivateFragment(1);
                return true;
            case  R.id.used:
                ActivateFragment(2);
                return true;
            case  R.id.reset:
                if(state == 1)
                {
                    ActRandFragment frag1 = (ActRandFragment) getFragmentManager().findFragmentById(R.id.lay_for_frags);
                    frag1.ResetUsedList();
                }
                return true;
            case  R.id.exit:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void ActivateFragment(int st)
    {
        switch(st)
        {
            case 1:
                if(state != 1)
                {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ActRandFragment ActFrag = new ActRandFragment();
                    ActFrag.setInitialSavedState(state1);
                    ft.replace(R.id.lay_for_frags, ActFrag).addToBackStack(null).commit();
                    state = 1;
                }
                break;
            case 2:
                if(state != 2)
                {
                    List<Integer> list1 = new ArrayList<>();
                    ActRandFragment frag1 = (ActRandFragment) getFragmentManager().findFragmentById(R.id.lay_for_frags);
                    list1 = frag1.GetUsedList();

                    FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                    state1 = getFragmentManager().saveFragmentInstanceState(getFragmentManager().findFragmentById(R.id.lay_for_frags));
                    ListFragment ListFrag = new ListFragment();
                    ListFrag.setNums(list1);
                    ft1.replace(R.id.lay_for_frags, ListFrag).addToBackStack(null).commit();
                    state = 2;
                }
                break;
            default:
                break;
        }
    }

//    @Override
//    public void onBackPressed() {
//
//    }


}
