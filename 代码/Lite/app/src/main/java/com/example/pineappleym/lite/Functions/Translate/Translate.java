package com.example.pineappleym.lite.Functions.Translate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pineappleym.lite.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Translate extends AppCompatActivity {
    @BindView(R.id.translate_button)
    Button translate;
    @BindView(R.id.src_language_text)
    TextView src_text;
    @BindView(R.id.des_language_text)
    TextView des_text;
    @BindView(R.id.src_language_spinner)
    Spinner src_spinner;
    @BindView(R.id.des_language_spinner)
    Spinner des_spinner;

    private static final String APP_ID = "20181226000252416";
    private static final String SECURITY_KEY = "SVucnPW6gJepb2t4zFfu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        ButterKnife.bind(this);

        translate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                start_translate();
            }
        });

    }


    public void start_translate(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String query_content = src_text.getText().toString();
                TransApi transApi = new TransApi(APP_ID,SECURITY_KEY);
                String src_code = get_language_code_src(src_spinner.getSelectedItem().toString());
                String des_code = get_language_code_des(des_spinner.getSelectedItem().toString());
                String response = transApi.getTransResult(query_content,src_code,des_code);
                TransData transData = new Gson().fromJson(response,TransData.class);
                List<TransResult> transResults = transData.getResults();
                TransResult transResult = transResults.get(0);
                set_des_translate_content(transResult.getDes());
            }
        }).start();
    }

    public void set_des_translate_content(final String des){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                des_text.setText(des);
            }
        });
    }


    public String get_language_code_src(String money_name)throws RuntimeException{
        Map<String,String> language_table = new HashMap<>();
        if(language_table.isEmpty()){
            String[] names = getResources().getStringArray(R.array.language_chinese_src);
            String[] codes = getResources().getStringArray(R.array.language_code_src);

            if(names.length != codes.length){
                throw new RuntimeException();
            }

            for(int i = 0; i < names.length;i++){
                language_table.put(names[i],codes[i]);
            }
        }
        return language_table.get(money_name);
    }

    public String get_language_code_des(String money_name)throws RuntimeException{
        Map<String,String> language_table = new HashMap<>();
        if(language_table.isEmpty()){
            String[] names = getResources().getStringArray(R.array.language_chinese_des);
            String[] codes = getResources().getStringArray(R.array.language_code_des);

            if(names.length != codes.length){
                throw new RuntimeException();
            }

            for(int i = 0; i < names.length;i++){
                language_table.put(names[i],codes[i]);
            }
        }
        return language_table.get(money_name);
    }
}
