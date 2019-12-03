package com.example.pineappleym.lite.Functions.ColorPick;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pineappleym.lite.R;
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;


public class ColorPick extends AppCompatActivity {
    public ColorPickerView colorPickerView;
    public TextView color;
    public Button sepicture,copy;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colorpick);
        colorPickerView = (ColorPickerView)findViewById(R.id.colorPickerView);
        color = (TextView)findViewById(R.id.color);
        copy = (Button)findViewById(R.id.copy);
        sepicture = (Button)findViewById(R.id.select_picture);

        colorPickerView.setColorListener(new ColorEnvelopeListener() {
            @Override
            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                ConstraintLayout linearLayout = findViewById(R.id.linearLayout);
                linearLayout.setBackgroundColor(envelope.getColor());
                color.setText("#" + envelope.getHexCode());
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String color_string = color.getText().toString();
                    //获取剪贴板管理器：
                    ClipboardManager cm = (ClipboardManager) getSystemService(ColorPick.CLIPBOARD_SERVICE);
                    // 创建普通字符型ClipData
                    ClipData mClipData = ClipData.newPlainText("Label", color_string);
                    // 将ClipData内容放到系统剪贴板里。
                    cm.setPrimaryClip(mClipData);
                }catch (Exception e){
                    return;
                }
                Toast.makeText(ColorPick.this, "已成功复制到粘贴板！", Toast.LENGTH_SHORT).show();
            }
        });

        sepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            // 得到图片的全路径
            uri = data.getData();
            Drawable d = null;
            try {
                 d = Drawable.createFromStream(this.getContentResolver().openInputStream(uri), null);
            }catch (Exception e){

            }
            if(d!=null){
                try {
                    colorPickerView.setPaletteDrawable(d);
                }catch (Exception e){

                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
