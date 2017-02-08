package com.jypj.yuexiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jypj.yuexiu.R;
import com.jypj.yuexiu.model.QueryAward;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 * 获奖信息页面的adapter
 */

public class AcquisitionAdapter extends BaseAdapter {

    private Context context;
    private List<QueryAward.DataBean> dataEntities;

    public AcquisitionAdapter(Context context, List<QueryAward.DataBean> dataEntities) {
        super();
        this.context = context;
        this.dataEntities = dataEntities;
    }

    @Override
    public int getCount() {
        return dataEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AcquisitionAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new AcquisitionAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_acquisition, null);
            holder.time = (TextView) convertView.findViewById(R.id.notify_time);
            holder.title = (TextView) convertView.findViewById(R.id.notify_title);
            convertView.setTag(holder);
        } else {
            holder = (AcquisitionAdapter.ViewHolder) convertView.getTag();
        }
        holder.time.setText(dataEntities.get(position).getAwardDate());
        holder.title.setText(dataEntities.get(position).getAwardInfo());
        return convertView;
    }

    class ViewHolder {
        private TextView time, title;

    }
}
