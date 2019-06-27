package cn.edu.gdpt.libraryhome;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdpt.libraryhome.adapters.Fragmentadapters;
import cn.edu.gdpt.libraryhome.ui.fragments.Fragment_myself;
import cn.edu.gdpt.libraryhome.ui.fragments.FunctionFragment;
import cn.edu.gdpt.libraryhome.ui.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tb;
    private List<Fragment> fragments=new ArrayList<>();
    private List<String> titles=new ArrayList<>();
    private Fragmentadapters fragmentadapters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tb = (TabLayout) findViewById(R.id.tb);
        addFragment(new HomeFragment(),"主页");
        addFragment(new FunctionFragment(),"书籍");
        addFragment(new Fragment_myself(),"我的");
        fragmentadapters=new Fragmentadapters(getSupportFragmentManager(),fragments,titles);
        vp.setAdapter(fragmentadapters);
        tb.setupWithViewPager(vp);
    }
    //添加fragment
    public void  addFragment(Fragment fragment,String tt){
        fragments.add(fragment);
        titles.add(tt);
    }
}
