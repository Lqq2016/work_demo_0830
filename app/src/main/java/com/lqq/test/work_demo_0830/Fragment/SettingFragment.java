package com.lqq.test.work_demo_0830.Fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lqq.test.work_demo_0830.Activities.LoginActivity;
import com.lqq.test.work_demo_0830.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements View.OnClickListener {

    RelativeLayout nameSet,autologin,showimage,loginout,cleancache,aboutus;
    Button btn_sure,btn_cancle;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    SharedPreferences login;
    CheckBox cb_autologin,cb_showimage;
    TextView tv_autologin,tv_showimage,tv_nickname;
    EditText et_nickname;
    boolean autoLogin,showImage;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        findViewBy(view);

        login = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        showSet();

        return view;
    }

    private void showSet() {

        autoLogin = login.getBoolean("AUTO_LOGIN", true);
        showImage = login.getBoolean("SHOW_IMAGE", true);
        cb_autologin.setChecked(autoLogin);
        cb_showimage.setChecked(showImage);
        if (autoLogin){
            tv_autologin.setText(R.string.pref_item_autologin_summary_on);
        }else {
            tv_autologin.setText(R.string.pref_item_autologin_summary_off);
        }
        if (showImage){
            tv_showimage.setText(R.string.pref_item_show_img_summary_on);
        }else {
            tv_showimage.setText(R.string.pref_item_show_img_summary_off);
        }

    }


    private void findViewBy(View view) {

        nameSet = (RelativeLayout) view.findViewById(R.id.rl_nickname_set);
        autologin = (RelativeLayout) view.findViewById(R.id.rl_autologin_set);
        showimage = (RelativeLayout) view.findViewById(R.id.rl_showimage_set);
        loginout = (RelativeLayout) view.findViewById(R.id.rl_loginout_set);
        cleancache = (RelativeLayout) view.findViewById(R.id.rl_cleancache_set);
        aboutus = (RelativeLayout) view.findViewById(R.id.rl_aboutus_set);
        cb_autologin = (CheckBox) view.findViewById(R.id.cb_autologin);
        cb_showimage = (CheckBox) view.findViewById(R.id.cb_showimage);
        tv_autologin = (TextView) view.findViewById(R.id.tv_autologin);
        tv_showimage = (TextView) view.findViewById(R.id.tv_showimage);

        nameSet.setOnClickListener(this);
        autologin.setOnClickListener(this);
        showimage.setOnClickListener(this);
        loginout.setOnClickListener(this);
        cleancache.setOnClickListener(this);
        aboutus.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.rl_nickname_set:

                showDialog(getContext());

                break;
            case R.id.rl_autologin_set:

                setAutoLogin();

                break;
            case R.id.rl_showimage_set:

                setShowImage();

                break;
            case R.id.rl_loginout_set:

                Intent it = new Intent(getContext(), LoginActivity.class);
                getContext().startActivity(it);

                break;
            case R.id.rl_cleancache_set:

                Toast.makeText(getContext(),R.string.pref_item_clear_cache,Toast.LENGTH_SHORT).show();

                break;
            case R.id.rl_aboutus_set:

                Toast.makeText(getContext(),R.string.pref_item_aboutus,Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_sure:

                String s = et_nickname.getText().toString();
//                tv_nickname.setText("**");
                SharedPreferences.Editor edit = login.edit();
                edit.putString("NICKNAME",s);
                edit.commit();
                Toast.makeText(getContext(),"修改昵称成功",Toast.LENGTH_SHORT).show();
                dialog.dismiss();

                break;
            case R.id.btn_cancle:

                Toast.makeText(getContext(),"===",Toast.LENGTH_SHORT).show();
                dialog.dismiss();

                break;

        }

    }

    private void setShowImage() {

        SharedPreferences.Editor edit = login.edit();
        if (showImage){

            cb_showimage.setChecked(false);
            tv_showimage.setText(R.string.pref_item_show_img_summary_off);
            edit.putBoolean("SHOW_IMAGE",false);
            showImage = false;
            edit.commit();

        }else{

            cb_showimage.setChecked(true);
            tv_showimage.setText(R.string.pref_item_show_img_summary_on);
            edit.putBoolean("SHOW_IMAGE",true);
            showImage = true;
            edit.commit();

        }

    }

    private void setAutoLogin() {

        SharedPreferences.Editor edit = login.edit();
        if (autoLogin){

            cb_autologin.setChecked(false);
            tv_autologin.setText(R.string.pref_item_autologin_summary_off);
            edit.putBoolean("AUTO_LOGIN",false);
            autoLogin = false;
            edit.commit();

        }else{

            cb_autologin.setChecked(true);
            tv_autologin.setText(R.string.pref_item_autologin_summary_on);
            edit.putBoolean("AUTO_LOGIN",true);
            autoLogin = true;
            edit.commit();

        }

    }

    public void showDialog(Context context){

        View view = LayoutInflater.from(context).inflate(R.layout.nickname_set, null);
        builder = new AlertDialog.Builder(context);
        builder.setView(view);
        dialog = builder.create();

        btn_sure = (Button) view.findViewById(R.id.btn_sure);
        btn_cancle = (Button) view.findViewById(R.id.btn_cancle);
//        tv_nickname = (TextView) view.findViewById(R.id.tv_nickname);
        et_nickname = (EditText) view.findViewById(R.id.et_nickname);
        btn_sure.setOnClickListener(this);
        btn_cancle.setOnClickListener(this);

        dialog.show();

    }
}
