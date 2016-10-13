package linchom.com.linchomspace.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import linchom.com.linchomspace.R;

public class RetrieveActivity extends AppCompatActivity {
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        back = ((ImageView) findViewById(R.id.iv_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RetrieveActivity.this,LoginActivity.class);
                startActivity(intent);
                RetrieveActivity.this.finish();
            }
        });
    }
}
