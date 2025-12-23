package com.welfare.activities;

import android.content.Intent;
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

public class AjudaActivity extends AppCompatActivity {

    private Button btnAjudaVoltar, btnAjudaConfirmar;

    private TextInputEditText inputEditAjudaEmail;

    private PessoaFacade pessoaFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);

        /*Metodo responsavel por Carregar os Componentes em Tela*/
        carregarComponentes();

        /*Metodo traz o e-mail do login caso preenchido*/
        carregarEmail();

        /*Metodo cria a ação de voltar a tela de Login*/
        botaoVoltar(btnAjudaVoltar);

        /*Chama a função para recuperar a senha*/
        botaoConfirmar(btnAjudaConfirmar);
    }

    /*Metodo responsavel por Carregar os Componentes em Tela*/
    private void carregarComponentes(){
        btnAjudaVoltar          = findViewById(R.id.btnAjudaVoltar);
        btnAjudaConfirmar       = findViewById(R.id.btnAjudaConfirmar);

        inputEditAjudaEmail     = findViewById(R.id.inputEditAjudaEmail);

        pessoaFacade = new PessoaFacade();
    }

    /*Metodo traz o e-mail do login caso preenchido*/
    private void carregarEmail(){
        Bundle data = getIntent().getExtras();
        inputEditAjudaEmail.setText(data.getString("email"));
    }

    private void botaoVoltar(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void botaoConfirmar(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gerarToken();
            }
        });
    }

    /*Metodo responsável em chamar em gerar token */
    public void gerarToken(){
        pessoaFacade.token(inputEditAjudaEmail.getText().toString(), new PessoaCallback() {
            @Override
            public void onSucess(Pessoa pessoa) {
               Intent intent = new Intent(getApplicationContext(), RecuperarSenha.class);
               intent.putExtra("pessoa", pessoa);
               startActivity(intent);
               finish();
            }

            @Override
            public void onSucess(List<Pessoa> pessoas) {

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Seu email não está cadastrado, por favor cadastra-se",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
