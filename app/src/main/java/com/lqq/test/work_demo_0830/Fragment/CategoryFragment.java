package com.lqq.test.work_demo_0830.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.lqq.test.work_demo_0830.Activities.QuestionListActivity;
import com.lqq.test.work_demo_0830.Adapter.GridViewAdapter;
import com.lqq.test.work_demo_0830.HelpUtils.XutilsHelp;
import com.lqq.test.work_demo_0830.Pojo.CategoryListItem;
import com.lqq.test.work_demo_0830.Pojo.ListItem;
import com.lqq.test.work_demo_0830.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
//@ContentView(R.layout.fragment_category)
public class CategoryFragment extends Fragment implements AdapterView.OnItemClickListener {

//    @ViewInject(R.id.grid_view)
    private GridView gridView;

    private GridViewAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private List<CategoryListItem> listItems,listItemList;
    int i = 0;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
//        x.view().inject(view);

        findView(view);

        loadData();
        adapter = new GridViewAdapter(listItems);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);


        return view;
    }

    private void findView(View view) {

        gridView = (GridView) view.findViewById(R.id.grid_view);
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.color1,R.color.color2,
                R.color.color3,R.color.color4);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i++;
//                listItems.clear();
//                listItemList = loadData();
//                for (CategoryListItem i :
//                        listItemList) {
//                    listItems.add(i);
//                    System.out.println(i.toString());
//                }

//                adapter = new GridViewAdapter(listItems);
//                gridView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(),i+"",Toast.LENGTH_SHORT).show();
                swipeRefresh.setRefreshing(false);
            }
        });

    }

    private List<CategoryListItem> loadData() {

        listItems = XutilsHelp.loadMainList();
        return listItems;

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(adapterView.getContext(),i+"",Toast.LENGTH_SHORT).show();
        Intent it = new Intent(getContext(), QuestionListActivity.class);
        it.putExtra("id",listItems.get(i).getId());
        it.putExtra("content",listItems.get(i).getName());
        startActivity(it);
        getActivity().overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

    }
}
