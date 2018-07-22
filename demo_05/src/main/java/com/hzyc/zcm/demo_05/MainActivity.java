package com.hzyc.zcm.demo_05;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int[] data = {R.drawable.buythg, R.drawable.newcategory, R.drawable.nicefood, R.drawable.paymoney, R.drawable.playfun, R.drawable.service,
            R.drawable.buythg, R.drawable.newcategory, R.drawable.nicefood, R.drawable.paymoney, R.drawable.playfun, R.drawable.service,
            R.drawable.buythg, R.drawable.newcategory, R.drawable.nicefood, R.drawable.paymoney, R.drawable.playfun, R.drawable.service,
            R.drawable.buythg, R.drawable.newcategory, R.drawable.nicefood, R.drawable.paymoney, R.drawable.playfun, R.drawable.service,
            R.drawable.buythg, R.drawable.newcategory, R.drawable.nicefood, R.drawable.paymoney, R.drawable.playfun, R.drawable.service,
            R.drawable.buythg, R.drawable.newcategory, R.drawable.nicefood, R.drawable.paymoney, R.drawable.playfun, R.drawable.service};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new myAdapter());

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "图片"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<String> getList(){
        List<String> list = new ArrayList<String>();
        for(int i=0; i<=data.length; i++){
            list.add("图片"+(i+1));
        }
        return list;
    }

    public class myAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            return data[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(MainActivity.this);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(data[position]);
            return imageView;
        }
    }

    public class myAdapter2 extends BaseAdapter {

        @Override
        public int getCount() {
            return getList().size();
        }

        @Override
        public Object getItem(int position) {
            return getList().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if (convertView == null) {
                textView = new TextView(MainActivity.this);
            } else {
                textView = (TextView) convertView;
            }
            textView.setText(getList().get(position));
            textView.setTextSize(30);
            return textView;
        }
    }
}
