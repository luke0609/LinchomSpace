package linchom.com.linchomspace.mine;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import linchom.com.linchomspace.R;

public class FeedBack_activity extends AppCompatActivity {

    private ImageView iv_fkback;
    private Button b_ok;
    private EditText et_dis;
    private ImageView iv_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_activity);

        et_dis = ((EditText) findViewById(R.id.et_dis));

        iv_post = ((ImageView) findViewById(R.id.iv_post));
        b_ok = ((Button) findViewById(R.id.b_ok));

        iv_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
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


        b_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String msg=et_dis.getText().toString();

                RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php?act=user_add_customer&user_id=135&content=321321");
                requestParams.addQueryStringParameter("msg",msg);
                x.http().post(requestParams, new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String result) {

//                        System.out.println("onsucess"+result);
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

                Toast.makeText(getApplicationContext(),"反馈提交成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        iv_fkback = ((ImageView) findViewById(R.id.iv_fkback));
        iv_fkback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
