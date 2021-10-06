package com.example.controleemprestimos2;

import androidx.appcompat.app.AppCompatActivity;

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

        Bundle bundle = this.getIntent().getExtras();
        boolean adicionar = bundle.getBoolean("adicionar");

        if (adicionar) {
            txtTitleEquipamento.setText("Adicionar Equipamento");
            btnAdicionarSalvar.setText("Adicionar");

            btnDeletar.setVisibility(View.INVISIBLE);
            btnAdicionarSalvar.setGravity(Gravity.CENTER_HORIZONTAL);
        } else {
            txtTitleEquipamento.setText("Adicionar Empréstimo");
            btnAdicionarSalvar.setText("Salvar");
        }
    }
}