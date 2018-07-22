package com.hzyc.zcm.demo_04;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

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
}
