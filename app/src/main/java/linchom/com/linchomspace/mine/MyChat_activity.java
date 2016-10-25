package linchom.com.linchomspace.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobeta.android.dslv.DragSortListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.pojo.LovedInfoBean;
import linchom.com.linchomspace.mine.pojo.MychatInfoBean;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;

public class MyChat_activity extends AppCompatActivity {

    private ImageView iv_chat_back;
    List<MychatInfoBean.Mdata.Imtems> chatlist=new ArrayList<MychatInfoBean.Mdata.Imtems>();
    private GoodsCommonAdapter<MychatInfoBean.Mdata.Imtems> chatCommonAdapter;
    private DragSortListView lv_myChatList;
//    private ListView lv_myChatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chat_activity);
        lv_myChatList = ((DragSortListView) findViewById(R.id.lv_myChatList));
//        lv_myChatList = ((ListView) findViewById(R.id.lv_myChatList));

//        System.out.println("onCreate");
        initView();

        initData();

    }


    private void initView() {

        iv_chat_back = ((ImageView) findViewById(R.id.iv_chat_back));

    }

    private void initEvent() {
//        System.out.println("---------");
        chatCommonAdapter=new GoodsCommonAdapter<MychatInfoBean.Mdata.Imtems>(getApplicationContext(),chatlist,R.layout.my_chat_items) {

            @Override
            public void convert(GoodsViewHolder viewHolder, MychatInfoBean.Mdata.Imtems imtems, int position) {

                TextView topic_name = ( viewHolder.getViewById(R.id.topic_name));
//        System.out.println("========="+imtems.topic_name);
                topic_name.setText(imtems.topic_name);
                TextView communication_title = ( viewHolder.getViewById(R.id.communication_title));
                communication_title.setText(imtems.communication_title);
                TextView user_name = viewHolder.getViewById(R.id.user_name);
                user_name.setText(imtems.user_name);
                TextView add_time = viewHolder.getViewById(R.id.add_time);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日",Locale.getDefault());
                Date date=new Date(Long.parseLong(imtems.add_time));
                add_time.setText(simpleDateFormat.format(date));

            }
        };
        lv_myChatList.setAdapter(chatCommonAdapter);
    }

    private void initData() {


        System.out.println("initData");
        RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php?act=topic&user_id=135");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
//                System.out.println("onSuccess");
//                System.out.println("onSuccess"+result);
                Gson gson=new Gson();
                MychatInfoBean mychatInfoBean=gson.fromJson(result,MychatInfoBean.class);
//                System.out.println(mychatInfoBean);
                MychatInfoBean.Mdata mdata=mychatInfoBean.getData();
//                System.out.println(mdata);
                chatlist.addAll(mdata.items);
                //页面添加数据
                initEvent();
                //  MychatInfoBean.Mdata mdata=gson.fromJson(result,MychatInfoBean.Mdata.class);

                    /*chatCommonAdapter=new GoodsCommonAdapter<MychatInfoBean.Mdata.Imtems>(getApplicationContext(),chatlist,R.layout.my_chat_items) {
                        @Override
                        public void convert(GoodsViewHolder viewHolder, MychatInfoBean.Mdata.Imtems imtems, int position) {
                            TextView topic_name = ( viewHolder.getViewById(R.id.topic_name));
                            System.out.println("========="+imtems.topic_name);
                            topic_name.setText(imtems.topic_name);
                            TextView communication_title = ( viewHolder.getViewById(R.id.communication_title));
                            communication_title.setText(imtems.communication_title);
                            TextView user_name = viewHolder.getViewById(R.id.user_name);
                            user_name.setText(imtems.user_name);
                            TextView add_time = viewHolder.getViewById(R.id.add_time);
                            add_time.setText(imtems.add_time);
                        }
                    };
                    lv_myChatList.setAdapter(chatCommonAdapter);
               */
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                System.out.println("onError"+ex+"");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

        iv_chat_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
