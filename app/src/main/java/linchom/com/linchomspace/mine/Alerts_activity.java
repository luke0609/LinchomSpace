package linchom.com.linchomspace.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import linchom.com.linchomspace.R;

public class Alerts_activity extends AppCompatActivity {

    private ImageView iv_alertback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts_activity);

        iv_alertback = ((ImageView) findViewById(R.id.iv_alertback));
        iv_alertback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();


            }
        });
    }
}
