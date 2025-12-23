package com.welfare.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.welfare.R;
import com.welfare.model.Pessoa;

public class MainMenuActivity extends AppCompatActivity {
    private Pessoa pessoaSession;

    private TextView txtViewMainMenuNome, txtViewMainMenuEmail, txtViewMainTelefone;

    private Button btnMainMenuConsulta, btnMainMenuTratamento, btnMainMenuContato, btnMainMenuPlanoSaude, btnMainMenuVoltar, btnMainMenuMudarInformacao;

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Carrega componentes
        carregarComponentes();

        //Manipula com o botão voltar
        botaoVoltar(btnMainMenuVoltar);

        //Manipula receber pessoa logada
        carregarPessoaSession();

        //Manipula labels do menu
        setTextViewInformacaoPessoa();

        //Manipula botão para alterar as informações da pessoa
        botaoMudarInformacao(btnMainMenuMudarInformacao);

        /**BOTÕES DO MENU**/
        //Manipula botão para abrir Consultas e exames
        botaoConsulta(btnMainMenuConsulta);

        //Manipula botão para abrir Trataments
        botaoTratamento(btnMainMenuTratamento);

        //Manipula botão para abrir Contatos
        botaoContato(btnMainMenuContato);

        //Manipula botão para abrir Planos de Saúde
        botaoPlanoSaude(btnMainMenuPlanoSaude);
    }

    /*Responsável por Lidar com os Instanciar os Componentes em tela para serem utilizados a qualquer momentos pelas funções e métodos*/
    private void carregarComponentes(){
        /* Text View's */
        txtViewMainMenuNome     = findViewById(R.id.txtViewMainMenuNome);
        txtViewMainMenuEmail    = findViewById(R.id.txtViewMainMenuEmail);
        txtViewMainTelefone     = findViewById(R.id.txtViewMainMenuPhone);

        /* Button's */
        btnMainMenuConsulta        = findViewById(R.id.btnMainMenuConsultation);
        btnMainMenuTratamento      = findViewById(R.id.btnMainMenuTratament);
        btnMainMenuContato         = findViewById(R.id.btnMainMenuContacts);
        btnMainMenuPlanoSaude      = findViewById(R.id.btnMainMenuHealthInsurance);
        btnMainMenuVoltar          = findViewById(R.id.btnMainMenuToBack);
        btnMainMenuMudarInformacao = findViewById(R.id.btnMainMenuChangeInformation);

        /*Alert Messages*/
        alertDialog     = carregarAlertaLogOut();
    }

    /*Ativa o AlertDialog para escolher se queres ou não sair do seu login*/
    private void botaoVoltar(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
            }
        });
    }

    /*Realiza a criação da mensagem de alerta ao logout*/
    private AlertDialog carregarAlertaLogOut() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat_Dialog));
        builder.setTitle(R.string.alertDialogLogout);

        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                limparPreferenciasCompartilhadas();
                finish();
            }
        });

        builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return builder.create();
    }

    /*Revisar não esta limpando por completo as preferencias do usuário*/
    private void limparPreferenciasCompartilhadas() {
        SharedPreferences preferences = getSharedPreferences(getString(R.string.pref_key), MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("email");
        editor.remove("senha");
        editor.clear();
        editor.apply();
    }

    private void carregarPessoaSession(){
        Bundle bundle = getIntent().getExtras();

        pessoaSession = (Pessoa) bundle.getSerializable("pessoa");
    }

    private void setTextViewInformacaoPessoa(){
        txtViewMainMenuNome.setText(pessoaSession.getNome());
        txtViewMainMenuEmail.setText(pessoaSession.getEmail());
        txtViewMainTelefone.setText(pessoaSession.getTelefone().getNumero());
    }

    /**Métodos dos botões do Menu principal**/
    private void botaoMudarInformacao(Button button){
        /*set click listenner in button btnMainMenuChangeInformation*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent inclui a classeda activity a ser iniciada
                Intent intent = new Intent(getApplicationContext(), PessoaAlterarActivity.class);
                //Inclui a váriavel PessoaSession para outra tela
                intent.putExtra("pessoa", pessoaSession);
                //Inicia a Activity
                startActivity(intent);
                finish();
            }
        });
    }

    private void botaoConsulta(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConsultasExamesActivity.class);
                intent.putExtra("pessoa", pessoaSession);
                startActivity(intent);
            }
        });
    }

    private void botaoTratamento(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TratamentsActivity.class);
                intent.putExtra("pessoa", pessoaSession);
                startActivity(intent);
            }
        });
    }

    private void botaoContato(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ContatosActivity.class);
                intent.putExtra("pessoa", pessoaSession);
                startActivity(intent);
            }
        });
    }

    private void botaoPlanoSaude(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlanoSaudeActivity.class);
                intent.putExtra("pessoa", pessoaSession);
                startActivity(intent);
            }
        });
    }
    /**Final dos metodos dos botoes do menu principal**/
}
