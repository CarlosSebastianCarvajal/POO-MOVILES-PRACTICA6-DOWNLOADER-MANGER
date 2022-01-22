package com.example.downloader_manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.downloader_manager.adapters.CuentoAdapter;
import com.example.downloader_manager.models.Cuento;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private CuentoAdapter mAdapter;
    private ArrayList<Cuento> mCuentoList = new ArrayList<>();

    private static final String URL1 = "https://smart-meter-project-35c6b-default-rtdb.firebaseio.com/libros-infantiles.json";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        requestQueue = Volley.newRequestQueue(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvCuentos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ObtenerDatos();

    }

    private void ObtenerDatos(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL1,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray names = response.names();
                            for(int i = 0; i < response.length(); i++){
                                JSONObject jsonObject = new JSONObject(response.get(names.getString(i)).toString());

                                String Id = names.getString(i).toString();
                                String Autor = jsonObject.getString("Autor");
                                String Categorias = jsonObject.getString("Categorias");
                                String Nombre = jsonObject.getString("Nombre");
                                String Nombre_Archivo = jsonObject.getString("Nombre_Archivo");
                                String Url = jsonObject.getString("Url");

                                mCuentoList.add(new Cuento(Id, Autor, Categorias, Nombre, Nombre_Archivo,Url));
                            }
                            mAdapter = new CuentoAdapter(mCuentoList, R.layout.cuento_descripcion, MainActivity.this, MainActivity.this);
                            mRecyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {  }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}