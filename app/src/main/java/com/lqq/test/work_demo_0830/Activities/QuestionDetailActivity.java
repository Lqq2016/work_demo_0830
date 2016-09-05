package com.lqq.test.work_demo_0830.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.lqq.test.work_demo_0830.GetResouse.getResourseHelp;
import com.lqq.test.work_demo_0830.Pojo.QuestionItemDetail;
import com.lqq.test.work_demo_0830.Pojo.QuestionList;
import com.lqq.test.work_demo_0830.Pojo.QuestionListItem;
import com.lqq.test.work_demo_0830.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ContentView(R.layout.activity_question_detail)
public class QuestionDetailActivity extends AppCompatActivity {

    @ViewInject(R.id.custom_toolbar)
    private Toolbar toolbar;
    @ViewInject(R.id.tv_question_detail_title)
    private TextView tv_title;
    @ViewInject(R.id.question_ask)
    private TextView question_ask;
    @ViewInject(R.id.quest_item_container)
    private LinearLayout quest_item_container;
    @ViewInject(R.id.sv_ask)
    private ScrollView sv_ask;
    @ViewInject(R.id.question_toolbar_prev)
    private TextView question_toolbar_prev;// 上一题
    @ViewInject(R.id.question_toolbar_favorite)
    private TextView question_toolbar_favorite; // 收藏
    @ViewInject(R.id.question_toolbar_next)
    private TextView question_toolbar_next;// 下一题

    Intent intent;
    private int questionId;
    private String stringExtra;
    private List<QuestionItemDetail> detailList;
    private QuestionItemDetail itemDetail;
    private JSONArray items;
    private String title;
    private boolean checked;
    private getResourseHelp help;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_question_detail);

        x.view().inject(this);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        intent = getIntent();
        questionId = intent.getIntExtra("QuestionId",0);
        getQuestionList();
        getCurrentQuestion(questionId);


//        tv_title.setText(question.getTitle());


    }

    @Event(value = {R.id.question_toolbar_prev,R.id.question_toolbar_favorite,R.id.question_toolbar_next},
    type = View.OnClickListener.class)
    private void onClick(View view){

        int size = detailList.size();

        switch (view.getId()){

            case R.id.question_toolbar_prev:

                if (questionId==0){
                    Toast.makeText(this,"没有上一题了",Toast.LENGTH_SHORT).show();
                }else {
                    getCurrentQuestion(--questionId);
                }

                break;
            case R.id.question_toolbar_favorite:


                break;
            case R.id.question_toolbar_next:

                if (questionId==size-1){
                    Toast.makeText(this,"没有下一题了",Toast.LENGTH_SHORT).show();
                }else {
                    getCurrentQuestion(++questionId);
                }

                break;

        }

    }

    private void getQuestionList() {

        detailList = new ArrayList<QuestionItemDetail>();
        help = getResourseHelp.getResourHelp();
        JSONArray jsonArray = help.getJsonArray();
//        System.out.println(jsonArray.toString()+"+++++++++++++++++");

        for (int i = 0; i < jsonArray.length(); i++) {

//            System.out.println(jsonArray.length()+String.valueOf(i)+"@@@@@@@@@");
            JSONObject json = null;
            try {
                json = (JSONObject) jsonArray.get(i);
                String content = json.getString("content");
                int typeid = json.getInt("typeid");
                int id = json.getInt("id");
                String answer = json.getString("answer");
                int cataid = json.getInt("cataid");
                String options = "";
                if (typeid==1||typeid==2){
                    options = json.getString("options");
                }
                long pubTime = json.getLong("pubTime");
//                System.out.println(pubTime);
                Date date = new Date(pubTime);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                String s = sdf.format(date);

                QuestionItemDetail detail = new QuestionItemDetail(content,
                        id,pubTime,typeid,answer,cataid,options);
                detailList.add(detail);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }

        num = detailList.size();


    }

    private void getCurrentQuestion(int id) {

        toolbar.setTitle("第"+(id+1)+"/"+num+"道题");

        itemDetail = detailList.get(id);
        tv_title.setText(itemDetail.getContent());
        if(itemDetail.getTypeid() == 3 || itemDetail.getTypeid() == 4){
            question_ask.setText(itemDetail.getAnswer());
            sv_ask.setVisibility(View.VISIBLE);
            quest_item_container.setVisibility(View.GONE);

        }else{ //选择题

            quest_item_container.removeAllViews();
            try {
                items = new JSONArray(itemDetail.getOptions());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for(int i = 0; i < items.length(); i++){
                JSONObject item = null;
                try {
                    item = (JSONObject) items.get(i);
                    title = item.getString("title");
                    checked = item.getBoolean("checked");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                CheckBox cbx = new CheckBox(QuestionDetailActivity.this);
                cbx.setText((i + 1) + title);
                if(checked){
                    cbx.setChecked(true);
                }else{
                    cbx.setChecked(false);
                }

                quest_item_container.addView(cbx);
            }

            sv_ask.setVisibility(View.GONE);
            quest_item_container.setVisibility(View.VISIBLE);
        }

    }



}
