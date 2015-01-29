package com.colormaster.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by oZBo on 26.01.2015.
 */
public class GridViewAdapter extends BaseAdapter {

    class ViewHolder {
        TextView colorName;
        ImageView color;
    }

    private ArrayList<LibraryColorObject> colorsList;
    private Context mContext;

    public GridViewAdapter(Context mContext, ArrayList<LibraryColorObject> colorsList) {
        this.mContext = mContext;
        this.colorsList = colorsList;
    }

    @Override
    public int getCount() {
        return colorsList.size();
    }

    @Override
    public Object getItem(int position) {
        return colorsList.get(position);
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
            holder.colorName = (TextView) convertView.findViewById(R.id.grid_item_text);
            holder.color = (ImageView) convertView.findViewById(R.id.grid_item_image);
            holder.colorName.setText(colorsList.get(position).getColorName());
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //TODO ПОФИКСИТЬ ЭТОТ ЕБУЧИЙ АДАПТЕР, КОТОРЫЙ МЕНЯ СУКА ЗАЕБАЛ!!!!!!!!!!!!!!!!!!!!!!
        return convertView;
    }
}
