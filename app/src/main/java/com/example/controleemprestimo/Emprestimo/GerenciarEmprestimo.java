package com.example.controleemprestimo.Emprestimo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.controleemprestimo.EmpresaDB;
import com.example.controleemprestimo.Equipamento.Equipamento;
import com.example.controleemprestimo.Equipamento.ListaDeEquipamentos;
import com.example.controleemprestimo.R;

import java.util.List;

public class GerenciarEmprestimo extends AppCompatActivity {

    EmpresaDB db;

    List<Equipamento> equipamentos;
    Equipamento equipamento;
    Emprestimo emprestimo;

    TextView txtTitleEmprestimo;
    TextView txtSpinnerEquipamento;
    EditText edtNomePessoa;
    EditText edtTelefone;
    EditText edtData;

    CheckBox checkDevolvido;
    Spinner spinnerEquipamentos;
    ArrayAdapter adapter;

    Button btnDeletar;
    Button btnAdicionarSalvar;
    Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerenciar_emprestimos);

        txtTitleEmprestimo = findViewById(R.id.txtTitleEmprestimo);
        txtSpinnerEquipamento = findViewById(R.id.txtSpinnerEquipamento);

        edtNomePessoa = findViewById(R.id.edtNomePessoa);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtData = findViewById(R.id.edtData);

        checkDevolvido = findViewById(R.id.checkDevolvido);
        spinnerEquipamentos = findViewById(R.id.spinnerEquipamentos);

        btnDeletar = findViewById(R.id.btnEmprDeletar);
        btnAdicionarSalvar = findViewById(R.id.btnEmprAdicionarSalvar);
        btnVoltar = findViewById(R.id.btnGerenciarEmprVoltar);

        db = EmpresaDB.getDatabase(getApplicationContext());
        equipamentos = db.equipamentoDAO().getAllEquipamentos();

        adapter = new ArrayAdapter(this,
                R.layout.support_simple_spinner_dropdown_item,
                equipamentos);
        spinnerEquipamentos.setAdapter(adapter);

        Bundle bundle = this.getIntent().getExtras();
        boolean adicionar = bundle.getBoolean("adicionar");

        if(adicionar) {
            txtTitleEmprestimo.setText("Adicionar Empréstimo");
            btnAdicionarSalvar.setText("Adicionar");
            btnDeletar.setVisibility(View.INVISIBLE);

            spinnerEquipamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    equipamento = (Equipamento) parent.getSelectedItem();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            btnAdicionarSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int idEquipamento = equipamento.getIdEquipamento();
                    String nomePessoa = edtNomePessoa.getText().toString();
                    String telefone = edtTelefone.getText().toString();
                    String data = edtData.getText().toString();
                    boolean devolvido = checkDevolvido.isChecked();

                    db.emprestimoDAO().insertAll(new Emprestimo(idEquipamento, nomePessoa, telefone, data, devolvido));
                    startActivity(new Intent(GerenciarEmprestimo.this, ListaDeEmprestimos.class));
                }
            });
        } else {
            txtTitleEmprestimo.setText("Editar Equipamento");
            btnAdicionarSalvar.setText("Salvar");

            emprestimo = db.emprestimoDAO().get(bundle.getInt("idEmprestimo"));

            edtNomePessoa.setText(emprestimo.getNomePessoa());
            edtTelefone.setText(emprestimo.getTelefone());
            edtData.setText(emprestimo.getData());
            checkDevolvido.setChecked(emprestimo.isDevolvido());

            spinnerEquipamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    equipamento = (Equipamento) parent.getSelectedItem();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    spinnerEquipamentos.setSelection(emprestimo.getIdEquipamento());
                }
            });

            btnAdicionarSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    emprestimo.setIdEquipamento(equipamento.getIdEquipamento());
                    emprestimo.setNomePessoa(edtNomePessoa.getText().toString());
                    emprestimo.setTelefone(edtTelefone.getText().toString());
                    emprestimo.setData(edtData.getText().toString());
                    emprestimo.setDevolvido(checkDevolvido.isChecked());

                    db.emprestimoDAO().update(emprestimo);
                    startActivity(new Intent(GerenciarEmprestimo.this, ListaDeEmprestimos.class));
                }
            });

            btnDeletar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.emprestimoDAO().delete(emprestimo);
                    startActivity(new Intent(GerenciarEmprestimo.this, ListaDeEmprestimos.class));
                }
            });
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GerenciarEmprestimo.this, ListaDeEmprestimos.class));
            }
        });
    }
}