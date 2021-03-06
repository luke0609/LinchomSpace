package linchom.com.linchomspace.mine;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.ChatDetilActivity;
import linchom.com.linchomspace.login.contantData.Contant;
import linchom.com.linchomspace.mine.pojo.MychatInfoBean;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;

public class MyChat_activity extends AppCompatActivity {

    private static final String TAG = "MyChat_activity";
    private ImageView iv_chat_back;
    List<MychatInfoBean.Mdata.Imtems> chatlist=new ArrayList<MychatInfoBean.Mdata.Imtems>();
    private GoodsCommonAdapter<MychatInfoBean.Mdata.Imtems> chatCommonAdapter;
    private PullToRefreshListView lv_myChatList;
    private int page=1;
    private int pageCount=1;
    private ProgressBar firstBar;
    private static int mDelId = 0;
    private String userId="";
    private String username="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chat_activity);
        lv_myChatList = ((PullToRefreshListView) findViewById(R.id.lv_myChatList));
        firstBar = ((ProgressBar) findViewById(R.id.firstBar));
//        lv_myChatList = ((ListView) findViewById(R.id.lv_myChatList));
        SharedPreferences shared_prefs = getSharedPreferences(Contant.userinfo_shared_prefs, Context.MODE_PRIVATE);
        userId = shared_prefs.getString("userId","");
        username = shared_prefs.getString("username","");
//        System.out.println("onCreate");
        initView();
        eventPullToRefresh();

        lv_myChatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MychatInfoBean.Mdata.Imtems topic=chatlist.get(position-1);
                Intent intent = new Intent(getApplicationContext(), ChatDetilActivity.class);
                intent.putExtra("topic_id",topic.getTopic_id());
                startActivity(intent);
            }
        });
        lv_myChatList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
              @Override
              public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                  mDelId=position-1;
                  showDialog();
                  return true;
              }
          });

        iv_chat_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void eventPullToRefresh() {
        lv_myChatList.setScrollingWhileRefreshingEnabled(true);
        lv_myChatList.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        lv_myChatList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                page++;
                initData();
                lv_myChatList.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        lv_myChatList.onRefreshComplete();
                    }
                }, 1000);

            }
        });

        chatCommonAdapter=new GoodsCommonAdapter<MychatInfoBean.Mdata.Imtems>(getApplicationContext(), chatlist, R.layout.my_chat_items) {
            @Override
            public void convert(GoodsViewHolder viewHolder, MychatInfoBean.Mdata.Imtems imtems, int position) {
                TextView topic_name = (viewHolder.getViewById(R.id.topic_name));
                System.out.println("=========" + imtems.topic_name);
                topic_name.setText(imtems.topic_name);
                TextView communication_title = (viewHolder.getViewById(R.id.communication_title));
                communication_title.setText(imtems.communication_title);
                TextView user_name = viewHolder.getViewById(R.id.user_name);
                user_name.setText(imtems.user_name);
                TextView add_time = viewHolder.getViewById(R.id.add_time);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date = new Date(Long.parseLong((imtems.add_time))*1000);
                add_time.setText(sdf.format(date));
            }
        };

        lv_myChatList.setAdapter(chatCommonAdapter);

       initData();


    }


    private void initView() {

        iv_chat_back = ((ImageView) findViewById(R.id.iv_chat_back));
    }

    private void initEvent() {
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
//        System.out.println("initData");
        if (page==1) {
            firstBar.setVisibility(View.VISIBLE);
        }
        RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php");
        requestParams.addBodyParameter("verification","e0d017ef76c8510244ebe0191f5dde15" );
        requestParams.addBodyParameter("act","topic");
        requestParams.addBodyParameter("user_id",userId);
        requestParams.addBodyParameter("page",page+"");

        x.http().post(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                if (page <= pageCount) {
                    Gson gson = new Gson();
                    MychatInfoBean mychatInfoBean = gson.fromJson(result, MychatInfoBean.class);
                    pageCount = Integer.parseInt(mychatInfoBean.data.total_pages.toString());
                    MychatInfoBean.Mdata mdata = mychatInfoBean.getData();
                    chatlist.addAll(mdata.items);

                    chatCommonAdapter.notifyDataSetChanged();

//                    System.out.println("=====" + chatlist);
                }else {
                    Toast.makeText(getApplicationContext(),"已经是最后一页了",Toast.LENGTH_SHORT).show();
                    chatCommonAdapter.notifyDataSetChanged();
                }

                firstBar.setVisibility(View.GONE);
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

    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定删除吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_LONG).show();
                deleteItem();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void deleteItem() {
        int size = chatlist.size();
        if (size > 0) {
            chatlist.remove(mDelId);
            chatCommonAdapter.notifyDataSetChanged();
        }
    }
}
