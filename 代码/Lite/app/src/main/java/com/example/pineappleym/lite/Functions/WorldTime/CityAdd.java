package com.example.pineappleym.lite.Functions.WorldTime;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pineappleym.lite.R;

import java.util.ArrayList;

public class CityAdd extends AppCompatActivity {
    public Citybase mydb;
    public ListView all_city;
    public ArrayList<City> mlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_city);
        mydb = new Citybase(CityAdd.this);
        if(mydb.getAllCitys()!=null){
            mlist = mydb.getAllCitys();
        }
        final TimeAdapter adapter = new TimeAdapter(CityAdd.this, mlist);
        all_city = (ListView)findViewById(R.id.citys);
        all_city.setAdapter(adapter);
        all_city.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(CityAdd.this);
                dialog.setTitle("info");
                dialog.setMessage("是否添加该城市?");
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            City city = ((City) adapter.getItem(position));
                            if (mydb.query(city) == false) {
                                Toast.makeText(CityAdd.this, "你已经添加过该城市了.", Toast.LENGTH_SHORT).show();
                            } else {
                                mydb.insertCity(city);
                            }
                        } catch (Exception e) {
                            Toast.makeText(CityAdd.this, "添加失败.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });

    }

}
