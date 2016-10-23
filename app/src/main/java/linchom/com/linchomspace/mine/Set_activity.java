package linchom.com.linchomspace.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import linchom.com.linchomspace.R;

public class Set_activity extends AppCompatActivity {


    private RelativeLayout rl_person;
    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_activity);

        iv_back = ((ImageView)findViewById(R.id.iv_back));
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
                Intent intent=new Intent(getApplicationContext(),Details_Activity.class);
                startActivity(intent);

            }
        });
    }
}