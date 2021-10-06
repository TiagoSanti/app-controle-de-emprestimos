package com.example.controleemprestimos2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Equipamento {
    @PrimaryKey(autoGenerate = true)
    private int idEquipamento;

    @ColumnInfo(name = "nome_equipamento")
    private String nomeEquipamento;

    @ColumnInfo(name = "marca")
    private String marca;

    @ColumnInfo(name = "modelo")
    private String modelo;

    @ColumnInfo(name = "num_patrimonio")
    private String numPatrimonio;

    public Equipamento(String nomeEquipamento, String marca, String modelo, String numPatrimonio) {
        this.nomeEquipamento = nomeEquipamento;
        this.marca = marca;
        this.modelo = modelo;
        this.numPatrimonio = numPatrimonio;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumPatrimonio() {
        return numPatrimonio;
    }

    public void setNumPatrimonio(String numPatrimonio) {
        this.numPatrimonio = numPatrimonio;
    }
}