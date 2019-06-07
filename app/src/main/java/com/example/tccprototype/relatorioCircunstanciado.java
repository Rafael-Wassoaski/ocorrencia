package com.example.tccprototype;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class relatorioCircunstanciado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relatoriocircunstanciadoactivity);

        TextView data = (TextView)findViewById(R.id.data);
        TextView hora = (TextView)findViewById(R.id.hora);
        Spinner municipioSpinner = (Spinner)findViewById(R.id.municipio);
        ArrayAdapter<CharSequence> municipiosArray = ArrayAdapter.createFromResource(this, R.array.municipioCadastrados, android.R.layout.simple_spinner_item);
        municipiosArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        municipioSpinner.setAdapter(municipiosArray);
        final Button danosHumanos = (Button)findViewById(R.id.danosHumano);
        Calendar calender = Calendar.getInstance();
        Date data1 = new Date();

        calender.setTime(data1);
        Date dataAtual = calender.getTime();
        SimpleDateFormat horaAtual = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy");

        data.setText((String)dataFormat.format(dataAtual));
        hora.setText((String)horaAtual.format(dataAtual));

        danosHumanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm2 = getSupportFragmentManager();
                FragmentTransaction ft2 = fm2.beginTransaction();
                danosHumanos fragDanosHumanso =  new danosHumanos();
                ft2.add(R.id.LinearLayout1, fragDanosHumanso, "danosHumanosFrag");
                ft2.commit();
            }
        });




    }
}
