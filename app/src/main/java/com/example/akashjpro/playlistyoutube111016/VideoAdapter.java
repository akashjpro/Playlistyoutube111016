package com.example.akashjpro.playlistyoutube111016;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Akashjpro on 10/11/2016.
 */

public class VideoAdapter extends BaseAdapter {
    Activity context;
    int layout;
    ArrayList<Video> mangVideo;

    public VideoAdapter(Activity context, int layout, ArrayList<Video> mangVideo) {
        this.context = context;
        this.layout = layout;
        this.mangVideo = mangVideo;
    }

    @Override
    public int getCount() {
        return mangVideo.size();
    }

    @Override
    public Object getItem(int position) {
        return mangVideo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHoler{
        TextView title, channelTitle;
        ImageView hinh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View rowView = convertView;

        ViewHoler holer = new ViewHoler();
        if(rowView == null){
            rowView = inflater.inflate(this.layout, null);

            holer.title        = (TextView) rowView.findViewById(R.id.textViewTitle);
            holer.channelTitle = (TextView) rowView.findViewById(R.id.textViewChannelTitle);
            holer.hinh         = (ImageView) rowView.findViewById(R.id.imageViewHinh);
            rowView.setTag(holer);
        }else {
            holer = (ViewHoler) rowView.getTag();
        }


        holer.title.setText(mangVideo.get(position).getTitle());
        holer.channelTitle.setText(mangVideo.get(position).getChannelTitle());
        Picasso.with(this.context).load(mangVideo.get(position).getHinh()).into(holer.hinh);

        return rowView;
    }
}
