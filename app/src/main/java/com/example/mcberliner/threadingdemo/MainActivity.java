package com.example.mcberliner.threadingdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends Activity {

    private TextView tvOutput;
    private static final int SUCCESS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutput = (TextView) findViewById(R.id.tvOutput);
    }

    public void fetchData(View v) {
        tvOutput.setText("Fetching data from a remote server...");
        thread.start();
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            handler.sendEmptyMessage(SUCCESS);
        }
    });

    Handler handler = new Handler() {
       public void handleMessage(android.os.Message msg) {
           if (msg.what == SUCCESS) {
               tvOutput.setText("Data fetched from remote server successfully");
           }
       }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
