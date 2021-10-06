package com.example.controleemprestimos2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaDeEquipamentos extends AppCompatActivity {

    RecyclerView RV;
    RecyclerView.Adapter RVAdapter;

    FloatingActionButton fabEquipamentos;
    Button btnVoltar;
    
    //ArrayList<Equipamento> array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_equipamentos);

        RV = findViewById(R.id.RVEquipamentos);
        fabEquipamentos = findViewById(R.id.fabEquipamentos);
        btnVoltar = findViewById(R.id.btnVoltarListaEquip);

        /*
        for(int i = 0; i < 10; i++) {
            Equipamento equipamento = new Equipamento(
                    "Notebook #"+i,
                    "Acer",
                    "H3BX",
                    "026754");
            array.add(equipamento);
        }
         */

        EmpresaDB db = Room.databaseBuilder(
                getApplicationContext(),
                EmpresaDB.class,
                "EmpresaDB")
                .allowMainThreadQueries()
                .build();

        List<Equipamento> equipamentos = db.equipamentoDAO().getAllEquipamentos();

        RV.setLayoutManager(new LinearLayoutManager(this));
        RVAdapter = new RVAdapterEquipamento(equipamentos);
        RV.setAdapter(RVAdapter);

        fabEquipamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaDeEquipamentos.this, GerenciarEquipamento.class);
                Bundle bundle = new Bundle();

                bundle.putBoolean("adicionar", true);

                it.putExtras(bundle);
                startActivity(it);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}