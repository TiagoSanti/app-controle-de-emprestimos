package com.example.controleemprestimo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.controleemprestimo.Emprestimo.Emprestimo;
import com.example.controleemprestimo.Emprestimo.EmprestimoDAO;
import com.example.controleemprestimo.Equipamento.Equipamento;
import com.example.controleemprestimo.Equipamento.EquipamentoDAO;

@Database(entities = {Emprestimo.class, Equipamento.class}, version = 2)
public abstract class EmpresaDB extends RoomDatabase {
    public abstract EquipamentoDAO equipamentoDAO();
    public abstract EmprestimoDAO emprestimoDAO();

    private static EmpresaDB INSTANCE;

    public static EmpresaDB getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    EmpresaDB.class,
                    "EmpresaDB")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
