package linchom.com.linchomspace.mine;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.pojo.MyDiscloseInfoBean;
import linchom.com.linchomspace.mine.pojo.MychatInfoBean;
import linchom.com.linchomspace.photoutil.NineGridTestLayout;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import linchom.com.linchomspace.shopping.utils.GoodsViewHolder;

public class Mydisclose extends AppCompatActivity {
    private GoodsCommonAdapter<MyDiscloseInfoBean.Databean.DItems> MydiscloseCommonAdapter;
    List<MyDiscloseInfoBean.Databean.DItems> myDisclosList=new ArrayList<MyDiscloseInfoBean.Databean.DItems>();
    private ListView lv_myDisclose;
    private ImageView iv_back;
    private static int mDelId;
    NineGridTestLayout photo_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydisclose);

        lv_myDisclose = ((ListView) findViewById(R.id.lv_myDisclose));
        iv_back = ((ImageView) findViewById(R.id.iv_back));

        lv_myDisclose.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mDelId=position;
                showDialog();
                return false;
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initData();
//      initEnvent();
    }


    private void initData() {
        RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php?");
        requestParams.addBodyParameter("act","user_article");
        requestParams.addBodyParameter("user_id","135");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("onSuccess"+result);

                List<String> urllist=new ArrayList<>();
                if(myDisclosList.get(myDisclosList.size()-1).getPhoto()!=null&&(!"".equals(myDisclosList.get(myDisclosList.size()-1).getPhoto()))){

                    String[] urls=myDisclosList.get(myDisclosList.size()-1).getPhoto().split(",");
                    for (int i = 0; i <urls.length ; i++) {
                        System.out.println("!!!"+urls[i]);
                        if ("0".equals(urls[i])){
                            continue;
                        }

                        urllist.add(urls[i]);
                    }
                    photo_show.setUrlList(urllist);
                }else {
                    urllist.clear();
                    photo_show.setUrlList(urllist);
                }
                Gson gson=new Gson();
                MyDiscloseInfoBean bean=gson.fromJson(result,MyDiscloseInfoBean.class);
                MyDiscloseInfoBean.Databean mdata=bean.data;

                myDisclosList.addAll(mdata.items);

//                System.out.println("789900"+myDisclosList.get(myDisclosList.size()-1).getPhoto());
                initEnvent();
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

    private void initEnvent() {

        MydiscloseCommonAdapter=new GoodsCommonAdapter<MyDiscloseInfoBean.Databean.DItems>(getApplicationContext(),myDisclosList,R.layout.mydisclose_item) {
            @Override
            public void convert(GoodsViewHolder viewHolder, MyDiscloseInfoBean.Databean.DItems dItems, int position) {
                 NineGridTestLayout photo_show = viewHolder.getViewById(R.id.photo_show);
                 photo_show.setIsShowAll(true);
                TextView tv_biaoti=viewHolder.getViewById(R.id.tv_biaoti);
                tv_biaoti.setText(dItems.title);
                TextView discontent=viewHolder.getViewById(R.id.discontent);
                discontent.setText(dItems.content);
                TextView tv_user=viewHolder.getViewById(R.id.tv_user);
                tv_user.setText(dItems.author);
                TextView tv_add_time=viewHolder.getViewById(R.id.tv_add_time);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date=new Date(Long.parseLong((dItems.add_time))*1000);
                tv_add_time.setText(simpleDateFormat.format(date));
            }
        };
        lv_myDisclose.setAdapter(MydiscloseCommonAdapter);
    }

    public void showDialog(){
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

    public void deleteItem(){
        int size = myDisclosList.size();
        if (size>0) {
            myDisclosList.remove(mDelId);
            MydiscloseCommonAdapter.notifyDataSetChanged();
        }
    }
}
