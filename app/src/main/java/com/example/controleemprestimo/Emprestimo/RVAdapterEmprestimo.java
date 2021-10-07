package com.example.controleemprestimo.Emprestimo;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.controleemprestimo.EmpresaDB;
import com.example.controleemprestimo.Equipamento.Equipamento;
import com.example.controleemprestimo.R;

import org.w3c.dom.Text;

import java.util.List;

public class RVAdapterEmprestimo extends RecyclerView.Adapter<RVAdapterEmprestimo.ViewHolder> {

    private List<Emprestimo> emprestimos;
    private OnItemListener onItemListener;

    public RVAdapterEmprestimo(List<Emprestimo> emprestimos, OnItemListener onItemListener) {
        this.emprestimos = emprestimos;
        this.onItemListener = onItemListener;
    }

    @Override
    public RVAdapterEmprestimo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emprestimos_row, parent, false);
        return new ViewHolder(view, onItemListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RVAdapterEmprestimo.ViewHolder holder, int position) {
        holder.txtIdEmprestimo.setText("Empréstimo #" + emprestimos.get(position).getIdEmprestimo());
        holder.txtNomePessoa.setText("Nome do Prestatário: " + emprestimos.get(position).getNomePessoa());

        // holder.txtNomeDoEquipamento.setText("Equipamento: " + emprestimos.get(position));

        if(emprestimos.get(position).isDevolvido())
            holder.txtDevolvido.setText("Devolvido: Sim");
        else
            holder.txtDevolvido.setText("Devolvido: Não");
    }

    @Override
    public int getItemCount() {
        return emprestimos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtIdEmprestimo;
        public TextView txtNomePessoa;
        public TextView txtNomeDoEquipamento;
        public TextView txtDevolvido;
        OnItemListener onItemListener;

        public ViewHolder(View itemView, OnItemListener onItemListener) {
            super(itemView);
            this.onItemListener = onItemListener;

            txtIdEmprestimo = itemView.findViewById(R.id.txtIdEmprestimo);
            txtNomePessoa = itemView.findViewById(R.id.txtNomePessoa);
            txtNomeDoEquipamento = itemView.findViewById(R.id.txtNomeDoEquipamento);
            txtDevolvido = itemView.findViewById(R.id.txtDevolvido);

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