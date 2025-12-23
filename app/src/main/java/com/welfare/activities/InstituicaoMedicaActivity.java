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

import com.welfare.R;
import com.welfare.adapter.AdapterInstituicaoLista;
import com.welfare.model.InstituicaoMedica;
import com.welfare.model.Pessoa;
import com.welfare.ws.CallBack.InstituicaoMedicaCallBack;
import com.welfare.ws.Facade.InstituicaoFacade;

import java.util.List;

public class InstituicaoMedicaActivity extends AppCompatActivity {

    private Button btnInstituicaoMedicaVoltar, btnInstituicaoMedicaAdd;

    private RecyclerView recyclerInstituicaoMedica;

    private AdapterInstituicaoLista adapterInstituicaoLista;

    private InstituicaoFacade instituicaoFacade;

    private Pessoa pessoaSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instituicao_medica);

        carregarComponentes();
        listarInstituicoesMedicar();

        botaoAdicionar(btnInstituicaoMedicaAdd);
        botaoVoltar(btnInstituicaoMedicaVoltar);
    }

    private void carregarComponentes(){
        btnInstituicaoMedicaVoltar  = findViewById(R.id.btnInstituicaoMedicaVoltar);
        btnInstituicaoMedicaAdd     = findViewById(R.id.btnInstituicaoMedicaAdd);

        recyclerInstituicaoMedica   = findViewById(R.id.recyclerInstituicaoMedica);

        instituicaoFacade           = new InstituicaoFacade();

        carregarPessoaSession();
    }

    private void carregarPessoaSession(){
        Bundle intent = getIntent().getExtras();
        pessoaSession = (Pessoa) intent.getSerializable("pessoa");
    }

    private void listarInstituicoesMedicar(){
        instituicaoFacade.list(new InstituicaoMedicaCallBack() {
            @Override
            public void onSucess(InstituicaoMedica instituicaoMedica) {

            }

            @Override
            public void onSucess(List<InstituicaoMedica> instituicaoMedicaList) {
                configurarRecyclerView(instituicaoMedicaList);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void configurarRecyclerView(List<InstituicaoMedica> instituicaoMedicaList){
        adapterInstituicaoLista = new AdapterInstituicaoLista(instituicaoMedicaList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerInstituicaoMedica.setLayoutManager(layoutManager);
        recyclerInstituicaoMedica.setHasFixedSize(true);
        recyclerInstituicaoMedica.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerInstituicaoMedica.setAdapter(adapterInstituicaoLista);
    }

    private void  botaoVoltar(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void botaoAdicionar(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarEditarInstituicaoMedica.class);
                intent.putExtra("pessoa", pessoaSession);
                startActivity(intent);
                finish();
            }
        });
    }
}
