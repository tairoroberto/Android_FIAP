package br.com.tairoroberto.fiapservidorrest.api;

import java.util.List;

import br.com.tairoroberto.fiapservidorrest.model.Carro;
import br.com.tairoroberto.fiapservidorrest.model.RetrofitResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by logonrm on 19/06/2017.
 */

public interface CarroAPI {

    @POST("/carros")
    Call<Void> salvar(@Body Carro carro);

    @GET("/carros")
    Call<List<RetrofitResponse>> consultarTodos();

}
