package com.jypj.yuexiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jypj.yuexiu.R;
import com.jypj.yuexiu.model.QueryMsgM;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 * 控件消息的adapter
 */

public class SpacelistAdapter extends BaseAdapter {

    private Context context;
    private List<QueryMsgM.DataEntity> dataEntities;

    public SpacelistAdapter(Context context, List<QueryMsgM.DataEntity> dataEntities) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_space_list, null);
            holder.name = (TextView) convertView.findViewById(R.id.space_name);
            holder.title = (TextView) convertView.findViewById(R.id.space_content);
            holder.time = (TextView) convertView.findViewById(R.id.space_time);
            holder.icon = (ImageView) convertView.findViewById(R.id.space_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (dataEntities.get(position).getSenderName() != null) {
            holder.name.setText(dataEntities.get(position).getSenderName());
        } else {
            holder.name.setText("匿名人");
        }
        holder.time.setText(dataEntities.get(position).getCreateDate());
        holder.title.setText(dataEntities.get(position).getMsgContent());
        if (dataEntities.get(position).getSenderImagePath() != null) {
            ImageLoader.getInstance().displayImage(dataEntities.get(position).getSenderImagePath(), holder.icon);
        } else {
            holder.icon.setBackgroundResource(R.drawable.userimg);
        }

        return convertView;
    }

    class ViewHolder {
        private TextView name, title, time;
        private ImageView icon;
    }
}
