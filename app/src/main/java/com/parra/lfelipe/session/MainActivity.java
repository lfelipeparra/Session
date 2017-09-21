package com.parra.lfelipe.session;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Calendar;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText eNombre, ePassword1, eCorreo, ePassword2, eFecha;
    private TextView tInfo;
    private String nombre, password1, password2, sexo="Masculino", hobbies="", correo, cuidad, fecha;
    private CheckBox cCine, cComer, cDeporte, cDormir;
    private Spinner spinner;
    private DatePickerDialog datepicker;
    private boolean error = false;
    private int year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eNombre = (EditText) findViewById(R.id.eNombre);
        ePassword1 = (EditText) findViewById(R.id.ePassword1);
        tInfo = (TextView) findViewById(R.id.tInfo);
        cCine = (CheckBox) findViewById(R.id.cCine);
        cComer = (CheckBox) findViewById(R.id.cComer);
        cDeporte = (CheckBox) findViewById(R.id.cDeporte);
        cDormir = (CheckBox) findViewById(R.id.cDormir);
        ePassword2 = (EditText) findViewById(R.id.ePassword2);
        eCorreo = (EditText) findViewById(R.id.eCorreo);
        spinner = (Spinner) findViewById(R.id.sCuidad);
        eFecha = (EditText) findViewById(R.id.eFecha);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cuidad_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cuidad = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /*eFecha.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datepicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        eFecha.setText(i2+"/"+(i1+1)+"/"+i);
                        year = i;
                    }
                },mYear,mMonth,mDay);
                datepicker.show();
            }
        });*/
    }


    public void Registrar(View view) {
        nombre = eNombre.getText().toString();
        password1 = ePassword1.getText().toString();
        password2 = ePassword2.getText().toString();
        correo = eCorreo.getText().toString();
        fecha = eFecha.getText().toString();
        ePassword1.setError(null);
        ePassword2.setError(null);
        eNombre.setError(null);
        eFecha.setError(null);
        eCorreo.setError(null);

        tInfo.setText("");

        if(!validarEmail(correo)){eCorreo.setError("Correo inválido");error = true;}
        if(!validarPassword(password1,password2)){
            ePassword1.setError("Contraseña inválida");
            ePassword2.setError("Contraseña inválida");
            error = true;
        }
        if(!validarNombre(nombre)){eNombre.setError("Nombre inválido");error=true;}
        if(!validarFecha(fecha)){eFecha.setError("Fecha inválida");error=true;}

        if(error == false) {
            hobbies = "";
            if (cCine.isChecked()) {
                hobbies = hobbies + " " + "Cine";
            }
            if (cComer.isChecked()) {
                hobbies = hobbies + " " + "Comer";
            }
            if (cDeporte.isChecked()) {
                hobbies = hobbies + " " + "Deporte";
            }
            if (cDormir.isChecked()) {
                hobbies = hobbies + " " + "Dormir";
            }

            tInfo.setText("Usuario: " + nombre +
                    "\nCorreo: " + correo +
                    "\nSexo: " + sexo +
                    "\nLugar de nacimiento: " + cuidad +
                    "\nFecha de nacimiento: " + fecha +
                    "\nHobbies: " + hobbies);
        }
        error = false;
    }

    public void onRadioButtonClicked(View view) {
        int id = view.getId();
        if (id == R.id.rMasculino){
            sexo = "Masculino";
        }
        if (id == R.id.rFemenino){
            sexo = "Femenino";
        }
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private boolean validarPassword(String pass1, String pass2){
        if(pass1.equals(pass2) && !pass1.equals("") && pass1.length()>=4){return true;}
        else{return false;}
    }
    private boolean validarNombre(String nombre) {
        if(nombre.length()>=3 && !nombre.equals("")){return true;}
        else{return false;}
    }
    private boolean validarFecha(String fecha){
        if(!fecha.equals("") && year<=2000){return true;}
        else{return false;}
    }

    public void asignarFecha(View view) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        datepicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                eFecha.setText(i2+"/"+(i1+1)+"/"+i);
                year = i;
            }
        },mYear,mMonth,mDay);
        datepicker.show();
    }
}
