package com.jypj.yuexiu;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jypj.yuexiu.adapter.OAdetailsAdapter;
import com.jypj.yuexiu.base.BaseActivity;
import com.jypj.yuexiu.model.FiledealM;

/**
 * Created by Administrator on 2016/10/18.
 */

public class OAdetailsActivity extends BaseActivity {
    private ListView mListView1;
    private OAdetailsAdapter mOAdetailsAdapter;
    private ViewGroup textGroup;
    private TextView[] mTextViews;
    private TextView mTextView;

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        inview();
        addStatusBarView();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_oadetails;
    }


    private void inview() {
        mListView1 = (ListView) findViewById(R.id.oa_list);
        textGroup = (ViewGroup) findViewById(R.id.attachment);
        mListView1.setVerticalScrollBarEnabled(false);
        ((TextView) findViewById(R.id.oadetailtitle)).setText(getIntent().getStringExtra("title"));
        FiledealM.DataEntity.MsgContentEntity msgContentEntity = new Gson().fromJson(getIntent().getStringExtra("MsgContent"), FiledealM.DataEntity.MsgContentEntity.class);
        mOAdetailsAdapter = new OAdetailsAdapter(OAdetailsActivity.this, msgContentEntity);
        mListView1.setAdapter(mOAdetailsAdapter);
        ((TextView) findViewById(R.id.detailtitle)).setText(msgContentEntity.getFileInfo().get(0).getValue());
        int PointCount = msgContentEntity.getAttach().size();
        mTextViews = new TextView[PointCount];
        for (int i = 0; i < PointCount; i++) {
            mTextView = new TextView(this);
            mTextView.setTextColor(getResources().getColor(R.color.black));
            mTextView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = params.height;
            params.leftMargin = 30;
            params.gravity = Gravity.CENTER;
            params.topMargin = 10;
            mTextView.setLayoutParams(params);
            mTextViews[i] = mTextView;
            mTextView.setText(msgContentEntity.getAttach().get(i));
            textGroup.addView(mTextViews[i]);
        }

    }
}
