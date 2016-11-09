package linchom.com.linchomspace.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import linchom.com.linchomspace.R;
import linchom.com.linchomspace.login.LoginActivity;

import static linchom.com.linchomspace.MainActivity.instance;

public class Set_activity extends AppCompatActivity {


    @InjectView(R.id.quitLogin)
    Button quitLogin;
    private RelativeLayout rl_person;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_activity);
        ButterKnife.inject(this);

        iv_back = ((ImageView) findViewById(R.id.iv_back));
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
        rl_person = ((RelativeLayout) findViewById(R.id.rl_person));
        rl_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Details_Activity.class);
                startActivity(intent);

            }
        });
    }

    @OnClick(R.id.quitLogin)
    public void onClick() {
        Intent intent =new Intent(Set_activity.this, LoginActivity.class);
        startActivity(intent);
        instance.finish();
        finish();


    }
}