package cn.edu.gdpt.libraryhome.ui.fragments;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import cn.edu.gdpt.libraryhome.R;
import cn.edu.gdpt.libraryhome.bean.Userinfo;
import cn.edu.gdpt.libraryhome.databinding.FragmentMyselfBinding;
import cn.edu.gdpt.libraryhome.sql.SqlHelper;


public class Fragment_myself extends Fragment implements View.OnClickListener {
    private LinearLayout show1;
    private Button login;
    private Activity activity;
    private EditText name;
    private EditText psw;
    private Button login_btn;
    private Button register_btn;
    FragmentMyselfBinding binding;
    private LinearLayout loginstaue;
    private LinearLayout show2;
    private LinearLayout show3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = getActivity();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_myself, container, false);
        View view = binding.getRoot();

        initView(view);
        return view;
    }


    private void initView(View view) {
        show1 = (LinearLayout) view.findViewById(R.id.show1);
        login = (Button) view.findViewById(R.id.login);
        login.setOnClickListener(this);
        name = (EditText) view.findViewById(R.id.name);
        name.setOnClickListener(this);
        psw = (EditText) view.findViewById(R.id.psw);
        psw.setOnClickListener(this);
        login_btn = (Button) view.findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);
        register_btn = (Button) view.findViewById(R.id.register_btn);
        register_btn.setOnClickListener(this);

        show2 = (LinearLayout) view.findViewById(R.id.show2);
        show2.setOnClickListener(this);
        show3 = (LinearLayout) view.findViewById(R.id.show3);
        show3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                show1.setVisibility(View.GONE);
                show2.setVisibility(View.VISIBLE);
                break;
            case R.id.login_btn:
                Login();
                break;
            case R.id.register_btn:
                ShowDialog();
                break;
        }
    }

    public void Login() {
        String nnnn = name.getText() + "";
        String ppp = psw.getText() + "";
        if (nnnn.equals("") || ppp.equals("")) {
            Toast.makeText(activity, "请不要留空", Toast.LENGTH_SHORT).show();
        } else {
            SqlHelper sqlHelper = new SqlHelper(activity);
            try {
                boolean b = sqlHelper.SelectUserinfo(new Userinfo(nnnn, ppp));
                if (b) {
                    Toast.makeText(activity, "登录成功", Toast.LENGTH_SHORT).show();
                    binding.setUser(new Userinfo(nnnn, ppp));
                    show1.setVisibility(View.GONE);
                    show2.setVisibility(View.GONE);
                    show3.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(activity, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(activity, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void ShowDialog() {
        View view = LayoutInflater.from(activity).inflate(R.layout.fragment_myself_dialog, null, false);
        ViewHolder viewHolder = new ViewHolder(view);
        final AlertDialog alertDialog = new AlertDialog.Builder(activity).setView(view).show();
        final EditText editText[] = {viewHolder.name, viewHolder.psw, viewHolder.psw2};
        viewHolder.cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        viewHolder.register_bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] name = {"", "", ""};
                for (int i = 0; i < editText.length; i++) {
                    String ss = editText[i].getText() + "";
                    if (ss.equals("")) {
                        Toast.makeText(activity, "请不要留空", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        name[i] = ss;
                    }
                }
                if (name[1].equals(name[2])) {
                    SqlHelper sqlHelper = new SqlHelper(activity);
                    Userinfo userinfo = new Userinfo(name[0], name[1]);
                    try {
                        boolean b = sqlHelper.AddUserinfo(userinfo);
                        if (b) {
                            Toast.makeText(activity, "成功注册", Toast.LENGTH_SHORT).show();
                            Timer timer = new Timer();
                            TimerTask timerTask = new TimerTask() {
                                @Override
                                public void run() {
                                    activity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            alertDialog.dismiss();

                                        }
                                    });

                                }
                            };
                            timer.schedule(timerTask, 1000);
                        } else {
                            Toast.makeText(activity, "用户已存在", Toast.LENGTH_SHORT).show();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(activity, "两次密码不一样", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void submit() {
        // validate
        String nameString = name.getText().toString().trim();
        if (TextUtils.isEmpty(nameString)) {
            Toast.makeText(getContext(), "nameString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String pswString = psw.getText().toString().trim();
        if (TextUtils.isEmpty(pswString)) {
            Toast.makeText(getContext(), "pswString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    public
    class ViewHolder {
        public View rootView;
        public EditText name;
        public EditText psw;
        public EditText psw2;
        public Button register_bb;
        public Button cancle;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.name = (EditText) rootView.findViewById(R.id.name);
            this.psw = (EditText) rootView.findViewById(R.id.psw);
            this.psw2 = (EditText) rootView.findViewById(R.id.psw2);
            this.register_bb = (Button) rootView.findViewById(R.id.register_bb);
            this.cancle = (Button) rootView.findViewById(R.id.cancle);
        }

    }
}
