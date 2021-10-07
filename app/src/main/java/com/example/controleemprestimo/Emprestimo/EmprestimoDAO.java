package com.example.controleemprestimo.Emprestimo;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmprestimoDAO {
    @Query("SELECT * FROM Emprestimo")
    List<Emprestimo> getAll();
}
