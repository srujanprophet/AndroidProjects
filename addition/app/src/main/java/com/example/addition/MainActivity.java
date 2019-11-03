package com.example.addition;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2,e3;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.editText_number1);
        e2 = (EditText) findViewById(R.id.editText2_number2);
        e3 = (EditText) findViewById(R.id.editText3);
        b1 = (Button) findViewById(R.id.button);
    }

    public void addit(View view) {
        int a = Integer.parseInt(e1.getText().toString());
        int b = Integer.parseInt(e2.getText().toString());
        int result = a + b;
        e3.setText(String.valueOf(result));
    }
}
