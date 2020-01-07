package com.example.onlineshop.view.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.onlineshop.model.ImagesItem;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderHolder> {

    private Context mContext;
    private List<ImagesItem> mList;

    public SliderAdapter(Context context){
        mContext = context;
        mList = Collections.synchronizedList(new ArrayList<>());
    }

    public void setList(List<ImagesItem> list){
        if(list == null)
            return;
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public SliderHolder onCreateViewHolder(ViewGroup parent) {
        return new SliderHolder(new ImageView(mContext));
    }

    @Override
    public void onBindViewHolder(SliderHolder viewHolder, int position) {
        viewHolder.onBind(mList.get(position).getSrc());
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    class SliderHolder extends SliderViewAdapter.ViewHolder{

        private ImageView mImageView;

        public SliderHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView;
        }
        public void onBind(String url){
            Picasso.get().load(url).centerCrop().fit().into(mImageView);
        }
    }
}
