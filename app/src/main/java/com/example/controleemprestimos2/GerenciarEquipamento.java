package com.example.controleemprestimos2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GerenciarEquipamento extends AppCompatActivity {

    TextView txtTitleEquipamento;
    EditText edtNomeEquipamento;
    EditText edtMarca;
    EditText edtModelo;
    EditText edtNumPatrimonio;
    Button btnDeletar;
    Button btnAdicionarSalvar;
    Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_equipamento);

        txtTitleEquipamento = findViewById(R.id.txtTitleEquipamento);
        edtNomeEquipamento = findViewById(R.id.edtNomeEquipamento);
        edtMarca = findViewById(R.id.edtMarca);
        edtModelo = findViewById(R.id.edtModelo);
        edtNumPatrimonio = findViewById(R.id.edtNumPatrimonio);

        btnDeletar = findViewById(R.id.btnDeletar);
        btnAdicionarSalvar = findViewById(R.id.btnAdicionarSalvar);
        btnVoltar = findViewById(R.id.btnVoltar);

        final EmpresaDB db = Room.databaseBuilder(
                getApplicationContext(),
                EmpresaDB.class,
                "EmpresaDB")
                .allowMainThreadQueries()
                .build();

        Bundle bundle = this.getIntent().getExtras();
        boolean adicionar = bundle.getBoolean("adicionar");

        if (adicionar) {
            txtTitleEquipamento.setText("Adicionar Equipamento");
            btnAdicionarSalvar.setText("Adicionar");
            btnDeletar.setVisibility(View.INVISIBLE);
            btnAdicionarSalvar.setGravity(Gravity.CENTER_HORIZONTAL);

            btnAdicionarSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nomeEquipamento = edtNomeEquipamento.getText().toString();
                    String marca = edtMarca.getText().toString();
                    String modelo = edtModelo.getText().toString();
                    String numPatrimonio = edtNumPatrimonio.getText().toString();

                    db.equipamentoDAO().insertAll(new Equipamento(nomeEquipamento, marca, modelo, numPatrimonio));
                    startActivity(new Intent(GerenciarEquipamento.this, ListaDeEquipamentos.class));
                }
            });
        } else {
            txtTitleEquipamento.setText("Editar Equipamento");
            btnAdicionarSalvar.setText("Salvar");


        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}