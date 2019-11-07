package com.example.silabuz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity implements View.OnClickListener{

    EditText enombre,eapellido,ecorreo,econtrasena;
    Button btn_registrarme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        enombre = findViewById(R.id.tv_nombre);
        eapellido = findViewById(R.id.tv_apellido);
        ecorreo = findViewById(R.id.tv_correo);
        econtrasena = findViewById(R.id.tv_contrasena);

        btn_registrarme = findViewById(R.id.btn_registrarme);
        btn_registrarme.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        final String nombre=enombre.getText().toString();
        final String apellido=eapellido.getText().toString();
        final String correo=ecorreo.getText().toString();
        final String contrasena= econtrasena.getText().toString();

        Response.Listener<String> respoListener =   new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success){
                        Intent intent = new Intent(Register.this,Login.class);
                        Register.this.startActivity(intent);
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                        builder.setMessage("Error registro")
                                .setNegativeButton("Retry",null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RegisterRequest registerRequest = new RegisterRequest(nombre,apellido,correo,contrasena,respoListener);
        RequestQueue queue = Volley.newRequestQueue(Register.this);
        queue.add(registerRequest);
    }
}
