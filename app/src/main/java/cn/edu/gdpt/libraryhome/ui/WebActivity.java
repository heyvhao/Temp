package cn.edu.gdpt.libraryhome.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.edu.gdpt.libraryhome.R;
import cn.edu.gdpt.libraryhome.databinding.ActivityWebBinding;

public class WebActivity extends AppCompatActivity {

private ActivityWebBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        binding= DataBindingUtil.setContentView(this,R.layout.activity_web);
        String url = intent.getStringExtra("url");
        if(url!=null){
            String[] split = url.split(" ");
            String s = split[0];
            String substring = s.substring(s.indexOf(":")+1, s.length());
            binding.setUrl(substring);
        }

        String urls = intent.getStringExtra("shuji");
        if(urls!=null){
            binding.setUrl(getResources().getString(R.string.jindong)+urls);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
