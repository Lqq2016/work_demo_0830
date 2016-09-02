package com.lqq.test.work_demo_0830.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lqq.test.work_demo_0830.HelpUtils.XutilsHelp;
import com.lqq.test.work_demo_0830.Pojo.CategoryListItem;
import com.lqq.test.work_demo_0830.Pojo.ListItem;
import com.lqq.test.work_demo_0830.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by lqq on 2016/8/31.
 */
public class GridViewAdapter extends BaseAdapter {

//    List<ListItem> list;
    List<CategoryListItem> list;
    ViewHolder holder;
    String imgUrl;

    public GridViewAdapter(List<CategoryListItem> list){

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
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gridview_item,null);
            holder.img = (ImageView) view.findViewById(R.id.iv_cate_icon);
            holder.tv_content = (TextView) view.findViewById(R.id.tv_cate_title);

        }

        imgUrl = XutilsHelp.URL_IMG_BASE + "/" + list.get(i).getIcon();
        x.image().bind(holder.img,imgUrl);

        holder.tv_content.setText(list.get(i).getName());

        return view;
    }

    class ViewHolder{

        @ViewInject(R.id.img_item)
        private ImageView img;
        @ViewInject(R.id.tv_item)
        private TextView tv_content;

    }



}
