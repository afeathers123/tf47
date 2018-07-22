package com.hzyc.zcm.demo_10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button submit;
    private EditText choice;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = (Button) findViewById(R.id.submit);
        choice = (EditText) findViewById(R.id.choice);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  c = choice.getText().toString().trim();
                switch(c){
                    case "1":
                        intent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);
                        break;
                    case "2":
                        intent = new Intent(MainActivity.this, Main3Activity.class);
                        startActivity(intent);
                        break;
                    case "3":
                        intent = new Intent(MainActivity.this, Main6Activity.class);
                        startActivity(intent);
                        break;
                    case "4":
                        intent = new Intent(MainActivity.this, Main7Activity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
