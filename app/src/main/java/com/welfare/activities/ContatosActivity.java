package com.welfare.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.welfare.adapter.AdapterContatoLista;
import com.welfare.R;
import com.welfare.model.Contato;
import com.welfare.model.Pessoa;
import com.welfare.ws.CallBack.ContatoCallBack;
import com.welfare.ws.Facade.ContatoFacade;

import java.util.List;

public class ContatosActivity extends AppCompatActivity {

    //TODO Realizar um Fragments para gerenciar os Contatos

    private Button btnContatosVoltar, btnContatosAdicionar;

    private AdapterContatoLista adapterContatoLista;

    private RecyclerView recyclerViewListaContatos;

    private ContatoFacade contatoFacade;

    private Pessoa pessoaSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);

        carregarComponentes();

        System.out.println("Carregando Contatos...");
        carregarContatosPessoa();


        botaoVoltar(btnContatosVoltar);
        botaoAdicionarContato(btnContatosAdicionar);
    }

    private void carregarComponentes(){
        btnContatosVoltar       = findViewById(R.id.btnContatosVoltar);
        btnContatosAdicionar    = findViewById(R.id.btnContatosAdicionar);

        recyclerViewListaContatos = findViewById(R.id.recyclerViewListaContatos);

        carregarPessoaSession();

        contatoFacade = new ContatoFacade();
    }

    private void carregarPessoaSession(){
        Bundle intent = getIntent().getExtras();
        pessoaSession = (Pessoa) intent.getSerializable("pessoa");
    }

    private void carregarContatosPessoa(){
        contatoFacade.findByContatoPessoa(pessoaSession.getIdPessoa(), new ContatoCallBack() {
            @Override
            public void onSucess(Contato contato) {
            }

            @Override
            public void onSucess(List<Contato> contatos) {
                configurarRecyclerView(contatos);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("Contatos Error");
                System.out.println(t.getMessage());
            }
        });
    }

    private void configurarRecyclerView(List<Contato> contatos){
        adapterContatoLista = new AdapterContatoLista(contatos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewListaContatos.setLayoutManager(layoutManager);
        recyclerViewListaContatos.setHasFixedSize(true);
        recyclerViewListaContatos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewListaContatos.setAdapter(adapterContatoLista);
    }

    private void botaoVoltar(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void botaoAdicionarContato(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarContatoActivity.class);
                intent.putExtra("pessoa", pessoaSession);
                startActivity(intent);
                finish();
            }
        });
    }


}
