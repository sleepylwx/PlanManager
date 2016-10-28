package com.lwx.likestudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lwx.likestudy.R;

import java.util.List;

/**
 * Created by 36249 on 2016/10/27.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    Context context;
    List<String> datas;

    public RecyclerViewAdapter(Context context,List<String> datas){

        this.context = context;
        this.datas = datas;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,int position){

        holder.textView.setText(datas.get(position));
    }

    @Override
    public int getItemCount(){
        return datas.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public MyViewHolder(View view){

            super(view);
            textView = (TextView) view.findViewById(R.id.recyclerview_item_textview);

        }
    }
}
