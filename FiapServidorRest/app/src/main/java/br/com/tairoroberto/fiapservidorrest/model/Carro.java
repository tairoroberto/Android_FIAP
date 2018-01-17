package br.com.tairoroberto.fiapservidorrest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by logonrm on 19/06/2017.
 */

public class Carro implements Parcelable {
    @SerializedName("marca")
    String marca;

    @SerializedName("modelo")
    String modelo;

    @SerializedName("ano")
    String ano;

    public Carro() {
    }

    protected Carro(Parcel in) {
        marca = in.readString();
        modelo = in.readString();
        ano = in.readString();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeString(modelo);
        dest.writeString(ano);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Carro> CREATOR = new Creator<Carro>() {
        @Override
        public Carro createFromParcel(Parcel in) {
            return new Carro(in);
        }

        @Override
        public Carro[] newArray(int size) {
            return new Carro[size];
        }
    };
}
