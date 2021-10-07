package com.example.controleemprestimo.Equipamento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.controleemprestimo.EmpresaDB;
import com.example.controleemprestimo.MainActivity;
import com.example.controleemprestimo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaDeEquipamentos extends AppCompatActivity implements RVAdapterEquipamento.OnItemListener {

    RecyclerView RV;
    RecyclerView.Adapter RVAdapter;

    FloatingActionButton fabEquipamentos;
    Button btnVoltar;

    List<Equipamento> equipamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_equipamentos);

        RV = findViewById(R.id.RVEquipamentos);
        fabEquipamentos = findViewById(R.id.fabEquipamentos);
        btnVoltar = findViewById(R.id.btnVoltarListaEquip);

        EmpresaDB db = Room.databaseBuilder(
                getApplicationContext(),
                EmpresaDB.class,
                "EmpresaDB")
                .allowMainThreadQueries()
                .build();

        equipamentos = db.equipamentoDAO().getAllEquipamentos();

        RV.setLayoutManager(new LinearLayoutManager(this));
        RVAdapter = new RVAdapterEquipamento(equipamentos, this);
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
                startActivity(new Intent(ListaDeEquipamentos.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onItemListener(int position) {
        Intent it = new Intent(ListaDeEquipamentos.this, GerenciarEquipamento.class);
        Bundle bundle = new Bundle();

        int idEquipamento = equipamentos.get(position).getIdEquipamento();

        bundle.putInt("idEquipamento", idEquipamento);
        bundle.putBoolean("adicionar", false);

        it.putExtras(bundle);

        startActivity(it);
    }
}