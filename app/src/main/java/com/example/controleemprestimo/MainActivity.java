package com.example.controleemprestimo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.controleemprestimo.Equipamento.ListaDeEquipamentos;

public class MainActivity extends AppCompatActivity {

    private Button btnEmprestimos;
    private Button btnEquipamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEmprestimos = findViewById(R.id.btnEmprestimos);
        btnEquipamentos = findViewById(R.id.btnEquipamentos);

        btnEmprestimos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, ListaDeEmprestimos.class));
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