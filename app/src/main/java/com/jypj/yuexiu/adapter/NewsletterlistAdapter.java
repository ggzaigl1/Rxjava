package com.jypj.yuexiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jypj.yuexiu.R;
import com.jypj.yuexiu.model.FlashM;

import java.util.List;

/**
 * Created by Administrator on 2016/10/10.
 * 越秀快讯adapter
 */

public class NewsletterlistAdapter extends BaseAdapter {

    private Context context;
    private List<FlashM.DataEntity> dataEntities;

    public NewsletterlistAdapter(Context context, List<FlashM.DataEntity> dataEntities) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_newslist, null);
            holder.time = (TextView) convertView.findViewById(R.id.news_time);
            holder.title = (TextView) convertView.findViewById(R.id.news_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.time.setText(dataEntities.get(position).getDate());
        holder.title.setText(dataEntities.get(position).getTitle());

        return convertView;
    }

    class ViewHolder {
        private TextView time, title;

    }
}
