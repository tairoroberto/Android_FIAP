package br.com.tairoroberto.fiapintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    public static final int USUARIO_RESULT = 0;
    @BindView(R.id.usuario)
    TextInputLayout usuario;

    @BindView(R.id.senha)
    TextInputLayout senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonLogin)
    public void btnClick(){

    }

    @OnClick(R.id.textViewCadastrar)
    public void txtCadastrarClick(){
        Intent intent = new Intent(this, NovoUsuarioActivity.class);
        startActivityForResult(intent, USUARIO_RESULT);
    }

    @OnClick(R.id.textViewSMS)
    public void txtSMSClick(){
        Intent intent = new Intent(this, SMSActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == USUARIO_RESULT){
            if (data.getExtras() != null){
                usuario.getEditText().setText(data.getStringExtra("USUARIO"));
            }
        }
    }
}
