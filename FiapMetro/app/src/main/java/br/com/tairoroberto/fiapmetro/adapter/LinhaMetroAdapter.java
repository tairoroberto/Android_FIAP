package br.com.tairoroberto.fiapmetro.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.tairoroberto.fiapmetro.LinhaDetalheActivity;
import br.com.tairoroberto.fiapmetro.R;
import br.com.tairoroberto.fiapmetro.model.LinhaMetro;

import static br.com.tairoroberto.fiapmetro.utils.ApiUtils.URL_BASE_METRO;

/**
 * Created by logonrm on 26/06/2017.
 */

public class LinhaMetroAdapter extends RecyclerView.Adapter<LinhaMetroAdapter.AndroidViewHolder>{

    private List<LinhaMetro> linhasMetro;
    private OnItemClickListener onItemClickListener;

    public LinhaMetroAdapter(List<LinhaMetro> androids, OnItemClickListener onItemClickListener){
        this.linhasMetro = androids;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public AndroidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.linha_metro_item, parent, false);
        return new AndroidViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AndroidViewHolder holder, int position) {
        final LinhaMetro linhaMetro = linhasMetro.get(position);
        holder.textCor.setText(linhaMetro.getCor());
        holder.textNumero.setText(linhaMetro.getNumero());
        Picasso.with(holder.imgMetro.getContext()).load(URL_BASE_METRO+linhaMetro.getUrlImagem()).into(holder.imgMetro);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(linhaMetro);
            }
        });
    }

    @Override
    public int getItemCount() {
        return linhasMetro.size();
    }


    public class AndroidViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgMetro;
        public TextView textCor;
        public TextView textNumero;

        public AndroidViewHolder(View itemView) {
            super(itemView);
            imgMetro = (ImageView) itemView.findViewById(R.id.imgMetro);
            textCor = (TextView) itemView.findViewById(R.id.textCor);
            textNumero = (TextView) itemView.findViewById(R.id.textNumero);
        }
    }

    public void update(List<LinhaMetro> linhasMetro){
        this.linhasMetro = linhasMetro;
        notifyDataSetChanged();
    }
}
