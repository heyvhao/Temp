package cn.edu.gdpt.libraryhome.ui.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import cn.edu.gdpt.libraryhome.BR;
import cn.edu.gdpt.libraryhome.R;
import cn.edu.gdpt.libraryhome.adapters.Baseadapters;
import cn.edu.gdpt.libraryhome.bean.Booklist;
import cn.edu.gdpt.libraryhome.bean.FindBook;
import cn.edu.gdpt.libraryhome.databinding.FragmentFunctionBinding;
import cn.edu.gdpt.libraryhome.ui.WebActivity;
import cn.edu.gdpt.libraryhome.until.Okhttpuntil;

public class FunctionFragment extends BaseFragment {
    FragmentFunctionBinding binding;
    private ListView lv;
    private GridView gv;
    private SearchView search;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = getActivity();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_function, container, false);
        View root = binding.getRoot();
        initView(root);
        GetBookList();

        return root;
    }

    private void initView(View root) {
        lv = (ListView) root.findViewById(R.id.lv);
        gv = (GridView) root.findViewById(R.id.gv);
        search = (SearchView) root.findViewById(R.id.search);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                Intent intent = new Intent(activity, WebActivity.class);
                intent.putExtra("shuji", s);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }

        });
    }

    //获取参数
    public void GetBookList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Booklist booklist = Okhttpuntil.GetSynsResponse(getResources().getString(R.string.booklist), Booklist.class);
                    if (booklist != null) {
                        if ("200".equals(booklist.getResultcode())) {
                            Log.i("MMMMM", booklist.getResult().size() + "");
                            final Baseadapters leftadapter = new Baseadapters(booklist.getResult(), activity, R.layout.fragment_function_left_item, BR.book);
                            binding.setAdapters1(leftadapter);
                            GetBookResult(booklist.getResult().get(0).getId());
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            GetBookResult(booklist.getResult().get(position).getId());
                                            List<Booklist.ResultBean> result = booklist.getResult();
                                            for (int i = 0; i <booklist.getResult().size() ; i++) {
                                                Booklist.ResultBean resultBean = result.get(i);
                                                resultBean.setIsclick(0);
                                                if(i==position){
                                                    resultBean.setIsclick(1);
                                                }else{
                                                    resultBean.setIsclick(0);
                                                }
                                                result.set(i,resultBean);

                                            }
                                            leftadapter.notifyDataSetChanged();
                                        }
                                    });
                                }
                            });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    //获取书本
    public void GetBookResult(final String tyid) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final FindBook findBook = Okhttpuntil.GetSynsResponse(getResources().getString(R.string.findbook) + tyid, FindBook.class);
                    if (findBook != null) {
                        Log.i("MMMMMMMMMMM", findBook.getResult().getData().size() + "");
                        if ("200".equals(findBook.getResultcode())) {
                            Baseadapters<FindBook.ResultBean.DataBean> baseadapters = new Baseadapters<>(findBook.getResult().getData(), activity, R.layout.fragment_function_riht_item, BR.resultbook);
                            binding.setAdapters2(baseadapters);
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            Intent intent = new Intent(activity, WebActivity.class);
                                            intent.putExtra("url", findBook.getResult().getData().get(position).getOnline());
                                            startActivity(intent);
                                        }
                                    });
                                }
                            });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
