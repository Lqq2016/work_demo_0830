package com.lqq.test.work_demo_0830.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lqq.test.work_demo_0830.GetResouse.getResourseHelp;
import com.lqq.test.work_demo_0830.HelpUtils.XutilsHelp;
import com.lqq.test.work_demo_0830.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_register)
public class RegisterActivity extends Activity {

    @ViewInject(R.id.custom_toolbar)
    private Toolbar toolbar;
    @ViewInject(R.id.et_register_name)
    private EditText et_name;
    @ViewInject(R.id.et_register_password)
    private EditText et_pass;
    @ViewInject(R.id.et_register_nickname)
    private EditText et_nick;
    @ViewInject(R.id.et_register_telephone)
    private EditText et_phone;
    @ViewInject(R.id.btn_register)
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);

        x.view().inject(this);
        addActivity(this);

        toolbar.setTitle("注册");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

    }

    private void addActivity(RegisterActivity registerActivity) {

        getResourseHelp help = getResourseHelp.getResourHelp();
        help.setActivityList(registerActivity);

    }

    @Event(value = R.id.btn_register,type = View.OnClickListener.class)
    private void onClick(View view){

        switch (view.getId()){

            case R.id.btn_register:

                doRegister();

                break;

        }

    }


    private void doRegister() {

        String name = et_name.getText().toString();
        String pass = et_pass.getText().toString();
        String nick = et_nick.getText().toString();
        String phone = et_phone.getText().toString();

        RequestParams params = new RequestParams(XutilsHelp.URL_REGISTE);
        params.addBodyParameter("username",name);
        params.addBodyParameter("password",pass);
        params.addBodyParameter("nickname",nick);
        params.addBodyParameter("telephone",phone);
        
        x.http().post(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {

                try {
                    boolean success = result.getBoolean("success");

                    if (success){

                        Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(it);
                        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

                    }else {
                        String reason = result.getString("reason");
                        Toast.makeText(RegisterActivity.this,reason,Toast.LENGTH_SHORT).show();
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

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        RegisterActivity.this.finish();

    }
}
