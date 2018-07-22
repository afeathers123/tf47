package com.hzyc.zcm.demo_03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private int[] data = {R.drawable.buythg, R.drawable.newcategory, R.drawable.nicefood, R.drawable.paymoney, R.drawable.playfun, R.drawable.service,
            R.drawable.buythg, R.drawable.newcategory, R.drawable.nicefood, R.drawable.paymoney, R.drawable.playfun, R.drawable.service,
            R.drawable.buythg, R.drawable.newcategory, R.drawable.nicefood, R.drawable.paymoney, R.drawable.playfun, R.drawable.service,
            R.drawable.buythg, R.drawable.newcategory, R.drawable.nicefood, R.drawable.paymoney, R.drawable.playfun, R.drawable.service,
            R.drawable.buythg, R.drawable.newcategory, R.drawable.nicefood, R.drawable.paymoney, R.drawable.playfun, R.drawable.service,
            R.drawable.buythg, R.drawable.newcategory, R.drawable.nicefood, R.drawable.paymoney, R.drawable.playfun, R.drawable.service};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new goodsMessage());
    }

    public void check(View v){
        int id = v.getId();
        switch (id){
            case R.id.nicefood :
                Toast.makeText(MainActivity.this, "美食", Toast.LENGTH_SHORT).show();
                break;
            case R.id.playfun :
                Toast.makeText(MainActivity.this, "娱乐", Toast.LENGTH_SHORT).show();
                break;
            case R.id.service :
                Toast.makeText(MainActivity.this, "生活", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public List<Map<String,Object>> getList(){

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i = 1; i<=16; i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("photo",data[i]);
            map.put("title","商品"+i);
            map.put("price","0.1");
            map.put("rating",5);
            map.put("xiaomi","小米(MI)");
            list.add(map);
        }
        return list;
    }

    public class goodsMessage extends BaseAdapter{

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
            View view;
            if(convertView == null){
                view = MainActivity.this.getLayoutInflater().inflate(R.layout.activity_list_view,null);
            }else{
                view = convertView;
            }

            ImageView photo = (ImageView) view.findViewById(R.id.photo);
            TextView title = (TextView) view.findViewById(R.id.title);
            TextView price = (TextView) view.findViewById(R.id.price);
            TextView xiaomi = (TextView) view.findViewById(R.id.xiaomi);
            RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);

            photo.setImageResource(Integer.parseInt(getList().get(position).get("photo").toString()));
            title.setText(getList().get(position).get("title").toString());
            price.setText(getList().get(position).get("price").toString());
            xiaomi.setText(getList().get(position).get("xiaomi").toString());
            ratingBar.setRating(Float.parseFloat(getList().get(position).get("rating").toString()));

            return view;
        }
    }
}
