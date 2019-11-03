package com.example.sdcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {

    EditText e1;
    Button write, read, clear;
    String message="";

    private static final int STORAGE_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.editText);
        write = (Button) findViewById(R.id.button_write);
        read = (Button) findViewById(R.id.button_read);
        clear = (Button) findViewById(R.id.button_clear);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,  new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    public void read(View view) {

        String Message;
        String buf = "";
        try {
            File f = new File("/sdcard/myfile.txt");
            FileInputStream fin = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            while ((Message = br.readLine()) != null) {
                buf += Message;
            }
            e1.setText(buf);
            br.close();
            fin.close();
            Toast.makeText(getBaseContext(), "Data received from SDCARD", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e1.setText(message);
            Toast.makeText(getBaseContext(), "Data receivved from SDCARD ", Toast.LENGTH_LONG).show();
        }
    }

    public void write(View view) {
//        checkPermission(
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                STORAGE_PERMISSION_CODE);

        message = e1.getText().toString();
        try {
            File f = new File(Environment.getExternalStorageDirectory().getPath()+"/myfile.txt");
            f.createNewFile();
            FileOutputStream fout = new FileOutputStream(f);
            fout.write(message.getBytes());
            fout.close();
            e1.setText("");
            Toast.makeText(getBaseContext(), "Data written to SD Card", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e1.setText("");
            Toast.makeText(getBaseContext(), "Data  Written to  SD Card", Toast.LENGTH_LONG).show();
        }
    }

    public void clear(View view) {
        e1.setText("");
    }
}

