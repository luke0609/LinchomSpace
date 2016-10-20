package linchom.com.linchomspace.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import linchom.com.linchomspace.R;

public class Active_activity extends AppCompatActivity {

    private ListView lv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_activity);
        lv_image = ((ListView) findViewById(R.id.lv_image));


    }
}
