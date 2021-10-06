package com.example.controleemprestimos2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaDeEmprestimos extends AppCompatActivity {

    ListView lstEmprestimos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_emprestimos);

        lstEmprestimos = findViewById(R.id.lstEmprestimos);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
            this,
                R.array.teste,
                android.R.layout.simple_list_item_1
        );

        lstEmprestimos.setAdapter(adapter);
    }
}