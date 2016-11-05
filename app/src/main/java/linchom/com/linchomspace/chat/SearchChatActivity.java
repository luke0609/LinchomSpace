//package linchom.com.linchomspace.chat;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshListView;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import butterknife.ButterKnife;
//import butterknife.InjectView;
//import butterknife.OnClick;
//import linchom.com.linchomspace.R;
//
//import linchom.com.linchomspace.chat.pojo.TopicList;
//import linchom.com.linchomspace.homepage.Activity.SearchArticleActivity;
//import linchom.com.linchomspace.homepage.Entity.ArticleListBean;
//import linchom.com.linchomspace.homepage.Utils.DateUtils;
//import linchom.com.linchomspace.homepage.Utils.xUtilsImageUtils;
//import linchom.com.linchomspace.homepage.progressbar.CircularProgress;
//
//import static linchom.com.linchomspace.R.id.iv_photo;
//
//import static linchom.com.linchomspace.R.id.remark_num;
//import static linchom.com.linchomspace.R.id.tv_chat_name;
//import static linchom.com.linchomspace.R.id.tv_chat_time;
//import static linchom.com.linchomspace.R.id.tv_name;
//import static linchom.com.linchomspace.R.id.tv_source;
//import static linchom.com.linchomspace.R.id.tv_title;
//
//public class SearchChatActivity extends AppCompatActivity {
//
//    @InjectView(R.id.searchWait_back)
//    ImageButton searchWaitBack;
//    @InjectView(R.id.search_wait_keyword)
//    TextView searchWaitKeyword;
//    @InjectView(R.id.rl_searchWait_titlebar)
//    RelativeLayout rlSearchWaitTitlebar;
//    @InjectView(R.id.pb_progressBar)
//    CircularProgress pbProgressBar;
//    @InjectView(R.id.rl_hide_searchArticle)
//    RelativeLayout rlHideSearchArticle;
//    @InjectView(R.id.searchSuccess_back)
//    ImageButton searchSuccessBack;
//    @InjectView(R.id.search_success_keyword)
//    TextView searchSuccessKeyword;
//    @InjectView(R.id.rl_searchSuccess_titlebar)
//    RelativeLayout rlSearchSuccessTitlebar;
//    @InjectView(R.id.ptr_arrlist_searchChat)
//    PullToRefreshListView ptrArrlistSearchChat;
//    @InjectView(R.id.nokeyword)
//    ImageView nokeyword;
//    @InjectView(R.id.rl_empty)
//    RelativeLayout rlEmpty;
//    @InjectView(R.id.iv_nonet)
//    ImageView ivNonet;
//    @InjectView(R.id.reload)
//    Button reload;
//    @InjectView(R.id.error_net)
//    RelativeLayout errorNet;
//    String keyword;
//    private boolean pullFlag = false;
//    private int page = 1;//加载页数
//    private int pageCount;
//    private ListView lv_chat;
//
//    final List<TopicList.DataBean.ItemsBean> topicList=new ArrayList<TopicList.DataBean.ItemsBean>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_chat);
//        ButterKnife.inject(this);
//
//        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("bundle");
//        keyword = bundle.getString("keyword");
//        initview();
//        initdata();
//    }
//
//
//
//    private void initview() {
//
//        lv_chat = ptrArrlistSearchChat.getRefreshableView();
//        adapter1 = new  MyAdapter();
//        lv_chat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent,View view,int position, long id) {
//                Intent intent = new Intent(SearchChatActivity.this, ChatDetilActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("article_id", topicList.get(position - 1).article_id);
//                intent.putExtra("bundle", bundle);
//                startActivity(intent);
//            }
//        });
//        lv_chat.setAdapter(adapter1);
//        ptrArrlistSearchChat.setScrollingWhileRefreshingEnabled(true);
//        ptrArrlistSearchChat.setMode(PullToRefreshBase.Mode.BOTH);
//        ptrArrlistSearchChat.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
//            @Override
//            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//
//                //异步任务拿数据
//                PullToRefreshBase.Mode mode = ptrArrlistSearchChat.getCurrentMode();
//                // View viewRefresh = null;
//
//                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {
//
//                    page++;
//                    getChat();
//                }
//                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
//                    pullFlag = true;
//                    page = 1;
//                    getChat();
//
//                }
//            }
//        });
//        ptrArrlistSearchChat.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
//            @Override
//            public void onLastItemVisible() {
//
//            }
//        });
//
//    }
//    private void initdata() {
//        ptrArrlistSearchChat.setScrollingWhileRefreshingEnabled(true);
//        ptrArrlistSearchChat.setMode(PullToRefreshBase.Mode.BOTH);
//        ptrArrlistSearchChat.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
//            @Override
//            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//
//                //异步任务拿数据
//                PullToRefreshBase.Mode mode = ptrArrlistSearchChat.getCurrentMode();
//
//                // View viewRefresh = null;
//
//                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {
//
//                    page++;
//                    getChat();
//                }
//                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
//                    pullFlag = true;
//                    page = 1;
//                    getChat();
//
//                }
//            }
//        });
//        ptrArrlistSearchChat.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
//            @Override
//            public void onLastItemVisible() {
//
//            }
//        });
//
//        lv_chat.setAdapter(adapter1);
//        getChat();
//    }
//
//    private void getChat() {
//
//
//    }
//
//    @OnClick({R.id.searchWait_back, R.id.searchSuccess_back})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.searchWait_back:
//                finish();
//                break;
//            case R.id.searchSuccess_back:
//                finish();
//                break;
//        }
//    }
//
//}
