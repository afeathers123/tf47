package com.hzyc.zcm.demo_10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main6Activity extends AppCompatActivity {

    private Button submitUpdate;
    private EditText codeUpdate;
    private Intent intent;
    private String cu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        submitUpdate = (Button) findViewById(R.id.submitUpdate);
        codeUpdate = (EditText) findViewById(R.id.codeUpdate);



        submitUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Main6Activity.this, Main4Activity.class);
                cu = codeUpdate.getText().toString().trim();
                Bundle bundle = new Bundle();
                bundle.putString("code",cu);

                intent.putExtras(bundle);

                startActivityForResult(intent,0);
            }
        });
    }
}
