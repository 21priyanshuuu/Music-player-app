package com.example.tattify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class customBaseAdapter extends BaseAdapter {
    String [] items;
    Context ctx;
    LayoutInflater layoutInflater;

    public customBaseAdapter(Context ctx,String items[]){
    this.items=items;
    this.ctx=ctx;
        layoutInflater=LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=layoutInflater.inflate(R.layout.activity_list_view,null);
        TextView txtview=(TextView) view.findViewById(R.id.textView);
        @SuppressLint("ResourceType")
        ImageView img=(ImageView) view.findViewById(R.drawable.musiclogo);
        txtview.setText(items[i]);
        img.setImageResource(R.drawable.musiclogo);
        return view;
    }
}
