package linchom.com.linchomspace.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import linchom.com.linchomspace.R;

public class Alerts_activity extends AppCompatActivity {

    private ImageView iv_alertback;
    private RelativeLayout rl_notify1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts_activity);

        iv_alertback = ((ImageView) findViewById(R.id.iv_alertback));
        rl_notify1 = ((RelativeLayout) findViewById(R.id.rl_notify1));

        iv_alertback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rl_notify1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Information_activity.class);
                startActivity(intent);
            }
        });
    }


}
