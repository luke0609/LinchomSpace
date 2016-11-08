package linchom.com.linchomspace.mine;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import linchom.com.linchomspace.R;
import linchom.com.linchomspace.mine.pojo.UserInfoBean;

public class HeadImage_activity extends AppCompatActivity {

    public static final String TAG =" Details_Activity" ;
    private ImageView iv_back1;
    private EditText tv_user_name;
    private TextView tv_sex;
    private TextView tv_birthday;
    private EditText tv_email;
    private EditText tv_office_phone;
    private EditText tv_home_phone;
    private ImageView iv_photo;
    private EditText tv_mobile_phone;
    private TextView tv_ok;
    private RelativeLayout tv_add;
    UserInfoBean.DataBean dataBean;
    private RelativeLayout rl_birthday;
    private int m_year, m_month, m_day;
    private Calendar c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_image_activity);
        iv_photo = ((ImageView) findViewById(R.id.iv_photo));
        tv_user_name = ((EditText)findViewById(R.id.tv_user_name));
        tv_sex = ((TextView) findViewById(R.id.tv_add));
        tv_birthday = ((TextView) findViewById(R.id.tv_birthday1));
        tv_email = ((EditText) findViewById(R.id.tv_email));
        tv_office_phone = ((EditText) findViewById(R.id.tv_office_phone));
        tv_home_phone = ((EditText) findViewById(R.id.tv_home_phone));
        tv_mobile_phone = ((EditText) findViewById(R.id.tv_mobile_phone));
        showDialog();
        rl_birthday = ((RelativeLayout) findViewById(R.id.rl_birthday));
        rl_birthday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                System.out.println("rl_birthday");
                showDialog(0);
            }
        });

        tv_add = ((RelativeLayout) findViewById(R.id.rl_add));
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showView();
            }
        });

//        System.out.println("onCreate");

        iv_back1 = ((ImageView) findViewById(R.id.iv_back1));
        iv_back1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
//                System.out.println("onCreatefinish");
            }
        });

        tv_ok = ((TextView) findViewById(R.id.tv_ok));
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Details_Activity.class);
                Bundle bundle=new Bundle();
                upData();
                System.out.println("dataBean"+dataBean);
                bundle.putSerializable("user",dataBean);
                intent.putExtras(bundle);
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
        tv_user_name.setText(dataBean.getUser_name());
        tv_sex.setText(dataBean.getSex().equals("1")?"男":"女");
        tv_birthday.setText(dataBean.getBirthday());
        tv_email.setText(dataBean.getEmail());
        tv_home_phone.setText(dataBean.getHome_phone());
        tv_office_phone.setText(dataBean.getOffice_phone());
        tv_mobile_phone.setText(dataBean.getMobile_phone());
    }

    private void initEvent() {

    }


    public void upData(){

        String name=tv_user_name.getText().toString();
        String sex=tv_sex.getText().toString();
        String birthday= tv_birthday.getText().toString();
        String[] birthdaies = birthday.split("-");
        final String email=tv_email.getText().toString();
        String homePhone=tv_home_phone.getText().toString();
        String officePhone=tv_office_phone.getText().toString();
        String mobilePhone=tv_mobile_phone.getText().toString();
        dataBean=new UserInfoBean.DataBean(name,email,sex,birthday,officePhone,homePhone,mobilePhone);
//        Gson gson=new Gson();
////      String userJson=gson.toJson(mUser);

        RequestParams requestParams=new RequestParams("http://app.linchom.com/appapi.php?act=edituserinfo&user_id=135");

        //requestParams.addBodyParameter("user_name","张晓文");
        //requestParams.addQueryStringParameter("mUser",userJson);

        //requestParams.addQueryStringParameter("email",email);
        requestParams.addQueryStringParameter("user_name",name);
        requestParams.addQueryStringParameter("sex",sex.equals("男")?"1":"0");
        /*requestParams.addQueryStringParameter("birthdayYear",birthdaies[0]);
        requestParams.addQueryStringParameter("birthdayMonth",birthdaies[1]);
        requestParams.addQueryStringParameter("birthdayDay",birthdaies[2]);*/

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                System.out.println("6666666666666666"+result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("error=="+ex+"");

//                Log.e("error",ex.getMessage().toString());
//                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
//                if (ex instanceof HttpException) { // 网络错误
//                    HttpException httpEx = (HttpException) ex;
//                    int responseCode = httpEx.getCode();
//                    String responseMsg = httpEx.getMessage();
//                    String errorResult = httpEx.getResult();
//                    Log.e("网络错误",ex.getMessage().toString());
//                    // ...
//                } else { // 其他错误
//
//                    Log.e("其他错误",ex.getMessage().toString());
//                    // ...
//                }
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
//        new AlertDialog.Builder(this).setTitle("单选框").setIcon(
//                android.R.drawable.ic_dialog_info).setSingleChoiceItems(
//                new String[] { "Item1", "Item2" }, 0,
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
////                        dialog.dismiss();
//                    }
//                }).setNegativeButton("取消", null).show();

    }

}