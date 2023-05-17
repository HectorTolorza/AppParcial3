package com.example.agendatuuduu.AgregarNota;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.agendatuuduu.R;

import java.util.Calendar;

public class AgregarNota extends AppCompatActivity {

    TextView Uid_Usuario, Correo_usuario, Fecha_hora_actual, Fecha, Estado;
    EditText Titulo, Descripcion;
    Button Btn_Calendario;

    int dia, mes, anio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_nota);

        IniciarlizarVariables();
        ObtenerDatos();
        Obtener_Fecha_Hora_Actual();

        Btn_Calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendario = Calendar.getInstance();

                dia = calendario.get(Calendar.DAY_OF_MONTH);
                mes = calendario.get(Calendar.MONTH);
                anio = calendario.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AgregarNota.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int AnioSele, int MesSele, int DiaSele) {

                        String diaFormateado, mesFormateado;

                        //Para tener un 0 delante
                        if (DiaSele < 10) {
                            diaFormateado = "0"+String.valueOf(DiaSele);
                        }else {
                            diaFormateado = String.valueOf(DiaSele);
                        }

                        int Mes = MesSele +1;

                        if (Mes < 10){
                            mesFormateado = "0"+String.valueOf(Mes);
                        }else {
                            mesFormateado = String.valueOf(Mes);
                        }

                        Fecha.setText(diaFormateado + "/" + mesFormateado + "/" + AnioSele);

                    }
                }
                ,anio,mes,dia);

            }
        });
    }

    private void IniciarlizarVariables() {

        Uid_Usuario = findViewById(R.id.Uid_Usuario);
        Correo_usuario = findViewById(R.id.Correo_Usuario);
        Fecha_hora_actual = findViewById(R.id.Fecha_hora_actual);
        Fecha = findViewById(R.id.Fecha);
        Estado = findViewById(R.id.Estado);

        Titulo = findViewById(R.id.Titulo);
        Descripcion = findViewById(R.id.Descripcion);

    }

    private void ObtenerDatos() {
    }

    private void Obtener_Fecha_Hora_Actual() {

    }

}

