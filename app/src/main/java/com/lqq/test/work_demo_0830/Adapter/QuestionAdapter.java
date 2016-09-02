package com.lqq.test.work_demo_0830.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lqq.test.work_demo_0830.Pojo.QuestionList;
import com.lqq.test.work_demo_0830.Pojo.QuestionListItem;
import com.lqq.test.work_demo_0830.R;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by lqq on 2016/8/31.
 */
public class QuestionAdapter extends BaseAdapter {

    List<QuestionList> list;
    ViewHolder holder;

    public QuestionAdapter(){}

//    public QuestionAdapter(List<QuestionList> list){
//
//        this.list = list;
//
//    }

    public void setData(List<QuestionList> list){

        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){

            holder = new ViewHolder();
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.question_list_item,null);
            holder.tv_title = (TextView) view.findViewById(R.id.tv_question_title);
            holder.type = (TextView) view.findViewById(R.id.tv_type);
            holder.tv_time = (TextView) view.findViewById(R.id.tv_pubTime);

        }

        holder.tv_title.setText(list.get(i).getContent());
        holder.type.setText(list.get(i).getType());
        holder.tv_time.setText(list.get(i).getDate());

        return view;
    }

    class ViewHolder{

        @ViewInject(R.id.tv_question_title)
        private TextView tv_title;
        @ViewInject(R.id.tv_type)
        private TextView type;
        @ViewInject(R.id.tv_pubTime)
        private TextView tv_time;

    }

}
