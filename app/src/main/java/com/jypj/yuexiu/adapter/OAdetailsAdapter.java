package com.jypj.yuexiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jypj.yuexiu.R;
import com.jypj.yuexiu.model.FiledealM;

/**
 * Created by Administrator on 2016/10/18.
 * OA详情界面的adaoter
 */

public class OAdetailsAdapter extends BaseAdapter {
    private Context mContext;
    private FiledealM.DataEntity.MsgContentEntity  msgContentEntity;
    public OAdetailsAdapter(Context mcontext,FiledealM.DataEntity.MsgContentEntity  msgContentEntity){
        super();
        this.mContext=mcontext;
        this.msgContentEntity=msgContentEntity;
    }

    @Override
    public int getCount() {
        return msgContentEntity.getFileInfo().size()-1;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_oadeatillist, null);
            holder.key = (TextView) convertView.findViewById(R.id.oa_list_key);
            holder.value = (TextView) convertView.findViewById(R.id.oa_list_value);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.key.setText(msgContentEntity.getFileInfo().get(position+1).getKey());
        holder.value.setText(msgContentEntity.getFileInfo().get(position+1).getValue());
        return convertView;
    }

    class ViewHolder {
        private TextView key, value;

    }
}
