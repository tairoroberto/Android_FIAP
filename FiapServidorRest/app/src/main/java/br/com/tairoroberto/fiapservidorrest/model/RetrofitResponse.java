package br.com.tairoroberto.fiapservidorrest.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by logonrm on 19/06/2017.
 */
public class RetrofitResponse implements Parcelable{
    Carro carro;


    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    protected RetrofitResponse(Parcel in) {
        carro = in.readParcelable(Carro.class.getClassLoader());
    }

    public static final Creator<RetrofitResponse> CREATOR = new Creator<RetrofitResponse>() {
        @Override
        public RetrofitResponse createFromParcel(Parcel in) {
            return new RetrofitResponse(in);
        }

        @Override
        public RetrofitResponse[] newArray(int size) {
            return new RetrofitResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(carro, flags);
    }
}
