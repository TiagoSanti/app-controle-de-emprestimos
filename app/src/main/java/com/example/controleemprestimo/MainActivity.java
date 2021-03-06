package com.example.controleemprestimo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.controleemprestimo.Emprestimo.ListaDeEmprestimos;
import com.example.controleemprestimo.Equipamento.ListaDeEquipamentos;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btnEmprestimos = findViewById(R.id.btnEmprestimos);
        Button btnEquipamentos = findViewById(R.id.btnEquipamentos);

        btnEmprestimos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ListaDeEmprestimos.class));
            }
        });

        btnEquipamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListaDeEquipamentos.class));
            }
        });
    }
}