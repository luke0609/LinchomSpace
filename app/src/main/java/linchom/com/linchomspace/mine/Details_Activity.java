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


    public static final String TAG = " Details_Activity";

    private ImageView iv_back;
    private ImageView iv_user_photo;
    private TextView tv_ed;
    private EditText tv_user_name;
    private EditText tv_sex;
    private EditText tv_qq;
    private EditText tv_birthday;
    private EditText tv_email;

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
        tv_qq = ((EditText) findViewById(R.id.tv_qq));
        tv_email = ((EditText) findViewById(R.id.tv_email));


        iv_back = ((ImageView) findViewById(R.id.iv_back));
        iv_user_photo = ((ImageView) findViewById(R.id.iv_user_photo));
        tv_ed = ((TextView) findViewById(R.id.tv_ed));

        tv_ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                upData();
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

                TextView tv_qq = ((TextView) findViewById(R.id.tv_qq));
                tv_qq.setText(dataBean.getQq());

                TextView tv_email = (TextView) findViewById(R.id.tv_email);
                tv_email.setText(dataBean.getEmail());
                TextView tv_sex = ((TextView) findViewById(R.id.tv_add));
                tv_sex.setText(dataBean.getSex().equals("1")?"男":"女");
//                System.out.println("修改成功"+dataBean);


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

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void upData(){

        String username= tv_user_name.getText().toString();
        String sex=tv_sex.getText().toString();
        String birthday=tv_birthday.getText().toString();
        String[] birthdaies = birthday.split("-");
//         String qq=tv_qq.getText().toString();
        String email=tv_email.getText().toString();

//         UserInfoBean.DataBean mUser=new UserInfoBean.DataBean(username,sex,birthday,email);

        Gson gson=new Gson();
//         String userJson=gson.toJson(mUser);

        RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php?act=edituserinfo&user_id=135");

        //requestParams.addBodyParameter("user_name","张晓文");

        //requestParams.addQueryStringParameter("mUser",userJson);

        requestParams.addQueryStringParameter("email",email);
        requestParams.addQueryStringParameter("user_name",username);
        requestParams.addQueryStringParameter("sex",sex);
        requestParams.addQueryStringParameter("birthdayYear",birthdaies[0]);
        requestParams.addQueryStringParameter("birthdayMonth",birthdaies[1]);
        requestParams.addQueryStringParameter("birthdayDay",birthdaies[2]);

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

//                System.out.println("onsucess" + result);
                Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
//                Log.i(TAG,result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.e("error",ex.getMessage().toString());
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                if (ex instanceof HttpException) { // 网络错误
                    HttpException httpEx = (HttpException) ex;
                    int responseCode = httpEx.getCode();
                    String responseMsg = httpEx.getMessage();
                    String errorResult = httpEx.getResult();
                    Log.e("网络错误",ex.getMessage().toString());
                    // ...
                } else { // 其他错误

                    Log.e("其他错误",ex.getMessage().toString());
                    // ...
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

                initData();

                finish();
            }
        });
    }
}