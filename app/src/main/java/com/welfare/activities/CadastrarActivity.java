package com.welfare.activities;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.welfare.R;
import com.welfare.model.Cidade;
import com.welfare.model.Endereco;
import com.welfare.model.Estado;
import com.welfare.model.Pais;
import com.welfare.model.Pessoa;
import com.welfare.model.Telefone;
import com.welfare.ws.CallBack.CidadeCallBack;
import com.welfare.ws.CallBack.EnderecoCallBack;
import com.welfare.ws.CallBack.EstadoCallBack;
import com.welfare.ws.CallBack.PessoaCallback;
import com.welfare.ws.CallBack.TelefoneCallBack;
import com.welfare.ws.Facade.CidadeFacade;
import com.welfare.ws.Facade.EnderecoFacade;
import com.welfare.ws.Facade.EstadoFacade;
import com.welfare.ws.Facade.PessoaFacade;
import com.welfare.ws.Facade.TelefoneFacade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class CadastrarActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    //Inputs da Tela
    private TextInputEditText txtInputCadastrarNome,
            txtInputCadastrarCPF,
            txtInputCadastrarEmail,
            txtInputCadastrarDataNasc,
            txtInputCadastrarTelefone,
            txtInputCadastrarSenha,
            txtInputCadastrarConfirmarSenha,
            txtInputCadastrarCEP,
            txtInputCadastrarCidade,
            txtInputCadastrarBairro,
            txtInputCadastrarRua,
            txtInputCadastrarNumero,
            txtInputCadastrarComplemento;

    //Botões da Tela
    private Button btnCadastrarVoltar,
            btnCadastrarConfirmar;

    private Spinner spinnerCadastrarEstado;

    private Pessoa pessoa;
    private Telefone telefone;
    private Estado estado;
    private Cidade cidade;
    private Endereco endereco;

    private PessoaFacade pessoaFacade;
    private TelefoneFacade telefoneFacade;
    private EstadoFacade estadoFacade;
    private CidadeFacade cidadeFacade;
    private EnderecoFacade enderecoFacade;

    private List<Estado> estados;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        carregarComponentes();

        //click para apresentar uma caixa de dialogo para escolha da data de nascimento
        txtInputCadastrarDataNasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePikerDialog();
            }
        });

        botaoVoltar(btnCadastrarVoltar);

        botaoConfirmar(btnCadastrarConfirmar);
    }

    public void carregarComponentes(){
        txtInputCadastrarNome           = findViewById(R.id.txtInputCadastrarNome);
        txtInputCadastrarCPF            = findViewById(R.id.txtInputCadastrarCPF);
        txtInputCadastrarEmail          = findViewById(R.id.txtInputCadastrarEmail);
        txtInputCadastrarDataNasc       = findViewById(R.id.txtInputCadastrarDataNasc);
        txtInputCadastrarTelefone       = findViewById(R.id.txtInputCadastrarTelefone);
        txtInputCadastrarSenha          = findViewById(R.id.txtInputCadastrarSenha);
        txtInputCadastrarConfirmarSenha = findViewById(R.id.txtInputCadastrarConfirmarSenha);

        txtInputCadastrarCEP         = findViewById(R.id.txtInputCadastrarCEP);
        txtInputCadastrarCidade      = findViewById(R.id.txtInputCadastrarCidade);
        txtInputCadastrarBairro      = findViewById(R.id.txtInputCadastrarBairro);
        txtInputCadastrarRua         = findViewById(R.id.txtInputCadastrarRua);
        txtInputCadastrarNumero      = findViewById(R.id.txtInputCadastrarNumero);
        txtInputCadastrarComplemento = findViewById(R.id.txtInputCadastrarComplemento);

        spinnerCadastrarEstado       = findViewById(R.id.spinnerCadastrarEstado);

        btnCadastrarConfirmar        = findViewById(R.id.btnCadastrarConfirmar);
        btnCadastrarVoltar           = findViewById(R.id.btnCadastrarVoltar);

        pessoa      = new Pessoa();
        telefone    = new Telefone();
        estado      = new Estado();
        cidade      = new Cidade();
        endereco    = new Endereco();

        pessoaFacade    = new PessoaFacade();
        telefoneFacade  = new TelefoneFacade();
        estadoFacade    = new EstadoFacade();
        cidadeFacade    = new CidadeFacade();
        enderecoFacade  = new EnderecoFacade();

        estados = new ArrayList<>();
        carregarEstados();
        atribuirMascara("NNN.NNN.NNN-NN", txtInputCadastrarCPF);
        atribuirMascara("(NN)N NNNN-NNNN", txtInputCadastrarTelefone);
        atribuirMascara("NN.NNN-NNN", txtInputCadastrarCEP);
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
                if (handleValidateFields()) {
                    handleIncludePerson();
                }
            }
        });
    }

    public void handleIncludePerson(){
        //Para incluir Pessoa incluimos Telefone, Endereço antes de realmente incluir a Pessoa.

        telefone.setNumero(txtInputCadastrarTelefone.getText().toString());
        inserirTelefone(telefone);
    }

    private void inserirTelefone(Telefone telefoneInsert){
        telefoneFacade.insert(telefoneInsert, new TelefoneCallBack() {
            @Override
            public void onSucess(Telefone telefoneRequest) {
                System.out.println("Telefone Incluída - ID" + telefoneRequest.getIdTelefone() + " Número " + telefoneRequest.getNumero());
                telefone.setIdTelefone(telefoneRequest.getIdTelefone());

                cidade.setNomeCidade(txtInputCadastrarCidade.getText().toString());
                cidade.setEstado(estado);

                inserirCidade(cidade);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),
                        R.string.errorIncluedPerson,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void inserirCidade(Cidade cidadeInsert){
        cidadeFacade.insert(cidadeInsert, new CidadeCallBack() {
            @Override
            public void onSucess(Cidade cidadeRequest) {
                System.out.println("Cidade Incluida - ID" + cidadeRequest.getIdCidade() + " Nome" + cidadeRequest.getNomeCidade());
                cidade.setIdCidade(cidadeRequest.getIdCidade());
                endereco.setCidade(cidade);
                endereco.setCep(txtInputCadastrarCEP.getText().toString());
                endereco.setBairro(txtInputCadastrarBairro.getText().toString());
                endereco.setRua(txtInputCadastrarRua.getText().toString());
                endereco.setComplemento(txtInputCadastrarComplemento.getText().toString());
                endereco.setNumRua(Integer.parseInt(txtInputCadastrarNumero.getText().toString()));
                inserirEndereco(endereco);
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),
                        R.string.errorIncluedPerson,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    /**Metodo Responsavel em receber o endereço para incluir*/
    private void inserirEndereco(Endereco enderecoInsert){
        enderecoFacade.insert(enderecoInsert, new EnderecoCallBack() {
            @Override
            public void onSucess(Endereco enderecoRequest) {
                endereco.setIdEndereco(enderecoRequest.getIdEndereco());

                pessoa.setEndereco(endereco);
                pessoa.setTelefone(telefone);
                pessoa.setNome(txtInputCadastrarNome.getText().toString());
                pessoa.setCpf(txtInputCadastrarCPF.getText().toString());
                pessoa.setEmail(txtInputCadastrarEmail.getText().toString());
                pessoa.setDtNascimento(converterDate(txtInputCadastrarDataNasc.getText().toString()));
                pessoa.setSenha(txtInputCadastrarSenha.getText().toString());

                inserirPessoa(pessoa);

            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),
                        R.string.errorIncluedPerson,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    /**Metodo Responsável em receber o endereço para incluir*/
    private void inserirPessoa(Pessoa pessoaInsert){
        pessoaFacade.insert(pessoaInsert, new PessoaCallback() {
            @Override
            public void onSucess(Pessoa pessoa) {
                Toast.makeText(getApplicationContext(),
                        "Cadastro Concluído com sucesso!",
                        Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onSucess(List<Pessoa> pessoas) {

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Ocorreu um erro na inclusão de pessoa, Tente novamente mais tarde",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    /**Metodo Responsavel em converter string em date com o padrão Dia/Mes/Ano*/
    private Date converterDate(String dateselected){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = format.parse(dateselected);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**Metodo Responsavel de incluir ao input o "dia/mes/ano"*/
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        month += 1;
        txtInputCadastrarDataNasc.setText(dayOfMonth + "/" + month + "/" + year);
    }

    /**Metodo responsavel em mostrar o date pikerDialog*/
    public void showDatePikerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.show();
    }

    /**Metodo Responsavel por validar os campos do cadastro de pessoa**/
    private boolean handleValidateFields(){
        boolean valid = true;

        if(txtInputCadastrarNome.getText().toString().equals("") ||
                txtInputCadastrarNome.getText().toString().length() < 3){
            valid = false;
            txtInputCadastrarNome.setError(getString(R.string.requiredInputSingInNome));
        }
        if(txtInputCadastrarCPF.getText().toString().equals("") ||
                txtInputCadastrarCPF.getText().toString().length() < 14){
            valid = false;
            txtInputCadastrarCPF.setError(getString(R.string.requiredInputSingInCPF));
        }
        if(txtInputCadastrarEmail.getText().toString().equals("") ||
                !txtInputCadastrarEmail.getText().toString().contains("@")){
            valid = false;
            txtInputCadastrarEmail.setError(getString(R.string.requiredInputSingInEmail));
        }
        if(txtInputCadastrarDataNasc.getText().toString().equals("")){
            valid = false;
            txtInputCadastrarDataNasc.setError(getString(R.string.requiredInputSingInDateBirth));
        }
        if(txtInputCadastrarTelefone.getText().toString().equals("") ||
                txtInputCadastrarTelefone.getText().toString().length() < 15){
            valid = false;
            txtInputCadastrarTelefone.setError(getString(R.string.requiredInputSingInPhone));
        }
        //senha maior que 8 caracteres
        if(txtInputCadastrarSenha.getText().toString().equals("") ||
                txtInputCadastrarSenha.getText().toString().length() < 8){
            valid = false;
            txtInputCadastrarSenha.setError(getString(R.string.requiredInputSingInPass));
        }
        //confirmação de senha maior que 8 caracteres
        if(txtInputCadastrarConfirmarSenha.getText().toString().equals("") ||
                txtInputCadastrarConfirmarSenha.getText().toString().length() < 8){
            valid = false;
            txtInputCadastrarConfirmarSenha.setError(getString(R.string.requiredInputSingInConfirmPass));
        }
        //Verificação se as senhas são iguais
        if(!txtInputCadastrarSenha.getText().toString().equals(txtInputCadastrarConfirmarSenha.getText().toString())){
            valid = false;
            txtInputCadastrarSenha.setError(getString(R.string.requiredInputSingInPassEqual));
            txtInputCadastrarConfirmarSenha.setError(getString(R.string.requiredInputSingInPassEqual));
        }
        if(txtInputCadastrarCEP.getText().toString().equals("") ||
                txtInputCadastrarCEP.getText().toString().length() < 10){
            valid = false;
            txtInputCadastrarCEP.setError(getString(R.string.requiredInputSingInCEP));
        }
        if(txtInputCadastrarCidade.getText().toString().length() < 3){
            valid = false;
            txtInputCadastrarCidade.setError(getString(R.string.requiredInputSingInCity));
        }
        if(txtInputCadastrarBairro.getText().toString().length() < 3){
            valid = false;
            txtInputCadastrarBairro.setError(getString(R.string.requiredInputSingInBairro));
        }
        if(txtInputCadastrarRua.getText().toString().length() < 3){
            valid = false;
            txtInputCadastrarRua.setError(getString(R.string.requiredInputSingInPlace));
        }
        if(estado == null){
            valid = false;
            Toast.makeText(getApplicationContext(),
                    R.string.errorNullState,
                    Toast.LENGTH_SHORT).show();
        }
        return valid;
    }

    /**Metodo Responsavel em realizar a busca dos estados no facade*/
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

    /**Metodo Responsavel em realizar a atribuição dos estados ao Spinner*/
    public void atribuirEstados(List<Estado> estadoList){

        estados.addAll(estadoList);
        Collections.sort(estados);

        Estado selecione = new Estado();
        selecione.setNomeEstado("Selecione");
        estados.add(0,selecione);

        ArrayAdapter<Estado> adapter = new ArrayAdapter<Estado>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, estados);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCadastrarEstado.setAdapter(adapter);

        spinnerCadastrarEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
}
