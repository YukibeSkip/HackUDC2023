package com.example.mindpalaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView textView_Nombre, textView_Correo, textView_Contrasenha, textView_ConComfirm;
    private EditText editText_Nombre, editText_Correo, editText_Contrasenha, editText_ConConfirm;

    private Button button_Enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView_Nombre = (TextView) findViewById(R.id.textView_Nombre);
        textView_Correo = (TextView) findViewById(R.id.textView_Correo);
        textView_Contrasenha = (TextView) findViewById(R.id.textView_Contrasenha);
        textView_ConComfirm = (TextView) findViewById(R.id.textView_ConComfirm);

        editText_Nombre = (EditText) findViewById(R.id.editText_Nombre);
        editText_Correo = (EditText) findViewById(R.id.editText_Correo);
        editText_Contrasenha = (EditText) findViewById(R.id.editText_Contrasenha);
        editText_ConConfirm = (EditText) findViewById(R.id.editText_ConConfirm);

        button_Enviar = (Button) findViewById(R.id.button_Enviar);

        Enviar();

    }

    public void Enviar(){

        button_Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Regojo los datos introducidos o la falta de ellos
                String Nombre = editText_Nombre.getText().toString();
                String Correo = editText_Correo.getText().toString();
                String Contrasenha = editText_Contrasenha.getText().toString();
                String ConfirmaContra = editText_ConConfirm.getText().toString();

                //Compruebo si tiene hay datos o no
                if( Nombre.length() == 0 || Correo.length() == 0 || Contrasenha.length() == 0 || ConfirmaContra.length() == 0){
                    //En caso de que esten vacios aviso al usuario por una alerta
                    android.app.AlertDialog.Builder alerta = new android.app.AlertDialog.Builder(MainActivity.this);
                    alerta.setMessage("Completa todos los datos").
                            setCancelable(false).
                            setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    android.app.AlertDialog FaltaDatos = alerta.create();
                    FaltaDatos.setTitle("Datos vacios");
                    FaltaDatos.show();
                }else{
                    //Compruebo que el correo esta en formato valido
                    Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            +"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                    Correo = editText_Correo.getText().toString();

                    Matcher matcher = pattern.matcher(Correo);

                    if (matcher.find() == true){

                        if (Contrasenha.equals(ConfirmaContra)){

                            Intent i = new Intent(view.getContext(), Entrar_Activity.class);
                            startActivity(i);

                        }else{
                            android.app.AlertDialog.Builder alerta = new android.app.AlertDialog.Builder(MainActivity.this);
                            alerta.setMessage("Las contraseñas tiene que ser iguales").
                                    setCancelable(false).
                                    setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                            android.app.AlertDialog MalCorreo = alerta.create();
                            MalCorreo.setTitle("Contraseña");
                            MalCorreo.show();
                        }

                    }else{
                        android.app.AlertDialog.Builder alerta = new android.app.AlertDialog.Builder(MainActivity.this);
                        alerta.setMessage("El correo no esta en un formato valido").
                                setCancelable(false).
                                setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                        AlertDialog MalCorreo = alerta.create();
                        MalCorreo.setTitle("Correo electronico");
                        MalCorreo.show();
                    }

                }
            }
        });

    }








}