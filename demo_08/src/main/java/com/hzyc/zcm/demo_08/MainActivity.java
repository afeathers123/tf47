package com.hzyc.zcm.demo_08;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button alertDialog, datePickerDialog, timePickerDialog, processDialog;
    private String [] data = {"长春市","吉林市","四平市"};

    private DialogInterface.OnClickListener click = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            switch (which){
                case -1:
                    Toast.makeText(MainActivity.this, which+"喜欢", Toast.LENGTH_SHORT).show();
                    break;
                case -2:
                    Toast.makeText(MainActivity.this, which+"不喜欢", Toast.LENGTH_SHORT).show();
                    break;
                case -3:
                    Toast.makeText(MainActivity.this, which+"一般般", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alertDialog = (Button) findViewById(R.id.alertDialog);
        datePickerDialog = (Button) findViewById(R.id.datePickerDialog);
        timePickerDialog = (Button) findViewById(R.id.timePickerDialog);
        processDialog = (Button) findViewById(R.id.processDialog);

        alertDialog.setOnClickListener(new View.OnClickListener(){
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.ic_launcher)
                        .setTitle("消息提示")
                        //.setMessage("您喜欢看好莱坞电影吗?")
                        .setView(R.layout.login)
                        //.show();//弹出的内容一定要show
                        //扩展
                        //1.扩展按钮 默认只能扩展3个  确定  否定   中立
                        //在show之前完成扩展
                        .setPositiveButton("喜欢", click)
                        .setNegativeButton("不喜欢", click)
                        .setNeutralButton("一般", click)
                        .show();

               /* AlertDialog dialog = builder.create();
                dialog.show();

                Window dialogWindow = dialog.getWindow();
                WindowManager m = dialogWindow.getWindowManager();
                Display d = m.getDefaultDisplay();//获取屏幕宽和高
                WindowManager.LayoutParams layout = dialogWindow.getAttributes();//获得dialog属性
                layout.width = (int) (d.getWidth() * 0.6);
                layout.height = (int) (d.getHeight() * 0.4);
                layout.alpha = 0.5f;
                layout.gravity = Gravity.BOTTOM;
                dialogWindow.setAttributes(layout);*/

                /*AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.ic_launcher)
                        .setTitle("消息提示")
                        *//*.setView(R.layout.login)*//*
                        .setSingleChoiceItems(data, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, data[which]+"", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();*/

               /* AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.ic_launcher)
                        .setTitle("消息提示")
                        .setMultiChoiceItems(data, new boolean[]{true,false,false}, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                Toast.makeText(MainActivity.this, data[which]+"", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();*/
            }
        });


        processDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //进度条  圆形  长方形
                final ProgressDialog pd = new ProgressDialog(MainActivity.this);
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.setIcon(R.drawable.ic_launcher);
                pd.setTitle("请等待");
                pd.setMessage("正在加载........");
                pd.show();

               /* for(int i = 1; i<=300; i++){
                    if(i == 200){
                        pd.dismiss();
                    }
                    pd.setProgress(i);
                }*/
                pd.setMax(100);
                //使用线程  问题就出现了 androidapp中线程 和Java的线程不一样的
                class myThread extends Thread {
                    @Override
                    public void run() {
                        int i = 0;
                        while (true) {
                            pd.setProgress(i+=10);
                            try {
                                sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            if(i == 100){
                                pd.dismiss();
                                break;
                            }
                        }
                    }
                }
                new myThread().start();


            }
        });


        datePickerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //需要获取年月日  Date
                //java日历类  获取时间  时间的计算
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String sj = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        Toast.makeText(MainActivity.this, sj, Toast.LENGTH_SHORT).show();
                    }
                }, year, month, day);
                dpd.show();
            }
        });

        timePickerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog time = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String sj = hourOfDay + ":" + minute;
                        Toast.makeText(MainActivity.this, sj, Toast.LENGTH_SHORT).show();
                    }
                }, hour, minute, true);
                time.show();
            }
        });


    }
}
