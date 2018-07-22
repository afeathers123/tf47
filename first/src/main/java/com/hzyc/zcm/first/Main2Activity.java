package com.hzyc.zcm.first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        View view = findViewById(R.id.login);
        Button login = (Button)view;

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText userName = (EditText)findViewById(R.id.userName);
                String un = userName.getText().toString();
                EditText password = (EditText)findViewById(R.id.password);
                String pw = password.getText().toString();
                if (un.equals("admin")&&pw.equals("admin")){
                    Toast.makeText(Main2Activity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Main2Activity.this,"登陆失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
