package com.example.tccprototype;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView  foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaocorrenciacadastro);
        setTitle("CADASTRO DE OCORRÊNCIA");


        final ImageButton botaoFoto = (ImageButton)findViewById(R.id.botaoCameraFoto);

        final Button enviar = (Button)findViewById(R.id.enviar);
        foto = (ImageView)findViewById(R.id.foto);

        final Spinner categoriasSpinner = (Spinner)findViewById(R.id.categorias);
        final ArrayAdapter<CharSequence> categoriasArray = ArrayAdapter.createFromResource(this, R.array.categorias_desastres, android.R.layout.simple_spinner_item);
        categoriasArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriasSpinner.setAdapter(categoriasArray);
        categoriasSpinner.setPrompt("Categorias");


        categoriasSpinner.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        categoriasArray,
                        R.layout.nada_selecionado,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));


        final TextView nome = (TextView)findViewById(R.id.nome);
        final TextView endereco = (TextView)findViewById(R.id.endereco);
        final TextView cpf = (TextView)findViewById(R.id.cpf);


        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }

        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);

            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!nome.getText().toString().matches("[a-zA-Z]+ ")){
                    Toast.makeText(enviar.getContext(), "NOME INVÁLIDO",Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(enviar.getContext(), "OCORRÊNCIA ENVIADA", Toast.LENGTH_LONG).show();
                    nome.setText("");
                    endereco.setText("");
                    cpf.setText("");
                }


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        Bundle fotoBundle = data.getExtras();
        Bitmap imageBitmap = (Bitmap) fotoBundle.get("data");
        foto.setImageBitmap(imageBitmap);



    }
}
