package com.example.pineappleym.lite.Functions.WorldTime;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pineappleym.lite.R;

import java.util.ArrayList;

public class WorldTime extends AppCompatActivity {
    public Citybase mydb;
    public TextView local_time,local_city;
    public ListView list_time;
    public FloatingActionButton fab;
    public City lcity;
    public ArrayList<City> mlist = new ArrayList<>();
    final TimeAdapter adapter = new TimeAdapter(WorldTime.this,mlist);
    private SharedPreferences sp;
    boolean isPause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timechange);
        sp = getSharedPreferences ( "info", MODE_PRIVATE );
        mydb = new Citybase(WorldTime.this);
        local_time=(TextView) findViewById(R.id.local_time);
        local_city=(TextView) findViewById(R.id.local_city);
        list_time=(ListView)findViewById(R.id.list_time);
        fab = (FloatingActionButton)findViewById(R.id.fab);

        String is_new = sp.getString("is_new","defValue");
        if(is_new.equals("defValue")){
            init();
        }
        String city1 = sp.getString("city","北京");
        String area1 = sp.getString("area","GMT+08");
        final City lcity =new City(city1,area1);
        final TimeThread timeThread1 = new TimeThread(local_time,lcity.getArea(),300);
        local_city.setText(lcity.getName());
        timeThread1.start();
        if(mydb.getCitys()!=null){
            mlist=mydb.getCitys();
        }
        adapter.setmList(mlist);
        list_time.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(WorldTime.this,CityAdd.class);
                startActivity(intent);
            }
        });
        list_time.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(WorldTime.this);
                dialog.setMessage("是否刪除该城市?");
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mydb.deleteCityByName(((City)adapter.getItem(position)).getName());
                        mlist.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.show();
                return true;
            }
        });
        list_time.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(WorldTime.this);
                dialog.setMessage("是否更改城市?");
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        City c = ((City)adapter.getItem(position));
                        lcity.setArea(c.getArea());
                        lcity.setName(c.getName());
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("city",lcity.getName());
                        editor.putString("area",lcity.getArea());
                        editor.commit();
                        local_city.setText(lcity.getName());
                        timeThread1.setArea(lcity.getArea());
                    }
                });
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.show();

            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        isPause = true; //记录页面已经被暂停
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPause){ //判断是否暂停
            isPause = false;
            if(mydb.getCitys()!=null){
                mlist=mydb.getCitys();
            }
            adapter.setmList(mlist); //需要adapter重新设置list的数据
            adapter.notifyDataSetChanged();//刷新
        }

    }
    public  void init(){

        mydb.insertCityToAll(new City("北京","GMT+08"));
        mydb.insertCityToAll(new City("东京","GMT+09"));
        mydb.insertCityToAll(new City("墨尔本 ","GMT+10"));
        mydb.insertCityToAll(new City("所罗门群岛","GMT+11"));
        mydb.insertCityToAll(new City("惠灵顿","GMT+12"));
        mydb.insertCityToAll(new City("萨摩亚","GMT-11"));
        mydb.insertCityToAll(new City("檀香山","GMT-10"));
        mydb.insertCityToAll(new City("阿拉斯加","GMT-09"));
        mydb.insertCityToAll(new City("西雅图","GMT-08"));
        mydb.insertCityToAll(new City("菲尼克斯","GMT-07"));
        mydb.insertCityToAll(new City("墨西哥城","GMT-06"));
        mydb.insertCityToAll(new City("纽约","GMT-05"));
        mydb.insertCityToAll(new City("百慕大","GMT-04"));
        mydb.insertCityToAll(new City("里约热内卢","GMT-03"));
        mydb.insertCityToAll(new City("佛德角","GMT-01"));
        mydb.insertCityToAll(new City("伦敦","GMT+00"));
        mydb.insertCityToAll(new City("柏林","GMT+01"));
        mydb.insertCityToAll(new City("雅典","GMT+02"));
        mydb.insertCityToAll(new City("莫斯科","GMT+03"));
        mydb.insertCityToAll(new City("留尼汪（法）","GMT+04"));
        mydb.insertCityToAll(new City("马尔代夫","GMT+05"));
        mydb.insertCityToAll(new City("不丹","GMT+06"));
        mydb.insertCityToAll(new City("胡志明市","GMT+07"));
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("is_new", "no");
        editor.putString("city","北京");
        editor.putString("area","GMT+08");
        editor.commit();

    }
}
