package com.welfare.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.welfare.R;
import com.welfare.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class AdapterPessoaLista extends RecyclerView.Adapter<AdapterPessoaLista.PessoaListaViewHolder> {

    private List<Pessoa> listPessoa;

    public AdapterPessoaLista(List<Pessoa> pessoas) {
        listPessoa = new ArrayList<Pessoa>();
        listPessoa.addAll(pessoas);
        System.out.println("Instanciado Adapter");
    }

    @NonNull
    @Override
    public PessoaListaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View pessoaLista = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_pessoa_lista, viewGroup, false);

        return new PessoaListaViewHolder(pessoaLista);
    }

    @Override
    public void onBindViewHolder(@NonNull PessoaListaViewHolder pessoaListaViewHolder, final int i) {
        final Pessoa pessoa = listPessoa.get(i);

        pessoaListaViewHolder.txtViewPessoaLista.setText(pessoa.getNome());
    }

    @Override
    public int getItemCount() {
        return listPessoa.size();
    }

    public class PessoaListaViewHolder extends RecyclerView.ViewHolder{
        TextView txtViewPessoaLista;

        public PessoaListaViewHolder(@NonNull View itemView) {
            super(itemView);

            txtViewPessoaLista = itemView.findViewById(R.id.txtViewPessoaLista);
        }
    }
}
