package com.example.controleemprestimos2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ListaDeEquipamentos extends AppCompatActivity {

    RecyclerView RV;
    RecyclerView.Adapter RVAdapter;
    
    ArrayList<Equipamento> array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_equipamentos);

        RV = findViewById(R.id.RVEquipamentos);
        
        for(int i = 0; i < 10; i++) {
            Equipamento equipamento = new Equipamento(
                    "Notebook #"+i,
                    "Acer",
                    "H3BX",
                    "026754");
            array.add(equipamento);
        }

        RV.setLayoutManager(new LinearLayoutManager(this));
        RVAdapter = new RVAdapter(array);
        RV.setAdapter(RVAdapter);
    }
}