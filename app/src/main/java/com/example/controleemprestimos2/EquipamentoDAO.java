package com.example.controleemprestimos2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EquipamentoDAO {
    @Query("SELECT * FROM Equipamento ORDER BY idEquipamento ASC")
    List<Equipamento> getAllEquipamentos();

    @Query("SELECT * FROM Equipamento WHERE idEquipamento = :id LIMIT 1")
    Equipamento get(int id);

    @Insert
    void insertAll(Equipamento... equipamentos);

    @Update
    void update(Equipamento equipamento);

    @Delete
    void delete(Equipamento equipamento);
}