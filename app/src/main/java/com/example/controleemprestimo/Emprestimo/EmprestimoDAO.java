package com.example.controleemprestimo.Emprestimo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.controleemprestimo.Equipamento.Equipamento;

import java.util.List;

@Dao
public interface EmprestimoDAO {
    @Query("SELECT * FROM Emprestimo ORDER BY idEmprestimo ASC")
    List<Emprestimo> getAllEmprestimos();

    @Query("SELECT * FROM Emprestimo WHERE idEmprestimo = :id LIMIT 1")
    Emprestimo get(int id);

    @Insert
    void insertAll(Emprestimo... emprestimos);

    @Update
    void update(Emprestimo emprestimo);

    @Delete
    void delete(Emprestimo emprestimo);
}
