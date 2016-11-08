package linchom.com.linchomspace.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.ButterKnife;
import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.pojo.UserInfoBean;

public class Details_Activity extends AppCompatActivity {

    //相机拍摄照片和视频的标准目录
//    private File file;
//    private Uri imageUri;
//
//    String items[]={"相册选择","拍照"};
//    public static final int SELECT_PIC=11;
//    public static final int TAKE_PHOTO=12;
//    public static final int CROP=13;

    UserInfoBean.DataBean userInfoBean;
    public static final String TAG = " Details_Activity";


    private ImageView iv_back;
    private ImageView iv_user_photo;
    private TextView tv_ed;
    private TextView tv_name;
    private TextView tv_user_name;
    private TextView tv_sex;
    private TextView tv_birthday;
    private TextView tv_email;
    private TextView tv_office_phone;
    private TextView tv_home_phone;
    private TextView tv_mobile_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        System.out.println("onCreate");

        tv_user_name = ((TextView) findViewById(R.id.tv_user_name));
        tv_name = ((TextView) findViewById(R.id.tv_name));
        tv_sex = ((TextView) findViewById(R.id.tv_add));
        tv_birthday = ((TextView) findViewById(R.id.tv_birthday));
        tv_email = ((TextView) findViewById(R.id.tv_email));
        iv_user_photo = ((ImageView) findViewById(R.id.iv_user_photo));
        tv_office_phone = ((TextView) findViewById(R.id.tv_office_phone));
        tv_mobile_phone = ((TextView) findViewById(R.id.tv_mobile_phone));
        tv_home_phone = ((TextView) findViewById(R.id.tv_home_phone));
        tv_ed = ((TextView) findViewById(R.id.tv_ed));

        tv_ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HeadImage_activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", userInfoBean);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

//        iv_user_photo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AlertDialog.Builder(getApplicationContext()).setTitle("选择").setItems(items,new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // TODO Auto-generated method stub
//
//                        switch(which){
//                            case 0:
//
//                                //相册选择
//                                Intent intent = new Intent(Intent.ACTION_PICK, null);
//                                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                                        "image/*");
//                                startActivityForResult(intent, SELECT_PIC);
//
//                                break;
//
//
//                            case 1:
//
//                                //拍照:
//                                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//                                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
//                                startActivityForResult(intent2,TAKE_PHOTO);
//
//                                break;
//                        }
//                    }
//                }).show();
//
//            }
//        });

        initData();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("userInfoBean111" + userInfoBean);
        if (requestCode == 2 && resultCode == 2) {
            Bundle bundle = data.getExtras();
            userInfoBean = (UserInfoBean.DataBean) bundle.getSerializable("user");
            System.out.println("userInfoBean" + userInfoBean);
            tv_user_name.setText(userInfoBean.getUser_name());
        }
    }

//    public void getData(){
//        String name=tv_user_name.getText().toString();
//        String sex=tv_sex.getText().toString();
//        String birthday=tv_birthday.getText().toString();
//        String email=tv_email.getText().toString();
//        String office_phone=tv_office_phone.getText().toString();
//        String home_office=tv_home_phone.getText().toString();
//        String mobile_phone=tv_mobile_phone.getText().toString();
//        userInfoBean=new UserInfoBean.DataBean(name,email,sex,birthday,office_phone,home_office,mobile_phone);
//    }

    public void initData() {

        RequestParams requestParams = new RequestParams("http://app.linchom.com/appapi.php?act=userinfo&user_id=135");
//        requestParams.addBodyParameter("key", "linchom");
//       requestParams.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
//        requestParams.addBodyParameter("user_id", "135");

        x.http().post(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
//                System.out.println("onsucess" + result);
                Gson gson = new Gson();
                UserInfoBean bean = gson.fromJson(result, UserInfoBean.class);
//                System.out.println("======"+bean);
//                System.out.println(bean.getResult());
                userInfoBean = bean.getData();
                System.out.println("======" + userInfoBean.getPhoto());
                x.image().bind(iv_user_photo,userInfoBean.getPhoto());
                tv_mobile_phone.setText(userInfoBean.getMobile_phone());
                tv_name.setText(userInfoBean.getName());
                tv_birthday.setText(userInfoBean.getBirthday());
                tv_email.setText(userInfoBean.getEmail());
                tv_office_phone.setText(userInfoBean.getOffice_phone());
                tv_home_phone.setText(userInfoBean.getHome_phone());
                tv_sex.setText((userInfoBean.getSex().equals("1")) ? "男" : "女");

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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