package com.jypj.yuexiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jypj.yuexiu.R;
import com.jypj.yuexiu.model.FiledealM;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 * OA界面的适配器
 */

public class OAListAdapter extends BaseAdapter {

    private Context context;
    private List<FiledealM.DataEntity> dataEntities;

    public OAListAdapter(Context context, List<FiledealM.DataEntity> dataEntities) {
        super();
        this.context = context;
        this.dataEntities = dataEntities;
    }

    @Override
    public int getCount() {
     return dataEntities==null? 0 :dataEntities.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_oalist, null);
            holder.time = (TextView) convertView.findViewById(R.id.oa_time);
            holder.title = (TextView) convertView.findViewById(R.id.oa_title);
//            holder.content = (TextView) convertView.findViewById(R.id.oa_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.time.setText(dataEntities.get(position).getCreateDate());
        holder.title.setText(dataEntities.get(position).getTitle());
//      holder.content.setText(filedealM.getData().get(position).getMsgContent());
        return convertView;
    }

    class ViewHolder {
        private TextView time, title, content;

    }
}
