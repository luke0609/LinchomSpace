package linchom.com.linchomspace.shopping;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.util.StatusBarCompat;
import linchom.com.linchomspace.shopping.goodsfragment.GoodsListFragment;
import linchom.com.linchomspace.shopping.goodsfragment.GoodsListWaterfallFragment;

public class GoodsListActivity extends AppCompatActivity implements View.OnClickListener{


    //10/16修改

    private static final String TAG = "GoodsListActivity";

    //Fragment fragmentGoodsList = new GoodsListFragment();

    //Fragment fragmentGoodsListWaterFall =new GoodsListWaterfallFragment();

    Fragment fragmentChange;


    private String  catId;

    private  String order =null;


    private String keyword="";



    private Button btn_goodsList_default;
    private Button btn_goodsList_hot;
    private Button btn_goodsList_new;


    private View v_goodLists_default_line;
    private View v_goodLists_hot_line;
    private View v_goodLists_new_line;


    private ImageView iv_goodsList_back;
    private CheckBox radiobtn_goodslist_listcate;
    private Button btn_goods_list_btnSearch;
    private EditText et_goods_search;


    private boolean layoutFlag =true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        StatusBarCompat.compat(this, Color.parseColor("#212121"));


        Intent intent  =getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        keyword = bundle.getString("keyword");

        if(keyword==null){

            keyword="";
        }

        catId=bundle.getString("cateId");

        Log.i(TAG,"keyword"+keyword+"catId"+catId);

        initView();
        initData();
        initEvent();


    }

    private void initView() {

        radiobtn_goodslist_listcate = ((CheckBox) findViewById(R.id.radiobtn_goodslist_listcate));


        btn_goodsList_default = ((Button) findViewById(R.id.btn_goodsList_default));
        btn_goodsList_hot = ((Button) findViewById(R.id.btn_goodsList_hot));
        btn_goodsList_new = ((Button) findViewById(R.id.btn_goodsList_new));

        v_goodLists_default_line = ((View) findViewById(R.id.v_goodLists_default_line));
        v_goodLists_hot_line = ((View) findViewById(R.id.v_goodLists_hot_line));
        v_goodLists_new_line = ((View) findViewById(R.id.v_goodLists_new_line));
        iv_goodsList_back = ((ImageView) findViewById(R.id.iv_goodsList_back));

        btn_goods_list_btnSearch = ((Button) findViewById(R.id.btn_goods_list_btnSearch));

        et_goods_search = ((EditText) findViewById(R.id.et_goods_search));


        //默认状态

        radiobtn_goodslist_listcate.setChecked(true);

        btn_goodsList_default.setTextColor(Color.rgb(255,64,00));
        v_goodLists_default_line.setVisibility(View.VISIBLE);
        btn_goodsList_default.setEnabled(false);

        fragmentChange =new GoodsListFragment();

        FragmentTransaction transaction;

        transaction=getSupportFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();

        bundle.putString("catId",catId);
        bundle.putString("order","desc");
        bundle.putString("sort","");

        bundle.putString("keyword",keyword);


        fragmentChange.setArguments(bundle);



        transaction.add(R.id.fl_goodslist_listcate,fragmentChange).show(fragmentChange).commit();



    }

    private void initData() {

        //cateId
        //bundle

    }



    private void initEvent() {



        btn_goodsList_default.setOnClickListener(this);
        btn_goodsList_hot.setOnClickListener(this);
        btn_goodsList_new.setOnClickListener(this);
        iv_goodsList_back.setOnClickListener(this);

        btn_goods_list_btnSearch.setOnClickListener(this);

        radiobtn_goodslist_listcate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                btn_goodsList_default.setTextColor(Color.rgb(255,64,00));
                btn_goodsList_default.setEnabled(false);
                v_goodLists_default_line.setVisibility(View.VISIBLE);



                btn_goodsList_hot.setEnabled(true);
                btn_goodsList_hot.setTextColor(Color.rgb(128,128,128));
                v_goodLists_hot_line.setVisibility(View.INVISIBLE);

                btn_goodsList_new.setEnabled(true);
                btn_goodsList_new.setTextColor(Color.rgb(128,128,128));
                v_goodLists_new_line.setVisibility(View.INVISIBLE);




                FragmentTransaction transaction;

                transaction=getSupportFragmentManager().beginTransaction();

                transaction.hide(fragmentChange);



                if(isChecked){

                    layoutFlag=true;

                    fragmentChange =new GoodsListFragment();
                    Bundle bundle = new Bundle();

                    bundle.putString("catId",catId);


                    bundle.putString("order","desc");
                    bundle.putString("sort","");

                    bundle.putString("keyword",keyword);




                    fragmentChange.setArguments(bundle);


                    transaction.add(R.id.fl_goodslist_listcate,fragmentChange).show(fragmentChange).commit();




                }else if(!isChecked){
                    layoutFlag =false;

                    fragmentChange =new GoodsListWaterfallFragment();

                    Bundle bundle = new Bundle();

                    bundle.putString("catId",catId);
                    bundle.putString("order","desc");
                    bundle.putString("sort","");

                    bundle.putString("keyword",keyword+"");




                    fragmentChange.setArguments(bundle);

                    transaction.add(R.id.fl_goodslist_listcate,fragmentChange).show(fragmentChange).commit();


                }







            }
        });



    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.btn_goodsList_default:
                pressDefault();

                break;
            case R.id.btn_goodsList_hot:
                pressHot();
                break;
            case R.id.btn_goodsList_new:
                pressNew(v);
                break;

            case R.id.iv_goodsList_back:
                this.finish();
                break;

            case R.id.btn_goods_list_btnSearch:

               toGoodsSearch();

                break;


        }



    }

    private void toGoodsSearch() {
        catId="";

        keyword =et_goods_search.getText().toString().trim()+"";

        FragmentTransaction transaction;

        transaction=getSupportFragmentManager().beginTransaction();

        transaction.hide(fragmentChange);

        if(layoutFlag==true){

            fragmentChange =new GoodsListFragment();
            Bundle bundle = new Bundle();

            bundle.putString("catId",catId);


            bundle.putString("order","desc");
            bundle.putString("sort","");

            bundle.putString("keyword",keyword);




            fragmentChange.setArguments(bundle);


            transaction.add(R.id.fl_goodslist_listcate,fragmentChange).show(fragmentChange).commit();



        }else{


            fragmentChange =new GoodsListWaterfallFragment();

            Bundle bundle = new Bundle();

            bundle.putString("catId",catId);
            bundle.putString("order","desc");
            bundle.putString("sort","");

            bundle.putString("keyword",keyword+"");




            fragmentChange.setArguments(bundle);

            transaction.add(R.id.fl_goodslist_listcate,fragmentChange).show(fragmentChange).commit();




        }

    }

    private void pressDefault() {
        btn_goodsList_default.setTextColor(Color.rgb(255,64,00));
        btn_goodsList_default.setEnabled(false);
        v_goodLists_default_line.setVisibility(View.VISIBLE);



        btn_goodsList_hot.setEnabled(true);
        btn_goodsList_hot.setTextColor(Color.rgb(128,128,128));
        v_goodLists_hot_line.setVisibility(View.INVISIBLE);

        btn_goodsList_new.setEnabled(true);
        btn_goodsList_new.setTextColor(Color.rgb(128,128,128));
        v_goodLists_new_line.setVisibility(View.INVISIBLE);




        //btn_goodsList_default.setClickable(false);
        //默认

        sortChange("desc","");


    }



    private void pressHot() {
        btn_goodsList_hot.setTextColor(Color.rgb(255,64,00));
        btn_goodsList_hot.setEnabled(false);
        v_goodLists_hot_line.setVisibility(View.VISIBLE);


        btn_goodsList_default.setEnabled(true);
        btn_goodsList_default.setTextColor(Color.rgb(128,128,128));
        v_goodLists_default_line.setVisibility(View.INVISIBLE);



        btn_goodsList_new.setEnabled(true);
        btn_goodsList_new.setTextColor(Color.rgb(128,128,128));
        v_goodLists_new_line.setVisibility(View.INVISIBLE);






        sortChange("desc","click_count");



    }

    private void pressNew(View v) {
        btn_goodsList_new.setTextColor(Color.rgb(255,64,00));
        v_goodLists_new_line.setVisibility(View.VISIBLE);


        btn_goodsList_default.setEnabled(true);
        btn_goodsList_default.setTextColor(Color.rgb(128,128,128));
        v_goodLists_default_line.setVisibility(View.INVISIBLE);

        btn_goodsList_hot.setEnabled(true);
        btn_goodsList_hot.setTextColor(Color.rgb(128,128,128));
        v_goodLists_hot_line.setVisibility(View.INVISIBLE);


        showPopupWindow(v);




        //sortChange("desc","");

    }




    private void sortChange(String order,String sort) {

        if(radiobtn_goodslist_listcate.isChecked()){

            FragmentTransaction transaction;

            transaction=getSupportFragmentManager().beginTransaction();

            //类别也要传过去  catId
            fragmentChange =new GoodsListFragment();

            Bundle bundle = new Bundle();

            bundle.putString("catId",catId);
            bundle.putString("keyword",keyword);



            bundle.putString("order",order);

            bundle.putString("sort",sort+"");



            fragmentChange.setArguments(bundle);


            transaction.replace(R.id.fl_goodslist_listcate,fragmentChange).commit();
        }else{
            FragmentTransaction transaction;

            transaction=getSupportFragmentManager().beginTransaction();
            fragmentChange =new GoodsListWaterfallFragment();
            Bundle bundle = new Bundle();
            bundle.putString("catId",catId);
            bundle.putString("order",order);
            bundle.putString("sort",sort+"");
            bundle.putString("keyword",keyword);

            fragmentChange.setArguments(bundle);
            transaction.replace(R.id.fl_goodslist_listcate,fragmentChange).commit();




        }




    }


    private void showPopupWindow(View view) {
        //自己写逻辑控制出现的位置

        View contentView = LayoutInflater.from(this).inflate(
                R.layout.goodslist_popupwindow, null);
         RelativeLayout rl_goodsList_pop_top = ((RelativeLayout) contentView.findViewById(R.id.rl_goodsList_pop_top));


        RelativeLayout rl_goodsList_pop_bottom=  ((RelativeLayout) contentView.findViewById(R.id.rl_goodsList_pop_bottom));


        rl_goodsList_pop_top.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sortChange("asc","shop_price");
            }
        });


        rl_goodsList_pop_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sortChange("desc","shop_price");

            }
        });

        final PopupWindow popupWindow = new PopupWindow(contentView,
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.ic_launcher));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

    }





    }




