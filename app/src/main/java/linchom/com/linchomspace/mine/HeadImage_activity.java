package linchom.com.linchomspace.mine;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.pojo.UserInfoBean;
import linchom.com.linchomspace.photoutil.UploadBean;

public class HeadImage_activity extends AppCompatActivity {

    public static final String TAG =" Details_Activity" ;
    private ImageView iv_back1;
    private EditText tv_name;
    private TextView tv_sex;
    private TextView tv_birthday;
    private EditText tv_email;
    private EditText tv_office_phone;
    private EditText tv_home_phone;
    private EditText tv_mobile_phone;
    private TextView tv_ok;
    private RelativeLayout tv_add;
    UserInfoBean.DataBean dataBean;
    private RelativeLayout rl_birthday;
    private  ImageView iv_user_photo;
    private int m_year, m_month, m_day;
    private Calendar c;

    private String photoAddress="";
    String photo="";

    private static final String IMAGE_FILE_LOCATION = "file:///sdcard/temp.jpg";//temp file
    Uri imageUri = Uri.parse(IMAGE_FILE_LOCATION);//The Uri to store the big bitmap
    private File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_image_activity);

        //判断sd卡是否存在，存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //目录，文件名Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            file = new File(Environment.getExternalStorageDirectory(), getPhotoFileName());
            imageUri = Uri.fromFile(file);
        }

         iv_user_photo=((ImageView) findViewById(R.id.iv_user_photo));
        tv_name= ((EditText)findViewById(R.id.tv_name));
        tv_sex = ((TextView) findViewById(R.id.tv_add));
        tv_birthday = ((TextView) findViewById(R.id.tv_birthday1));
        tv_email = ((EditText) findViewById(R.id.tv_email));
        tv_office_phone = ((EditText) findViewById(R.id.tv_office_phone));
        tv_home_phone = ((EditText) findViewById(R.id.tv_home_phone));
        tv_mobile_phone = ((EditText) findViewById(R.id.tv_mobile_phone));
        showDialog();
        rl_birthday = ((RelativeLayout) findViewById(R.id.rl_birthday));
        rl_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println("rl_birthday");
                showDialog(0);
            }
        });

        tv_add = ((RelativeLayout) findViewById(R.id.rl_add));
        iv_user_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选择封面
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setType("image/*");
                /*intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                        "image*//*");*/
                startActivityForResult(intent,1);

            }
        });
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showView();
            }
        });

//        System.out.println("onCreate");

        iv_back1 = ((ImageView) findViewById(R.id.iv_back1));
        iv_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        tv_ok = ((TextView) findViewById(R.id.tv_ok));
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Details_Activity.class);
                Bundle bundle=new Bundle();
                upData();
//                uploadImage();
//                System.out.println("dataBean"+dataBean);
                bundle.putSerializable("user",dataBean);
                intent.putExtras(bundle);
                //startActivityForResult(intent,2);
                setResult(2,intent);
                finish();//标识
            }
        });

        initEvent();

        getData();
    }
    public void showDialog(){
        c = Calendar.getInstance();// 获取日历的实例
        m_year = c.get(Calendar.YEAR);// 年
        m_month = c.get(Calendar.MONTH);// 月
        m_day = c.get(Calendar.DAY_OF_MONTH);// 日
        System.out.println("-"+m_year+"-"+m_month+"-"+m_day);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == 0) {
            return new DatePickerDialog(this, datePickerButtonListener, m_year,
                    m_month, m_day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerButtonListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // 将当前的设置的时间赋值到文本框中
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            tv_birthday.setText(df.format(c.getTime()));

        }
    };

    public void getData(){
        Intent intent=getIntent();
        dataBean= (UserInfoBean.DataBean) intent.getSerializableExtra("user");
        System.out.println("dataBean"+dataBean);
        if (dataBean!=null) {
            tv_name.setText(dataBean.getName());
            tv_sex.setText(dataBean.getSex().equals("1") ? "男" : "女");
            tv_birthday.setText(dataBean.getBirthday());
            tv_email.setText(dataBean.getEmail());
            tv_home_phone.setText(dataBean.getHome_phone());
            tv_office_phone.setText(dataBean.getOffice_phone());
            tv_mobile_phone.setText(dataBean.getMobile_phone());
        }
    }

    private void initEvent() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case 1:

                if (data != null) {
                    crop(data.getData());

                }
                break;
            case 2:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    System.out.println("extras"+extras);
                    if (extras != null) {
                        Bitmap bitmap = extras.getParcelable("data");
                        System.out.println("data"+data);
                        System.out.println("bitmap"+bitmap);
                        showImage(bitmap);
                    }
                }
                break;

        }
    }

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return sdf.format(date) + ".png";
    }

    String s=null;
    //显示图片，上传服务器
    public void showImage(Bitmap bitmap) {
        iv_user_photo.setImageBitmap(bitmap);//iv显示图片
        saveImage(bitmap);//保存文件
        System.out.println("file"+file);
        System.out.println("begin");
       /* FileInputStream inputStream= null;
        try {
            FileInputStream is = new FileInputStream(file+"");
            byte[] b = new byte[9024];
            int len;
            while((len=is.read(b))!=-1){
                s=new String(b,0,len);
                System.out.println(s);
            }
            is.close();
            System.out.println("end");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //uploadImage();//上传服务器
    }

    public void uploadImage(){

        RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php");
        requestParams.addBodyParameter("act", "uploadimage");
        requestParams.addBodyParameter("photo",new File(file+""));
        requestParams.addBodyParameter("image",s);

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                Gson gson = new Gson();
                UploadBean bean = gson.fromJson(result, UploadBean.class);
                photo=bean.getData();
                photoAddress=photoAddress+photo+"";
                System.out.println(photo);
//                upData(photo);


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

    public void crop(Uri uri){
        //  intent.setType("image/*");
        //裁剪
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        intent.putExtra("crop", "true");

        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 2);
    }

    public void saveImage(Bitmap bitmap) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
    }

    public void upData(){

        String name=tv_name.getText().toString();
        String sex=tv_sex.getText().toString();
        String birthday= tv_birthday.getText().toString();
        String[] birthdaies = birthday.split("-");
        final String email=tv_email.getText().toString();
        String homePhone=tv_home_phone.getText().toString();
        String officePhone=tv_office_phone.getText().toString();
        String mobilePhone=tv_mobile_phone.getText().toString();
        dataBean=new UserInfoBean.DataBean(name,email,sex,birthday,officePhone,homePhone,mobilePhone);
//        Gson gson=new Gson();
//        String userJson=gson.toJson(dataBean);
        RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php?act=edituserinfo");
            requestParams.addBodyParameter("user_id","135");
//            requestParams.addQueryStringParameter("user",userJson);
        requestParams.addQueryStringParameter("email",email);
        requestParams.addQueryStringParameter("name",name);
        requestParams.addQueryStringParameter("sex",sex.equals("男")?"1":"0");
        requestParams.addQueryStringParameter("birthdayYear",birthdaies[0]);
        requestParams.addQueryStringParameter("birthdayMonth",birthdaies[1]);
        requestParams.addQueryStringParameter("birthdayDay",birthdaies[2]);
        requestParams.addQueryStringParameter("birthday",birthday);
        requestParams.addQueryStringParameter("photo",photo);
        requestParams.addBodyParameter("office_phone",officePhone);
        requestParams.addBodyParameter("mobile_phone",mobilePhone);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

//               System.out.println("6666666666666666"+result);

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("error=="+ex+"");

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    public void showView() {
        final CharSequence[] items = {"男", "女"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(items,-1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        tv_sex.setText("男");
                        break;
                    case 1:
                        tv_sex.setText("女");
                        break;
                }
                dialog.dismiss();
            }
        });
        builder.show();


    }

}