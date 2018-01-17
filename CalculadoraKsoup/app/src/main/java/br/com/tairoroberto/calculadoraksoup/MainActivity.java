package br.com.tairoroberto.calculadoraksoup;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {

    private static final String SOAP_METHOD_NAME = "soma";
    private static final String SOAP_ACTION = "";
    private static final String SOAP_NAME_SPACE = "http://tairoroberto.com.br/";
    private static final String SOAP_ADDRESS = "http://10.3.1.39:8080/Calculadora/Calculadora";

    private EditText editNum1;
    private EditText editNum2;
    private TextView textViewresult;
    private ProgressBar progressBar;
    SoapObject request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNum1 = (EditText) findViewById(R.id.editTextNume1);
        editNum2 = (EditText) findViewById(R.id.editTextNume2);
        textViewresult = (TextView) findViewById(R.id.textViewresult);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    public void calcular(View view){
        if (!TextUtils.isEmpty(editNum1.getText().toString()) && !TextUtils.isEmpty(editNum1.getText().toString())){

            int num = Integer.parseInt(editNum1.getText().toString());
            int num2 = Integer.parseInt(editNum2.getText().toString());

            textViewresult.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

            CalcularAsync calcularAsync = new CalcularAsync();
            calcularAsync.execute(num, num2);
        }else {
            Toast.makeText(this, "Preencha os campos", Toast.LENGTH_SHORT).show();
        }
    }

    private class CalcularAsync extends AsyncTask<Object, Object, String> {

        @Override
        protected String doInBackground(Object... params) {

            request = new SoapObject(SOAP_NAME_SPACE, SOAP_METHOD_NAME);
            request.addProperty("num", params[0]);
            request.addProperty("num2", params[1]);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            //envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            try {
                httpTransport.call(SOAP_ACTION, envelope);
                SoapPrimitive calcular = (SoapPrimitive) envelope.getResponse();

                Log.i("LOG", "doInBackground: " + calcular.getValue());
                SystemClock.sleep(2000);

                return (String) calcular.getValue();

            } catch (Exception e) {
                e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textViewresult.setText(s);
            textViewresult.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }
}
