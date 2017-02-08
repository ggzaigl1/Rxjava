package com.jypj.yuexiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jypj.yuexiu.R;
import com.jypj.yuexiu.model.Myrecorditem;

import org.json.JSONArray;

import java.util.List;

/**
 * Home2和Myrecord 界面的listviewadapter
 */
public class HomeListAdapter extends BaseAdapter {

	private List<Myrecorditem> myrecorditems;
	public JSONArray list = new JSONArray();
	private Context context;

	public HomeListAdapter(Context context, List<Myrecorditem> myrecorditems) {
		super();
		this.context = context;
		this.myrecorditems=myrecorditems;
	}
	@Override
	public int getCount() {
			return myrecorditems.size();
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
	public View getView(int arg0, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_home2list, null);
			holder.Name = (TextView)convertView.findViewById(R.id.Name);
			holder.Detail = (TextView)convertView.findViewById(R.id.Detail);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

			holder.Name.setText(myrecorditems.get(arg0).fieldName);
		if(myrecorditems.get(arg0).value==null||myrecorditems.get(arg0).value==""){
			holder.Detail.setText("无");
		}else  {
			holder.Detail.setText(myrecorditems.get(arg0).value);
		}

		return convertView;
	}

	class ViewHolder {
		private TextView Name,Detail;

	}

}