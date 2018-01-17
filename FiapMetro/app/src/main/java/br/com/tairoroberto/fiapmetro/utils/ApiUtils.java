package br.com.tairoroberto.fiapmetro.utils;

import br.com.tairoroberto.fiapmetro.api.MetroApi;

/**
 * Created by logonrm on 26/06/2017.
 */

public class ApiUtils {

    public static final String URL_BASE_METRO = "http://10.3.1.39:3000";

    public static MetroApi getMetroApiService(){
        return RetrofitClient.getRetrofit(URL_BASE_METRO).create(MetroApi.class);
    }
}
