package com.welfare.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

import com.welfare.R;
import com.welfare.model.InstituicaoMedica;
import com.welfare.model.Pessoa;

public class AdicionarEditarPlanoSaude extends AppCompatActivity {

    private Button btnPlanoSaudeVoltar;

    private TextInputEditText txtInputPlanoSaudePrestadora,
                            txtInputPlanoSaudeInstituicao,
                            txtInputPlanoSaudeCobertura,
                            txtInputPlanoSaudeContratacao,
                            txtInputPlanoSaudeValidade,
                            txtInputPlanoSaudeValorMensal;

    private Switch swtPlanoSaudeSituacao;

    private ImageView imgPlanoSaudePesquisaInstituicao;

    private InstituicaoMedica instituicaomedica;
    private Pessoa pessoaSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_editar_plano_saude);

        carregarComponentes();

        carregarPessoaSession();

        carregarInstituicao();

        botaoVoltar(btnPlanoSaudeVoltar);
        botaoPesquisaInstituicao(imgPlanoSaudePesquisaInstituicao);
    }

    private void carregarComponentes() {
        btnPlanoSaudeVoltar = findViewById(R.id.btnPlanoSaudeVoltar);

        txtInputPlanoSaudePrestadora    = findViewById(R.id.txtInputPlanoSaudePrestadora);
        txtInputPlanoSaudeInstituicao   = findViewById(R.id.txtInputPlanoSaudeInstituicao);
        txtInputPlanoSaudeCobertura     = findViewById(R.id.txtInputPlanoSaudeCobertura);
        txtInputPlanoSaudeContratacao   = findViewById(R.id.txtInputPlanoSaudeContratacao);
        txtInputPlanoSaudeValidade      = findViewById(R.id.txtInputPlanoSaudeValidade);
        txtInputPlanoSaudeValorMensal   = findViewById(R.id.txtInputPlanoSaudeValorMensal);

        swtPlanoSaudeSituacao   = findViewById(R.id.swtPlanoSaudeSituacao);

        imgPlanoSaudePesquisaInstituicao    = findViewById(R.id.imgPlanoSaudePesquisaInstituicao);
    }

    private void carregarPessoaSession() {
        Bundle intent = getIntent().getExtras();
        pessoaSession = (Pessoa) intent.getSerializable("pessoa");
    }

    private void carregarInstituicao(){
        Bundle intent = getIntent().getExtras();
        instituicaomedica = (InstituicaoMedica) intent.getSerializable("instituicaoMedica");

        if(instituicaomedica != null){
            txtInputPlanoSaudeInstituicao.setText(instituicaomedica.getNome());
        }
    }

    private void botaoVoltar(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlanoSaudeActivity.class);
                intent.putExtra("pessoa", pessoaSession);
                startActivity(intent);
                finish();
            }
        });
    }

    private void botaoPesquisaInstituicao(ImageView imgPesquisaInstituicao){
        imgPesquisaInstituicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InstituicaoMedicaActivity.class);
                intent.putExtra("pessoa", pessoaSession);
                startActivity(intent);
                finish();
            }
        });
    }
}
