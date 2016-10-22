package linchom.com.linchomspace.homepage.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import linchom.com.linchomspace.R;

public class NavigationActivity extends AppCompatActivity {

    @InjectView(R.id.iv_navigation_delete)
    ImageView ivNavigationDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.iv_navigation_delete)
    public void onClick() {
       NavigationActivity.this.finish();

    }
}
