package com.welfare.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.welfare.R;
import com.welfare.adapter.AdapterPessoaLista;
import com.welfare.model.Contato;
import com.welfare.model.Pessoa;
import com.welfare.recycler.ClickListener;
import com.welfare.recycler.RecyclerTouchListener;
import com.welfare.ws.CallBack.ContatoCallBack;
import com.welfare.ws.CallBack.PessoaCallback;
import com.welfare.ws.Facade.ContatoFacade;
import com.welfare.ws.Facade.PessoaFacade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdicionarContatoActivity extends AppCompatActivity {

    private Button btnAdicionarContatoVoltar;

    private TextInputEditText txtInputBuscarPessoa;

    private AdapterPessoaLista adapterPessoaLista;
    private RecyclerView recyclerViewPessoaLista;

    private Pessoa pessoaSession;
    private Contato contato;
    private PessoaFacade pessoaFacade;
    private ContatoFacade contatoFacade;

    private List<Pessoa> pessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_contato);

        carregarComponentes();

        configurarAdapterPessoa();

        adicionarContato(recyclerViewPessoaLista);
        botaoVoltar(btnAdicionarContatoVoltar);
    }

    private void carregarComponentes(){
        btnAdicionarContatoVoltar = findViewById(R.id.btnAdicionarContatoVoltar);

        txtInputBuscarPessoa    = findViewById(R.id.txtInputBuscarPessoa);
        recyclerViewPessoaLista = findViewById(R.id.recyclerViewPessoaLista);

        pessoas = new ArrayList<Pessoa>();

        contato = new Contato();

        pessoaFacade    =  new PessoaFacade();
        contatoFacade   =  new ContatoFacade();

        carregarPessoaSession();

    }

    private void carregarPessoaSession(){
        Bundle intent = getIntent().getExtras();
        pessoaSession = (Pessoa) intent.getSerializable("pessoa");
    }

    private void configurarAdapterPessoa(){
        pessoaFacade.list(new PessoaCallback() {
            @Override
            public void onSucess(Pessoa pessoa) {
            }

            @Override
            public void onSucess(List<Pessoa> pessoasList) {
                tratarPessoasList(pessoasList);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Erro na instancia de Listagem das pessoas",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void tratarPessoasList(final List<Pessoa> pessoaList){
        //Remover
        contatoFacade.findByContatoPessoa(pessoaSession.getIdPessoa(), new ContatoCallBack() {
            @Override
            public void onSucess(Contato contato) {

            }

            @Override
            public void onSucess(List<Contato> contatosList) {

                if(!contatosList.isEmpty()) {
                    List<Pessoa> pessoaList2 = new ArrayList<Pessoa>();
                    pessoaList2.addAll(pessoaList);
                    for (Pessoa pessoa : pessoaList2) {
                        for (Contato contato : contatosList) {
                            if (pessoa.getIdPessoa() == contato.getContato().getIdPessoa()) {
                                pessoaList.remove(pessoa);
                            }
                        }
                    }

                }
                removerPessoaSesion(pessoaList);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void removerPessoaSesion(List<Pessoa> pessoasList){
        for(Pessoa pessoa: pessoasList){
            if(pessoa.getIdPessoa() == pessoaSession.getIdPessoa()){
                pessoasList.remove(pessoa);
                break;
            }
        }
        pessoas.addAll(pessoasList);
        configurarRecyclerView(pessoas);
    }

    private void configurarRecyclerView(List<Pessoa> pessoas){
        adapterPessoaLista      = new AdapterPessoaLista(pessoas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPessoaLista.setLayoutManager(layoutManager);
        recyclerViewPessoaLista.setHasFixedSize(true);
        recyclerViewPessoaLista.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewPessoaLista.setAdapter(adapterPessoaLista);
    }

    private void adicionarContato(RecyclerView recyclerView){
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                System.out.println("POSIÇÃO :" + position);
                /**Inserir Contato*/

                contato.setStatus(true);
                contato.setContato(pessoas.get(position));
                contato.setPessoa(pessoaSession);
                contatoFacade.insert(contato, new ContatoCallBack() {
                    @Override
                    public void onSucess(Contato contato) {
                        /**Carregar ContatoActivity Passando (PessoaSession)*/
                        Intent intent = new Intent(getApplicationContext(), ContatosActivity.class);
                        intent.putExtra("pessoa", pessoaSession);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onSucess(List<Contato> contatos) {

                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private void botaoVoltar(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ContatosActivity.class);
                intent.putExtra("pessoa", pessoaSession);
                startActivity(intent);
                finish();
            }
        });
    }
}
