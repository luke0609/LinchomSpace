package linchom.com.linchomspace.shopping.goodsfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.GoodsActivity;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.pojo.GoodsListNewBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;
import linchom.com.linchomspace.shopping.utils.GoodsXUtilsImage;

/**
 * Created by Administrator on 2016/10/16.
 */

public class GoodsListFragment extends Fragment {





    private static final String TAG = "GoodsListFragment";

    private String category_id;

    private int pageCount=1;

    private String orderSort;

    private String sort=null;


    private String keyword;



    View view;
    private PullToRefreshListView ptr_goodsList_ptr;

    private boolean refreshFlag =false;


    private ListView goodsListView;

    private GoodsCommonAdapter<GoodsListNewBean.GoodsMap> goodsCommonAdapter;

    private List<GoodsListNewBean.GoodsMap> goodsList =new ArrayList<GoodsListNewBean.GoodsMap>();

    private int page =1;
    private RelativeLayout rl_goodsList_load_list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.goodslist_fragment, null);

        initView();
        initData();
        initEvent();


        return view;

    }

    private void initView() {

        Bundle bundle = getArguments();

        category_id = bundle.getString("catId");

        orderSort=bundle.getString("order");

        sort=bundle.getString("sort");

        keyword =bundle.getString("keyword");
        if (keyword==null){
            keyword="";

        }


        ptr_goodsList_ptr = ((PullToRefreshListView) view.findViewById(R.id.ptr_goodsList_ptr));
        rl_goodsList_load_list = ((RelativeLayout) view.findViewById(R.id.rl_goodsList_load_list));


    }

    private void initData() {

    }

    private void initEvent() {

        eventPullToRefresh();

        goodsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getActivity(), GoodsActivity.class);
                //1开始
                Bundle bundle =new Bundle();
                bundle.putString("goodsId",goodsList.get(position-1).goods_id);
                intent.putExtra("bundle",bundle);

                startActivity(intent);
            }
        });

    }

    private void getData() {

        if(page==1&&refreshFlag==false){

            rl_goodsList_load_list.setVisibility(View.VISIBLE);


        }



        RequestParams requestParams = new RequestParams(GoodsHttpUtils.NEWGOODSLISTURL);

        requestParams.addBodyParameter("verification", GoodsHttpUtils.VERIFICATION);



        requestParams.addBodyParameter("page", page + "");

        //Keyword

        requestParams.addBodyParameter("keyword", keyword);

       requestParams.addBodyParameter("category_id",category_id+"");

        //order = desc
        //Sort_type

        //sort=shop_price   click_count

        //requestParams.addBodyParameter("sort", "click_count");

        Log.i(TAG,"orderSort"+orderSort);

        requestParams.addBodyParameter("order", orderSort);

        requestParams.addBodyParameter("sort", sort+"");

        Log.i(TAG,category_id+"");


        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {


                if (page == 1) {


                    goodsList.clear();

                }

                List<GoodsListNewBean.GoodsMap> newList = new ArrayList<GoodsListNewBean.GoodsMap>();


                if(page<=pageCount) {


                    Gson gson = new Gson();

                    JsonParser parser = new JsonParser();

                    JsonElement element = parser.parse(result);

                    JsonObject root = element.getAsJsonObject();


                    JsonPrimitive resultjson = root.getAsJsonPrimitive("result");

                    String resultBean = resultjson.getAsString();

                    Log.i(TAG, "resultBean" + resultBean);

                    JsonObject datajson = root.getAsJsonObject("data");

                    String str = datajson.toString();


                    JsonParser parser2 = new JsonParser();

                    JsonElement element2 = parser2.parse(str);

                    JsonObject root2 = element2.getAsJsonObject();

                    JsonPrimitive total_pages = root2.getAsJsonPrimitive("total_pages");

                    pageCount = Integer.parseInt(total_pages.getAsString());


                    JsonObject items = root2.getAsJsonObject("items");


                    Map<String, GoodsListNewBean.GoodsMap> mapNew = gson.fromJson(items, new TypeToken<Map<String, GoodsListNewBean.GoodsMap>>() {
                    }.getType());




                    newList.clear();


                    for (Map.Entry<String, GoodsListNewBean.GoodsMap> m : mapNew.entrySet()) {


                        GoodsListNewBean.GoodsMap goodsInfo = m.getValue();

                        newList.add(goodsInfo);

                    }

                    goodsList.addAll(newList);
                    goodsCommonAdapter.notifyDataSetChanged();


                }else{

                    Toast.makeText(getActivity(),"已经是最后一页了",Toast.LENGTH_SHORT).show();
                    goodsCommonAdapter.notifyDataSetChanged();

                }

                ptr_goodsList_ptr.onRefreshComplete();

                rl_goodsList_load_list.setVisibility(View.GONE);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.i(TAG,ex+"");



            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }


    private void eventPullToRefresh() {

        ptr_goodsList_ptr.setScrollingWhileRefreshingEnabled(true);
        ptr_goodsList_ptr.setMode(PullToRefreshBase.Mode.BOTH);
        ptr_goodsList_ptr.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                //异步任务拿数据

                PullToRefreshBase.Mode mode = ptr_goodsList_ptr.getCurrentMode();

                if(mode == PullToRefreshBase.Mode.PULL_FROM_START){
                    refreshFlag=true;

                    page =1;
                    getData();


                }else if(mode==PullToRefreshBase.Mode.PULL_FROM_END){


                    page++;


                    getData();

                }



            }
        });

        ptr_goodsList_ptr.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                //Toast.makeText(getApplicationContext(), "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        goodsListView=ptr_goodsList_ptr.getRefreshableView();

        //new adapter


        goodsCommonAdapter =new GoodsCommonAdapter<GoodsListNewBean.GoodsMap>(getActivity(),goodsList,R.layout.goodslist_item) {




            @Override
            public void convert(GoodsViewHolder viewHolder, GoodsListNewBean.GoodsMap goods, int position) {


                ImageView iv_goodsList_item_image = ((ImageView) viewHolder.getViewById(R.id.iv_goodsList_item_image));
                TextView tv_goodsList_item_title = ((TextView) viewHolder.getViewById(R.id.tv_goodsList_item_title));

                TextView tv_goodsList_item_price = ((TextView) viewHolder.getViewById(R.id.tv_goodsList_item_price));

                tv_goodsList_item_title.setText(goods.goods_name);
                tv_goodsList_item_price.setText(goods.shop_price);

                //GoodsHttpUtils.IMGURL+goods.goods_thumb

                String imgUrlChange = goods.goods_thumb;

                if("h".equals(imgUrlChange.substring(0,1))){

                }else{
                    imgUrlChange=GoodsHttpUtils.IMGURL+imgUrlChange;
                }



                GoodsXUtilsImage.display(iv_goodsList_item_image,imgUrlChange);


            }
        };




        //setadapter
        goodsListView.setAdapter(goodsCommonAdapter);
        //getdata()更新数据

        getData();




    }


}
