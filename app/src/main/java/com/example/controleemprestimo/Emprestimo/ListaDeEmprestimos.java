package com.example.controleemprestimo.Emprestimo;

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

public class ListaDeEmprestimos extends AppCompatActivity implements RVAdapterEmprestimo.OnItemListener{

    RecyclerView RV;
    RecyclerView.Adapter RVAdapter;

    FloatingActionButton fabEmprestimos;
    Button btnVoltar;

    List<Emprestimo> emprestimos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_de_emprestimos);

        RV = findViewById(R.id.RVEmprestimos);
        fabEmprestimos = findViewById(R.id.fabEmprestimos);
        btnVoltar = findViewById(R.id.btnVoltarListaEmpr);

        EmpresaDB db = Room.databaseBuilder(
                getApplicationContext(),
                EmpresaDB.class,
                "EmpresaDB")
                .allowMainThreadQueries()
                .build();

        emprestimos = db.emprestimoDAO().getAllEmprestimos();

        RV.setLayoutManager(new LinearLayoutManager(this));
        RVAdapter = new RVAdapterEmprestimo(emprestimos, this);
        RV.setAdapter(RVAdapter);

        fabEmprestimos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaDeEmprestimos.this, GerenciarEmprestimo.class);
                Bundle bundle = new Bundle();

                bundle.putBoolean("adicionar", true);

                it.putExtras(bundle);
                startActivity(it);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaDeEmprestimos.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onItemListener(int position) {
        Intent it = new Intent(ListaDeEmprestimos.this, GerenciarEmprestimo.class);
        Bundle bundle = new Bundle();

        int idEmprestimo = emprestimos.get(position).getIdEmprestimo();

        bundle.putInt("idEquipamento", idEmprestimo);
        bundle.putBoolean("adicionar", false);

        it.putExtras(bundle);

        startActivity(it);
    }
}