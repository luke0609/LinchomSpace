package linchom.com.linchomspace;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import linchom.com.linchomspace.chat.ChatFragment;
import linchom.com.linchomspace.chat.util.StatusBarCompat;
import linchom.com.linchomspace.homepage.Fragment.HomeFragment;
import linchom.com.linchomspace.mine.MineFragment;
import linchom.com.linchomspace.service.ServiceFragment;
import linchom.com.linchomspace.shopping.ShoppingFragment;


public class MainActivity extends AppCompatActivity {
    int i=5;
    private long exitTime = 0;
    Fragment[] fragments;
    HomeFragment homeFragment;//主页
    ShoppingFragment shoppingFragment;//商城
    ChatFragment chatFragment;//交流
    MineFragment mineFragment ;//个人中心
    ServiceFragment serviceFragment;//服务
    //按钮的数组，一开始第一个按钮被选中
    RadioButton[] tabs;
    int oldIndex;//用户看到的item
    int newIndex;//用户即将看到的item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarCompat.compat(this,Color.parseColor("#4EAFAB"));



        //初始化fragment
        homeFragment=new HomeFragment();
        shoppingFragment=new ShoppingFragment();
        chatFragment=new ChatFragment();
        mineFragment=new MineFragment();
        serviceFragment=new ServiceFragment();
        //所有fragment的数组
        fragments=new Fragment[]{homeFragment,serviceFragment,shoppingFragment,chatFragment,mineFragment};



        //设置按钮的数组
        tabs=new RadioButton[5];
        tabs[0]=(RadioButton) findViewById(R.id.rb_rb1);//主页的button
        tabs[1]=(RadioButton) findViewById(R.id.rb_rb2);//服务的button
        tabs[2]=(RadioButton) findViewById(R.id.rb_rb3);//商城的button
        tabs[3]=(RadioButton) findViewById(R.id.rb_rb4);//交流的button
        tabs[4]=(RadioButton) findViewById(R.id.rb_rb5);//我的的button
        //界面初始显示第一个fragment;添加第一个fragment
        getSupportFragmentManager().beginTransaction().add(R.id.f1_content, fragments[0]).commit();
        //初始时，按钮1选中
        tabs[0].setChecked(true);
        int id=getIntent().getIntExtra("id",0);
        int idchat=getIntent().getIntExtra("idchat",0);
        int idpchat=getIntent().getIntExtra("idpchat",0);
        String pdId=getIntent().getStringExtra("pdId");
        String chatId=getIntent().getStringExtra("chatId");
        String pchatId=getIntent().getStringExtra("pchatId");
        //Toast.makeText(MainActivity.this,pdId+id,Toast.LENGTH_SHORT).show();
        if(id==1){
            Bundle bundle=new Bundle();
            bundle.putString("PdId",pdId);
            fragments[0].setArguments(bundle);
            //this.getSupportFragmentManager().beginTransaction().replace(R.id.f1_content, fragments[0]).commit();
           addshow(0);
            tabs[0].setChecked(true);
        }
        if(idchat==1){
            Bundle bundle=new Bundle();
            bundle.putString("chatId",chatId);
            fragments[3].setArguments(bundle);
            addshow(3);
            tabs[3].setChecked(true);
            //this.getSupportFragmentManager().beginTransaction().replace(R.id.f1_content, fragments[3]).commit();
        }
        if(idpchat==1){
            Bundle bundle=new Bundle();
            bundle.putString("chatId",pchatId);
            fragments[3].setArguments(bundle);
            addshow(3);
            tabs[3].setChecked(true);
            //this.getSupportFragmentManager().beginTransaction().replace(R.id.f1_content, fragments[3]).commit();
        }

    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//      if(resultCode==103){
//          this.getSupportFragmentManager().beginTransaction().replace(R.id.f1_content, fragments[0]).commit();
//          tabs[0].setSelected(true);
//      }
//    }

    //按钮的点击事件:选中不同的按钮，不同的fragment显示
    public void onTabClicked(View view) {

        //点击按钮时，表示选中不同的项
        switch(view.getId()){
            case R.id.rb_rb1:
                newIndex=0;//选中第一项
                break;
            case R.id.rb_rb2:
                newIndex=1;//选中第二项
                break;
            case R.id.rb_rb3:
                newIndex=2;//选中第三项
                break;
            case R.id.rb_rb4:
                newIndex=3;//选中第四项
                break;
            case R.id.rb_rb5:
                newIndex=4;//选中第五项
                break;
        }
        addshow(newIndex);
    }

   public void addshow(int newIndex){
       FragmentTransaction transaction;

       //如果选择的项不是当前选中项，则替换；否则，不做操作
       if(newIndex!=oldIndex){
           transaction=getSupportFragmentManager().beginTransaction();
           transaction.hide(fragments[oldIndex]);//隐藏当前显示项


           //如果选中项没有加过，则添加
           if(!fragments[newIndex].isAdded()){
               //添加fragment
               transaction.add(R.id.f1_content,fragments[newIndex]);
           }
           //显示当前选择项
           transaction.show(fragments[newIndex]).commit();
       }


       //之前选中的项，取消选中
       tabs[oldIndex].setSelected(false);
       //当前选择项，按钮被选中
       tabs[newIndex].setSelected(true);


       //当前选择项变为选中项
       oldIndex=newIndex;

   }



    //再按一次退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:

        }
        return super.onTouchEvent(event);
    }
}
