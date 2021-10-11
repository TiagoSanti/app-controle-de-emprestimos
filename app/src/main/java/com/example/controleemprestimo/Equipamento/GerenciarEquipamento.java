package com.example.controleemprestimo.Equipamento;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.controleemprestimo.EmpresaDB;
import com.example.controleemprestimo.Emprestimo.Emprestimo;
import com.example.controleemprestimo.R;

import java.util.List;

public class GerenciarEquipamento extends AppCompatActivity {

    EmpresaDB db;

    TextView txtTitleEquipamento;
    EditText edtNomeEquipamento;
    EditText edtMarca;
    EditText edtModelo;
    EditText edtNumPatrimonio;

    Button btnDeletar;
    Button btnAdicionarSalvar;
    Button btnVoltar;

    List<Emprestimo> emprestimos;
    Equipamento equipamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerenciar_equipamento);

        txtTitleEquipamento = findViewById(R.id.txtTitleEquipamento);
        edtNomeEquipamento = findViewById(R.id.edtNomeEquipamento);
        edtMarca = findViewById(R.id.edtMarca);
        edtModelo = findViewById(R.id.edtModelo);
        edtNumPatrimonio = findViewById(R.id.edtNumPatrimonio);
        btnDeletar = findViewById(R.id.btnEquipDeletar);
        btnAdicionarSalvar = findViewById(R.id.btnEquipAdicionarSalvar);
        btnVoltar = findViewById(R.id.btnGerenciarEquipVoltar);

        db = EmpresaDB.getDatabase(getApplicationContext());

        Bundle bundle = this.getIntent().getExtras();
        boolean adicionar = bundle.getBoolean("adicionar");

        if(adicionar) {
            txtTitleEquipamento.setText("Adicionar Equipamento");
            btnAdicionarSalvar.setText("Adicionar");
            btnDeletar.setVisibility(View.INVISIBLE);

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
                    emprestimos = db.emprestimoDAO().getAllEmprestimosFromEquip(equipamento.getIdEquipamento());

                    if(emprestimos != null) {
                        showAlertDialog(v);
                    } else {
                        db.equipamentoDAO().delete(equipamento);
                        startActivity(new Intent(GerenciarEquipamento.this, ListaDeEquipamentos.class));
                    }
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

    public void showAlertDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(GerenciarEquipamento.this);

        builder.setCancelable(true);
        builder.setTitle("Alerta");
        builder.setMessage("Este equipamento está registrado em, pelo menos, um empréstimo," +
                " caso prossiga com a exclusão o(s) registro(s) de empréstimo também será(ão) excluído(s)." +
                " Deseja confirmar a exclusão?");
        builder.setPositiveButton("Confirmar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.equipamentoDAO().delete(equipamento);
                        startActivity(new Intent(GerenciarEquipamento.this, ListaDeEquipamentos.class));
                    }
                });
        builder.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        builder.create().show();
    }
}