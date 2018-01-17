package br.com.tairoroberto.fiapservidorrest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.com.tairoroberto.fiapservidorrest.api.CarroAPI;
import br.com.tairoroberto.fiapservidorrest.model.Carro;
import br.com.tairoroberto.fiapservidorrest.model.RetrofitResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.tairoroberto.fiapservidorrest.utils.ApiUtils.getCarroApiService;

public class MainActivity extends AppCompatActivity {

    private EditText edtMarca;
    private EditText edtModelo;
    private EditText edtAno;
    CarroAPI carroAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtMarca = (EditText) findViewById(R.id.editTextMarca);
        edtModelo = (EditText) findViewById(R.id.editTextModelo);
        edtAno = (EditText) findViewById(R.id.editTextAno);
        carroAPI = getCarroApiService();
    }

    public void enviar(View view){

        Carro carro = new Carro();
        carro.setMarca(edtMarca.getText().toString());
        carro.setModelo(edtModelo.getText().toString());
        carro.setAno(edtAno.getText().toString());

        carroAPI.salvar(carro).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(MainActivity.this, "Carro salvo com sucesso!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro ao salvar carros! \n " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void receber(View view){
        carroAPI.consultarTodos().enqueue(new Callback<List<RetrofitResponse>>() {
            @Override
            public void onResponse(Call<List<RetrofitResponse>> call, Response<List<RetrofitResponse>> response) {
                String retorno = "";
                for (int i = 0; i < response.body().size(); i++) {
                    if (response.body().get(i).getCarro() != null){
                        retorno += "\n Marca: " + response.body().get(i).getCarro().getMarca() +
                                " \n Modelo: " + response.body().get(i).getCarro().getModelo() + " \n " +
                                " \n Ano: " + response.body().get(i).getCarro().getAno();
                    }
                }
                Toast.makeText(MainActivity.this, retorno, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<RetrofitResponse>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro ao listar carros! \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void callClientes(View view) {
        Intent intent = new Intent(MainActivity.this, ListAndroidActivity.class);
        startActivity(intent);
    }
}
