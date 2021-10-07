package com.example.controleemprestimo.Emprestimo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.controleemprestimo.EmpresaDB;
import com.example.controleemprestimo.Equipamento.Equipamento;
import com.example.controleemprestimo.Equipamento.GerenciarEquipamento;
import com.example.controleemprestimo.Equipamento.ListaDeEquipamentos;
import com.example.controleemprestimo.R;

import org.w3c.dom.Text;

public class GerenciarEmprestimo extends AppCompatActivity {

    TextView txtTitleEmprestimo;
    EditText edtNomePessoa;
    EditText edtTelefone;
    EditText edtData;

    Spinner spinnerEquipamentos;
    Switch switchDevolvido;

    Button btnDeletar;
    Button btnAdicionarSalvar;
    Button btnVoltar;

    Emprestimo emprestimo;
    Equipamento equipamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_emprestimos);

        txtTitleEmprestimo = findViewById(R.id.txtTitleEmprestimo);
        edtNomePessoa = findViewById(R.id.edtNomePessoa);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtData = findViewById(R.id.edtData);

        switchDevolvido = findViewById(R.id.switchDevolvido);
        spinnerEquipamentos = findViewById(R.id.spinnerEquipamentos);

        btnDeletar = findViewById(R.id.btnEmprDeletar);
        btnAdicionarSalvar = findViewById(R.id.btnEmprAdicionarSalvar);
        btnVoltar = findViewById(R.id.btnGerenciarEmprVoltar);

        EmpresaDB db = Room.databaseBuilder(
                getApplicationContext(),
                EmpresaDB.class,
                "EmpresaDB")
                .allowMainThreadQueries()
                .build();

        Bundle bundle = this.getIntent().getExtras();
        boolean adicionar = bundle.getBoolean("adicionar");

        if(adicionar) {
            txtTitleEmprestimo.setText("Adicionar Empréstimo");
            btnAdicionarSalvar.setText("Adicionar");
            btnDeletar.setVisibility(View.INVISIBLE);

            Emprestimo emprestimo = new Emprestimo();

            switchDevolvido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    emprestimo.setDevolvido(isChecked);
                }
            });

            btnAdicionarSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String idEquipamento = ...
                    String nomePessoa = edtNomePessoa.getText().toString();
                    String telefone = edtTelefone.getText().toString();
                    String data = edtData.getText().toString();

                    //db.emprestimoDAO().insertAll(new Equipamento(idEquipamento, nomePessoa, telefone, data));
                    startActivity(new Intent(GerenciarEmprestimo.this, ListaDeEquipamentos.class));
                }
            });
        } else {
            txtTitleEmprestimo.setText("Editar Equipamento");
            btnAdicionarSalvar.setText("Salvar");

            emprestimo = db.emprestimoDAO().get(bundle.getInt("idEmprestimo"));
        }
    }
}