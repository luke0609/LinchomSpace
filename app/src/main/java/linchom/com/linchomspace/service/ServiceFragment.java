package linchom.com.linchomspace.service;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import linchom.com.linchomspace.R;

public class ServiceFragment extends Fragment {

    View view_main;
    private ViewPager vp_service;
    private List<View> viewList = new ArrayList<View>();
    private LayoutInflater inflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view_main=  inflater.inflate(R.layout.fragment_service, container, false);
        initView();
        return view_main;
    }

    private void initView() {


        vp_service = ((ViewPager) view_main.findViewById(R.id.vp_service));
        inflater = LayoutInflater.from(getContext());
               //获取四个view
               View view1 = inflater.inflate(R.layout.service_require_layout, null);
               View view2 = inflater.inflate(R.layout.service_service_layout, null);
            viewList.add(view1);
            viewList.add(view2);

        vp_service.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);

            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));

            }
        });
    }


}