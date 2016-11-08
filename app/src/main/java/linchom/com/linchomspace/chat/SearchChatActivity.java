package linchom.com.linchomspace.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.pojo.TopicList;
import linchom.com.linchomspace.chat.util.CommonAdapter;
import linchom.com.linchomspace.chat.util.DateUtils;
import linchom.com.linchomspace.chat.util.ViewHolder;
import linchom.com.linchomspace.homepage.progressbar.CircularProgress;
import linchom.com.linchomspace.photoutil.NineGridTestLayout;

import static linchom.com.linchomspace.R.id.error_net;
import static linchom.com.linchomspace.R.id.rl_empty;
import static linchom.com.linchomspace.R.id.rl_hide_searchArticle;

public class SearchChatActivity extends AppCompatActivity {

    @InjectView(R.id.searchWait_back)
    ImageButton searchWaitBack;
    @InjectView(R.id.search_wait_keyword)
    TextView searchWaitKeyword;
    @InjectView(R.id.rl_searchWait_titlebar)
    RelativeLayout rlSearchWaitTitlebar;
    @InjectView(R.id.pb_progressBar)
    CircularProgress pbProgressBar;
    @InjectView(rl_hide_searchArticle)
    RelativeLayout rlHideSearchArticle;
    @InjectView(R.id.searchSuccess_back)
    ImageButton searchSuccessBack;
    @InjectView(R.id.search_success_keyword)
    TextView searchSuccessKeyword;
    @InjectView(R.id.rl_searchSuccess_titlebar)
    RelativeLayout rlSearchSuccessTitlebar;
    @InjectView(R.id.ptr_arrlist_searchChat)
    PullToRefreshListView ptrArrlistSearchChat;
    @InjectView(R.id.nokeyword)
    ImageView nokeyword;
    @InjectView(rl_empty)
    RelativeLayout rlEmpty;
    @InjectView(R.id.iv_nonet)
    ImageView ivNonet;
    @InjectView(R.id.reload)
    Button reload;
    @InjectView(error_net)
    RelativeLayout errorNet;
    String keyword;
    private boolean pullFlag = false;
    private int page = 1;//加载页数
    private  int pageCount;
    private int total_pages;
    private ListView lv_chat;
   CommonAdapter<TopicList.DataBean.ItemsBean> adapter;

    final List<TopicList.DataBean.ItemsBean> topicList=new ArrayList<TopicList.DataBean.ItemsBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_chat);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        keyword = bundle.getString("keyword");
        initview();
        initdata();
    }



    private void initview() {
        searchWaitKeyword.setText(keyword);
        searchSuccessKeyword.setText(keyword);
        lv_chat = ptrArrlistSearchChat.getRefreshableView();
        //adapter1 = new  MyAdapter();
        lv_chat.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position, long id) {
                Intent intent = new Intent(SearchChatActivity.this, ChatDetilActivity.class);
                Bundle bundle = new Bundle();
               // bundle.putString("article_id", topicList.get(position - 1).article_id);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
        //lv_chat.setAdapter(adapter1);
        ptrArrlistSearchChat.setScrollingWhileRefreshingEnabled(true);
        ptrArrlistSearchChat.setMode(PullToRefreshBase.Mode.BOTH);
        ptrArrlistSearchChat.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                //异步任务拿数据
                PullToRefreshBase.Mode mode = ptrArrlistSearchChat.getCurrentMode();
                // View viewRefresh = null;

                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                    page++;
                    getChat();
                }
                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                    pullFlag = true;
                    page = 1;
                    getChat();

                }
            }
        });
        ptrArrlistSearchChat.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible(){

            }
        });
        lv_chat = ptrArrlistSearchChat.getRefreshableView();
        adapter = new CommonAdapter<TopicList.DataBean.ItemsBean>(getApplicationContext(), topicList, R.layout.topiclist_layout) {


            @Override
            public void convert(ViewHolder viewHolder, TopicList.DataBean.ItemsBean itemsBean, int position) {
                TextView tv_name = viewHolder.getViewById(R.id.tv_chat_username);
                TextView tv_chat_name = viewHolder.getViewById(R.id.tv_chat_name);
                TextView tv_chat_time = viewHolder.getViewById(R.id.tv_chat_time);
                TextView tv_chat_title = viewHolder.getViewById(R.id.tv_title);
                TextView remark_num = viewHolder.getViewById(R.id.remark_num);
                NineGridTestLayout photo_show = viewHolder.getViewById(R.id.photo_show);


                tv_name.setText(itemsBean.getUser_name());
                tv_chat_name.setText(itemsBean.getTopic_name().trim());
                tv_chat_title.setText(itemsBean.getCommunication_title());
                int timeB = Integer.parseInt(itemsBean.getAdd_time());
                tv_chat_time.setText(DateUtils.getGapTimeFromNow(new Date(timeB * 1000L)));
                remark_num.setText(itemsBean.getRepliesnumber());
                List<String> urllist = new ArrayList<>();
                if (itemsBean.getPhoto() != null && (!"".equals(itemsBean.getPhoto()))) {

                    String[] urls = itemsBean.getPhoto().split(",");
                    for (int i = 0; i < urls.length; i++) {
                        System.out.println("!!!" + urls[i]);
                        if ("0".equals(urls[i])) {
                            continue;
                        }

                        urllist.add(urls[i]);
                    }
                    photo_show.setUrlList(urllist);
                } else {
                    urllist.clear();
                    photo_show.setUrlList(urllist);
                }


            }
        };
        lv_chat.setAdapter(adapter);
        getChat();

    }
    private void initdata() {
        ptrArrlistSearchChat.setScrollingWhileRefreshingEnabled(true);
        ptrArrlistSearchChat.setMode(PullToRefreshBase.Mode.BOTH);
        ptrArrlistSearchChat.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                //异步任务拿数据
                PullToRefreshBase.Mode mode = ptrArrlistSearchChat.getCurrentMode();

                // View viewRefresh = null;

                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                    page++;
                    getChat();
                }
                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                    pullFlag = true;
                    page = 1;
                    getChat();

                }
            }
        });
        ptrArrlistSearchChat.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {

            }
        });



    }

    private void getChat() {
        rlEmpty.setVisibility(View.GONE);
        errorNet.setVisibility(View.GONE);
        if (page == 1 && pullFlag == false){
            rlHideSearchArticle.setVisibility(View.VISIBLE);
        }
        RequestParams params = new RequestParams("http://app.linchom.com/appapi.php");
        params.addQueryStringParameter("act", "topic");
        params.addQueryStringParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        params.addBodyParameter("keyword",keyword);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptrArrlistSearchChat.setVisibility(View.GONE);
                rlHideSearchArticle.setVisibility(View.GONE);
                if (page == 1) {
                    topicList.clear();
                }
                Log.i("aaaaaaa", result + "");
                Gson gson = new Gson();
                TopicList bean = gson.fromJson(result, TopicList.class);
                TopicList.DataBean chatdata = bean.getData();

                pageCount =chatdata.getTotal_pages();
                //Toast.makeText(getApplicationContext(),pageCount,Toast.LENGTH_SHORT).show();
                if (chatdata.getItems().size() != 0) {
                    ptrArrlistSearchChat.setVisibility(View.VISIBLE);
//                System.out.println(bean.status + "????");
                System.out.println(pageCount + "====");
//                通知listview更新界面
                    if (page <= pageCount){
                            topicList.addAll(bean.getData().getItems());
                            adapter.notifyDataSetChanged();
                            ptrArrlistSearchChat.onRefreshComplete();
                        } else {
                            Toast.makeText(SearchChatActivity.this, "已经是最后一页了", Toast.LENGTH_SHORT).show();
                            ptrArrlistSearchChat.onRefreshComplete();
                    }
                }else{
                    rlEmpty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("aaa", "exexex" + ex + "");
//                Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
                rlHideSearchArticle.setVisibility(View.GONE);
                ptrArrlistSearchChat.setVisibility(View.GONE);
                rlEmpty.setVisibility(View.GONE);
                errorNet.setVisibility(View.VISIBLE);
                reload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getChat();
                    }
                });


            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });



    }

    @OnClick({R.id.searchWait_back, R.id.searchSuccess_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchWait_back:
                finish();
                break;
            case R.id.searchSuccess_back:
                finish();
                break;
        }
    }

}
