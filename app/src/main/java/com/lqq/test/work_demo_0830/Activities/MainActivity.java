package com.lqq.test.work_demo_0830.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lqq.test.work_demo_0830.Adapter.MyAdapter;
import com.lqq.test.work_demo_0830.Fragment.CategoryFragment;
import com.lqq.test.work_demo_0830.Fragment.SettingFragment;
import com.lqq.test.work_demo_0830.GetResouse.getResourseHelp;
import com.lqq.test.work_demo_0830.Pojo.ListItem;
import com.lqq.test.work_demo_0830.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    @ViewInject(R.id.custom_toolbar)
    private Toolbar custom_toolbar;
    @ViewInject(R.id.custom_drawer)
    private DrawerLayout custom_drawer;
    @ViewInject(R.id.iv_head_img)
    private ImageView img_head;
    @ViewInject(R.id.tv_nickname)
    private TextView tv_nickname;
    @ViewInject(R.id.lv_cehua_middle)
    private ListView lv_cehua;
    @ViewInject(R.id.tv_setting)
    private TextView tv_setting;
    @ViewInject(R.id.tv_exit)
    private TextView tv_exit;
//    @ViewInject(R.id.container)
//    private RelativeLayout rl_container;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private List<ListItem> list;
    private MyAdapter adapter;
    private FragmentManager fragmentManager;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button btn_sure,btn_cancle;
    private EditText et_nickname;
//    private AnimationDrawable animationDrawable;//动画

    private getResourseHelp help;
    private SharedPreferences login;
    private long curTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        x.view().inject(this);
        addActivity(this);

        login = getSharedPreferences("Login", MODE_PRIVATE);
        help = getResourseHelp.getResourHelp();

        boolean auto_login = login.getBoolean("AUTO_LOGIN", true);
        if (auto_login){
            String nickname = login.getString("NICKNAME", "xxx");
            tv_nickname.setText(nickname);
        }else{
            String nickname = help.getUserDetail().getNickname();
            System.out.println(help.getUserDetail().toString()+"**********");
            tv_nickname.setText(nickname);
        }


        fragmentManager = getSupportFragmentManager();
        changeFragment(new CategoryFragment());

        custom_toolbar.setTitle("分类练习");
        custom_toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

//        custom_toolbar.inflateMenu(R.menu.toolbar_menu);
//        custom_toolbar.setOnMenuItemClickListener(this);

        setSupportActionBar(custom_toolbar);
        //设置返回键可用
        getSupportActionBar().setHomeButtonEnabled(true);
        //创建返回键，并实现打开/关闭监听
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,custom_drawer,custom_toolbar,R.string.open,R.string.close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                animationDrawable.start();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                animationDrawable.stop();
            }
        };
        actionBarDrawerToggle.syncState();
//        custom_drawer.setDrawerListener(actionBarDrawerToggle);//废弃掉了
        custom_drawer.addDrawerListener(actionBarDrawerToggle);

        list = initListData();
        adapter = new MyAdapter(list);
        lv_cehua.setAdapter(adapter);
        lv_cehua.setOnItemClickListener(this);

    }

    private void addActivity(MainActivity mainActivity) {

        getResourseHelp help = getResourseHelp.getResourHelp();
        help.setActivityList(mainActivity);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){

            if ((System.currentTimeMillis() - curTime) >2000 ){

                curTime = System.currentTimeMillis();
                Toast.makeText(MainActivity.this,"再按一次退出",Toast.LENGTH_SHORT).show();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_toolbar_manu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_search){

            startActivity(new Intent(MainActivity.this, SearchActivity.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        String nickname = login.getString("NICKNAME", "xxx");
        tv_nickname.setText(nickname);
        custom_toolbar.setTitle(R.string.menu_exercise);

    }

    private List<ListItem> initListData() {

        list = new ArrayList<ListItem>();
        list.add(new ListItem("分类练习",R.drawable.home_nav_icon01));
        list.add(new ListItem("题目查找",R.drawable.home_nav_icon02));
        list.add(new ListItem("我的成就",R.drawable.home_nav_icon03));
        list.add(new ListItem("我的收藏",R.drawable.home_nav_icon04));
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        switch (i) {
            case 0:
                changeFragment(new CategoryFragment());
                custom_toolbar.setTitle(R.string.menu_exercise);
                break;
            case 1:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                break;
            case 2:
                custom_toolbar.setTitle(R.string.menu_achievement);
                Toast.makeText(MainActivity.this,"暂时没有，敬请期待",Toast.LENGTH_SHORT).show();
                custom_toolbar.setTitle(R.string.menu_exercise);

                break;
            case 3:

                help.setOpenFavorite(true);
                custom_toolbar.setTitle(R.string.menu_fav);
                Intent it = new Intent(MainActivity.this,QuestionListActivity.class);
                startActivity(it);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                break;
        }
        custom_drawer.closeDrawers();

    }

    private void changeFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();

    }

    @Event(value = {R.id.tv_nickname,R.id.tv_setting,R.id.tv_exit},type = View.OnClickListener.class)
    private void onClick(View view){

        switch (view.getId()){

            case R.id.tv_nickname:

                showDialog(this);

                break;
            case R.id.tv_setting:

                changeFragment(new SettingFragment());
                custom_toolbar.setTitle(R.string.menu_setting);
                custom_drawer.closeDrawers();

                break;
            case R.id.tv_exit:

                List<Activity> activityList = help.getActivityList();
                for (Activity activity:activityList){
                    activity.finish();
                }

                break;


        }

    }
    public void showDialog(Context context){

        View view = LayoutInflater.from(context).inflate(R.layout.nickname_set, null);
        builder = new AlertDialog.Builder(context);
        builder.setView(view);
        dialog = builder.create();

        btn_sure = (Button) view.findViewById(R.id.btn_sure);
        btn_cancle = (Button) view.findViewById(R.id.btn_cancle);
        et_nickname = (EditText) view.findViewById(R.id.et_nickname);
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){

                    case R.id.btn_sure:

                        String s = et_nickname.getText().toString();
                        tv_nickname.setText(s);
                        dialog.dismiss();

                        break;

                }
            }
        });
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()){

                    case R.id.btn_cancle:

                        Toast.makeText(MainActivity.this,"===",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                        break;

                }

            }
        });

        dialog.show();

    }

}
