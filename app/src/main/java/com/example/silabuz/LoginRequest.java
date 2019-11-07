package com.example.silabuz;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest  extends StringRequest {

    private static final String LOGIN_REQUEST_URL="http://192.168.1.10:8080/Login_reto-ambiental.php";

    private Map<String,String> params;
    public LoginRequest(String correo, String contrasena, Response.Listener<String> Listener){
        super(Request.Method.POST, LOGIN_REQUEST_URL, Listener,null);

        params=new HashMap<>();
        params.put("correo",correo);
        params.put("contrasena",contrasena);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}