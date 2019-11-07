package com.example.silabuz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    Button btn_registrar,btn_login;
    TextView ecorreo,econtrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ecorreo = findViewById(R.id.tv_correo);
        econtrasena = findViewById(R.id.tv_contrasena);
        btn_registrar = findViewById(R.id.btn_registrar);
        btn_login = findViewById(R.id.btn_login);

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentReg = new Intent(Login.this,Register.class);
                Login.this.startActivity(intentReg);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ecorreo.getText().toString().isEmpty()) {
                    ecorreo.setError("Complete");
                }else
                if(econtrasena.getText().toString().isEmpty()) {
                    econtrasena.setError("Complete");
                }

                final String correo = ecorreo.getText().toString();
                final String contrasena = econtrasena.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                String nombre = jsonResponse.getString("nombre");
                                String apellido = jsonResponse.getString("apellido");

                                Intent intent = new Intent(Login.this, MainActivity.class);
                                intent.putExtra("nombre",nombre);
                                intent.putExtra("apellido",apellido);
                                intent.putExtra("correo",correo);
                                intent.putExtra("contrasena",contrasena);

                                Login.this.startActivity(intent);

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage("NO EXISTE EL USUARIO QUE INGRESO")
                                        .setNegativeButton("Intentar de nuevo",null)
                                        .create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(correo,contrasena,responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(loginRequest);
            }
        });
    }
}
