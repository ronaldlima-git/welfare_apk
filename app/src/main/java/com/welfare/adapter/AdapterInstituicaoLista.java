package com.welfare.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.welfare.R;
import com.welfare.model.InstituicaoMedica;
import com.welfare.ws.Facade.InstituicaoFacade;

import java.util.ArrayList;
import java.util.List;

public class AdapterInstituicaoLista extends RecyclerView.Adapter<AdapterInstituicaoLista.InstituicaoListaViewHolder>{

    private List<InstituicaoMedica> listInstituicaoMedica;

    public AdapterInstituicaoLista(List<InstituicaoMedica> instituicaoMedicaList){
        listInstituicaoMedica = new ArrayList<InstituicaoMedica>();
        listInstituicaoMedica.addAll(instituicaoMedicaList);
    }


    @NonNull
    @Override
    public InstituicaoListaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View instituicaoLista = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_instituicao_lista, viewGroup, false);

        return new InstituicaoListaViewHolder(instituicaoLista);
    }

    @Override
    public void onBindViewHolder(@NonNull InstituicaoListaViewHolder instituicaoListaViewHolder, int position) {
        InstituicaoMedica instituicaoMedica = listInstituicaoMedica.get(position);
        String str = instituicaoMedica.getNome() + " - " + instituicaoMedica.getEndereco().getCidade().getNomeCidade();
        instituicaoListaViewHolder.txtInstituicaoLista.setText(str);
    }

    @Override
    public int getItemCount() {
        return listInstituicaoMedica.size();
    }

    public class InstituicaoListaViewHolder extends RecyclerView.ViewHolder {
        TextView txtInstituicaoLista;


        public InstituicaoListaViewHolder(@NonNull View itemView) {
            super(itemView);

            txtInstituicaoLista = itemView.findViewById(R.id.txtInstituicaoLista);
        }
    }
}
