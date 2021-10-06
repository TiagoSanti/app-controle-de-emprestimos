package com.example.controleemprestimos2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Emprestimo.class, Equipamento.class}, version = 1)
public abstract class EmpresaDB extends RoomDatabase {
    public abstract EmprestimoDAO emprestimoDAO();
}
