package br.com.tairoroberto.fiapservidorrest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.tairoroberto.fiapservidorrest.adapter.AndroidAdapter;
import br.com.tairoroberto.fiapservidorrest.model.Android;
import br.com.tairoroberto.fiapservidorrest.model.ResponseAndroid;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.tairoroberto.fiapservidorrest.utils.ApiUtils.getAndroidApiService;

public class ListAndroidActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Android> androids = new ArrayList<>();
    private ProgressBar progressBar;
    private AndroidAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_android);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListAndroidActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        /*RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);*/
        adapter = new AndroidAdapter(new ArrayList<Android>());
        recyclerView.setAdapter(adapter);

        getAndroidApiService().getVersoes().enqueue(new Callback<ResponseAndroid>() {
            @Override
            public void onResponse(Call<ResponseAndroid> call, Response<ResponseAndroid> response) {
                adapter.update(response.body().getAndroids());
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseAndroid> call, Throwable t) {
                Toast.makeText(ListAndroidActivity.this, "Erro ao buscar versões: \n " + t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
