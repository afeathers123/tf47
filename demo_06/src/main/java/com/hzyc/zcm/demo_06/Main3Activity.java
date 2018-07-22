package com.hzyc.zcm.demo_06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    private Button toMain;
    private EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        message = (EditText) findViewById(R.id.message);
        toMain = (Button) findViewById(R.id.button2);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        message.setText(bundle.getString("username")+"#####"+bundle.getString("password"));

        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String backValue = message.getText().toString().trim();


                Intent intent = new Intent(Main3Activity.this,MainActivity.class);
                intent.putExtra("backValue",backValue);
                setResult(200,intent);
                finish();

            }
        });
    }
}