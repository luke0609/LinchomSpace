package linchom.com.linchomspace.shopping.goodsfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.GoodsActivity;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.PuBuAdapter;
import linchom.com.linchomspace.shopping.pojo.GoodsListNewBean;
import linchom.com.linchomspace.shopping.widget.PullToRefreshStaggeredGridView;


@SuppressLint("NewApi")
public class GoodsListWaterfallFragment extends Fragment implements PullToRefreshBase.OnRefreshListener<StaggeredGridView>{

    private static final String TAG = "GoodsListWaterfallFragment";
    private StaggeredGridView staggeredGridView;

    private PullToRefreshStaggeredGridView mPullToRefreshStaggerdGridView;

    private boolean refreshFlag =false;

    private   int  page = 1;


    private int category_id;

    private String orderSort=null;

    private String sort=null;



    private int pageCount=1;

    List<GoodsListNewBean.GoodsMap> goodsList = new ArrayList<GoodsListNewBean.GoodsMap>();

    PuBuAdapter puBuAdapter;


    View view;
    private RelativeLayout rl_goodsList_load_listpubu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.goodslistwaterfall_fragment, null);



        initView();
        initData();
        initEvent();



        return view;


    }

    @Override
    public void onRefresh(PullToRefreshBase<StaggeredGridView> refreshView) {


        PullToRefreshBase.Mode mode = mPullToRefreshStaggerdGridView.getCurrentMode();

        // View viewRefresh = null;

        if(mode == PullToRefreshBase.Mode.PULL_FROM_START){

            refreshFlag=true;

            page =1;
            getData();


        }else if(mode == PullToRefreshBase.Mode.PULL_FROM_END){

            page++;
            getData();


        }

    }

    private void initView() {

        Bundle bundle = getArguments();

        category_id = bundle.getInt("catId");

        orderSort =bundle.getString("order");

        sort =bundle.getString("sort");

        System.out.println("orderSort"+orderSort);
        System.out.println("sort"+sort);






        // introType = bundle.getString("introType");

        mPullToRefreshStaggerdGridView = (PullToRefreshStaggeredGridView) view.findViewById(R.id.pull_grid_view);
        rl_goodsList_load_listpubu = ((RelativeLayout) view.findViewById(R.id.rl_goodsList_load_listpubu));

    }

    private void initData() {

    }

    private void initEvent() {

        staggeredGridViewEvent();



    }

    private void staggeredGridViewEvent() {

        mPullToRefreshStaggerdGridView.setMode(PullToRefreshBase.Mode.BOTH);

        mPullToRefreshStaggerdGridView.setScrollingWhileRefreshingEnabled(true);


        mPullToRefreshStaggerdGridView.setOnRefreshListener(this);

        staggeredGridView = mPullToRefreshStaggerdGridView.getRefreshableView();

        puBuAdapter = new PuBuAdapter(getActivity(),goodsList);

        staggeredGridView.setAdapter(puBuAdapter);

        getData();

        staggeredGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //0开始
                Intent intent =new Intent(getActivity(), GoodsActivity.class);

                Bundle bundle =new Bundle();
                bundle.putString("goodsId",goodsList.get(position).goods_id);
                intent.putExtra("bundle",bundle);

                startActivity(intent);




            }
        });


    }

    private void getData() {

        if(page==1&&refreshFlag==false){

            rl_goodsList_load_listpubu.setVisibility(View.VISIBLE);


        }

        RequestParams requestParams = new RequestParams(GoodsHttpUtils.NEWGOODSLISTURL);

        requestParams.addBodyParameter("verification", GoodsHttpUtils.VERIFICATION);

        requestParams.addBodyParameter("page", page + "");

        requestParams.addBodyParameter("category_id", category_id + "");
        requestParams.addBodyParameter("order", orderSort + "");
        requestParams.addBodyParameter("sort", sort + "");


        System.out.println("orderSort"+orderSort);
        System.out.println("sort"+sort);


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
                    puBuAdapter.notifyDataSetChanged();


                }else{

                    Toast.makeText(getActivity(),"已经是最后一页了",Toast.LENGTH_SHORT).show();
                    puBuAdapter.notifyDataSetChanged();

                }



                mPullToRefreshStaggerdGridView.onRefreshComplete();


                rl_goodsList_load_listpubu.setVisibility(View.GONE);




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
