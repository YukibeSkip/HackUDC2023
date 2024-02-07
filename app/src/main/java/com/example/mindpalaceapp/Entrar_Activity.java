package com.example.mindpalaceapp;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class Entrar_Activity extends AppCompatActivity {

    private TextView textView_VerFecha;
    private Button button_VerFecha;
    int dia, mes, anho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        textView_VerFecha = (TextView) findViewById(R.id.textView_VerFecha);

        button_VerFecha = (Button) findViewById(R.id.button_VerFecha);

        VerFecha();
    }

    public void VerFecha() {
        button_VerFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendario = Calendar.getInstance();
                dia = calendario.get(Calendar.DAY_OF_MONTH);
                mes = calendario.get(Calendar.MONTH);
                anho = calendario.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Entrar_Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int anhoSelec, int mesSelec, int diaSelec) {
                        String diaFormat, mesFormat;

                        if (diaSelec < 10) {
                            diaFormat = "0" + String.valueOf(diaSelec);
                        } else {
                            diaFormat = String.valueOf(diaSelec);
                        }

                        int MES = mesSelec + 1;
                        if (mesSelec < 10) {
                            mesFormat = "0" + String.valueOf(MES);
                        } else {
                            mesFormat = String.valueOf(MES);
                        }

                        textView_VerFecha.setText(diaFormat + "/" + mesFormat + "/" + anhoSelec);

                    }
                }
                        , anho, mes, dia);

                datePickerDialog.show();
            }
        });
    }
}