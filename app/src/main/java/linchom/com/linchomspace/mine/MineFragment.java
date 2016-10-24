package linchom.com.linchomspace.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import linchom.com.linchomspace.R;


public class MineFragment extends Fragment {


    private RelativeLayout iv_store;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_mine,null);
        ((ImageView)view.findViewById(R.id.iv_head)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Details_Activity.class);
                startActivity(intent);
            }
        });
        ((TextView)view.findViewById(R.id.tv_name)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Details_Activity.class);
                startActivity(intent);
            }
        });
        ((Button) view.findViewById(R.id.b_loved)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Loved_activity.class);
                startActivity(intent);
            }
        });
        ((Button) view.findViewById(R.id.b_set)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Set_activity.class);
                startActivity(intent);
            }
        });
        ((RelativeLayout) view.findViewById(R.id.person_center_content)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Active_activity.class);
                startActivity(intent);
            }
        });
        ((RelativeLayout)view.findViewById(R.id.person_center_content1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SaveMoney_activity.class);
                startActivity(intent);
            }
        });

        ((RelativeLayout) view.findViewById(R.id.person_center_content2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Indent_activity.class);
                startActivity(intent);
            }
        });
        ((RelativeLayout) view.findViewById(R.id.person_center_content3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyChat_activity.class);
                startActivity(intent);
            }
        });
        ((RelativeLayout) view.findViewById(R.id.person_center_content4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Disclose_activity.class);
                startActivity(intent);
            }
        });
        ((RelativeLayout) view.findViewById(R.id.person_center_content6)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Alerts_activity.class);
                startActivity(intent);
            }
        });
        ((RelativeLayout) view.findViewById(R.id.person_center_content7)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeedBack_activity.class);
                startActivity(intent);
            }
        });
        ((RelativeLayout) view.findViewById(R.id.person_center_content5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), log_activity.class);
                startActivity(intent);
            }
        });
        return view;
    }



}
