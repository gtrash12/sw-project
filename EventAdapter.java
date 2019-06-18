package com.example.dday;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EventAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItemlist = new ArrayList<ListViewItem>();

    public EventAdapter() {

    }

    @Override
    public int getCount() {
        return listViewItemlist.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_array, parent, false);
        }

        TextView eventcontentsView = (TextView) convertView.findViewById(R.id.ec);
        TextView ddayView = (TextView) convertView.findViewById(R.id.eddd);

        ListViewItem listViewItem = listViewItemlist.get(position);

        eventcontentsView.setText(listViewItem.getEventContents());
        ddayView.setText(listViewItem.getDday());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemlist.get(position);
    }

    public void addItem(String contents, int dday){
        ListViewItem item = new ListViewItem();
        item.setEventContents(contents);
        item.setD_day(dday);
    }
}
