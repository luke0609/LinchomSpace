package linchom.com.linchomspace.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.pojo.UserInfoBean;
import linchom.com.linchomspace.shopping.GoodsAllOrderActivity;
import linchom.com.linchomspace.shopping.GoodsCartActivity;


public class MineFragment extends Fragment {


    private static final String TAG = "MineFragment";
    private RelativeLayout iv_store;
    UserInfoBean.DataBean userInfoBean;
    private ImageView iv_back;
    private ImageView iv_user_photo;
    private TextView tv_ed;
    private TextView tv_user_name;
    private TextView tv_sex;
    private TextView tv_birthday;
    private TextView tv_email;
    private TextView tv_office_phone;
    private TextView tv_home_phone;
    private TextView tv_mobile_phone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_mine,null);

        ((ImageView)view.findViewById(R.id.iv_head)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Details_Activity.class);
                startActivity(intent);
            }
    });
        tv_user_name=((TextView)view.findViewById(R.id.tv_user_name));
        tv_user_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Details_Activity.class);
                startActivity(intent);
            }
        });
        ((Button) view.findViewById(R.id.b_loved)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Loved_activity.class);
                startActivity(intent);
            }
        });
        ((Button) view.findViewById(R.id.b_set)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Set_activity.class);
                startActivity(intent);
            }
        });
        ((RelativeLayout) view.findViewById(R.id.person_center_content)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Active_activity.class);
                startActivity(intent);
            }
        });
        ((RelativeLayout)view.findViewById(R.id.person_center_content1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GoodsCartActivity.class);
                startActivity(intent);
            }
        });

        ((RelativeLayout) view.findViewById(R.id.person_center_content2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GoodsAllOrderActivity.class);
                startActivity(intent);
            }
        });
        ((RelativeLayout) view.findViewById(R.id.person_center_content3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyChat_activity.class);
                startActivity(intent);
            }
        });
        ((RelativeLayout) view.findViewById(R.id.person_center_content4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Disclose_activity.class);
                startActivity(intent);
            }
        });
//        ((RelativeLayout) view.findViewById(R.id.person_center_content6)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Alerts_activity.class);
//                startActivity(intent);
//            }
//        });
        ((RelativeLayout) view.findViewById(R.id.person_center_content7)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeedBack_activity.class);
                startActivity(intent);
            }
        });
//        ((RelativeLayout) view.findViewById(R.id.person_center_content5)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), log_activity.class);
//                startActivity(intent);
//            }
//        });
        initData();
        return view;
    }

    public void initData() {

        RequestParams requestParams = new RequestParams("http://app.linchom.com/appapi.php?act=userinfo&user_id=135");
//        requestParams.addBodyParameter("key", "linchom");
//       requestParams.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
//        requestParams.addBodyParameter("user_id", "135");

        x.http().post(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                System.out.println("onsucess" + result);
                Gson gson = new Gson();
                UserInfoBean bean = gson.fromJson(result, UserInfoBean.class);
                userInfoBean = bean.getData();
                System.out.println("===777777==="+userInfoBean);
//
                 tv_user_name.setText(userInfoBean.getUser_name());
//
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("ex"+ex+"");
                Log.i(TAG, ex + "");

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
