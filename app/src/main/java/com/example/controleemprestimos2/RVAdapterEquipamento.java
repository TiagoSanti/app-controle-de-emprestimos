package com.example.controleemprestimos2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RVAdapterEquipamento extends RecyclerView.Adapter<RVAdapterEquipamento.ViewHolder> {

    List<Equipamento> equipamentos;

    public RVAdapterEquipamento(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    @Override
    public RVAdapterEquipamento.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.equipamentos_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapterEquipamento.ViewHolder holder, int position) {
        holder.txtNomeEquipamento.setText(equipamentos.get(position).getNomeEquipamento());
        holder.txtMarca.setText(equipamentos.get(position).getMarca());
        holder.txtModelo.setText(equipamentos.get(position).getModelo());
        holder.txtNumPatrimonio.setText(equipamentos.get(position).getNumPatrimonio());
    }

    @Override
    public int getItemCount() {
        return equipamentos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNomeEquipamento;
        public TextView txtMarca;
        public TextView txtModelo;
        public TextView txtNumPatrimonio;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNomeEquipamento = itemView.findViewById(R.id.txtNomeEquipamento);
            txtMarca = itemView.findViewById(R.id.txtMarca);
            txtModelo = itemView.findViewById(R.id.txtModelo);
            txtNumPatrimonio = itemView.findViewById(R.id.txtNumPatrimonio);
        }
    }
}
