package com.hzyc.zcm.demo_10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main7Activity extends AppCompatActivity {

    private Button search;
    private EditText searchNum, searchText;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        search = (Button) findViewById(R.id.search);
        searchNum = (EditText) findViewById(R.id.searchNum);
        searchText = (EditText) findViewById(R.id.searchText);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sN = searchNum.getText().toString().trim();
                String sT = searchText.getText().toString().trim();

                intent = new Intent(Main7Activity.this, Main8Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("searchNum",sN);
                bundle.putString("searchText",sT);

                intent.putExtras(bundle);

                startActivityForResult(intent,0);
            }
        });

    }
}
