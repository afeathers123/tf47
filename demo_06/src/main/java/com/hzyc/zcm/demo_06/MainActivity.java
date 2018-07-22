package com.hzyc.zcm.demo_06;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button toMain2;
    private EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        toMain2 = (Button) findViewById(R.id.button);

        SharedPreferences spf = getSharedPreferences("data",0);
        String name1 = spf.getString("username1","nothing");

        if(!"nothing".equals(name1)){
            username.setText(name1);
        }

        toMain2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String un = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();

                Intent intent = new Intent(MainActivity.this,Main3Activity.class);

                Bundle bundle = new Bundle();
                bundle.putString("username",un);
                bundle.putString("password",pwd);

                intent.putExtras(bundle);

                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == 200){
            String backValue = data.getStringExtra("backValue");
            Toast.makeText(MainActivity.this, backValue, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String username1 = username.getText().toString().trim();
        if("".equals(username1)){
            Toast.makeText(MainActivity.this, "不需要保护的", Toast.LENGTH_SHORT).show();
        }else{
            SharedPreferences spf = getSharedPreferences("data",0);
            SharedPreferences.Editor editor = spf.edit();
            editor.putString("username1",username1);
            boolean bol = editor.commit();
            Toast.makeText(MainActivity.this, "保护状态="+bol, Toast.LENGTH_SHORT).show();
        }
    }
}
