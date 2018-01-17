package br.com.tairoroberto.fiapmetro.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by logonrm on 26/06/2017.
 */

public class Estacao implements Parcelable{

    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("endereco")
    @Expose
    private String endereco;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("capacidade_passageiro_hora_pico")
    @Expose
    private String capacidadePassageiroHoraPico;
    @SerializedName("area_construida_m_2")
    @Expose
    private Integer areaConstruidaM2;
    @SerializedName("inauguracao")
    @Expose
    private String inauguracao;

    protected Estacao(Parcel in) {
        nome = in.readString();
        endereco = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        capacidadePassageiroHoraPico = in.readString();
        inauguracao = in.readString();
    }

    public static final Creator<Estacao> CREATOR = new Creator<Estacao>() {
        @Override
        public Estacao createFromParcel(Parcel in) {
            return new Estacao(in);
        }

        @Override
        public Estacao[] newArray(int size) {
            return new Estacao[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCapacidadePassageiroHoraPico() {
        return capacidadePassageiroHoraPico;
    }

    public void setCapacidadePassageiroHoraPico(String capacidadePassageiroHoraPico) {
        this.capacidadePassageiroHoraPico = capacidadePassageiroHoraPico;
    }

    public Integer getAreaConstruidaM2() {
        return areaConstruidaM2;
    }

    public void setAreaConstruidaM2(Integer areaConstruidaM2) {
        this.areaConstruidaM2 = areaConstruidaM2;
    }

    public String getInauguracao() {
        return inauguracao;
    }

    public void setInauguracao(String inauguracao) {
        this.inauguracao = inauguracao;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(endereco);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(capacidadePassageiroHoraPico);
        dest.writeString(inauguracao);
    }
}