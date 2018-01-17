package br.com.tairoroberto.fiapintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.tairoroberto.fiapintent.LoginActivity.USUARIO_RESULT;

public class NovoUsuarioActivity extends AppCompatActivity {

    @BindView(R.id.nomeUsuario)
    TextInputLayout nomeUsuario;

    @BindView(R.id.usuario)
    TextInputLayout usuario;

    @BindView(R.id.senha)
    TextInputLayout senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.buttonLogin)
    public void btnClick(){
        Intent intent = new Intent();
        intent.putExtra("NOMEUSUARIO", nomeUsuario.getEditText().getText().toString());
        intent.putExtra("USUARIO", usuario.getEditText().getText().toString());
        setResult(USUARIO_RESULT, intent);
        finish();
    }
}
