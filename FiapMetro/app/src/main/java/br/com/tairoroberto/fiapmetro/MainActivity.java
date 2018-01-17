package br.com.tairoroberto.fiapmetro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.tairoroberto.fiapmetro.adapter.LinhaMetroAdapter;
import br.com.tairoroberto.fiapmetro.adapter.OnItemClickListener;
import br.com.tairoroberto.fiapmetro.api.MetroApi;
import br.com.tairoroberto.fiapmetro.model.LinhaMetro;
import br.com.tairoroberto.fiapmetro.model.LinhasMetroResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.tairoroberto.fiapmetro.utils.ApiUtils.getMetroApiService;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewLInhas;
    private ProgressBar progress;
    private MetroApi metroApi;
    private LinhaMetroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewLInhas = (RecyclerView) findViewById(R.id.recycleViewLinhas);
        progress = (ProgressBar) findViewById(R.id.progress);

        progress.setVisibility(View.VISIBLE);
        metroApi = getMetroApiService();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerViewLInhas.setLayoutManager(layoutManager);
        recyclerViewLInhas.setHasFixedSize(true);

        adapter = new LinhaMetroAdapter(new ArrayList<LinhaMetro>(), new OnItemClickListener() {
            @Override
            public void onItemClick(LinhaMetro linhaMetro) {
                startActivity(new Intent(MainActivity.this, LinhaDetalheActivity.class)
                        .putExtra("linha", linhaMetro.getCor()));
            }
        });

        recyclerViewLInhas.setAdapter(adapter);

        metroApi.consultarLinhasMetro().enqueue(new Callback<List<LinhaMetro>>() {
            @Override
            public void onResponse(Call<List<LinhaMetro>> call, Response<List<LinhaMetro>> response) {
                LinhasMetroResponse linhasMetroResponse = new LinhasMetroResponse();
                linhasMetroResponse.setLinhasMetro(response.body());
                adapter.update(linhasMetroResponse.getLinhasMetro());
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<LinhaMetro>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Não foi possível carregar linhas do metrô :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
