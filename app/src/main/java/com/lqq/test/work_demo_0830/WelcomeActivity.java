package com.lqq.test.work_demo_0830;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.lqq.test.work_demo_0830.Activities.GuideActivity;
import com.lqq.test.work_demo_0830.Activities.LoginActivity;
import com.lqq.test.work_demo_0830.Activities.MainActivity;
import com.lqq.test.work_demo_0830.HelpUtils.NetUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

//@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_welcome, null);
        setContentView(view);
//        x.view().inject(this);

//        View view = getWindow().getDecorView();


        AlphaAnimation aa = new AlphaAnimation(0.3f,1.0f);
        aa.setDuration(3000);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                doJump();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }

    @Override
    protected void onRestart() {
        super.onRestart();

        doJump();

    }

    private void doJump() {

        SharedPreferences login = getSharedPreferences("Login", MODE_PRIVATE);
        boolean first = login.getBoolean("FIRST", true);
        if (first){

            SharedPreferences.Editor edit = login.edit();
            edit.putBoolean("FIRST",false);
            edit.commit();
            Intent it = new Intent(this, GuideActivity.class);
            startActivity(it);
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

        }else{

            NetUtils netUtils = new NetUtils(this);
            boolean netWorkInfo = netUtils.getNetWorkInfo();
            if (!netWorkInfo){

                Toast.makeText(this,"网络不可用，请检查您的网络",Toast.LENGTH_SHORT).show();
                return;

            }else {

                boolean auto_login = login.getBoolean("AUTO_LOGIN", true);
                if (auto_login){

                    autoLogin(first);

                }else {

                    Intent it = new Intent(this,LoginActivity.class);
                    startActivity(it);
                    overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                }

            }

        }

    }

    private void autoLogin(boolean first) {

        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        finish();
    }
}
