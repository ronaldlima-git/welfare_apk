package com.welfare.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.welfare.R;
import com.welfare.model.Cidade;
import com.welfare.model.Endereco;
import com.welfare.model.Estado;
import com.welfare.model.InstituicaoMedica;
import com.welfare.model.Pais;
import com.welfare.model.Pessoa;
import com.welfare.model.Telefone;
import com.welfare.ws.CallBack.CidadeCallBack;
import com.welfare.ws.CallBack.EnderecoCallBack;
import com.welfare.ws.CallBack.EstadoCallBack;
import com.welfare.ws.CallBack.InstituicaoMedicaCallBack;
import com.welfare.ws.CallBack.TelefoneCallBack;
import com.welfare.ws.Facade.CidadeFacade;
import com.welfare.ws.Facade.EnderecoFacade;
import com.welfare.ws.Facade.EstadoFacade;
import com.welfare.ws.Facade.InstituicaoFacade;
import com.welfare.ws.Facade.TelefoneFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdicionarEditarInstituicaoMedica extends AppCompatActivity {

    private TextInputEditText txtInputAddEditInstNome,
                            txtInputAddEditInstEmail,
                            txtinputAddEditInstTelefone,
                            txtInputAddEditInstCEP,
                            txtInputAddEditInstCidade,
                            txtInputAddEditInstBairro,
                            txtInputAddEditInstLogradouro,
                            txtInputAddEditInstNumero,
                            txtInputAddEditInstComplemento;

    private Spinner spinnerAddEditInstEstado;

    private Button btnAddEditInstConfirm,
                btnAddInstituicaoMedicaVoltar;

    private InstituicaoFacade instituicaoFacade;
    private EstadoFacade estadoFacade;
    private EnderecoFacade enderecoFacade;
    private TelefoneFacade telefoneFacade;
    private CidadeFacade cidadeFacade;

    private InstituicaoMedica instituicaoMedica;
    private Estado estado;
    private Cidade cidade;
    private Telefone telefone;
    private Endereco endereco;
    private Pessoa pessoaSession;


    private List<Estado> estados;

    private boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_editar_instituicao_medica);

        carregarComponentes();
        carregarPessoaSession();

        botaoConfirmar(btnAddEditInstConfirm);
        botaoVoltar(btnAddInstituicaoMedicaVoltar);
    }

    private void carregarComponentes(){
        txtInputAddEditInstNome         = findViewById(R.id.txtInputAddEditInstNome);
        txtInputAddEditInstEmail        = findViewById(R.id.txtInputAddEditInstEmail);
        txtInputAddEditInstCEP          = findViewById(R.id.txtInputAddEditInstCEP);
        txtInputAddEditInstCidade       = findViewById(R.id.txtInputAddEditInstCidade);
        txtInputAddEditInstBairro       = findViewById(R.id.txtInputAddEditInstBairro);
        txtInputAddEditInstLogradouro   = findViewById(R.id.txtInputAddEditInstLogradouro);
        txtInputAddEditInstNumero       = findViewById(R.id.txtInputAddEditInstNumero);
        txtInputAddEditInstComplemento  = findViewById(R.id.txtInputAddEditInstComplemento);
        txtinputAddEditInstTelefone     = findViewById(R.id.txtInputAddEditInstTelefone);

        spinnerAddEditInstEstado        = findViewById(R.id.spinnerAddEditInstEstado);

        btnAddEditInstConfirm           = findViewById(R.id.btnAddEditInstConfirm);
        btnAddInstituicaoMedicaVoltar   = findViewById(R.id.btnAddInstituicaoMedicaVoltar);

        instituicaoFacade   = new InstituicaoFacade();
        estadoFacade        = new EstadoFacade();
        telefoneFacade      = new TelefoneFacade();
        cidadeFacade        = new CidadeFacade();
        enderecoFacade      = new EnderecoFacade();

        instituicaoMedica   = new InstituicaoMedica();
        estado              = new Estado();
        telefone            = new Telefone();
        cidade              = new Cidade();
        endereco            = new Endereco();

        estados = new ArrayList<>();
        carregarEstados();

        atribuirMascara("(NN)N NNNN-NNNN", txtinputAddEditInstTelefone);
        atribuirMascara("NN.NNN-NNN", txtInputAddEditInstCEP);

        /*if (editarInstituicao()) {
            carregarInstituicao();
        }*/
    }

    private void atribuirMascara(String Pattern, TextInputEditText component){
        /**
         * N - for numbers.
         * L - for letters.
         * L - for numbers and letters.
         * l - for lowercase letters.
         * U - for uppedcase letters.
         * Metodo Responsavel em atribuir um padrão de um textInput**/
        SimpleMaskFormatter smf = new SimpleMaskFormatter(Pattern);
        MaskTextWatcher mtw = new MaskTextWatcher(component, smf);
        component.addTextChangedListener(mtw);
    }

    public void carregarPessoaSession(){
        Bundle intent = getIntent().getExtras();
        pessoaSession = (Pessoa) intent.getSerializable("pessoa");
    }

    private boolean editarInstituicao() {
        Bundle intent = getIntent().getExtras();
        return intent.getSerializable("instituicao") != null;
    }
    private void carregarInstituicao(){
        Bundle intent = getIntent().getExtras();
        instituicaoMedica = (InstituicaoMedica) intent.getSerializable("instituicao");
        txtInputAddEditInstNome.setText(instituicaoMedica.getNome());
        txtInputAddEditInstEmail.setText(instituicaoMedica.getEmail());
        txtinputAddEditInstTelefone.setText(instituicaoMedica.getTelefone().getNumero());
        txtInputAddEditInstCEP.setText(instituicaoMedica.getEndereco().getCep());
        txtInputAddEditInstCidade.setText(instituicaoMedica.getEndereco().getCidade().getNomeCidade());
        txtInputAddEditInstBairro.setText(instituicaoMedica.getEndereco().getBairro());
        txtInputAddEditInstLogradouro.setText(instituicaoMedica.getEndereco().getRua());
        txtInputAddEditInstNumero.setText(instituicaoMedica.getEndereco().getNumRua());
        txtInputAddEditInstComplemento.setText(instituicaoMedica.getEndereco().getComplemento());
        edit = true;
    }

    private void carregarEstados(){
        estadoFacade.list(new EstadoCallBack() {
            @Override
            public void onSucess(Estado estado) {

            }

            @Override
            public void onSucess(List<Estado> estadoList) {
                atribuirEstados(estadoList);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void atribuirEstados(List<Estado> estadoList){
        estados.addAll(estadoList);
        Collections.sort(estados);

        Estado selecione = new Estado();
        selecione.setNomeEstado("Selecione");
        estados.add(0,selecione);

        ArrayAdapter<Estado> adapter = new ArrayAdapter<Estado>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, estados);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAddEditInstEstado.setAdapter(adapter);

        spinnerAddEditInstEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(position != 0){
                    estado = estados.get(position);
                    System.out.println("Estado selecionado " + estado.getNomeEstado());
                    estado.setPais(new Pais(1,"Brasil"));
                }else{
                    estado = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void botaoConfirmar(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(camposValidos()) {
                    if (edit) {
                        atualizarInstituicaoMedica();
                    } else {
                        inserirInstituicaoMedica();
                    }
                //}
            }
        });
    }

    public void atualizarInstituicaoMedica(){

    }

    public void inserirInstituicaoMedica(){
        telefone.setNumero(txtinputAddEditInstTelefone.getText().toString());
        inserirTelefone(telefone);
    }

    private void inserirTelefone(Telefone telefoneInsert){
        telefoneFacade.insert(telefoneInsert, new TelefoneCallBack() {
            @Override
            public void onSucess(Telefone telefoneRequest) {
                telefone.setIdTelefone(telefoneRequest.getIdTelefone());

                cidade.setEstado(estado);
                cidade.setNomeCidade(txtInputAddEditInstCidade.getText().toString());
                inserirCidade(cidade);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void inserirCidade(Cidade cidadeInsert){
        cidadeFacade.insert(cidadeInsert, new CidadeCallBack() {
            @Override
            public void onSucess(Cidade cidadeRequest) {
                cidade.setIdCidade(cidadeRequest.getIdCidade());
                endereco.setCidade(cidade);
                endereco.setCep(txtInputAddEditInstCEP.getText().toString());
                endereco.setBairro(txtInputAddEditInstBairro.getText().toString());
                endereco.setRua(txtInputAddEditInstLogradouro.getText().toString());
                endereco.setComplemento(txtInputAddEditInstComplemento.getText().toString());
                endereco.setNumRua(Integer.parseInt(txtInputAddEditInstNumero.getText().toString()));

                inserirEndereco(endereco);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void inserirEndereco(Endereco enderecoInsert){
        enderecoFacade.insert(enderecoInsert, new EnderecoCallBack() {
            @Override
            public void onSucess(Endereco enderecoRequest) {
                endereco.setIdEndereco(enderecoRequest.getIdEndereco());

                instituicaoMedica.setEndereco(endereco);
                instituicaoMedica.setTelefone(telefone);
                instituicaoMedica.setNome(txtInputAddEditInstNome.getText().toString());
                instituicaoMedica.setEmail(txtInputAddEditInstEmail.getText().toString());
                incluirInstituicao(instituicaoMedica);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void incluirInstituicao(InstituicaoMedica instituicaoMedicaInsert){
        instituicaoFacade.insert(instituicaoMedicaInsert, new InstituicaoMedicaCallBack() {
            @Override
            public void onSucess(InstituicaoMedica instituicaoMedicaRequest) {

                Intent intent = new Intent(getApplicationContext(), AdicionarEditarPlanoSaude.class);
                intent.putExtra("pessoa", pessoaSession);
                intent.putExtra("instituicaoMedica", instituicaoMedicaRequest);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Instituicao Medica " + instituicaoMedica.getNome() + " Inserida com sucesso!",
                        Toast.LENGTH_SHORT
                ).show();
                finish();
            }

            @Override
            public void onSucess(List<InstituicaoMedica> instituicaoMedicaList) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


    public void botaoVoltar(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
