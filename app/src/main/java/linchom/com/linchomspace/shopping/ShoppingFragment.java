package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.Indicator;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.GoodsSqlite.GoodsHistorySqLiteOpenHelper;
import linchom.com.linchomspace.shopping.contant.GoodsHttpUtils;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.goodsadapter.MyGoodsIndicatorAdapter;
import linchom.com.linchomspace.shopping.pojo.GoodsAdvDataBean;
import linchom.com.linchomspace.shopping.pojo.GoodsAdvNewBean;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;


public class ShoppingFragment extends Fragment implements View.OnClickListener{




    private Map<String ,String> advMap =new HashMap<String ,String>();

    private List<GoodsAdvDataBean> getAdvList = new ArrayList<GoodsAdvDataBean>();

    //private List<String> tempList =new ArrayList<String>();


    private static final String TAG = "ShoppingFragment";
    View view;


    private ViewPager vp_banner_goods;
    private Indicator indicator_banner_goods;

    private BannerComponent bannerComponent;

    private MyGoodsIndicatorAdapter myGoodsIndicatorAdapter;

    private Button btn_goods_one_one;
    private Button btn_goods_one_two;
    private Button btn_goods_one_three;
    private Button btn_goods_one_four;
    private Button btn_goods_one_five;
    private Button btn_goods_one_six;
    private Button btn_goods_one_seven;
    private Button btn_goods_one_eight;
    private Button btn_goods_one_nine;
    private Button btn_goods_two_one;
    private Button btn_goods_two_two;
    private Button btn_goods_two_three;
    private Button btn_goods_two_four;
    private Button btn_goods_two_five;
    private Button btn_goods_two_six;
    private Button btn_goods_two_seven;
    private Button btn_goods_two_eight;
    private Button btn_goods_two_nine;
    private Button btn_goods_two_ten;
    private Button btn_goods_two_eleven;
    private Button btn_goods_two_twelve;
    private Button btn_goods_two_thirteen;
    private Button btn_goods_two_fourteen;
    private Button btn_goods_two_fifteen;

    private Button btn_goods_three_one;
    private Button btn_goods_three_two;
    private Button btn_goods_three_three;
    private Button btn_goods_three_four;
    private Button btn_goods_three_five;
    private Button btn_goods_three_six;

    private Button btn_goods_four_one;
    private Button btn_goods_four_two;
    private Button btn_goods_four_three;
    private Button btn_goods_four_four;
    private Button btn_goods_four_five;
    private Button btn_goods_four_six;
    private Button btn_goods_four_seven;
    private Button btn_goods_four_eight;

    private Button btn_goods_five_one;
    private Button btn_goods_five_two;
    private Button btn_goods_five_three;
    private Button btn_goods_five_four;
    private Button btn_goods_five_five;
    private Button btn_goods_five_six;
    private Button btn_goods_five_seven;
    private Button btn_goods_five_eight;

    private Button btn_goods_six_one;
    private Button btn_goods_six_two;
    private Button btn_goods_six_three;
    private Button btn_goods_six_four;
    private Button btn_goods_six_five;
    private Button btn_goods_six_six;
    private Button btn_goods_six_seven;
    private Button btn_goods_six_eight;
    private Button btn_goods_six_nine;
    private Button btn_goods_six_ten;

    private RelativeLayout rl_goods_first;
    private RelativeLayout rl_goods_second;
    private RelativeLayout rl_goods_third;
    private RelativeLayout rl_goods_fourth;
    private RelativeLayout rl_goods_fifth;
    private RelativeLayout rl_goods_sixth;
    private RelativeLayout rl_goodsHome_load;
    private Button btn_goods_btnSearch;
   // private EditText et_goods_cate_search;
    private TextView et_goods_cate_search;
    private LinearLayout linear;
    private ListView lv_goods_search_history;
    private EditText et_goods_search_history;

    private List<String> historyList = new ArrayList<String>();

    private GoodsCommonAdapter<String> goodsCommonAdapter;
    private Button btn_goods_search_history;
    private ImageView iv_search_history_delete;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.fragment_shopping,container, false);



        initView();
        initData();
        initEvent();

        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        bannerComponent.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        bannerComponent.stopAutoPlay();
    }

    private void initView() {
        vp_banner_goods = ((ViewPager) view.findViewById(R.id.vp_banner_goods));
        indicator_banner_goods = ((Indicator) view.findViewById(R.id.indicator_banner_goods));

        btn_goods_one_one = ((Button) view.findViewById(R.id.btn_goods_one_one));
        btn_goods_one_two = ((Button) view.findViewById(R.id.btn_goods_one_two));
        btn_goods_one_three = ((Button) view.findViewById(R.id.btn_goods_one_three));
        btn_goods_one_four = ((Button) view.findViewById(R.id.btn_goods_one_four));
        btn_goods_one_five = ((Button) view.findViewById(R.id.btn_goods_one_five));
        btn_goods_one_six = ((Button) view.findViewById(R.id.btn_goods_one_six));
        btn_goods_one_seven = ((Button) view.findViewById(R.id.btn_goods_one_seven));
        btn_goods_one_eight = ((Button) view.findViewById(R.id.btn_goods_one_eight));
        btn_goods_one_nine = ((Button) view.findViewById(R.id.btn_goods_one_nine));

        btn_goods_two_one = ((Button) view.findViewById(R.id.btn_goods_two_one));
        btn_goods_two_two = ((Button) view.findViewById(R.id.btn_goods_two_two));
        btn_goods_two_three = ((Button) view.findViewById(R.id.btn_goods_two_three));
        btn_goods_two_four = ((Button) view.findViewById(R.id.btn_goods_two_four));
        btn_goods_two_five = ((Button) view.findViewById(R.id.btn_goods_two_five));
        btn_goods_two_six = ((Button) view.findViewById(R.id.btn_goods_two_six));
        btn_goods_two_seven = ((Button) view.findViewById(R.id.btn_goods_two_seven));
        btn_goods_two_eight = ((Button) view.findViewById(R.id.btn_goods_two_eight));
        btn_goods_two_nine = ((Button) view.findViewById(R.id.btn_goods_two_nine));
        btn_goods_two_ten = ((Button) view.findViewById(R.id.btn_goods_two_ten));
        btn_goods_two_eleven = ((Button) view.findViewById(R.id.btn_goods_two_eleven));
        btn_goods_two_twelve = ((Button) view.findViewById(R.id.btn_goods_two_twelve));
        btn_goods_two_thirteen = ((Button) view.findViewById(R.id.btn_goods_two_thirteen));
        btn_goods_two_fourteen = ((Button) view.findViewById(R.id.btn_goods_two_fourteen));
        btn_goods_two_fifteen = ((Button) view.findViewById(R.id.btn_goods_two_fifteen));

        btn_goods_three_one = ((Button) view.findViewById(R.id.btn_goods_three_one));
        btn_goods_three_two = ((Button) view.findViewById(R.id.btn_goods_three_two));
        btn_goods_three_three = ((Button) view.findViewById(R.id.btn_goods_three_three));
        btn_goods_three_four = ((Button) view.findViewById(R.id.btn_goods_three_four));
        btn_goods_three_five = ((Button) view.findViewById(R.id.btn_goods_three_five));
        btn_goods_three_six = ((Button) view.findViewById(R.id.btn_goods_three_six));

        btn_goods_four_one = ((Button) view.findViewById(R.id.btn_goods_four_one));
        btn_goods_four_two = ((Button) view.findViewById(R.id.btn_goods_four_two));
        btn_goods_four_three = ((Button) view.findViewById(R.id.btn_goods_four_three));
        btn_goods_four_four = ((Button) view.findViewById(R.id.btn_goods_four_four));
        btn_goods_four_five = ((Button) view.findViewById(R.id.btn_goods_four_five));
        btn_goods_four_six = ((Button) view.findViewById(R.id.btn_goods_four_six));
        btn_goods_four_seven = ((Button) view.findViewById(R.id.btn_goods_four_seven));
        btn_goods_four_eight = ((Button) view.findViewById(R.id.btn_goods_four_eight));

        btn_goods_five_one = ((Button) view.findViewById(R.id.btn_goods_five_one));
        btn_goods_five_two = ((Button) view.findViewById(R.id.btn_goods_five_two));
        btn_goods_five_three = ((Button) view.findViewById(R.id.btn_goods_five_three));
        btn_goods_five_four = ((Button) view.findViewById(R.id.btn_goods_five_four));
        btn_goods_five_five = ((Button) view.findViewById(R.id.btn_goods_five_five));
        btn_goods_five_six = ((Button) view.findViewById(R.id.btn_goods_five_six));
        btn_goods_five_seven = ((Button) view.findViewById(R.id.btn_goods_five_seven));
        btn_goods_five_eight = ((Button) view.findViewById(R.id.btn_goods_five_eight));

        btn_goods_six_one = ((Button) view.findViewById(R.id.btn_goods_six_one));
        btn_goods_six_two = ((Button) view.findViewById(R.id.btn_goods_six_two));
        btn_goods_six_three = ((Button) view.findViewById(R.id.btn_goods_six_three));
        btn_goods_six_four = ((Button) view.findViewById(R.id.btn_goods_six_four));
        btn_goods_six_five = ((Button) view.findViewById(R.id.btn_goods_six_five));
        btn_goods_six_six = ((Button) view.findViewById(R.id.btn_goods_six_six));
        btn_goods_six_seven = ((Button) view.findViewById(R.id.btn_goods_six_seven));
        btn_goods_six_eight = ((Button) view.findViewById(R.id.btn_goods_six_eight));
        btn_goods_six_nine = ((Button) view.findViewById(R.id.btn_goods_six_nine));
        btn_goods_six_ten = ((Button) view.findViewById(R.id.btn_goods_six_ten));

        rl_goods_first = ((RelativeLayout) view.findViewById(R.id.rl_goods_first));
        rl_goods_second = ((RelativeLayout) view.findViewById(R.id.rl_goods_second));
        rl_goods_third = ((RelativeLayout) view.findViewById(R.id.rl_goods_third));
        rl_goods_fourth = ((RelativeLayout) view.findViewById(R.id.rl_goods_fourth));
        rl_goods_fifth = ((RelativeLayout) view.findViewById(R.id.rl_goods_fifth));
        rl_goods_sixth = ((RelativeLayout) view.findViewById(R.id.rl_goods_sixth));

        rl_goodsHome_load = ((RelativeLayout) view.findViewById(R.id.rl_goodsHome_load));

        btn_goods_btnSearch = ((Button) view.findViewById(R.id.btn_goods_btnSearch));

       // et_goods_cate_search = ((EditText) view.findViewById(R.id.et_goods_cate_search));

        et_goods_cate_search = ((TextView) view.findViewById(R.id.et_goods_cate_search));

        linear = ((LinearLayout) view.findViewById(R.id.linear));


    }

    private void initData() {
        rl_goodsHome_load.setVisibility(View.VISIBLE);
        initAdvList();

    }

    private void initEvent() {

        setButtonClick();
        advLoopPlay();

       et_goods_cate_search.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               toPopUpWindow(v);



           }
       });




    }

    private void toPopUpWindow(View view){
        View contentView = LayoutInflater.from(getActivity()).inflate(
                R.layout.goods_search_history_pop, null);

        toSqlite();

        goodsCommonAdapter= new GoodsCommonAdapter<String>(getActivity(),historyList,R.layout.goods_history_list_item) {
            @Override
            public void convert(GoodsViewHolder viewHolder, String s, int position) {

               TextView tv_goods_history_item= viewHolder.getViewById(R.id.tv_goods_history_item);

                tv_goods_history_item.setText(s);

            }
        };

        lv_goods_search_history = ((ListView) contentView.findViewById(R.id.lv_goods_search_history));

        et_goods_search_history = ((EditText) contentView.findViewById(R.id.et_goods_search_history));

        btn_goods_search_history = ((Button) contentView.findViewById(R.id.btn_goods_search_history));

        iv_search_history_delete = ((ImageView) contentView.findViewById(R.id.iv_search_history_delete));


        lv_goods_search_history.setAdapter(goodsCommonAdapter);


        final PopupWindow popupWindow = new PopupWindow(contentView,
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);


        iv_search_history_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toDeleteSqlite();

                historyList.clear();

                goodsCommonAdapter.notifyDataSetChanged();




            }
        });

        btn_goods_search_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //写入数据库
                //页面跳转
                //获取edittext数据

               // Log.i(TAG,"et_goods_search_history"+et_goods_search_history.getText().toString());


                if(et_goods_search_history.getText().toString()!=""
                        ||et_goods_search_history.getText().toString()!=null
                        ||et_goods_search_history.getText().toString().length()==0){

                    toInsertSqlite(et_goods_search_history.getText().toString().trim());


                    Intent intent =new Intent(getActivity(), GoodsListActivity.class);

                    Bundle bundle =new Bundle();
                    bundle.putString("keyword",et_goods_search_history.getText().toString().trim()+"");

                    bundle.putString("cateId","");

                    intent.putExtra("bundle",bundle);

                    popupWindow.dismiss();

                    toSqlite();

                    goodsCommonAdapter.notifyDataSetChanged();

                    startActivity(intent);

                }else{

                    Toast.makeText(getActivity(),"请输入",Toast.LENGTH_SHORT).show();

                }

            }
        });

        lv_goods_search_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // Toast.makeText(getActivity(),historyList.get(position)+"",Toast.LENGTH_SHORT).show();

                et_goods_search_history.setText(historyList.get(position));


            }
        });



        popupWindow.setTouchable(true);

        popupWindow.setFocusable(true);

        popupWindow.setOutsideTouchable(true);



        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.goods_default_city));


        popupWindow.showAtLocation(view,0,0,0);


    }

    private void toInsertSqlite(String content){

        boolean insertFlag =false;

        GoodsHistorySqLiteOpenHelper goodsHistorySqLiteOpenHelper =  new GoodsHistorySqLiteOpenHelper(getActivity());


        SQLiteDatabase db =  goodsHistorySqLiteOpenHelper.getWritableDatabase();

        for (int i=0 ;i<historyList.size();i++){

            if(historyList.get(i).equals(content)){

                insertFlag = true;
            }

        }

        if(insertFlag == false){
            db.execSQL("insert into goodshistory(goodsname) values('"+content+"')");



        }

        db.close();


    }


    private void toDeleteSqlite(){


        GoodsHistorySqLiteOpenHelper goodsHistorySqLiteOpenHelper =  new GoodsHistorySqLiteOpenHelper(getActivity());


        SQLiteDatabase db =  goodsHistorySqLiteOpenHelper.getWritableDatabase();

        db.execSQL("delete from goodshistory");

        db.close();


    }

    private void toSqlite() {

        GoodsHistorySqLiteOpenHelper goodsHistorySqLiteOpenHelper =  new GoodsHistorySqLiteOpenHelper(getActivity());


       SQLiteDatabase db =  goodsHistorySqLiteOpenHelper.getWritableDatabase();


        historyList.clear();

        Cursor cursor =db.query("goodshistory",null,null,null,null,null,"_id desc");

        while(cursor.moveToNext()){


            historyList.add(cursor.getString(cursor.getColumnIndex("goodsname")));

        }

        db.close();





    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_goods_one_one:
                toGoodsIntent("54");

                break;
            case R.id.btn_goods_one_two:
                toGoodsIntent("56");
                break;
            case R.id.btn_goods_one_three:
                toGoodsIntent("55");
                break;
            case R.id.btn_goods_one_four:
                toGoodsIntent("57");
                break;
            case R.id.btn_goods_one_five:
                toGoodsIntent("60");
                break;
            case R.id.btn_goods_one_six:
                toGoodsIntent("61");
                break;
            case R.id.btn_goods_one_seven:
                toGoodsIntent("82");
                break;
            case R.id.btn_goods_one_eight:
                toGoodsIntent("85");
                break;
            case R.id.btn_goods_one_nine:
                toGoodsIntent("87");
                break;


            case R.id.btn_goods_two_one:
                toGoodsIntent("150");
                break;
            case R.id.btn_goods_two_two:
                toGoodsIntent("152");
                break;
            case R.id.btn_goods_two_three:
                toGoodsIntent("151");
                break;
            case R.id.btn_goods_two_four:
                toGoodsIntent("86");
                break;
            case R.id.btn_goods_two_five:
                toGoodsIntent("153");
                break;
            case R.id.btn_goods_two_six:
                toGoodsIntent("58");
                break;
            case R.id.btn_goods_two_seven:
                toGoodsIntent("170");
                break;
            case R.id.btn_goods_two_eight:
                toGoodsIntent("157");
                break;
            case R.id.btn_goods_two_nine:
                toGoodsIntent("156");
                break;
            case R.id.btn_goods_two_ten:
                toGoodsIntent("154");
                break;
            case R.id.btn_goods_two_eleven:
                toGoodsIntent("155");
                break;
            case R.id.btn_goods_two_twelve:
                toGoodsIntent("158");
                break;
            case R.id.btn_goods_two_thirteen:
                toGoodsIntent("159");
                break;
            case R.id.btn_goods_two_fourteen:
                toGoodsIntent("160");
                break;
            case R.id.btn_goods_two_fifteen:
                toGoodsIntent("161");
                break;

            case R.id.btn_goods_three_one:
                toGoodsIntent("76");
                break;
            case R.id.btn_goods_three_two:
                toGoodsIntent("75");
                break;
            case R.id.btn_goods_three_three:
                toGoodsIntent("71");
                break;
            case R.id.btn_goods_three_four:
                toGoodsIntent("67");
                break;
            case R.id.btn_goods_three_five:
                toGoodsIntent("93");
                break;
            case R.id.btn_goods_three_six:
                toGoodsIntent("89");
                break;

            case R.id.btn_goods_four_one:
                toGoodsIntent("162");
                break;
            case R.id.btn_goods_four_two:
                toGoodsIntent("77");
                break;
            case R.id.btn_goods_four_three:
                toGoodsIntent("64");
                break;
            case R.id.btn_goods_four_four:
                toGoodsIntent("163");
                break;
            case R.id.btn_goods_four_five:
                toGoodsIntent("63");
                break;
            case R.id.btn_goods_four_six:
                toGoodsIntent("68");
                break;
            case R.id.btn_goods_four_seven:
                toGoodsIntent("72");
                break;
            case R.id.btn_goods_four_eight:
                toGoodsIntent("90");
                break;

            case R.id.btn_goods_five_one:
                toGoodsIntent("51");
                break;
            case R.id.btn_goods_five_two:
                toGoodsIntent("52");
                break;
            case R.id.btn_goods_five_three:
                toGoodsIntent("78");
                break;
            case R.id.btn_goods_five_four:
                toGoodsIntent("92");
                break;
            case R.id.btn_goods_five_five:
                toGoodsIntent("164");
                break;
            case R.id.btn_goods_five_six:
                toGoodsIntent("79");
                break;
            case R.id.btn_goods_five_seven:
                toGoodsIntent("91");
                break;
            case R.id.btn_goods_five_eight:
                toGoodsIntent("88");
                break;

            case R.id.btn_goods_six_one:
                toGoodsIntent("70");
                break;
            case R.id.btn_goods_six_two:
                toGoodsIntent("69");
                break;
            case R.id.btn_goods_six_three:
                toGoodsIntent("165");
                break;
            case R.id.btn_goods_six_four:
                toGoodsIntent("166");
                break;
            case R.id.btn_goods_six_five:
                toGoodsIntent("167");
                break;
            case R.id.btn_goods_six_six:
                toGoodsIntent("73");
                break;
            case R.id.btn_goods_six_seven:
                toGoodsIntent("168");
                break;
            case R.id.btn_goods_six_eight:
                toGoodsIntent("84");
                break;
            case R.id.btn_goods_six_nine:
                toGoodsIntent("169");
                break;
            case R.id.btn_goods_six_ten:
                toGoodsIntent("44");
                break;

            case R.id.rl_goods_first:
                toGoodsIntent("6");
                break;
            case R.id.rl_goods_second:
                toGoodsIntent("149");
                break;
            case R.id.rl_goods_third:
                toGoodsIntent("12");
                break;
            case R.id.rl_goods_fourth:
                toGoodsIntent("17");
                break;
            case R.id.rl_goods_fifth:
                toGoodsIntent("16");
                break;
            case R.id.rl_goods_sixth:
                toGoodsIntent("20");
                break;

            case R.id.btn_goods_btnSearch:
                //toGoodsSearch();

                break;




        }



    }

    private void toGoodsSearch() {

        Intent intent =new Intent(getActivity(), GoodsListActivity.class);

        Bundle bundle =new Bundle();
        bundle.putString("keyword",et_goods_search_history.getText().toString().trim()+"");

        bundle.putString("cateId","");

        intent.putExtra("bundle",bundle);

        startActivity(intent);




    }

   /* private void toGoodsSearch() {
        Intent intent =new Intent(getActivity(), SearchActivity.class);

        Bundle bundle =new Bundle();
        bundle.putString("search_type","goods");
        intent.putExtra("bundle",bundle);

        startActivity(intent);


    }*/

    public void toGoodsIntent(String cateId){

        Intent intent = new Intent(getActivity(),GoodsListActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("cateId",cateId);
        intent.putExtra("bundle",bundle);

        startActivity(intent);



    }

    private void initAdvList() {


        getAdvList.add(new GoodsAdvDataBean("1","1","1","1","1","1","1","1","1"));
        getAdvList.add(new GoodsAdvDataBean("1","1","1","1","1","1","1","1","1"));

        //http://app.linchom.com/appapi.php?act=ads&position_id=3



        RequestParams requestParams =new RequestParams(GoodsHttpUtils.SHOPURL);

        requestParams.addBodyParameter("act","ads");

        requestParams.addBodyParameter("position_id","3");


        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.i(TAG,"result"+result);

                getAdvList.clear();

                Gson gson =new Gson();

                GoodsAdvNewBean goodsAdvNewBean = gson.fromJson(result,GoodsAdvNewBean.class);

                getAdvList.addAll(goodsAdvNewBean.data);

                rl_goodsHome_load.setVisibility(View.GONE);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_goodsHome_load.setVisibility(View.GONE);


            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

    private void advLoopPlay(){

        bannerComponent=new BannerComponent(indicator_banner_goods,vp_banner_goods,true);

        myGoodsIndicatorAdapter =new MyGoodsIndicatorAdapter(getActivity(),getAdvList);


        bannerComponent.setAdapter(myGoodsIndicatorAdapter);

        //设置播放间隔时间，默认情况是3000毫秒
        bannerComponent.setAutoPlayTime(2500);

    }

    private void setButtonClick() {

        btn_goods_one_one.setOnClickListener(this);
        btn_goods_one_two.setOnClickListener(this);
        btn_goods_one_three.setOnClickListener(this);
        btn_goods_one_four.setOnClickListener(this);
        btn_goods_one_five.setOnClickListener(this);
        btn_goods_one_six.setOnClickListener(this);
        btn_goods_one_seven.setOnClickListener(this);
        btn_goods_one_eight.setOnClickListener(this);
        btn_goods_one_nine.setOnClickListener(this);

        btn_goods_two_one.setOnClickListener(this);
        btn_goods_two_two.setOnClickListener(this);
        btn_goods_two_three.setOnClickListener(this);
        btn_goods_two_four.setOnClickListener(this);
        btn_goods_two_five.setOnClickListener(this);
        btn_goods_two_six.setOnClickListener(this);
        btn_goods_two_seven.setOnClickListener(this);
        btn_goods_two_eight.setOnClickListener(this);
        btn_goods_two_nine.setOnClickListener(this);
        btn_goods_two_ten.setOnClickListener(this);
        btn_goods_two_eleven.setOnClickListener(this);
        btn_goods_two_twelve.setOnClickListener(this);
        btn_goods_two_thirteen.setOnClickListener(this);
        btn_goods_two_fourteen.setOnClickListener(this);
        btn_goods_two_fifteen.setOnClickListener(this);

        btn_goods_three_one.setOnClickListener(this);
        btn_goods_three_two.setOnClickListener(this);
        btn_goods_three_three.setOnClickListener(this);
        btn_goods_three_four.setOnClickListener(this);
        btn_goods_three_five.setOnClickListener(this);
        btn_goods_three_six.setOnClickListener(this);

        btn_goods_four_one.setOnClickListener(this);
        btn_goods_four_two.setOnClickListener(this);
        btn_goods_four_three.setOnClickListener(this);
        btn_goods_four_four.setOnClickListener(this);
        btn_goods_four_five.setOnClickListener(this);
        btn_goods_four_six.setOnClickListener(this);
        btn_goods_four_seven.setOnClickListener(this);
        btn_goods_four_eight.setOnClickListener(this);

        btn_goods_five_one.setOnClickListener(this);
        btn_goods_five_two.setOnClickListener(this);
        btn_goods_five_three.setOnClickListener(this);
        btn_goods_five_four.setOnClickListener(this);
        btn_goods_five_five.setOnClickListener(this);
        btn_goods_five_six.setOnClickListener(this);
        btn_goods_five_seven.setOnClickListener(this);
        btn_goods_five_eight.setOnClickListener(this);

        btn_goods_six_one.setOnClickListener(this);
        btn_goods_six_two.setOnClickListener(this);
        btn_goods_six_three.setOnClickListener(this);
        btn_goods_six_four.setOnClickListener(this);
        btn_goods_six_five.setOnClickListener(this);
        btn_goods_six_six.setOnClickListener(this);
        btn_goods_six_seven.setOnClickListener(this);
        btn_goods_six_eight.setOnClickListener(this);
        btn_goods_six_nine.setOnClickListener(this);
        btn_goods_six_ten.setOnClickListener(this);


        rl_goods_first.setOnClickListener(this);
        rl_goods_second.setOnClickListener(this);
        rl_goods_third.setOnClickListener(this);
        rl_goods_fourth.setOnClickListener(this);
        rl_goods_fifth.setOnClickListener(this);
        rl_goods_sixth.setOnClickListener(this);

        btn_goods_btnSearch.setOnClickListener(this);



    }
}
