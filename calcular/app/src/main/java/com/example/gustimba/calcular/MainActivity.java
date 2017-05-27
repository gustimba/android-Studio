package com.example.gustimba.calcular;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.R.attr.onClick;

public class MainActivity extends AppCompatActivity {
    EditText numero;
    EditText numerodois;
    EditText result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero = (EditText) findViewById(R.id.num1);
        numerodois = (EditText) findViewById(R.id.num2);
        result = (EditText) findViewById(R.id.resultado);

    }
    public void calculando(View v) {
        Double n1, n2,resultado, resultado2;
        String  nome="R";
         n1 = Double.parseDouble(numero.getText().toString());
        n2 = Double.parseDouble(numerodois.getText().toString());
        resultado = n1 * 1000;
        resultado2 = resultado/n2;

        result.setText(String.format("%.2f",resultado2));
    }
}

