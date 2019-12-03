package com.example.pineappleym.lite.Functions.Transform;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pineappleym.lite.R;

public class transformActivity extends AppCompatActivity {
    Button bin, oct, dec, hex;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0;
    Button ba, bb, bc, bd, be, bf;
    TextView input, binNum, octNum, decNum, hexNum;
    Drawable red, white;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform);
        init();
    }

    public void init(){
        bin = findViewById(R.id.bin);
        oct = findViewById(R.id.oct);
        dec = findViewById(R.id.dec);
        hex = findViewById(R.id.hex);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        b0 = findViewById(R.id.b0);
        ba = findViewById(R.id.ba);
        bb = findViewById(R.id.bb);
        bc = findViewById(R.id.bc);
        bd = findViewById(R.id.bd);
        be = findViewById(R.id.be);
        bf = findViewById(R.id.bf);
        input = findViewById(R.id.input);
        binNum = findViewById(R.id.binNum);
        octNum = findViewById(R.id.octNum);
        decNum = findViewById(R.id.decNum);
        hexNum = findViewById(R.id.hexNum);
        red = dec.getBackground();
        white = bin.getBackground();
    }

    public void sysc(){
        String str = input.getText().toString();
        if(str.equals("0"))clearText();
        else if(bin.getTag().toString().equals("1"))transformNum(2);
        else if(oct.getTag().toString().equals("1"))transformNum(8);
        else if(dec.getTag().toString().equals("1"))transformNum(10);
        else if(hex.getTag().toString().equals("1"))transformNum(16);
    }

    public void transformNum(int key){
        String str = input.getText().toString();
        int i;
        switch (key){
            case 2:
                i = Integer.parseInt(str, 2);
                transformStr(i);
                break;
            case 8:
                i = Integer.parseInt(str, 8);
                transformStr(i);
                break;
            case 10:
                i = Integer.parseInt(str, 10);
                transformStr(i);
                break;
            case 16:
                i = Integer.parseInt(str, 16);
                transformStr(i);
                break;
        }
    }

    public void transformStr(int i){
        binNum.setText(Integer.toBinaryString(i));
        octNum.setText(Integer.toOctalString(i));
        decNum.setText(Integer.toString(i));
        hexNum.setText(Integer.toHexString(i));
    }

    public void allWhite(){
        bin.setBackground(white);
        bin.setTag("0");
        oct.setBackground(white);
        oct.setTag("0");
        dec.setBackground(white);
        dec.setTag("0");
        hex.setBackground(white);
        hex.setTag("0");
    }

    public void allGrey(){
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(false);
        b9.setEnabled(false);
        ba.setEnabled(false);
        bb.setEnabled(false);
        bc.setEnabled(false);
        bd.setEnabled(false);
        be.setEnabled(false);
        bf.setEnabled(false);
    }

    public void clearText(){
        input.setText("0");
        binNum.setText("");
        octNum.setText("");
        decNum.setText("");
        hexNum.setText("");
    }

    public void p1(View view) {
        if(input.getText().toString().length() >= 7)return;
        if(input.getText().toString().equals("0"))input.setText("1");
        else input.setText(input.getText().toString() + "1");
        sysc();
    }

    public void p2(View view) {
        if(input.getText().toString().length() >= 7)return;
        if(input.getText().toString().equals("0"))input.setText("2");
        else input.setText(input.getText().toString() + "2");
        sysc();
    }

    public void p3(View view) {
        if(input.getText().toString().length() >= 7)return;
        if(input.getText().toString().equals("0"))input.setText("3");
        else input.setText(input.getText().toString() + "3");
        sysc();
    }

    public void p4(View view) {
        if(input.getText().toString().length() >= 7)return;
        if(input.getText().toString().equals("0"))input.setText("4");
        else input.setText(input.getText().toString() + "4");
        sysc();
    }

    public void p5(View view) {
        if(input.getText().toString().length() >= 7)return;
        if(input.getText().toString().equals("0"))input.setText("5");
        else input.setText(input.getText().toString() + "5");
        sysc();
    }

    public void p6(View view) {
        if(input.getText().toString().length() >= 7)return;
        if(input.getText().toString().equals("0"))input.setText("6");
        else input.setText(input.getText().toString() + "6");
        sysc();
    }

    public void p7(View view) {
        if(input.getText().toString().length() >= 7)return;
        if(input.getText().toString().equals("0"))input.setText("7");
        else input.setText(input.getText().toString() + "7");
        sysc();
    }

    public void p8(View view) {
        if(input.getText().toString().length() >= 7)return;
        if(input.getText().toString().equals("0"))input.setText("8");
        else input.setText(input.getText().toString() + "8");
        sysc();
    }

    public void p9(View view) {
        if(input.getText().toString().length() >= 7)return;
        if(input.getText().toString().equals("0"))input.setText("9");
        else input.setText(input.getText().toString() + "9");
        sysc();
    }

    public void p0(View view) {
        if(input.getText().toString().length() >= 7)return;
        if(input.getText().toString().equals("0"))return;
        else input.setText(input.getText().toString() + "0");
        sysc();
    }

    public void pa(View view) {
        if(input.getText().toString().length() >= 7)return;
        if(input.getText().toString().equals("0"))input.setText("A");
        else input.setText(input.getText().toString() + "A");
        sysc();
    }

    public void pb(View view) {
        if(input.getText().toString().length() >= 7)return;
        if(input.getText().toString().equals("0"))input.setText("B");
        else input.setText(input.getText().toString() + "B");
        sysc();
    }

    public void pc(View view) {
        if(input.getText().toString().length() >= 7)return;
        if(input.getText().toString().equals("0"))input.setText("C");
        else input.setText(input.getText().toString() + "C");
        sysc();
    }

    public void pd(View view) {
        if(input.getText().toString().length() >= 7)return;
        if(input.getText().toString().equals("0"))input.setText("D");
        else input.setText(input.getText().toString() + "D");
        sysc();
    }

    public void pe(View view) {
        if(input.getText().toString().equals("0"))input.setText("E");
        else input.setText(input.getText().toString() + "E");
        sysc();
    }

    public void pf(View view) {
        if(input.getText().toString().length() >= 7)return;
        if(input.getText().toString().equals("0"))input.setText("F");
        else input.setText(input.getText().toString() + "F");
        sysc();
    }

    public void clr(View view) {
        clearText();
    }

    public void del(View view) {
        if(input.getText().toString().equals("0"))return;
        String str = input.getText().toString();
        str = str.substring(0, str.length() - 1);
        if(str.length() == 0)clearText();
        else{
            input.setText(str);
            sysc();
        }
    }

    public void binClick(View view) {
        if(bin.getTag().toString().equals("1"))return;
        clearText();
        allWhite();
        bin.setTag("1");
        allGrey();
        bin.setBackground(red);
    }

    public void octClick(View view) {
        if(oct.getTag().toString().equals("1"))return;
        clearText();
        allWhite();
        oct.setTag("1");
        allGrey();
        oct.setBackground(red);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);
        b5.setEnabled(true);
        b6.setEnabled(true);
        b7.setEnabled(true);
    }

    public void decClick(View view) {
        if(dec.getTag().toString().equals("1"))return;
        clearText();
        allWhite();
        dec.setTag("1");
        allGrey();
        dec.setBackground(red);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);
        b5.setEnabled(true);
        b6.setEnabled(true);
        b7.setEnabled(true);
        b8.setEnabled(true);
        b9.setEnabled(true);
    }

    public void hexClick(View view) {
        if(hex.getTag().toString().equals("1"))return;
        clearText();
        allWhite();
        hex.setTag("1");
        hex.setBackground(red);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);
        b5.setEnabled(true);
        b6.setEnabled(true);
        b7.setEnabled(true);
        b8.setEnabled(true);
        b9.setEnabled(true);
        ba.setEnabled(true);
        bb.setEnabled(true);
        bc.setEnabled(true);
        bd.setEnabled(true);
        be.setEnabled(true);
        bf.setEnabled(true);
    }
}
