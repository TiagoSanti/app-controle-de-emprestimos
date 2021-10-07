package com.example.controleemprestimo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.controleemprestimo.Emprestimo.Emprestimo;
import com.example.controleemprestimo.Emprestimo.EmprestimoDAO;
import com.example.controleemprestimo.Equipamento.Equipamento;
import com.example.controleemprestimo.Equipamento.EquipamentoDAO;

@Database(entities = {Emprestimo.class, Equipamento.class}, version = 1)
public abstract class EmpresaDB extends RoomDatabase {
    public abstract EquipamentoDAO equipamentoDAO();
    public abstract EmprestimoDAO emprestimoDAO();
}
