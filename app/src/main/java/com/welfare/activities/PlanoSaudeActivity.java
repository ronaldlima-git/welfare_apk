package com.welfare.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.welfare.R;
import com.welfare.model.Pessoa;

public class PlanoSaudeActivity extends AppCompatActivity {

    //TODO Realizar Fragment's para gerenciar o Plano de Saude.

    private Button btnPlanoSaudeVoltar, btnPlanoSaudeAdicionar;

    private RecyclerView recyclerViewListaPlanos;

    private Pessoa pessoaSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plano_saude);

        carregarComponentes();

        botaoVoltar(btnPlanoSaudeVoltar);

        botaoAdicionarPlanoSaude(btnPlanoSaudeAdicionar);
    }

    public void carregarComponentes(){
        btnPlanoSaudeVoltar     = findViewById(R.id.btnPlanoSaudeVoltar);
        btnPlanoSaudeAdicionar  = findViewById(R.id.btnPlanoSaudeAdicionar);

        recyclerViewListaPlanos = findViewById(R.id.recyclerViewListaPlanos);

        carregarPessoaSession();
    }

    public void carregarPessoaSession(){
        Bundle intent = getIntent().getExtras();

        pessoaSession = (Pessoa) intent.getSerializable("pessoa");
    }

    public void botaoVoltar(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void botaoAdicionarPlanoSaude(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarEditarPlanoSaude.class);
                intent.putExtra("pessoa", pessoaSession);
                startActivity(intent);
                finish();
            }
        });
    }
}
