package br.com.tairoroberto.fiapmetro.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by logonrm on 26/06/2017.
 */

public class LinhasMetroResponse implements Parcelable {

    private List<LinhaMetro> linhasMetro;

    public LinhasMetroResponse() {
    }

    protected LinhasMetroResponse(Parcel in) {

    }

    public static final Creator<LinhasMetroResponse> CREATOR = new Creator<LinhasMetroResponse>() {
        @Override
        public LinhasMetroResponse createFromParcel(Parcel in) {
            return new LinhasMetroResponse(in);
        }

        @Override
        public LinhasMetroResponse[] newArray(int size) {
            return new LinhasMetroResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public List<LinhaMetro> getLinhasMetro() {
        return linhasMetro;
    }

    public void setLinhasMetro(List<LinhaMetro> linhasMetro) {
        this.linhasMetro = linhasMetro;
    }
}
