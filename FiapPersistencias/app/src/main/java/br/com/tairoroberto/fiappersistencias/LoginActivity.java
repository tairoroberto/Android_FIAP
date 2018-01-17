package br.com.tairoroberto.fiappersistencias;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private static final String LOGIN_DEFAULT = "teste";
    private static final String SENHA_DEFAULT = "123";
    private static final String KEY_APP_PREFERENCES​ = "info";
    private static final String KEY_LOGIN​ = "login";
    private static final String KEY_SENHA = "senha";
    private EditText edtUserName;
    private EditText edtPassword;
    private CheckBox checkLemprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        checkLemprar = (CheckBox) findViewById(R.id.checkLembrar);
        ler();
        if (isConectado())
            iniciarApp();
    }

    private boolean isLoginValido(){
        String login = edtUserName.getText().toString();
        String senha = edtPassword.getText().toString();
        return login.equals(LOGIN_DEFAULT) && senha.equals(SENHA_DEFAULT);
    }

    public void logar(View view) {
        if (isLoginValido()){
            if (checkLemprar.isChecked()){
                manterConectado();
            }
            iniciarApp();
        }
    }

    private boolean isConectado(){
        SharedPreferences shared = getSharedPreferences(KEY_APP_PREFERENCES​, Context.MODE_PRIVATE);
        String login = shared.getString(KEY_LOGIN​, "");
        return !login.equals("");
    }

    public void ler(){
        SharedPreferences shared = getSharedPreferences(KEY_APP_PREFERENCES​, Context.MODE_PRIVATE);
        String login = shared.getString(KEY_LOGIN​, "");
        String senha = shared.getString(KEY_SENHA, "");
        edtUserName.setText(login);
        edtUserName.setText(senha);
    }

    private void manterConectado(){
        String login = edtUserName.getText().toString();
        String senha = edtUserName.getText().toString();
        SharedPreferences pref = getSharedPreferences(KEY_APP_PREFERENCES​, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_LOGIN​, login);
        editor.putString(KEY_SENHA, senha);
        editor.apply();
    }
    private void iniciarApp() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
