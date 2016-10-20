package linchom.com.linchomspace.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.chat.ChatDetilActivity;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {


    private RelativeLayout rl_msg;
    private EditText searchContentEt;
    private LinearLayout searchRecordsLl;
    private View recordsHistoryView;
    private ListView recordsListLv;
    private TextView clearAllRecordsTv;
    private RecordsDao recordsDao;
    private ArrayList<String> searchRecordsList;
    private ArrayList<String> tempList;
    private SearchRecordsAdapter recordsAdapter;
    private TextView clearEditText;
    private ImageView search_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rl_msg = ((RelativeLayout) findViewById(R.id.rl_msg));
        rl_msg.setVisibility(View.VISIBLE);



        initView();
        initData();
        bindAdapter();
        initListener();
    }

    private void initView() {

        initRecordsView();

        searchRecordsLl = (LinearLayout) findViewById(R.id.search_content_show_ll);
        searchContentEt = (EditText) findViewById(R.id.et_search);

        //添加搜索view
        searchRecordsLl.addView(recordsHistoryView);

        clearEditText=(TextView) findViewById(R.id.tv_search);
        search_back = ((ImageView) findViewById(R.id.search_back));

    }

    //初始化搜索历史记录View
    private void initRecordsView() {
        recordsHistoryView = LayoutInflater.from(this).inflate(R.layout.search_records_list_layout, null);
        //显示历史记录lv
        recordsListLv = (ListView) recordsHistoryView.findViewById(R.id.search_records_lv);
        //清除搜索历史记录
        clearAllRecordsTv = (TextView) recordsHistoryView.findViewById(R.id.clear_all_records_tv);

    }


    private void initData() {
        recordsDao = new RecordsDao(this);
        searchRecordsList = new ArrayList<>();
        tempList = new ArrayList<>();
        tempList.addAll(recordsDao.getRecordsList());

        reversedList();
        //第一次进入判断数据库中是否有历史记录，没有则不显示
        checkRecordsSize();
    }


    private void bindAdapter() {
        recordsAdapter = new SearchRecordsAdapter(this, searchRecordsList);

        recordsListLv.setAdapter(recordsAdapter);
    }

    private void initListener() {
        clearAllRecordsTv.setOnClickListener(this);
        clearEditText.setOnClickListener(this);
        search_back.setOnClickListener(this);
        searchContentEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (searchContentEt.getText().toString().length() > 0) {

                        String record = searchContentEt.getText().toString();

                        //判断数据库中是否存在该记录
                        if (!recordsDao.isHasRecord(record)) {
                            tempList.add(record);
                        }
                        //将搜索记录保存至数据库中
                        recordsDao.addRecords(record);
                        reversedList();
                        checkRecordsSize();
                        judgeIsEmpty();
                        // recordsAdapter.notifyDataSetChanged();

                        //根据关键词去搜索
                        Intent intent=new Intent(SearchActivity.this, ChatDetilActivity.class);
                        intent.putExtra("type",1);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SearchActivity.this, "搜索内容不能为空", Toast.LENGTH_SHORT).show();
                    }

                }
                return false;
            }
        });

        //根据输入的信息去模糊搜索
        searchContentEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String tempName = searchContentEt.getText().toString();
                tempList.clear();
                tempList.addAll(recordsDao.querySimlarRecord(tempName));
                reversedList();
                checkRecordsSize();
                recordsAdapter.notifyDataSetChanged();
            }
        });

        //历史记录点击事件
        recordsListLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //将获取到的字符串传到搜索结果界面

            }
        });
    }

    //当没有匹配的搜索数据的时候不显示历史记录栏
    private void checkRecordsSize(){
        if(searchRecordsList.size() == 0){
            searchRecordsLl.setVisibility(View.GONE);
        }else{
            searchRecordsLl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //清空所有历史数据
            case R.id.clear_all_records_tv:
                tempList.clear();
                reversedList();
                recordsDao.deleteAllRecords();
                recordsAdapter.notifyDataSetChanged();
                searchRecordsLl.setVisibility(View.GONE);
                break;
            case R.id.tv_search:
                searchContentEt.setText("");
                break;
            case R.id.search_back:
                finish();
        }
    }

    //颠倒list顺序，用户输入的信息会从上依次往下显示
    private void reversedList(){
        searchRecordsList.clear();
        for(int i = tempList.size() - 1 ; i >= 0 ; i --){
            searchRecordsList.add(tempList.get(i));
            judgeIsEmpty();
        }

    }
    private void judgeIsEmpty(){
        if(!searchRecordsList.isEmpty()){
            rl_msg.setVisibility(View.GONE);
        }
    }
}
