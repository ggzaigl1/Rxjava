package com.jypj.yuexiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jypj.yuexiu.R;
import com.jypj.yuexiu.model.Griditem;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;


/**
 * Created by Administrator on 2016/10/9.
 * Home1的Gridview
 */

public class HomeGridAdapter extends BaseAdapter {
    public List<Griditem> mGriditems;
    private Context context;

    public HomeGridAdapter(Context context, List<Griditem> mGriditems) {
        super();
        this.context = context;
        this.mGriditems = mGriditems;

    }

    @Override
    public int getCount() {
        return mGriditems.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler holder;
        if (convertView == null) {
            holder = new ViewHodler();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home1grid, null);
            holder.item_gridimg = (ImageView) convertView.findViewById(R.id.item_gridimg);
            holder.item_gridtv = (TextView) convertView.findViewById(R.id.item_gridtv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHodler) convertView.getTag();
        }

        holder.item_gridimg.setImageResource(R.drawable.ic_launcher);

        Griditem item = mGriditems.get(position);
        ImageLoader.getInstance().displayImage(item.imageUrl, holder.item_gridimg);
        holder.item_gridtv.setText(item.name);

        return convertView;
    }

    class ViewHodler {
        TextView item_gridtv;
        ImageView item_gridimg;
    }
}
