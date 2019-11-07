package com.example.silabuz;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL="http://192.168.1.10:8080/Register_reto-ambiental.php";

    private Map<String,String> params;
    public RegisterRequest(String nombre, String apellido, String correo, String contrasena,Response.Listener<String> Listener){
        super(Method.POST, REGISTER_REQUEST_URL, Listener,null);
        params=new HashMap<>();
        params.put("nombre",nombre);
        params.put("apellido",apellido);
        params.put("correo",correo);
        params.put("contrasena",contrasena);

    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}