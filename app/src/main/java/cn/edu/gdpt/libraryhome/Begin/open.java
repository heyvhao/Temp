package cn.edu.gdpt.libraryhome.Begin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import cn.edu.gdpt.libraryhome.MainActivity;
import cn.edu.gdpt.libraryhome.R;

public class open extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        init();
    }
    private void init(){
        Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(open.this, MainActivity.class);
                startActivity(intent);
                open.this.finish();
            }
        };
        timer.schedule(task,3000);
    }
}
