package linchom.com.linchomspace.shopping.goodsfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.shopping.pojo.GoodsOrderFormBean;

/**
 * Created by Administrator on 2016/10/20.
 */

public class AllOrderFragment extends Fragment {


    private static final String TAG = "AllOrderFragment";
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.goods_orderform_layout,null);


        initView();

        initData();


        initEvent();



        return view;

    }


    private void initView() {



    }

    private void initData() {


    }

    private void initEvent() {


    }



    private void getData() {

        RequestParams requestParams =new RequestParams("http://app.linchom.com/appapi.php?act=ordersinfo&user_id=12");

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.i(TAG,"result"+result);

                Gson gson =new Gson();

                GoodsOrderFormBean goodsOrderFormBean = gson.fromJson(result, GoodsOrderFormBean.class);

                String str = goodsOrderFormBean.data.items.get(0).order_goods.get(0).goods_name;
                Log.i(TAG,"str"+str);


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
