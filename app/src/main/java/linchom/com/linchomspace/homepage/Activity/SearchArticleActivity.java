package linchom.com.linchomspace.homepage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import linchom.com.linchomspace.R;
import linchom.com.linchomspace.homepage.Constant.Constant;
import linchom.com.linchomspace.homepage.Entity.ArticleListBean;
import linchom.com.linchomspace.homepage.Utils.DateUtils;
import linchom.com.linchomspace.homepage.Utils.xUtilsImageUtils;
import linchom.com.linchomspace.homepage.progressbar.CircularProgress;

import static linchom.com.linchomspace.R.id.iv_photo;
import static linchom.com.linchomspace.R.id.tv_name;
import static linchom.com.linchomspace.R.id.tv_source;
import static linchom.com.linchomspace.R.id.tv_title;

public class SearchArticleActivity extends AppCompatActivity {

    final List<ArticleListBean.Article_list> arrList = new ArrayList<ArticleListBean.Article_list>();
    @InjectView(R.id.searchWait_back)
    ImageButton searchWaitBack;
    @InjectView(R.id.searchSuccess_back)
    ImageButton searchSuccessBack;
    @InjectView(R.id.search_wait_keyword)
    TextView searchWaitKeyword;
    @InjectView(R.id.search_success_keyword)
    TextView searchSuccessKeyword;
    private String keyword;
    private CircularProgress pb_progressBar;
    private RelativeLayout rl_hide_searchArticle;
    private MyAdapter adapter1;
    private ListView lv_searchArticle;
    private PullToRefreshListView ptr_arrlist_searchArticle;
    private boolean pullFlag = false;
    private int page = 1;//加载页数
    private int pageCount;
    private RelativeLayout rl_empty;
    private RelativeLayout error_net;
    private Button reload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_article);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        keyword = bundle.getString("keyword");
        initview();
        initdata();

    }

    private void initview() {
        reload = ((Button) findViewById(R.id.reload));
        searchWaitKeyword.setText(keyword);
        searchSuccessKeyword.setText(keyword);
        pb_progressBar = (CircularProgress)findViewById(R.id.pb_progressBar);
        rl_hide_searchArticle = (RelativeLayout) findViewById(R.id.rl_hide_searchArticle);
        rl_empty = (RelativeLayout) findViewById(R.id.rl_empty);
        error_net = (RelativeLayout) findViewById(R.id.error_net);
        ptr_arrlist_searchArticle = ((PullToRefreshListView)findViewById(R.id.ptr_arrlist_searchArticle));
        lv_searchArticle = ptr_arrlist_searchArticle.getRefreshableView();
        adapter1 = new MyAdapter();
        lv_searchArticle.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position, long id) {
                Intent intent = new Intent(SearchArticleActivity.this, ArticleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("article_id", arrList.get(position - 1).article_id);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
        lv_searchArticle.setAdapter(adapter1);
        ptr_arrlist_searchArticle.setScrollingWhileRefreshingEnabled(true);
        ptr_arrlist_searchArticle.setMode(PullToRefreshBase.Mode.BOTH);
        ptr_arrlist_searchArticle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = android.text.format.DateUtils.formatDateTime(SearchArticleActivity.this, System.currentTimeMillis(),
                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                //异步任务拿数据
                PullToRefreshBase.Mode mode = ptr_arrlist_searchArticle.getCurrentMode();
                // View viewRefresh = null;

                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                    page++;
                    getSearchArticle();
                }
                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                    pullFlag = true;
                    page = 1;
                    getSearchArticle();

                }
            }
        });
        ptr_arrlist_searchArticle.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                Toast.makeText(SearchArticleActivity.this, "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void initdata() {
        ptr_arrlist_searchArticle.setScrollingWhileRefreshingEnabled(true);
        ptr_arrlist_searchArticle.setMode(PullToRefreshBase.Mode.BOTH);
        ptr_arrlist_searchArticle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = android.text.format.DateUtils.formatDateTime(SearchArticleActivity.this, System.currentTimeMillis(),
                        android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_SHOW_DATE | android.text.format.DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                //异步任务拿数据
                PullToRefreshBase.Mode mode = ptr_arrlist_searchArticle.getCurrentMode();

                // View viewRefresh = null;

                if (mode == PullToRefreshBase.Mode.PULL_FROM_END) {

                    page++;
                    getSearchArticle();
                }
                if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                    pullFlag = true;
                    page = 1;
                    getSearchArticle();

                }
            }
        });
        ptr_arrlist_searchArticle.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                Toast.makeText(SearchArticleActivity.this, "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });

        lv_searchArticle.setAdapter(adapter1);
        getSearchArticle();
    }

    private void getSearchArticle() {
        rl_empty.setVisibility(View.GONE);
        error_net.setVisibility(View.GONE);
        if (page == 1 && pullFlag == false) {
            rl_hide_searchArticle.setVisibility(View.VISIBLE);
        }
        RequestParams params = new RequestParams(Constant.ArticleList);
        params.addBodyParameter("key", "linchom");
        params.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
        //params.addBodyParameter("cat_id", "12");
        Log.i("keyword", keyword);
        //搜索功能
        params.addBodyParameter("keyword", keyword);
        params.addBodyParameter("page", page + "");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ptr_arrlist_searchArticle.setVisibility(View.GONE);
              // Log.i("bbbb",arrList.size()+"");
                    rl_hide_searchArticle.setVisibility(View.GONE);
                    if (page == 1) {
                        arrList.clear();
                    }
                    Log.i("aaaaaaa", result + "");
                    Gson gson = new Gson();
                    ArticleListBean bean = gson.fromJson(result, ArticleListBean.class);
                    ArticleListBean.Data articleData = bean.data;
                    pageCount = Integer.parseInt(articleData.page_count);
                    Log.i("aaaaaaa", articleData.getArticle_list().size() + "");
                if (articleData.getArticle_list().size() != 0) {
                    ptr_arrlist_searchArticle.setVisibility(View.VISIBLE);
//                System.out.println(bean.status + "????");
//                System.out.println(bean.dongtaiList.size() + "====");
//                通知listview更新界面


                    if (page <= pageCount) {
                        arrList.addAll(articleData.getArticle_list());
                        adapter1.notifyDataSetChanged();
                        ptr_arrlist_searchArticle.onRefreshComplete();
                    } else {
                        Toast.makeText(SearchArticleActivity.this, "已经是最后一页了", Toast.LENGTH_SHORT).show();
                        ptr_arrlist_searchArticle.onRefreshComplete();


                    }
                }else{
                    rl_hide_searchArticle.setVisibility(View.GONE);
                    rl_empty.setVisibility(View.VISIBLE);
                    ptr_arrlist_searchArticle.setVisibility(View.GONE);
                    error_net.setVisibility(View.GONE);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.i("aaa", "exexex" + ex + "");
//                Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
                rl_hide_searchArticle.setVisibility(View.GONE);
                ptr_arrlist_searchArticle.setVisibility(View.GONE);
                rl_empty.setVisibility(View.GONE);
                error_net.setVisibility(View.VISIBLE);
                reload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getSearchArticle();
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
                this.finish();
                break;
            case R.id.searchSuccess_back:
                this.finish();
                break;
        }
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i("convertView", "getView at position:" + position + " convertView:" + (convertView == null ? "null" : convertView.hashCode()));
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(SearchArticleActivity.this, R.layout.tuijian_item, null);
                viewHolder.tv_title = ((TextView) convertView.findViewById(tv_title));
                viewHolder.tv_name = ((TextView) convertView.findViewById(tv_name));
                viewHolder.iv_photo = ((ImageView) convertView.findViewById(iv_photo));
                viewHolder.tv_source = ((TextView) convertView.findViewById(tv_source));


                convertView.setTag(viewHolder);//缓存对象的
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //ListActivityBean.Dongtai dongtai = dongtaiList.get(position);
            ArticleListBean.Article_list arr = arrList.get(position);

            viewHolder.tv_title.setText(arr.title);

            viewHolder.tv_name.setText(DateUtils.getGapTimeFromNow(DateUtils.stringToDate(arr.date)));
            if (arr.article_pic == "") {
                viewHolder.iv_photo.setImageResource(R.drawable.aiqinhai);
            } else {
                viewHolder.iv_photo.setVisibility(View.VISIBLE);
                xUtilsImageUtils.display(viewHolder.iv_photo, "http://linchom.com//" + arr.article_pic);
            }


            return convertView;
        }
    }


    private static class ViewHolder {
        private TextView tv_title;
        private TextView tv_name;
        private TextView tv_source;
        private ImageView iv_photo;
    }
}
