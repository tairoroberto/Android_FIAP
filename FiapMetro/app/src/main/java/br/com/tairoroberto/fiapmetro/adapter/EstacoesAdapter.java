package br.com.tairoroberto.fiapmetro.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.tairoroberto.fiapmetro.LinhaDetalheActivity;
import br.com.tairoroberto.fiapmetro.MapsActivity;
import br.com.tairoroberto.fiapmetro.R;
import br.com.tairoroberto.fiapmetro.model.Estacao;

import static br.com.tairoroberto.fiapmetro.utils.ApiUtils.URL_BASE_METRO;

/**
 * Created by logonrm on 26/06/2017.
 */

public class EstacoesAdapter extends RecyclerView.Adapter<EstacoesAdapter.AndroidViewHolder> {

    private List<Estacao> estacoes;

    public EstacoesAdapter(List<Estacao> estacoes) {
        this.estacoes = estacoes;
    }

    @Override
    public EstacoesAdapter.AndroidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.estacao_item, parent, false);
        return new EstacoesAdapter.AndroidViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AndroidViewHolder holder, int position) {
        final Estacao estacao = estacoes.get(position);
        holder.textNomeEstacao.setText(estacao.getNome());
        holder.textEnderecoEstacao.setText(estacao.getEndereco());
        Picasso.with(holder.imgEstacao.getContext()).load(URL_BASE_METRO + "/images/metro_sp.png").into(holder.imgEstacao);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), MapsActivity.class)
                        .putExtra("linha", estacao));
            }
        });
    }

    @Override
    public int getItemCount() {
        return estacoes.size();
    }


    public class AndroidViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgEstacao;
        public TextView textNomeEstacao;
        public TextView textEnderecoEstacao;

        public AndroidViewHolder(View itemView) {
            super(itemView);
            imgEstacao = (ImageView) itemView.findViewById(R.id.imgEstacao);
            textNomeEstacao = (TextView) itemView.findViewById(R.id.textNomeEstacao);
            textEnderecoEstacao = (TextView) itemView.findViewById(R.id.textEnderecoEstacao);
        }
    }

    public void update(List<Estacao> estacoes) {
        this.estacoes = estacoes;
        notifyDataSetChanged();
    }
}
