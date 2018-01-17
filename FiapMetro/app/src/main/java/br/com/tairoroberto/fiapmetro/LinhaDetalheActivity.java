package br.com.tairoroberto.fiapmetro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.tairoroberto.fiapmetro.adapter.EstacoesAdapter;
import br.com.tairoroberto.fiapmetro.adapter.LinhaMetroAdapter;
import br.com.tairoroberto.fiapmetro.adapter.OnItemClickListener;
import br.com.tairoroberto.fiapmetro.api.MetroApi;
import br.com.tairoroberto.fiapmetro.model.Estacao;
import br.com.tairoroberto.fiapmetro.model.LinhaMetro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static br.com.tairoroberto.fiapmetro.utils.ApiUtils.getMetroApiService;

public class LinhaDetalheActivity extends AppCompatActivity {


    private RecyclerView recyclerEstacoes;
    private ProgressBar progress;
    private MetroApi metroApi;
    private EstacoesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linha_detalhe);

        recyclerEstacoes = (RecyclerView) findViewById(R.id.recycleViewEstacoes);
        progress = (ProgressBar) findViewById(R.id.progress);
        metroApi = getMetroApiService();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LinhaDetalheActivity.this);
        recyclerEstacoes.setLayoutManager(layoutManager);
        recyclerEstacoes.setHasFixedSize(true);
        adapter = new EstacoesAdapter(new ArrayList<Estacao>());
        recyclerEstacoes.setAdapter(adapter);

        final String linha = getIntent().getStringExtra("linha");

        if (!TextUtils.isEmpty(linha)){
            progress.setVisibility(View.VISIBLE);
            metroApi.consultarEstacaoes(linha)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<Estacao>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            progress.setVisibility(View.GONE);
                            Toast.makeText(LinhaDetalheActivity.this, "Não foi possível buscar dados das estação da linha " + linha, Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onNext(List<Estacao> linhas) {
                            if (linhas != null && !linhas.isEmpty()){
                                adapter.update(linhas);
                            }else {
                                Toast.makeText(LinhaDetalheActivity.this, "Não existem estações para a linha selecionada :(", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            progress.setVisibility(View.GONE);
                        }
                    });
        }else {
            Toast.makeText(this, "Estação selecionada inválida !", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
