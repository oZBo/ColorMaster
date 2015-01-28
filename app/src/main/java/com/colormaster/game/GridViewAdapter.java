package com.colormaster.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by oZBo on 26.01.2015.
 */
public class GridViewAdapter extends BaseAdapter {

    static class ViewHolder {
        TextView tv, tv2, tv3; //define views
    }

    private List<String> objectList; // define listOfObjectw
    private Context mContext;

    public GridViewAdapter(Context mContext, List<String> objectList) {
        this.mContext = mContext;
        this.objectList = objectList;
    }

    @Override
    public int getCount() {
        return objectList.size();
    }

    @Override
    public Object getItem(int position) {
        return objectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.gridview_item, parent, false);
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.tv_score);
            holder.tv2 = (TextView) convertView.findViewById(R.id.tv_score);
            holder.tv3 = (TextView) convertView.findViewById(R.id.tv_score);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        // code for gridView item
        holder.tv.setText("ok");

        return convertView;
    }
}
