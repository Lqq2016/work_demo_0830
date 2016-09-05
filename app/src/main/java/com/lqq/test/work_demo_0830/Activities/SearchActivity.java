package com.lqq.test.work_demo_0830.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lqq.test.work_demo_0830.Adapter.QuestionAdapter;
import com.lqq.test.work_demo_0830.GetResouse.getResourseHelp;
import com.lqq.test.work_demo_0830.HelpUtils.XutilsHelp;
import com.lqq.test.work_demo_0830.Pojo.QuestionList;
import com.lqq.test.work_demo_0830.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ContentView(R.layout.activity_search)
public class SearchActivity extends AppCompatActivity {

    @ViewInject(R.id.et_search)
    private EditText et_search;
    @ViewInject(R.id.tv_search_btn)
    private TextView tv_search;
    @ViewInject(R.id.lv_search)
    private ListView lv_search;
    @ViewInject(R.id.custom_toolbar)
    private Toolbar custom_toolbar;

    private String type,search;
    private JSONArray jsonArray;
    private getResourseHelp resourseHelp;
    private List<QuestionList> tempList;
    private Handler handler;
    private QuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search);

        x.view().inject(this);

        resourseHelp = getResourseHelp.getResourHelp();
        custom_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        custom_toolbar.setTitle("题目查找");
        custom_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

    }

    @Event(value = {R.id.tv_search_btn,R.id.home},type = View.OnClickListener.class)
    private void onClick(View view){

        switch (view.getId()){

            case R.id.tv_search_btn:

                search = et_search.getText().toString();
                doSearch();

                break;

        }

    }

    private void doSearch(){

        RequestParams params = new RequestParams(XutilsHelp.URL_QUESTION_SEARCH);
        params.addBodyParameter("questionName",search);
        tempList = new ArrayList<QuestionList>();
        handler = new Handler();

        x.http().post(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {

                try {
                    jsonArray = result.getJSONArray("content");
                    System.out.println(jsonArray+"=================");

                    resourseHelp.setJsonArray(jsonArray);
//                    int totalElements = result.getInt("totalElements");
//                    int page = result.getInt("page");
//                    int totalPages = result.getInt("totalPages");
//                    int size = result.getInt("size");

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

                    adapter = new QuestionAdapter();
                    adapter.setData(tempList);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            lv_search.setAdapter(adapter);
                            lv_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    Intent it = new Intent(SearchActivity.this,QuestionDetailActivity.class);
                                    it.putExtra("QuestionId",i);
                                    startActivity(it);

                                }
                            });

                        }
                    });

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

}
