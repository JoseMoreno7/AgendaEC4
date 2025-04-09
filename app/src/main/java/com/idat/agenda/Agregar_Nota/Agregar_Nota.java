package com.idat.agenda.Agregar_Nota;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.idat.agenda.R;

import java.util.Calendar;

public class Agregar_Nota extends AppCompatActivity {

    TextView Uid_Usuario, Correo_usuario, Fecha_hora_actual, Fecha, Estado;
    EditText Titulo, Descripcion;
    Button Btn_Calendario;

    int dia, mes, anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_nota);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        InicializarVariables();
        ObtenerDatos();
        Obtener_Fecha_Hora_Actual();

        Btn_Calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();

                dia = calendar.get(Calendar.DAY_OF_MONTH);
                mes = calendar.get(Calendar.MONTH);
                anio = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Agregar_Nota.this,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int AnioSeleccionado,
                                          int MesSeleccionado, int DiaSeleccionado) {

                        String diaFormateado, mesFormateado;

                        if (DiaSeleccionado < 10) {
                            diaFormateado = "0" + String.valueOf(DiaSeleccionado);
                        } else {
                            diaFormateado = String.valueOf(DiaSeleccionado);
                        }

                        int Mes = MesSeleccionado + 1;

                        if (Mes < 10) {
                            mesFormateado = "0" + String.valueOf(Mes);
                        } else {
                            mesFormateado = String.valueOf(Mes);
                        }

                        Fecha.setText(diaFormateado + "/" + mesFormateado + "/" + AnioSeleccionado);

                    }
                }
                , anio, mes, dia);
                datePickerDialog.show();
            }
        });
    }

    private void InicializarVariables() {
        Uid_Usuario = findViewById(R.id.Uid_Usuario);
        Correo_usuario = findViewById(R.id.Correo_usuario);
        Fecha_hora_actual = findViewById(R.id.Fecha_hora_actual);
        Fecha = findViewById(R.id.Fecha);
        Estado = findViewById(R.id.Estado);

        Titulo = findViewById(R.id.Titulo);
    }

    private void Obtener_Fecha_Hora_Actual() {

    }

    private void ObtenerDatos() {
    }
    // Add your methods and logic here for adding notes
}
