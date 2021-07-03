package com.cibertec.semana03;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cibertec.semana03.api.ServiceApi;
import com.cibertec.semana03.entity.User;
import com.cibertec.semana03.util.ConnectionRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> datos = new ArrayList<String>();
    ListView listviewUsuario;
    ArrayAdapter<String> adaptador ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listviewUsuario = findViewById(R.id.idListUsuarios);
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datos);
        listviewUsuario.setAdapter(adaptador);

        cargaData();
    }
    public  void cargaData(){
        ServiceApi api = ConnectionRest.getConnection().create(ServiceApi.class);
        Call<List<User>> call= api.listaUsuarios();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                //muestraMensaje("--> Exitos!");
                if(response.isSuccessful()){
                    List<User> respuesta = response.body();
                    for(User x:respuesta){
                        datos.add(x.getId()+"-->"+x.getName());
                        //datos.add(x.getThumbnailUrl());
                    }
                    adaptador.notifyDataSetChanged();
                }else {
                    muestraMensaje("La respuesta no es satisfactoria");
                }


            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                muestraMensaje("--> Error: "+ t.getMessage());

            }
        });

    }
    public  void muestraMensaje(String msg){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(msg);
        alert.show();
    }
}