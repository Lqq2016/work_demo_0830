package com.lqq.test.work_demo_0830.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lqq.test.work_demo_0830.Adapter.QuestionAdapter;
import com.lqq.test.work_demo_0830.GetResouse.getResourseHelp;
import com.lqq.test.work_demo_0830.HelpUtils.XutilsHelp;
import com.lqq.test.work_demo_0830.Pojo.QuestionItemDetail;
import com.lqq.test.work_demo_0830.Pojo.QuestionList;
import com.lqq.test.work_demo_0830.Pojo.QuestionListItem;
import com.lqq.test.work_demo_0830.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ContentView(R.layout.activity_question_list)
public class QuestionListActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewInject(R.id.lv_question)
    private ListView lv_list;
    @ViewInject(R.id.swipe_question)
    private SwipeRefreshLayout swipeRefresh;
    @ViewInject(R.id.custom_toolbar)
    private Toolbar toolbar;

    private List<QuestionList> list,tempList;
    private QuestionAdapter adapter;
    private QuestionListItem listItem;
    private QuestionItemDetail itemDetail;
    private String type;
    private static RequestParams params;
    private JSONArray jsonArray;
    private getResourseHelp resourseHelp;
    private boolean b;
    private SharedPreferences login;
    private int userId;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_question_list);

        x.view().inject(this);

        resourseHelp = getResourseHelp.getResourHelp();
        login = getSharedPreferences("Login", MODE_PRIVATE);
        b = resourseHelp.getOpenFavorite();

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(this);

        adapter = new QuestionAdapter();

        doGetThread();

        swipeRefresh.setColorSchemeResources(R.color.color1,R.color.color2,
                R.color.color3,R.color.color4);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (b){
                    resourseHelp.setOpenFavorite(true);
                }
                doGetThread();
                System.out.println(adapter.getCount()+"----------");
                adapter.notifyDataSetChanged();

            }
        });


    }

    public void doGetThread(){

        new Thread(new Runnable() {
            @Override
            public void run() {


                if (b){
                    list = initData(XutilsHelp.URL_MYFAVORITE);
                }else {
                    list = initData(XutilsHelp.URL_QUESTION_LIST);
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {


                        adapter.setData(list);
                        lv_list.setAdapter(adapter);
//                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent it = new Intent(QuestionListActivity.this,QuestionDetailActivity.class);
                                it.putExtra("QuestionId",i);
                                startActivity(it);

                            }
                        });


                    }
                });

            }
        }).start();

    }

    private List<QuestionList> initData(String urlQuestionList) {

        tempList = new ArrayList<QuestionList>();

//        listItem = XutilsHelp.loadQutesinList(id,1);

        params = new RequestParams(urlQuestionList);
//        System.out.println(params);
        if (b){
            boolean auto_login = login.getBoolean("AUTO_LOGIN", true);
            toolbar.setTitle("我的收藏");
            if (auto_login){
                userId = login.getInt("USERID",2);
            }else{
                userId = resourseHelp.getUserDetail().getId();
            }
            System.out.println(userId+">>>>>>>>>>>>>>>");
            params.addBodyParameter("userId", String.valueOf(userId));
            resourseHelp.setOpenFavorite(false);
        }else {
            Intent intent = getIntent();
            int id = intent.getIntExtra("id", 1);
            String content = intent.getStringExtra("content");
            toolbar.setTitle(content);

            params.addBodyParameter("catalogId", String.valueOf(id));
            params.addBodyParameter("page", String.valueOf(1));
        }
        x.http().post(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {

                try {
                    jsonArray = result.getJSONArray("content");
                    System.out.println(jsonArray+"=================");

                    resourseHelp.setJsonArray(jsonArray);
                    int totalElements = result.getInt("totalElements");
                    int page = result.getInt("page");
                    int totalPages = result.getInt("totalPages");
                    int size = result.getInt("size");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject json = null;
                        try {
                            json = (JSONObject) jsonArray.get(i);
                            String content = json.getString("content");
                            int typeid = json.getInt("typeid");
                            if (typeid == 1) {
                                type = "单选";
                            } else if (typeid == 2) {
                                type = "多选";

                            } else if (typeid == 3) {
                                type = "判断";

                            } else if (typeid == 4) {
                                type = "简答";

                            }
//                            int id1 = json.getInt("id");
//                            String answer = json.getString("answer");
//                            int cataid = json.getInt("cataid");
//                            String options = json.getString("options");
                            long pubTime = json.getLong("pubTime");
//                            System.out.println(pubTime);
                            Date date = new Date(pubTime);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                            String s = sdf.format(date);

                            QuestionList temp = new QuestionList(content, type, s);
                            tempList.add(temp);

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
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

        return tempList;
    }

    @Override
    public void onClick(View view) {

        finish();

    }
}
