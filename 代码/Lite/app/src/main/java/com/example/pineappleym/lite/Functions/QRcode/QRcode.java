package com.example.pineappleym.lite.Functions.QRcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pineappleym.lite.R;


public class QRcode extends AppCompatActivity {
    public EditText link,background,color,qrsize;
    public Button save,show;
    public ImageView code;
    public Bitmap bitcode;
    protected void init(){
        link=(EditText)findViewById(R.id.link);
        background=(EditText)findViewById(R.id.background);
        color=(EditText)findViewById(R.id.color);
        qrsize=(EditText)findViewById(R.id.qrsize);
        save=(Button)findViewById(R.id.save);
        show=(Button)findViewById(R.id.show);
        code=(ImageView)findViewById(R.id.qrcode);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        init();
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link_string = link.getText().toString();
                String background_string = background.getText().toString();
                String color_string = color.getText().toString();
                String qrsize_string = qrsize.getText().toString();
                int color_int,background_int,qrsize_int;
                try{
                    color_int = Integer.parseInt(color_string, 16);
                }catch (Exception e){
                    color_int = 0x000000;
                }
                try{
                    background_int = Integer.parseInt(background_string, 16);
                }catch (Exception e){
                    background_int = 0xFFFFFF;
                }
                try{
                    qrsize_int = Integer.parseInt(qrsize_string);
                }catch (Exception e){
                    qrsize_int = 600;
                }
                if("".equals(link_string)){
                    Toast.makeText(QRcode.this, "第一项不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                bitcode = QRCodeUtil.createQRImage(link_string, qrsize_int, qrsize_int,color_int,background_int);
                code.setImageBitmap(bitcode);
                code.setVisibility(View.VISIBLE);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MediaStore.Images.Media.insertImage(getContentResolver(), bitcode, "title", "description");
                    MediaScannerConnection.scanFile(QRcode.this, new String[]{}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                            mediaScanIntent.setData(uri);
                            QRcode.this.sendBroadcast(mediaScanIntent);
                        }
                    });
                }catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(QRcode.this, "保存失败！", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(QRcode.this, "已保存到本地相册", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
