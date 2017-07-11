package com.example.gustimba.calcular;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.R.attr.onClick;

public class MainActivity extends AppCompatActivity {
    EditText valorceva;
    EditText valorml;
    EditText result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valorceva = (EditText) findViewById(R.id.valordaceva);
        valorml = (EditText) findViewById(R.id.valorml);
        result = (EditText) findViewById(R.id.resultado);

    }
    public void calculando(View v) {
        Double ceva, ml ,resultado , resultado2;
        String  nome = "R$:";
        ceva = Double.parseDouble(valorceva.getText().toString());
        ml  = Double.parseDouble(valorml.getText().toString());
        resultado = ceva * 1000;
        resultado2 = resultado/ml;

        result.setText(String.format(nome +" %.2f",resultado2));
    }
}

