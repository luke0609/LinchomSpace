package linchom.com.linchomspace.mine.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import linchom.com.linchomspace.R;

/**
 * Created by lenovo on 2016/10/20.
 */
public class vidio_fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.mine_vidiofragment,null);
        return view;
    }
}
