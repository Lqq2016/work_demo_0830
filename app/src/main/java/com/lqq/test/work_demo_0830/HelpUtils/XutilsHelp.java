package com.lqq.test.work_demo_0830.HelpUtils;

import com.lqq.test.work_demo_0830.Pojo.CategoryListItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lqq on 2016/9/1.
 */
public class XutilsHelp {

    public static final String HTTP_SERVER = "http://115.29.136.118"; //"http://192.168.1.103";
    public static final int PORT = 8080; //8000
    public static final String CONTEXT_ROOT =  "/web-question"; //"/work_demo_0829";

    public static final String URL_IMG_BASE = HTTP_SERVER + ":" + PORT + CONTEXT_ROOT;
    public static final String URL_BASE = HTTP_SERVER + ":" + PORT + CONTEXT_ROOT + "/app";//"/servlet"

    public static final String URL_LOGIN = URL_BASE  + "/login" ;//+ "/myServlet"
    public static final String URL_LOGOUT = URL_BASE + "/logout";
    public static final String URL_REGISTE = URL_BASE + "/registe";
    public static final String URL_CATALOG_LIST = URL_BASE + "/catalog?method=list";
    public static final String URL_QUESTION_LIST = URL_BASE + "/question?method=list";
    public static final String URL_QUESTION_DETAIL = URL_BASE + "/question?method=findone";
    public static final String URL_QUESTION_PREV = URL_BASE + "/question?method=prev";
    public static final String URL_QUESTION_NEXT = URL_BASE + "/question?method=next";
    public static final String URL_MYFAVORITE= URL_BASE + "/mng/store?method=list";
    public static final String URL_STORE_MYFAVORITE= URL_BASE + "/mng/store?method=add";
    public static final String URL_QUESTION_SEARCH = URL_BASE + "/question?method=list";

    private static RequestParams params;
    private static List<CategoryListItem> listItems;
    private static JSONObject json;

    public static List<CategoryListItem> loadMainList(){

        listItems = new ArrayList<CategoryListItem>();
        params = new RequestParams(URL_CATALOG_LIST);
        x.http().get(params, new Callback.CommonCallback<JSONArray>() {
            @Override
            public void onSuccess(JSONArray result) {

//                System.out.println(result.toString());
                for (int i = 0;i<result.length();i++){

                    try {
                        JSONObject json = (JSONObject) result.get(i);
                        CategoryListItem item = new CategoryListItem();
                        item.setId(json.getInt("id"));
                        item.setIcon(json.getString("icon"));
                        item.setName(json.getString("name"));
                        listItems.add(item);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

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

        return listItems;

    }

}
