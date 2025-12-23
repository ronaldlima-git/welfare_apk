package com.welfare.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.welfare.R;
import com.welfare.model.Contato;
import com.welfare.ws.CallBack.ContatoCallBack;
import com.welfare.ws.Facade.ContatoFacade;

import java.util.ArrayList;
import java.util.List;

public class AdapterContatoLista extends RecyclerView.Adapter<AdapterContatoLista.ContatoListaViewHolder> {

    private List<Contato> contatos;
    private ContatoFacade contatoFacade;

    public AdapterContatoLista(List<Contato> contatoList){
        contatos = new ArrayList<Contato>();
        contatos.addAll(contatoList);

        contatoFacade = new ContatoFacade();
    }

    /**onCreateViewHolder -> Faz a Criação do layout conforme xml adapater_contato_lista*/
    @NonNull
    @Override
    public ContatoListaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View contatoLista = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_contato_lista, viewGroup, false);

        return new ContatoListaViewHolder(contatoLista);
    }

    /**onBindViewHolder -> Realiza a inclusão das informações nos componentes conforme o layout*/
    @Override
    public void onBindViewHolder(@NonNull final ContatoListaViewHolder contatosListaViewHolder, final int position) {
        final Contato contato = contatos.get(position);
        contatosListaViewHolder.txtViewContatoLista.setText(contato.getContato().getNome());
        contatosListaViewHolder.switchContatoLista.setChecked(contato.getStatus());

        contatosListaViewHolder.switchContatoLista.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                contato.setStatus(b);
                contatoFacade.update(contato.getIdContato(), contato, new ContatoCallBack() {
                    @Override
                    public void onSucess(Contato contato) {

                    }

                    @Override
                    public void onSucess(List<Contato> contatos) {

                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }
        });
    }

    /**getItemCount -> retorna a quantidade de itens que irão ser exibidos*/
    @Override
    public int getItemCount() {
        return contatos.size();
    }

    public class ContatoListaViewHolder extends RecyclerView.ViewHolder{
        TextView txtViewContatoLista;
        Switch switchContatoLista;

        public ContatoListaViewHolder(@NonNull View itemView) {
            super(itemView);

            txtViewContatoLista = itemView.findViewById(R.id.txtViewContatoLista);
            switchContatoLista = itemView.findViewById(R.id.switchContatoLista);
        }


    }

}
