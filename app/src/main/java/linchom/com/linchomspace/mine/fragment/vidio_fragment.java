package linchom.com.linchomspace.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import linchom.com.linchomspace.R;

/**
 * Created by lenovo on 2016/10/20.
 */
public class vidio_fragment extends Fragment {


    private RadioButton rb_vidio;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.mine_vidiofragment,container,false);
//        rb_vidio = ((RadioButton) view.findViewById(R.id.rb_vidio));
//        rb_vidio.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "你uwiuoqiwjoiqjowiio", Toast.LENGTH_LONG).show();
//
//            }
//        });

        return view;
    }
}
