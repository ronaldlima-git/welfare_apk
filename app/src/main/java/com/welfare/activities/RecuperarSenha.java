package com.welfare.activities;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.welfare.R;
import com.welfare.model.Pessoa;
import com.welfare.ws.CallBack.PessoaCallback;
import com.welfare.ws.Facade.PessoaFacade;

import java.util.List;

public class RecuperarSenha extends AppCompatActivity {

    private Button btnRecuperarVoltar, btnRecuperarConfirmar;
    private TextInputEditText txtInputRecuperarToken,
            txtInputRecuperarSenha,
            txtInputRecuperarConfirmarSenha;
    private PessoaFacade pessoaFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        carregarComponentes();

        botaoConfirmar(btnRecuperarConfirmar);

        botaoVoltar(btnRecuperarVoltar);
    }

    private void carregarComponentes(){
        btnRecuperarVoltar          = findViewById(R.id.btnRecuperarVoltar);
        btnRecuperarConfirmar       = findViewById(R.id.btnRecuperarConfirmar);

        txtInputRecuperarToken          = findViewById(R.id.txtInputRecuperarToken);
        txtInputRecuperarSenha          = findViewById(R.id.txtInputRecuperarSenha);
        txtInputRecuperarConfirmarSenha = findViewById(R.id.txtInputRecuperarConfirmarSenha);

        pessoaFacade = new PessoaFacade();
    }

    private void botaoConfirmar(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (handleValidadeFields()) {
                    atualizarSenha(txtInputRecuperarToken.getText().toString());
                }
            }
        });
    }

    private void botaoVoltar(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private boolean handleValidadeFields(){
        boolean valid = true;

        if(txtInputRecuperarSenha.getText().toString().equals("") ||
                txtInputRecuperarSenha.getText().toString().length() < 8){
            valid = false;
            txtInputRecuperarSenha.setError(getString(R.string.requiredInputSingInPass));
        }
        if(txtInputRecuperarConfirmarSenha.getText().toString().equals("") ||
                txtInputRecuperarConfirmarSenha.getText().toString().length() < 8){
            valid = false;
            txtInputRecuperarConfirmarSenha.setError(getString(R.string.requiredInputSingInConfirmPass));
        }
        if(!txtInputRecuperarSenha.getText().toString().equals(txtInputRecuperarConfirmarSenha.getText().toString())){
            valid = false;
            txtInputRecuperarSenha.setError(getString(R.string.requiredInputSingInPassEqual));
            txtInputRecuperarConfirmarSenha.setError(getString(R.string.requiredInputSingInPassEqual));
        }
        if(txtInputRecuperarToken.getText().toString().equals("") ||
                (txtInputRecuperarToken.getText().toString().length() < 6 && txtInputRecuperarToken.getText().toString().length() > 7)){
            valid = false;
        }
        return valid;
    }

    public void atualizarSenha(String token) {
        pessoaFacade.findToken(token, new PessoaCallback() {
            @Override
            public void onSucess(Pessoa pessoaRequest) {
                pessoaRequest.setSenha(txtInputRecuperarSenha.getText().toString());
                pessoaFacade.update(pessoaRequest.getIdPessoa(), pessoaRequest, new PessoaCallback() {
                    @Override
                    public void onSucess(Pessoa pessoa) {
                        Toast.makeText(getApplicationContext(),
                                "Senha Alterada com sucesso",
                                Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onSucess(List<Pessoa> pessoas) {

                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getApplicationContext(),
                                "Verifique sua senha, Ela não esta nas regras do Aplicativo!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onSucess(List<Pessoa> pessoas) {

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Token Inválido, verifique seu email e tente novamente!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
