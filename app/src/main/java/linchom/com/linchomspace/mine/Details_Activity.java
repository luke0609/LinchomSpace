package linchom.com.linchomspace.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import linchom.com.linchomspace.R;

public class Details_Activity extends AppCompatActivity {


    private TextView tv_ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

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

