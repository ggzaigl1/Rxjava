package com.jypj.yuexiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jypj.yuexiu.R;
import com.jypj.yuexiu.model.LoginM;

/**
 * Created by Administrator on 2016/10/11.
 * 角色切换的adapter
 */

public class RolelistAdapter extends BaseAdapter {

    private Context mContext;
    private LoginM.DataEntity mDataEntity;

    public RolelistAdapter(Context context, LoginM.DataEntity mDataEntity) {
        super();
        this.mContext = context;
        this.mDataEntity = mDataEntity;
    }

    @Override
    public int getCount() {
        return mDataEntity.getRoles().size();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_rolelist, null);
            holder.Name = (TextView) convertView.findViewById(R.id.rolelist_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.Name.setText(mDataEntity.getRoles().get(position).getRole_name());

        return convertView;
    }

    class ViewHolder {
        private TextView Name;

    }
}
