package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText Name,Rollno;
    Button  b1,b2;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = (EditText) findViewById(R.id.name);
        Rollno = (EditText) findViewById(R.id.rollno);
        b1 = (Button) findViewById(R.id.button_insert);
        b2 = (Button)  findViewById(R.id.button_view);

        db = openOrCreateDatabase("studentdb", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student (rollno VARCHAR, name VARCHAR);");
    }

    public void view(View view) {
        Cursor c = db.rawQuery("SELECT  * FROM student",null);
        if(c.getCount()  == 0) {
            showMessage("Error","No  Records Found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext())  {
            buffer.append("Roll No. : "  + c.getString(0)  +  "\n");
            buffer.append("Name : " +  c.getString(1)  + "\n");
        }
        showMessage("Student Details:",buffer.toString());
    }

    public void insert(View view) {
        if((Rollno.getText().toString().trim().length() == 0) || (Name.getText().toString().trim().length() == 0)) {
            showMessage("Error", "Please Enter all Values");
            return;
        }
        db.execSQL("INSERT  INTO student VALUES('"+Rollno.getText()+"','"+Name.getText()+"')");
        showMessage("Success","Data Inserted");
        Rollno.setText("");
        Name.setText("");
        Name.requestFocus();
    }

    public void showMessage(String  title, String m)  {
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(m);
        builder.show();
    }
}
