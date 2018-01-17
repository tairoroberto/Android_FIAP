package br.com.tairoroberto.fiapservidorrest.utils;

import br.com.tairoroberto.fiapservidorrest.api.AndroidAPI;
import br.com.tairoroberto.fiapservidorrest.api.CarroAPI;

/**
 * Created by logonrm on 19/06/2017.
 */

public class ApiUtils {

    private static final String URL_BASE_CARRO = "http://10.3.1.39:3000";
    private static final String URL_BASE_CLIENTE = "http://www.mocky.io";

    public static CarroAPI getCarroApiService(){
        return RetrofitClient.getRetrofit(URL_BASE_CARRO).create(CarroAPI.class);
    }

    public static AndroidAPI getAndroidApiService(){
        return RetrofitClient.getRetrofit(URL_BASE_CLIENTE).create(AndroidAPI.class);
    }
}
