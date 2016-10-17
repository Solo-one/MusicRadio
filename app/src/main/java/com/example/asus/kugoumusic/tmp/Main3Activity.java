package com.example.asus.kugoumusic.tmp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ext.SatelliteMenu;
import android.view.ext.SatelliteMenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.asus.kugoumusic.R;
import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main3Activity extends AppCompatActivity  {

    @BindView(R.id.menu)
    SatelliteMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);

        List<SatelliteMenuItem> items = new ArrayList<SatelliteMenuItem>();
        items.add(new SatelliteMenuItem(6, R.drawable.ic_launcher));
        items.add(new SatelliteMenuItem(5, R.drawable.ic_launcher));
        items.add(new SatelliteMenuItem(4, R.drawable.ic_launcher));
        items.add(new SatelliteMenuItem(3, R.drawable.ic_launcher));
        items.add(new SatelliteMenuItem(2, R.drawable.ic_launcher));
        items.add(new SatelliteMenuItem(1, R.drawable.ic_launcher));
        menu.addItems(items);
        menu.setOnItemClickedListener(new SatelliteMenu.SateliteClickedListener() {
            public void eventOccured(int id) {
                Log.i("sat", "Clicked on " + id);
                Toast.makeText(Main3Activity.this, id + " ", Toast.LENGTH_SHORT).show();
            }
        });



    }

}
