package br.com.tairoroberto.fiapmetro.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by logonrm on 26/06/2017.
 */
public class LinhaMetro implements Parcelable {
    @SerializedName("cor")
    String cor;

    @SerializedName("numero")
    String numero;

    @SerializedName("urlImagem")
    String urlImagem;

    protected LinhaMetro(Parcel in) {
        cor = in.readString();
        numero = in.readString();
        urlImagem = in.readString();
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public static final Creator<LinhaMetro> CREATOR = new Creator<LinhaMetro>() {
        @Override
        public LinhaMetro createFromParcel(Parcel in) {
            return new LinhaMetro(in);
        }

        @Override
        public LinhaMetro[] newArray(int size) {
            return new LinhaMetro[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cor);
        dest.writeString(numero);
        dest.writeString(urlImagem);
    }
}
