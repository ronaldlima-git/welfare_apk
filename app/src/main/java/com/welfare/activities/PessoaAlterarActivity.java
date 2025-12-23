package com.welfare.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
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

public class PessoaAlterarActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Button btnPessoaAlterarVoltar, btnPessoaAlterarConfirmar;

    private TextInputEditText txtInputPessoaAlterarNome,
            txtInputPessoaAlterarCPF,
            txtInputPessoaAlterarEmail,
            txtInputPessoaAlterarDataNasc,
            txtInputPessoaAlterarTelefone,
            txtInputPessoaAlterarSenha,
            txtInputPessoaAlterarConfirmaSenha,
            txtInputPessoaAlterarCEP,
            txtInputPessoaAlterarCidade,
            txtInputPessoaAlterarBairro,
            txtInputPessoaAlterarRua,
            txtInputPessoaAlterarNumero,
            txtInputPessoaAlterarComplemento;

    private Spinner spinnerPessoaAlterarEstado;

    private Pessoa pessoaSession;
    private Estado estado;

    private List<Estado> estados;

    private EstadoFacade estadoFacade;
    private PessoaFacade pessoaFacade;
    private TelefoneFacade telefoneFacade;
    private CidadeFacade cidadeFacade;
    private EnderecoFacade enderecoFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_alterar);
        //Carrega componentes
        carregarComponentes();

        //Manipula Botão para voltar a tela do menu
        botaoVoltar(btnPessoaAlterarVoltar);

        InputDataNascimento(txtInputPessoaAlterarDataNasc);

        //Manipula Botão para Confirmar as alterações
        botaoConfirmar(btnPessoaAlterarConfirmar);

        //Manipula PessoaSession aos TextsInputs
        carregarPessoaSession();

    }

    /**Carrega componentes*/
    private void carregarComponentes(){
        btnPessoaAlterarVoltar       = findViewById(R.id.btnPessoaAlterarVoltar);
        btnPessoaAlterarConfirmar    = findViewById(R.id.btnPessoaAlterarConfirmar);

        txtInputPessoaAlterarNome           = findViewById(R.id.txtInputPessoaAlterarNome);
        txtInputPessoaAlterarCPF            = findViewById(R.id.txtInputPessoaAlterarCPF);
        txtInputPessoaAlterarEmail          = findViewById(R.id.txtInputPessoaAlterarEmail);
        txtInputPessoaAlterarDataNasc       = findViewById(R.id.txtInputPessoaAlterarDataNasc);
        txtInputPessoaAlterarTelefone       = findViewById(R.id.txtInputPessoaAlterarTelefone);
        txtInputPessoaAlterarSenha          = findViewById(R.id.txtInputPessoaAlterarSenha);
        txtInputPessoaAlterarConfirmaSenha  = findViewById(R.id.txtInputPessoaAlterarConfirmaSenha);
        txtInputPessoaAlterarCEP            = findViewById(R.id.txtInputPessoaAlterarCEP);
        txtInputPessoaAlterarCidade         = findViewById(R.id.txtInputPessoaAlterarCidade);
        txtInputPessoaAlterarBairro         = findViewById(R.id.txtInputPessoaAlterarBairro);
        txtInputPessoaAlterarRua            = findViewById(R.id.txtInputPessoaAlterarRua);
        txtInputPessoaAlterarNumero         = findViewById(R.id.txtInputPessoaAlterarNumero);
        txtInputPessoaAlterarComplemento    = findViewById(R.id.txtInputPessoaAlterarComplemento);

        spinnerPessoaAlterarEstado          = findViewById(R.id.spinnerPessoaAlterarEstado);

        pessoaSession   = new Pessoa();
        estado          = new Estado();

        estados = new ArrayList<Estado>();

        estadoFacade    = new EstadoFacade();
        pessoaFacade    = new PessoaFacade();
        telefoneFacade  = new TelefoneFacade();
        cidadeFacade    = new CidadeFacade();
        enderecoFacade  = new EnderecoFacade();
    }

    /**Manipula Botão para voltar a tela do menu*/
    private void botaoVoltar(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                intent.putExtra("pessoa", pessoaSession);
                startActivity(intent);
                finish();
            }
        });
    }

    /**Manipual a Pessoa Session passada da Activity anterior*/
    private void carregarPessoaSession(){
        Bundle bundle = getIntent().getExtras();

        pessoaSession = (Pessoa) bundle.getSerializable("pessoa");

        txtInputPessoaAlterarNome.setText(pessoaSession.getNome());
        txtInputPessoaAlterarCPF.setText(pessoaSession.getCpf());
        txtInputPessoaAlterarEmail.setText(pessoaSession.getEmail());
        converterDataEmString(pessoaSession.getDtNascimento());
        txtInputPessoaAlterarTelefone.setText(pessoaSession.getTelefone().getNumero());
        txtInputPessoaAlterarSenha.setText(pessoaSession.getSenha());
        txtInputPessoaAlterarConfirmaSenha.setText(pessoaSession.getSenha());
        txtInputPessoaAlterarCEP.setText(pessoaSession.getEndereco().getCep());
        txtInputPessoaAlterarCidade.setText(pessoaSession.getEndereco().getCidade().getNomeCidade());

        txtInputPessoaAlterarBairro.setText(pessoaSession.getEndereco().getBairro());
        txtInputPessoaAlterarRua.setText(pessoaSession.getEndereco().getRua());
        txtInputPessoaAlterarNumero.setText(Integer.toString(pessoaSession.getEndereco().getNumRua()));
        txtInputPessoaAlterarComplemento.setText(pessoaSession.getEndereco().getComplemento());
        carregarEstados();
    }

    private void converterDataEmString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        txtInputPessoaAlterarDataNasc.setText(format.format(date));
    }

    private Date converterStringInDate(String dataSelecionada){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = format.parse(dataSelecionada);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private void InputDataNascimento(TextInputEditText textInputEditText){
        textInputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicketDialog();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        month += 1;
        txtInputPessoaAlterarDataNasc.setText(dayOfMonth + "/" + month + "/" + year);
    }

    /**Realiza botão para confirmar as alterações do usuário*/
    private void botaoConfirmar(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCampos()){
                    setCamposPessoaSession();

                    /**Atualizar campos de Telefone **/

                    telefoneFacade.update(pessoaSession.getTelefone().getIdTelefone(), pessoaSession.getTelefone(), new TelefoneCallBack() {
                        @Override
                        public void onSucess(Telefone telefone) {
                            System.out.println("TELEFONE ATUALIZADO");
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            System.out.println("NÃO FOI POSSIVEL ATUALIZAR O TELEFONE");
                        }
                    });

                    /**Atualizar Estado**/
                    estadoFacade.update(pessoaSession.getEndereco().getCidade().getEstado().getIdEstado(),
                            pessoaSession.getEndereco().getCidade().getEstado(), new EstadoCallBack() {
                                @Override
                                public void onSucess(Estado estado) {
                                    System.out.println("ESTADO ATUALIZADO");
                                }

                                @Override
                                public void onSucess(List<Estado> estadoList) {

                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    System.out.println("NÃO FOI POSSIVEL ATUALIZAR O ESTADO");
                                }
                            });


                    /**Atualizar campos de Cidade   **/
                    cidadeFacade.update(pessoaSession.getEndereco().getCidade().getIdCidade(),
                            pessoaSession.getEndereco().getCidade(), new CidadeCallBack() {
                                @Override
                                public void onSucess(Cidade cidade) {
                                    System.out.println("CIDADE ATUALIZADO");
                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    System.out.println("NÃO FOI POSSIVEL ATUALIZAR A CIDADE");
                                }
                            });

                    /**Atualziar campos de Endereco **/
                    enderecoFacade.update(pessoaSession.getEndereco().getIdEndereco(),
                            pessoaSession.getEndereco(), new EnderecoCallBack() {
                                @Override
                                public void onSucess(Endereco endereco) {
                                    System.out.println("ENDERECO ATUALIZADO");
                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    System.out.println("NÃO FOI POSSIVEL ATUALIZAR O ENDERECO");
                                }
                            });

                    /**Atualizar campos da Pessoa   **/
                    pessoaFacade.update(pessoaSession.getIdPessoa(), pessoaSession, new PessoaCallback() {
                        @Override
                        public void onSucess(Pessoa pessoa) {
                            Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                            intent.putExtra("pessoa", pessoaSession);
                            startActivity(intent);
                            finish();

                            Toast.makeText(getApplicationContext(),
                                    "Cadastro Atualizado",
                                    Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSucess(List<Pessoa> pessoas) {

                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Toast.makeText(getApplicationContext(),
                                    "Ocorreu um erro na atualização das informações, por favor tente novamente mais tarde",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Campos Preenchidos de forma incorreta, verifique e tente novamente",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void showDatePicketDialog(){
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

    private void carregarEstados(){
        estadoFacade.list(new EstadoCallBack() {
            @Override
            public void onSucess(Estado estado) {
            }

            @Override
            public void onSucess(List<Estado> estadoList) {
                int idPerson = pessoaSession.getEndereco().getCidade().getEstado().getIdEstado();
                for(Estado estado1 :estadoList){
                    if(estado1.getIdEstado() == idPerson){
                        if(estadoList.remove(estado1)){
                            System.out.println("Pronto");
                            break;
                        }
                    }
                }
                atribuirEstado(estadoList);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void atribuirEstado(List<Estado> estadoList){
        Collections.sort(estados);
        estados.addAll(estadoList);

        Estado estadoPessoa = new Estado();
        estadoPessoa.setIdEstado(pessoaSession.getEndereco().getCidade().getEstado().getIdEstado());
        estadoPessoa.setUfEstado(pessoaSession.getEndereco().getCidade().getEstado().getUfEstado());
        estadoPessoa.setPais(pessoaSession.getEndereco().getCidade().getEstado().getPais());
        estadoPessoa.setNomeEstado(pessoaSession.getEndereco().getCidade().getEstado().getNomeEstado());

        estados.set(0, estadoPessoa);
        ArrayAdapter<Estado> adapter = new ArrayAdapter<Estado>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, estados);

        spinnerPessoaAlterarEstado.setAdapter(adapter);

        spinnerPessoaAlterarEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                estado = estados.get(position);
                estado.setPais(new Pais(1, "Brasil"));
                pessoaSession.getEndereco().getCidade().setEstado(estado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private boolean validarCampos(){
        boolean valido = true;

        if(txtInputPessoaAlterarNome.getText().toString().equals("") ||
                txtInputPessoaAlterarNome.getText().toString().length() < 3){
            valido = false;
            txtInputPessoaAlterarNome.setError(getString(R.string.requiredInputSingInNome));
        }
        if(txtInputPessoaAlterarCPF.getText().toString().equals("") ||
                txtInputPessoaAlterarCPF.getText().toString().length() < 14){
            valido = false;
            txtInputPessoaAlterarCPF.setError(getString(R.string.requiredInputSingInCPF));
        }
        if(txtInputPessoaAlterarEmail.getText().toString().equals("") ||
                !txtInputPessoaAlterarEmail.getText().toString().contains("@")){
            valido = false;
            txtInputPessoaAlterarEmail.setError(getString(R.string.requiredInputSingInEmail));
        }
        if(txtInputPessoaAlterarDataNasc.getText().toString().equals("")){
            valido = false;
            txtInputPessoaAlterarDataNasc.setError(getString(R.string.requiredInputSingInDateBirth));
        }
        if(txtInputPessoaAlterarTelefone.getText().toString().equals("") ||
                txtInputPessoaAlterarTelefone.getText().toString().length() < 15){
            valido = false;
            txtInputPessoaAlterarTelefone.setError(getString(R.string.requiredInputSingInPhone));
        }
        //senha maior que 8 caracteres
        if(txtInputPessoaAlterarSenha.getText().toString().equals("") ||
                txtInputPessoaAlterarSenha.getText().toString().length() < 8){
            valido = false;
            txtInputPessoaAlterarSenha.setError(getString(R.string.requiredInputSingInPass));
        }
        //confirmação de senha maior que 8 caracteres
        if(txtInputPessoaAlterarConfirmaSenha.getText().toString().equals("") ||
                txtInputPessoaAlterarConfirmaSenha.getText().toString().length() < 8){
            valido = false;
            txtInputPessoaAlterarConfirmaSenha.setError(getString(R.string.requiredInputSingInConfirmPass));
        }
        //Verificação se as senhas são iguais
        if(!txtInputPessoaAlterarSenha.getText().toString().equals(txtInputPessoaAlterarConfirmaSenha.getText().toString())){
            valido = false;
            txtInputPessoaAlterarSenha.setError(getString(R.string.requiredInputSingInPassEqual));
            txtInputPessoaAlterarConfirmaSenha.setError(getString(R.string.requiredInputSingInPassEqual));
        }
        if(txtInputPessoaAlterarCEP.getText().toString().equals("") ||
                txtInputPessoaAlterarCEP.getText().toString().length() < 10){
            valido = false;
            txtInputPessoaAlterarCEP.setError(getString(R.string.requiredInputSingInCEP));
        }
        if(txtInputPessoaAlterarCidade.getText().toString().length() < 3){
            valido = false;
            txtInputPessoaAlterarCidade.setError(getString(R.string.requiredInputSingInCity));
        }
        if(txtInputPessoaAlterarBairro.getText().toString().length() < 3){
            valido = false;
            txtInputPessoaAlterarBairro.setError(getString(R.string.requiredInputSingInBairro));
        }
        if(txtInputPessoaAlterarRua.getText().toString().length() < 3){
            valido = false;
            txtInputPessoaAlterarRua.setError(getString(R.string.requiredInputSingInPlace));
        }
        if(estado == null){
            valido = false;
            Toast.makeText(
                    getApplicationContext(),
                    R.string.errorNullState,
                    Toast.LENGTH_SHORT
            );
        }
        return valido;
    }

    private void setCamposPessoaSession(){
        pessoaSession.setNome(txtInputPessoaAlterarNome.getText().toString());
        pessoaSession.setCpf(txtInputPessoaAlterarCPF.getText().toString());
        pessoaSession.setEmail(txtInputPessoaAlterarEmail.getText().toString());
        pessoaSession.setDtNascimento(converterStringInDate(txtInputPessoaAlterarDataNasc.getText().toString()));
        pessoaSession.getTelefone().setNumero(txtInputPessoaAlterarTelefone.getText().toString());
        pessoaSession.setTelefone(pessoaSession.getTelefone());
        pessoaSession.setSenha(txtInputPessoaAlterarSenha.getText().toString());
        pessoaSession.getEndereco().setCep(txtInputPessoaAlterarCEP.getText().toString());
        pessoaSession.getEndereco().getCidade().setNomeCidade(txtInputPessoaAlterarCidade.getText().toString());
        pessoaSession.getEndereco().setBairro(txtInputPessoaAlterarBairro.getText().toString());
        pessoaSession.getEndereco().setRua(txtInputPessoaAlterarRua.getText().toString());
        pessoaSession.getEndereco().setComplemento(txtInputPessoaAlterarComplemento.getText().toString());
    }
}
