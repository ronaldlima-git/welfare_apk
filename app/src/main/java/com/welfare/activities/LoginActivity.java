package com.welfare.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.welfare.R;
import com.welfare.model.Pessoa;
import com.welfare.ws.CallBack.PessoaCallback;
import com.welfare.ws.Facade.PessoaFacade;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    //Atribuo na classe todos os componentes que utilizo em tela.
    private TextInputEditText inputEditLoginEmail, inputEditLoginPassword;

    private TextView txtViewLoginNeedHelp;

    private Button buttonLoginSubmit, buttonLoginSingIn;

    private Toast toastInvalidCredencials;

    private PessoaFacade pessoaFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Chamo o metodo para carregar os atributos conforme seus atributos
        handleLoadComponents();

        //Lida com preferencias compartilhadas para verificar se o login está salvo ou não
        handleAutoLogin();

        //Login Listener
        buttonLoginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin(inputEditLoginEmail.getText().toString(),
                        inputEditLoginPassword.getText().toString());
            }
        });

        //Cadastra-se Listener
        buttonLoginSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singIn();
            }
        });

        //Precisa de Ajuda Listener
        txtViewLoginNeedHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                needHelp();
            }
        });
    }

    public void handleLoadComponents(){
        txtViewLoginNeedHelp    = (TextView)    findViewById(R.id.txtViewAjudaRecuperarSenha);

        inputEditLoginEmail     = (TextInputEditText)    findViewById(R.id.inputEditAjudaEmail);
        inputEditLoginPassword  = (TextInputEditText)    findViewById(R.id.inputEditLoginPassword);

        buttonLoginSubmit       = (Button)    findViewById(R.id.buttonLoginSubmit);
        buttonLoginSingIn       = (Button)    findViewById(R.id.buttonLoginSingIn);

        pessoaFacade = new PessoaFacade();

        toastInvalidCredencials = (Toast) Toast.makeText(
                getApplicationContext(),
                R.string.loginInvalidCredentials,
                Toast.LENGTH_SHORT
        );
        inputEditLoginEmail.setFocusable(true);
    }

    /*Metodo Responsável em lidar a limpeza dos campos Inputs*/
    public void handleCleanInputs(){
        inputEditLoginEmail.setText(null);
        inputEditLoginPassword.setText(null);
    }

    /*Metodo responsável em lidar com o Login*/
    public void handleLogin(String email, String senha){

        pessoaFacade.login(email, senha, new PessoaCallback() {
            @Override
            public void onSucess(Pessoa pessoa) {
                handleCleanInputs();

                handleSaveSharedPreferences(pessoa.getEmail(), pessoa.getSenha());

                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                intent.putExtra("pessoa", pessoa);
                startActivity(intent);
            }

            @Override
            public void onSucess(List<Pessoa> pessoas) {

            }

            @Override
            public void onFailure(Throwable t) {
                toastInvalidCredencials.show();
            }
        });
    }

    /*Metodo responsável em lidar com salvar o login do usuário nas preferencias compartilhadas*/
    private void handleSaveSharedPreferences(String email, String password){
        SharedPreferences preferences  = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor     = preferences.edit();
        editor.clear();

        editor.putString("email", email);
        editor.putString("senha", password);
        editor.apply();
    }

    /*Metodo responsável em verificar se as preferencias compartilhadas possuem informações da chave "email"*/
    private void handleAutoLogin(){
        SharedPreferences preferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
        if(!preferences.getString("email","").isEmpty()){
            String email = preferences.getString("email","");
            String password = preferences.getString("senha","");

            System.out.println("Realizando Login automatico com Email" + email + ", senha: " + password);

            handleLogin(email, password);
        }
    }

    /*Metodo responsável em abrir a activity de Cadastro*/
    private void singIn(){
        startActivity(new Intent(getApplicationContext(), CadastrarActivity.class));
    }

    /*Metodo responsável em abrir a activity de solicitar ajudar com o cadastro*/
    private void needHelp (){
        Intent intent = new Intent(getApplicationContext(), AjudaActivity.class);
        intent.putExtra("email", inputEditLoginEmail.getText().toString());
        startActivity(intent);
    }


}
