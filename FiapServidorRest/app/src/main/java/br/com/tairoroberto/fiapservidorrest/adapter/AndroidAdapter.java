package br.com.tairoroberto.fiapservidorrest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.tairoroberto.fiapservidorrest.R;
import br.com.tairoroberto.fiapservidorrest.model.Android;

/**
 * Created by logonrm on 21/06/2017.
 */

public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.AndroidViewHolder>{

    private List<Android> androids;

    public AndroidAdapter(List<Android> androids){
            this.androids = androids;
    }

    @Override
    public AndroidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.android_item, parent, false);
        return new AndroidViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AndroidViewHolder holder, int position) {
        Android android = androids.get(position);
        holder.textTitle.setText(android.getNome());
        holder.textSubTitle.setText(android.getVersao());
        Picasso.with(holder.imgAndroid.getContext()).load(android.getUrlImage()).into(holder.imgAndroid);
    }

    @Override
    public int getItemCount() {
        return androids.size();
    }


    public class AndroidViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgAndroid;
        public TextView textTitle;
        public TextView textSubTitle;

        public AndroidViewHolder(View itemView) {
            super(itemView);
            imgAndroid = (ImageView) itemView.findViewById(R.id.imgAndroid);
            textTitle = (TextView) itemView.findViewById(R.id.textTitle);
            textSubTitle = (TextView) itemView.findViewById(R.id.subTitle);
        }
    }

    public void update(List<Android> androids){
        this.androids = androids;
        notifyDataSetChanged();
    }
}
