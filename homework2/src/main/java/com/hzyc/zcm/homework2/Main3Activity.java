package com.hzyc.zcm.homework2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Main3Activity.this,v);
                popupMenu.getMenuInflater().inflate(R.menu.menu1,popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.save:
                                Toast.makeText(Main3Activity.this, "点击了保存", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.delete:
                                Toast.makeText(Main3Activity.this, "点击了删除", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.sao:
                                Toast.makeText(Main3Activity.this, "点击了扫一扫", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
            }
        });
    }
}
