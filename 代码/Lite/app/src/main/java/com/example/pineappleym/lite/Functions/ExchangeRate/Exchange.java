package com.example.pineappleym.lite.Functions.ExchangeRate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pineappleym.lite.R;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Exchange extends AppCompatActivity {
    @BindView(R.id.src_money)
    EditText src_money;
    @BindView(R.id.des_money)
    EditText des_money;
    @BindView(R.id.src_spin)
    Spinner src_spin;
    @BindView(R.id.des_spin)
    Spinner des_spin;
    @BindView(R.id.update_time)
    TextView update_time;
    @BindView(R.id.exchange_button)
    Button exchange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        ButterKnife.bind(this);

        exchange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                exchange_money();
            }
        });
    }
    public void exchange_money(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                String response = "";
                try{
                    String scur = get_money_code(src_spin.getSelectedItem().toString());
                    String tcur = get_money_code(des_spin.getSelectedItem().toString());

                    URL url = new URL("http://api.k780.com/?app=finance.rate"+
                            "&scur="+scur+"&tcur="+tcur+
                            "&appkey=39255&sign=81236bba88dd68d90bb6511d8cff957f&format=json");
                    httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response += line;
                    }
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    if(httpURLConnection != null){
                        httpURLConnection.disconnect();
                    }
                }
                Log.e("1",response);
                Rate rate = new Gson().fromJson(response, Rate.class);
                RateData rateData = rate.getRateData();
                String rate1 = rateData.getRate();
                String update = rateData.getUpdate();
                Log.e("1",rate1);
                float rate_number = Float.valueOf(rate1);
                float des = Float.valueOf(String.valueOf(src_money.getText()));
                String des_number = String.valueOf(des * rate_number);
                change_rate(des_number,update);
            }
        }).start();

    }

    private void change_rate(final String des_number,final String update){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                des_money.setText(des_number);
                update_time.setText(getResources().getString(R.string.update_time)+update);
            }
        });
    }



    public String get_money_code(String money_name)throws RuntimeException{
        Map<String,String> money_table = new HashMap<>();
        if(money_table.isEmpty()){
            String[] names = getResources().getStringArray(R.array.money_chinese);
            String[] codes = getResources().getStringArray(R.array.money_code);

            if(names.length != codes.length){
                throw new RuntimeException();
            }

            for(int i = 0; i < names.length;i++){
                money_table.put(names[i],codes[i]);
            }
        }
        return money_table.get(money_name);
    }
}
