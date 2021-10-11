package com.example.controleemprestimo.Emprestimo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.health.SystemHealthManager;
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
import com.example.controleemprestimo.R;

import java.util.List;

public class GerenciarEmprestimo extends AppCompatActivity {

    EmpresaDB db;

    List<Equipamento> equipamentos, allEquipamentos;
    Equipamento equipamento;
    Emprestimo emprestimo, emprAuxiliar;

    TextView txtTitleEmprestimo;
    TextView txtSpinnerEquipamento;
    EditText edtNomePessoa;
    EditText edtTelefone;
    EditText edtData;

    CheckBox checkDevolvido;
    Spinner spinnerEquipamentos;
    ArrayAdapter<Equipamento> adapter;

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

        Bundle bundle = this.getIntent().getExtras();
        boolean adicionar = bundle.getBoolean("adicionar");

        if(adicionar) {
            txtTitleEmprestimo.setText("Adicionar Empréstimo");
            btnAdicionarSalvar.setText("Adicionar");
            btnDeletar.setVisibility(View.INVISIBLE);

            equipamentos = db.equipamentoDAO().getAllEquipamentos();
            for(int i = 0; i < equipamentos.size(); i++) {
                equipamento = equipamentos.get(i);
                emprAuxiliar = db.emprestimoDAO().getEmprEquip(equipamento.getIdEquipamento());

                if(emprAuxiliar != null && !emprAuxiliar.isDevolvido()) {
                    equipamentos.remove(i);
                    i = i-1;
                }
            }

            adapter = new ArrayAdapter<>(this,
                    R.layout.support_simple_spinner_dropdown_item,
                    equipamentos);
            spinnerEquipamentos.setAdapter(adapter);

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
                    if(equipamento != null) {
                        int idEquipamento = equipamento.getIdEquipamento();
                        String nomePessoa = edtNomePessoa.getText().toString();
                        String telefone = edtTelefone.getText().toString();
                        String data = edtData.getText().toString();
                        boolean devolvido = checkDevolvido.isChecked();

                        db.emprestimoDAO().insertAll(new Emprestimo(idEquipamento, nomePessoa, telefone, data, devolvido));
                        startActivity(new Intent(GerenciarEmprestimo.this, ListaDeEmprestimos.class));
                    } else {
                        showAlertDialog(v,
                                "Nenhum equipamento foi selecionado.\n" +
                                        "Selecione um equipamento existente ou adicione " +
                                        "algum antes de vinculá-lo a um empréstimo.");
                    }
                }
            });
        } else {
            txtTitleEmprestimo.setText("Editar Empréstimo");
            btnAdicionarSalvar.setText("Salvar");

            emprestimo = db.emprestimoDAO().get(bundle.getInt("idEmprestimo"));
            int idEquipamento = emprestimo.getIdEquipamento();
            int spinnerIndexShow = -1;
            equipamento = db.equipamentoDAO().get(idEquipamento);

            edtNomePessoa.setText(emprestimo.getNomePessoa());
            edtTelefone.setText(emprestimo.getTelefone());
            edtData.setText(emprestimo.getData());
            checkDevolvido.setChecked(emprestimo.isDevolvido());

            equipamentos = db.equipamentoDAO().getAllEquipamentos();
            for(int i = 0; i < equipamentos.size(); i++) {
                equipamento = equipamentos.get(i);
                emprAuxiliar = db.emprestimoDAO().getEmprEquip(equipamento.getIdEquipamento());

                if(emprAuxiliar != null && !emprAuxiliar.isDevolvido()) {
                    if(equipamentos.get(i).getIdEquipamento() != idEquipamento) {
                        equipamentos.remove(i);
                        i -= 1;
                    }
                    else
                        spinnerIndexShow = i;
                }
            }

            adapter = new ArrayAdapter<>(this,
                    R.layout.support_simple_spinner_dropdown_item,
                    equipamentos);
            spinnerEquipamentos.setAdapter(adapter);
            spinnerEquipamentos.setSelection(spinnerIndexShow);

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

    public void showAlertDialog(View v, String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(GerenciarEmprestimo.this);

        builder.setCancelable(true);
        builder.setTitle("Alerta");
        builder.setMessage(text);
        builder.setNeutralButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        builder.create().show();
    }
}