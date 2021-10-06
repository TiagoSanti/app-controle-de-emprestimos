package com.example.controleemprestimos2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EquipamentoDAO {
    @Query("SELECT * FROM Equipamento")
    List<Equipamento> getAllEquipamentos();

    @Insert
    void insertAll(Equipamento... equipamentos);

}
