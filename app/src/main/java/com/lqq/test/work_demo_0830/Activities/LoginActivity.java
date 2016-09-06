package com.lqq.test.work_demo_0830.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lqq.test.work_demo_0830.GetResouse.getResourseHelp;
import com.lqq.test.work_demo_0830.HelpUtils.XutilsHelp;
import com.lqq.test.work_demo_0830.Pojo.UserDetail;
import com.lqq.test.work_demo_0830.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

@ContentView(value = R.layout.activity_login)
public class LoginActivity extends Activity {

    @ViewInject(value = R.id.et_username)
    private EditText et_name;
    @ViewInject(value = R.id.et_password)
    private EditText et_pass;
    @ViewInject(value = R.id.btn_login)
    private Button btn_login;
    @ViewInject(value = R.id.tv_forget_pwd)
    private TextView tv_forget;
    @ViewInject(value = R.id.tv_register)
    private TextView tv_register;
    @ViewInject(R.id.custom_toolbar)
    private Toolbar toolbar;

    private RequestParams params;
    private SharedPreferences login;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private long curTime;
    private getResourseHelp help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);

        x.view().inject(this);
        addActivity(this);
        help = getResourseHelp.getResourHelp();

        login = getSharedPreferences("Login", MODE_PRIVATE);
        toolbar.setTitle("登录");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        builder = new AlertDialog.Builder(this);

    }

    private void addActivity(LoginActivity loginActivity) {

        getResourseHelp help = getResourseHelp.getResourHelp();
        help.setActivityList(loginActivity);

    }

    @Event(value = {R.id.btn_login,R.id.tv_forget_pwd,R.id.tv_register},type = View.OnClickListener.class)
    private void onClick(View view){

        switch (view.getId()){

            case R.id.btn_login:

                builder.setView(R.layout.pub_dialog_loading);
                dialog = builder.create();
                dialog.show();

                String name = et_name.getText().toString();
                String pass = et_pass.getText().toString();
                params = new RequestParams(XutilsHelp.URL_LOGIN);
                params.addBodyParameter("username",name);
                params.addBodyParameter("password",pass);
                x.http().post(params, new Callback.CommonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject result) {

                        try {
                            boolean success = result.getBoolean("success");
                            if (success){
                                SharedPreferences.Editor edit = login.edit();
                                JSONObject user = result.getJSONObject("user");
                                int id = user.getInt("id");
                                edit.putInt("USERID",id);
                                String nickname = user.getString("nickname");
                                edit.putString("NICKNAME",nickname);
                                edit.commit();
                                String username = user.getString("username");
                                String password = user.getString("password");
                                String telephone = user.getString("telephone");
//                                String picurl = user.getString("picurl");
                                getResourseHelp help = getResourseHelp.getResourHelp();
                                help.setUserDetail(new UserDetail(id,username,password,
                                        nickname,telephone," "));
                                System.out.println(help.getUserDetail().toString());

                                Intent it = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(it);
                                dialog.dismiss();
                                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                            }else {

                                dialog.dismiss();
                                Toast.makeText(LoginActivity.this,"账号或密码不正确，请重新输入！",Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });


                break;
            case R.id.tv_forget_pwd:

                Toast.makeText(LoginActivity.this,"请重新登录",Toast.LENGTH_SHORT).show();

                break;
            case R.id.tv_register:

                Intent it = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(it);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

                break;

        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){

            if ((System.currentTimeMillis() - curTime) >2000 ){

                curTime = System.currentTimeMillis();
                Toast.makeText(LoginActivity.this,"再按一次退出",Toast.LENGTH_SHORT).show();

            }else {

                List<Activity> activityList = help.getActivityList();
                for (Activity activity:activityList){
                    activity.finish();
                }

            }

            return true;

        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LoginActivity.this.finish();
    }
}
