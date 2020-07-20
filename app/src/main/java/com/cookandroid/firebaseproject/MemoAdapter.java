package com.cookandroid.firebaseproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MemoAdapter  extends BaseAdapter {
    private final List<Memo> mData;

    public MemoAdapter(List<Memo> memoList) {
        mData = memoList;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_memo, parent, false);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.title_text);
            TextView contentTextView = (TextView) convertView.findViewById(R.id.content_text);

            viewHolder.titleTextView = titleTextView;
            viewHolder.contentTextView = contentTextView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Memo memo = mData.get(position);

        viewHolder.titleTextView.setText(memo.getTitle());
        viewHolder.contentTextView.setText(memo.getContent());

        return convertView;
    }

    private static class ViewHolder {
        TextView titleTextView;
        TextView contentTextView;

    }
}