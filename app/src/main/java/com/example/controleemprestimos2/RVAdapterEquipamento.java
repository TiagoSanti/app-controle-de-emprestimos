package com.example.controleemprestimos2;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RVAdapterEquipamento extends RecyclerView.Adapter<RVAdapterEquipamento.ViewHolder> {

    private List<Equipamento> equipamentos;
    private OnItemListener onItemListener;

    public RVAdapterEquipamento(List<Equipamento> equipamentos, OnItemListener onItemListener) {
        this.equipamentos = equipamentos;
        this.onItemListener = onItemListener;
    }

    @Override
    public RVAdapterEquipamento.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.equipamentos_row, parent, false);
        return new ViewHolder(view, onItemListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RVAdapterEquipamento.ViewHolder holder, int position) {
        holder.txtIdEquipamento.setText("Equipamento #" + equipamentos.get(position).getIdEquipamento());
        holder.txtNomeEquipamento.setText("Nome: " + equipamentos.get(position).getNomeEquipamento());
        holder.txtMarca.setText("Marca: " + equipamentos.get(position).getMarca());
        holder.txtModelo.setText("Modelo: " + equipamentos.get(position).getModelo());
        holder.txtNumPatrimonio.setText("Num. Patrimônio: " + equipamentos.get(position).getNumPatrimonio());
    }

    @Override
    public int getItemCount() {
        return equipamentos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtIdEquipamento;
        public TextView txtNomeEquipamento;
        public TextView txtMarca;
        public TextView txtModelo;
        public TextView txtNumPatrimonio;
        OnItemListener onItemListener;

        public ViewHolder(View itemView, OnItemListener onItemListener) {
            super(itemView);
            this.onItemListener = onItemListener;

            txtIdEquipamento = itemView.findViewById(R.id.txtIdEquipamento);
            txtNomeEquipamento = itemView.findViewById(R.id.txtNomeEquipamento);
            txtMarca = itemView.findViewById(R.id.txtMarca);
            txtModelo = itemView.findViewById(R.id.txtModelo);
            txtNumPatrimonio = itemView.findViewById(R.id.txtNumPatrimonio);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemListener.onItemListener(getAdapterPosition());
        };
    }

    public interface OnItemListener {
        void onItemListener(int position);
    }
}