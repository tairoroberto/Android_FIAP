package br.com.tairoroberto.fiapmetro.api;

import java.util.List;

import br.com.tairoroberto.fiapmetro.model.Estacao;
import br.com.tairoroberto.fiapmetro.model.LinhaMetro;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by logonrm on 26/06/2017.
 */

public interface MetroApi {
    @GET("/linhas")
    Call<List<LinhaMetro>> consultarLinhasMetro();

    @GET("/linhas/{linha}/estacoes")
    rx.Observable<List<Estacao>> consultarEstacaoes(@Path("linha") String linha);
}
