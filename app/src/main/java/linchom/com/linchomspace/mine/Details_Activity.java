package linchom.com.linchomspace.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;


import linchom.com.linchomspace.MainActivity;
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
    private EditText tv_user_name;
    private EditText tv_sex;
    private EditText tv_birthday;
    private EditText tv_email;
    private EditText tv_office_phone;
    private EditText tv_home_phone;
    private EditText tv_mobile_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
//        System.out.println("onCreate");


        //判断sd卡是否存在，存在
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            //目录，文件名Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
//            file = new File(Environment.getExternalStorageDirectory(), getPhotoFileName());
//            imageUri = Uri.fromFile(file);
//        }

        tv_user_name = ((EditText)findViewById(R.id.tv_user_name));
        tv_sex = ((EditText) findViewById(R.id.tv_add));
        tv_birthday = ((EditText) findViewById(R.id.tv_birthday));
        tv_email = ((EditText) findViewById(R.id.tv_email));
        iv_user_photo = ((ImageView) findViewById(R.id.iv_user_photo));
        tv_office_phone = ((EditText) findViewById(R.id.tv_office_phone));
        tv_mobile_phone = ((EditText) findViewById(R.id.tv_mobile_phone));
        tv_home_phone = ((EditText) findViewById(R.id.tv_home_phone));


//        iv_back = ((ImageView) findViewById(R.id.iv_back));
//        iv_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
//                intent.putExtra("mine",5);
//                startActivity(intent);
//
//            }
//        });

        tv_ed = ((TextView) findViewById(R.id.tv_ed));

        tv_ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),HeadImage_activity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("user",userInfoBean);
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
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void getData(){
        String name=tv_user_name.getText().toString();
        String sex=tv_sex.getText().toString();
        String birthday=tv_birthday.getText().toString();
        String email=tv_email.getText().toString();
        String office_phone=tv_office_phone.getText().toString();
        String home_office=tv_home_phone.getText().toString();
        String mobile_phone=tv_mobile_phone.getText().toString();
        userInfoBean=new UserInfoBean.DataBean(name,email,sex,birthday,office_phone,home_office,mobile_phone);
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case SELECT_PIC:
//                //相册选择
//                if (data != null) {
//                    crop(data.getData());
//
//                }
//
//                break;
//            case TAKE_PHOTO:
//                crop(Uri.fromFile(file));
//                break;
//
//
//            case CROP:
//                if (data != null) {
//                    Bundle extras = data.getExtras();
//                    if (extras != null) {
//
//                        Bitmap bitmap = extras.getParcelable("data");
//                        showImage(bitmap);
//                    }
//                }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

//    private String getPhotoFileName() {
//        Date date = new Date(System.currentTimeMillis());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
//        return sdf.format(date) + ".png";
//    }

//    public void crop(Uri uri){
//        //  intent.setType("image/*");
//        //裁剪
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//
//        intent.putExtra("crop", "true");
//
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//
//        intent.putExtra("outputX", 200);
//        intent.putExtra("outputY", 200);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, CROP);
//    }

    //显示图片，上传服务器
//    public void showImage(Bitmap bitmap){
//        iv_user_photo.setImageBitmap(bitmap);//iv显示图片
//        saveImage(bitmap);//保存文件
//        uploadImage();//上传服务器
//
//    }

    //将bitmap保存在文件中
//    public void saveImage(Bitmap bitmap){
//        FileOutputStream fos=null;
//        try {
//            fos=new FileOutputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        bitmap.compress(Bitmap.CompressFormat.JPEG,50,fos);
//    }

    //上传图片
//    public void uploadImage(){
//
//        RequestParams requestParams=new RequestParams("uploadimageservlet");
//        requestParams.setMultipart(true);
//        requestParams.addBodyParameter("file",file);
//
//        x.http().post(requestParams, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Log.i("ModifyPersonInfo", "onSuccess: ");
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//
//    }

    public void initData() {

        RequestParams requestParams = new RequestParams("http://app.linchom.com/appapi.php?act=userinfo&user_id=135");
//        requestParams.addBodyParameter("key", "linchom");
//       requestParams.addBodyParameter("verification", "e0d017ef76c8510244ebe0191f5dde15");
//        requestParams.addBodyParameter("user_id", "135");

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            private TextView tv_home_phone;

            @Override
            public void onSuccess(String result) {
//                System.out.println("onsucess" + result);
                Gson gson = new Gson();
                UserInfoBean bean = gson.fromJson(result, UserInfoBean.class);
//                System.out.println("======"+bean);
//                System.out.println(bean.getResult());
                UserInfoBean.DataBean dataBean = bean.getData();
                TextView tv_mobile_phone = ((TextView) findViewById(R.id.tv_mobile_phone));
                tv_mobile_phone.setText(dataBean.getMobile_phone());
                TextView tv_user_name = ((TextView) findViewById(R.id.tv_user_name));
                tv_user_name.setText(dataBean.getUser_name());
                TextView tv_birthday = ((TextView) findViewById(R.id.tv_birthday));
                tv_birthday.setText(dataBean.getBirthday());
                TextView tv_email = (TextView) findViewById(R.id.tv_email);
                tv_email.setText(dataBean.getEmail());
                TextView tv_office_phone=(TextView) findViewById(R.id.tv_office_phone);
                tv_office_phone.setText(dataBean.getOffice_phone());
                TextView tv_home_phone = ((TextView) findViewById(R.id.tv_home_phone));
                tv_home_phone.setText(dataBean.getHome_phone());
                TextView tv_sex = ((TextView) findViewById(R.id.tv_add));
                tv_sex.setText((dataBean.getSex().equals("0"))?"男":"女");


                getData();
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