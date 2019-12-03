package com.example.pineappleym.lite.Functions.Random;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pineappleym.lite.R;

import java.util.Random;

public class randomActivity extends AppCompatActivity {
    TextView t, t1, t2;
    Button createButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        init();
    }

    public void createRandom(View view) {
        String s1 = t1.getText().toString();
        String s2 = t2.getText().toString();
        if(s1.length() == 0 || s2.length() == 0){
            Toast.makeText(randomActivity.this,"请输入",Toast.LENGTH_SHORT).show();
            return;
        }
        if (s1.length() > 8 || s2.length() > 8){
            Toast.makeText(randomActivity.this,"请输入不超过8位整数",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!judgement(s1, s2)){
            Toast.makeText(randomActivity.this,"请在输入框中输入合理的数字",Toast.LENGTH_SHORT).show();
            return;
        }
        if(createButton.getTag().toString().equals("0")){
            createButton.setTag("1");
            createButton.setText("继续");
        }
        int n1, n2;
        n1 = Integer.valueOf(s1);
        n2 = Integer.valueOf(s2);
        Random random = new Random();
        int result = random.nextInt(n2) % (n2 - n1 + 1) + n1;
        String s = String.valueOf(result);
        t.setText(s);
    }

    public void init(){
        t = findViewById(R.id.num);
        t1 = findViewById(R.id.min);
        t2 = findViewById(R.id.max);
        createButton = findViewById(R.id.create);
    }

    public boolean judgement(String s1, String s2){
        if(!TextUtils.isDigitsOnly(s1) || !TextUtils.isDigitsOnly(s2))return false;
        int n1, n2;
        n1 = Integer.valueOf(s1);
        n2 = Integer.valueOf(s2);
        if(n1 >= n2)return false;
        return true;
    }
}
