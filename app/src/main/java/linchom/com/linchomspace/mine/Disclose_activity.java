package linchom.com.linchomspace.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import linchom.com.linchomspace.R;

public class Disclose_activity extends AppCompatActivity {

    private ImageView iv_disback;
    private TextView b_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclose_activity);


        iv_disback = ((ImageView) findViewById(R.id.iv_disback));
        iv_disback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        b_submit = ((TextView) findViewById(R.id.b_submit));
        b_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"提交成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
