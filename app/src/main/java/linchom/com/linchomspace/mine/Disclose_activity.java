package linchom.com.linchomspace.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.pojo.KindsInfoBean;
import linchom.com.linchomspace.photoutil.UploadBean;
import linchom.com.linchomspace.shopping.goodsadapter.GoodsCommonAdapter;
import me.iwf.photopicker.widget.MultiPickResultView;

public class Disclose_activity extends AppCompatActivity {

    private ImageView iv_disback;
    private TextView b_submit;
    private TextView tv_mine_baoliao;
    private TextView tv_biaoti;
    private GoodsCommonAdapter<KindsInfoBean.DaBean> kindsCommonAdapter;
    List<KindsInfoBean.DaBean> kindslist=new ArrayList<KindsInfoBean.DaBean>();
    List<String> content=new ArrayList<String>();
    private ListView lv_fenlei;

    PopupWindow popupWindow;
    private EditText ed_disclose;
    private TextView tv_user;
    private RelativeLayout rl_fj;
    private MultiPickResultView pic_view;
    ArrayList<String> list_photo;
    private String photoAddress="";
    private int count=0;
    String photo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclose_activity);

//        System.out.println("onCreate");
        ed_disclose = ((EditText) findViewById(R.id.ed_disclose));
        tv_user = ((TextView) findViewById(R.id.tv_user));
        lv_fenlei = ((ListView) findViewById(R.id.lv_fenlei));

        iv_disback = ((ImageView) findViewById(R.id.iv_disback));

        tv_mine_baoliao = ((TextView) findViewById(R.id.tv_mine_baoliao));

        tv_biaoti = ((TextView) findViewById(R.id.tv_biaoti));
        pic_view = ((MultiPickResultView) findViewById(R.id.pic_view));
        pic_view.init(this,MultiPickResultView.ACTION_SELECT,null);

        iv_disback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        b_submit = ((TextView) findViewById(R.id.b_submit));
        b_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list_photo.size()==0){
                    post();
                }else {
                    for (int i = 0; i < list_photo.size(); i++) {
                        photoUpload(i);

                    }
                    }

            }
        });

        getData();
        initData();
//      initEvent();

        tv_biaoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popupView = getLayoutInflater().inflate(R.layout.pop_item, null);
                popupWindow=new PopupWindow(popupView,200,ViewGroup.LayoutParams.MATCH_PARENT);
                ListView lv= (ListView) popupView.findViewById(R.id.lv_fenlei);
//                System.out.println("content========="+content);
                ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(),R.layout.pop_items,content);
                lv.setAdapter(arrayAdapter);
                popupWindow.setTouchable(true);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String name=content.get(position);
                        tv_biaoti.setText(name);
                        popupWindow.dismiss();
                    }
                });
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
                popupWindow.showAsDropDown(v);
            }
        });
    }

    public void initData() {

        tv_mine_baoliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Mydisclose.class);
                startActivity(intent);
            }
        });


//
    }
    public void getData(){
        RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php?act=topic_category");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson=new Gson();
                KindsInfoBean kindsInfoBean=gson.fromJson(result,KindsInfoBean.class);
                kindslist.addAll(kindsInfoBean.data);
//                System.out.println("kindslist"+kindslist);
                //拿到pop里的list内容
                for (int i=0;i<kindslist.size();i++) {
                    String a = kindslist.get(i).topic_category_name;
                    content.add(a);

                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

//                System.out.println("errrr"+ex.getMessage().toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("");
        builder.setTitle("提示");
        builder.setPositiveButton("拍照", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_LONG).show();

            }
        });

        builder.setNegativeButton("从相册选择照片", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    public void post(){
        String biaoti=tv_biaoti.getText().toString();
        String content=ed_disclose.getText().toString();
//                String author=tv_user.getText().toString();
        RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php?act=user_add_article&user_id=135&cat_id=16&title=%E5%BC%A0%E6%99%93%E6%96%87%E7%9A%84%E6%96%87%E7%AB%A0&content=111");
        //requestParams.addBodyParameter("user_id","135");
        requestParams.addBodyParameter("title",biaoti);
        requestParams.addBodyParameter("content",content);
//                requestParams.addBodyParameter("author",author);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
//                        System.out.println("onsucess" + result);

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
//  Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT).show();
        finish();
    }
    private void photoUpload(int i){
        System.out.println(list_photo.get(i));

        RequestParams params = new RequestParams("http://app.linchom.com/appapi.php?act=user_add_article&user_id=135&cat_id=16&title=%E5%BC%A0%E6%99%93%E6%96%87%E7%9A%84%E6%96%87%E7%AB%A0&content=111");

        params.addBodyParameter("act", "uploadimage");

        params.addBodyParameter("goods_img",new File(list_photo.get(i)));
        System.out.println(params);
        x.http().post(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                Gson gson = new Gson();
                UploadBean bean = gson.fromJson(result, UploadBean.class);
                photo=bean.getData();

                photoAddress=photoAddress+photo+",";
                count++;
                System.out.println(photoAddress);
                System.out.println(count);
                if (count==list_photo.size()){
                    System.out.println("xxxxxxx");
                    post();
                }
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        pic_view.onActivityResult(requestCode, resultCode, data);
        list_photo = pic_view.getPhotos();
        //  recycler_view.showPics(list_photo);

        for (int i = 0; i < list_photo.size(); i++) {
            System.out.println(list_photo.get(i));
        }
    }

}



