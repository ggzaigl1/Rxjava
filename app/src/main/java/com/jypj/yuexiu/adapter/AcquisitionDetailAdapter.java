package com.jypj.yuexiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jypj.yuexiu.R;
import com.jypj.yuexiu.model.AwardM;

import java.util.List;

/**
 * Created by Administrator on 2016/10/19.
 * 获奖信息详情的adapter
 */

public class AcquisitionDetailAdapter extends BaseAdapter {
    private Context context;
    private List<AwardM.DataEntity> dataEntities;

    public AcquisitionDetailAdapter(Context context, List<AwardM.DataEntity> dataEntities) {
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
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_oadeatillist, null);
            holder.key = (TextView) convertView.findViewById(R.id.oa_list_key);
            holder.value = (TextView) convertView.findViewById(R.id.oa_list_value);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.key.setText(dataEntities.get(position).getFieldName());
        holder.value.setText(dataEntities.get(position).getValue());
        return convertView;
    }

    class ViewHolder {
        private TextView key, value;

    }
}
