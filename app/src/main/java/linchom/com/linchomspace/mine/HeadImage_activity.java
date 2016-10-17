package linchom.com.linchomspace.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import linchom.com.linchomspace.R;

public class HeadImage_activity extends AppCompatActivity {
    private TextView tv_ed;
    private ImageView iv_back;
    private ImageView iv_head;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_image_activity);
        iv_back = ((ImageView) findViewById(R.id.iv_back));

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent=new Intent(getApplicationContext(),Minefragment.class);
                startActivity(intent);*/
                finish();
            }
        });

        tv_ed = ((TextView) findViewById(R.id.tv_ed));
        tv_ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Details_s_Activity.class);
                startActivity(intent);
            }
        });
    }
}
