package com.example.controleemprestimo.Equipamento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.controleemprestimo.EmpresaDB;
import com.example.controleemprestimo.Emprestimo.Emprestimo;
import com.example.controleemprestimo.R;

public class GerenciarEquipamento extends AppCompatActivity {

    TextView txtTitleEquipamento;
    EditText edtNomeEquipamento;
    EditText edtMarca;
    EditText edtModelo;
    EditText edtNumPatrimonio;

    Button btnDeletar;
    Button btnAdicionarSalvar;
    Button btnVoltar;

    Emprestimo emprestimo;
    Equipamento equipamento;

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

            equipamento = db.equipamentoDAO().get(bundle.getInt("idEquipamento"));

            edtNomeEquipamento.setText(equipamento.getNomeEquipamento());
            edtMarca.setText(equipamento.getMarca());
            edtModelo.setText(equipamento.getModelo());
            edtNumPatrimonio.setText(equipamento.getNumPatrimonio());

            btnAdicionarSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    equipamento.setNomeEquipamento(edtNomeEquipamento.getText().toString());
                    equipamento.setMarca(edtMarca.getText().toString());
                    equipamento.setModelo(edtModelo.getText().toString());
                    equipamento.setNumPatrimonio(edtNumPatrimonio.getText().toString());

                    db.equipamentoDAO().update(equipamento);
                    startActivity(new Intent(GerenciarEquipamento.this, ListaDeEquipamentos.class));
                }
            });

            btnDeletar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.equipamentoDAO().delete(equipamento);
                    startActivity(new Intent(GerenciarEquipamento.this, ListaDeEquipamentos.class));
                }
            });
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GerenciarEquipamento.this, ListaDeEquipamentos.class));
            }
        });
    }
}