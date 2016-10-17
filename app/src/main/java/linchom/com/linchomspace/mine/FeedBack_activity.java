package linchom.com.linchomspace.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import linchom.com.linchomspace.R;

public class FeedBack_activity extends AppCompatActivity {

    private ImageView iv_fkback;
    private Button b_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_activity);

        b_ok = ((Button) findViewById(R.id.b_ok));
        b_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"反馈提交成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        iv_fkback = ((ImageView) findViewById(R.id.iv_fkback));
        iv_fkback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
