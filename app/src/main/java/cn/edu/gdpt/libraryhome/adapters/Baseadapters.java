package cn.edu.gdpt.libraryhome.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class Baseadapters<T> extends BaseAdapter {

    private List<T> objects ;

    private Context context;
    private LayoutInflater layoutInflater;
    private int variabled;
    private int layout;

    public Baseadapters(List<T> objects, Context context, int layout, int variabled) {
        this.objects = objects;
        this.context = context;
        this.variabled = variabled;
        this.layout=layout;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding viewDataBinding;
        if (convertView == null) {
            viewDataBinding = DataBindingUtil.inflate(layoutInflater,layout,parent,false);
        }else{
            viewDataBinding= DataBindingUtil.getBinding(convertView);
        }
        viewDataBinding.setVariable(variabled,objects.get(position));

        return viewDataBinding.getRoot();
    }




}
