package com.example.controleemprestimos2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    ArrayList<Equipamento> array;

    public RVAdapter(ArrayList<Equipamento> array) {
        this.array = array;
    }

    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.equipamentos_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.ViewHolder holder, int position) {
        holder.txtNomeEquipamento.setText(array.get(position).getNomeEquipamento());
        holder.txtMarca.setText(array.get(position).getMarca());
        holder.txtModelo.setText(array.get(position).getModelo());
        holder.txtNumPatrimonio.setText(array.get(position).getNumPatrimonio());
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNomeEquipamento;
        public TextView txtMarca;
        public TextView txtModelo;
        public TextView txtNumPatrimonio;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNomeEquipamento = itemView.findViewById(R.id.txtNomePessoa);
            txtMarca = itemView.findViewById(R.id.txtMarca);
            txtModelo = itemView.findViewById(R.id.txtModelo);
            txtNumPatrimonio = itemView.findViewById(R.id.txtNumPatrimonio);
        }
    }
}
