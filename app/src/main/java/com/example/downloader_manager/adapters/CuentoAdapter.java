package com.example.downloader_manager.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.downloader_manager.Download;
import com.example.downloader_manager.MainActivity;
import com.example.downloader_manager.R;
import com.example.downloader_manager.models.Cuento;

import java.util.ArrayList;

public class CuentoAdapter extends RecyclerView.Adapter<CuentoAdapter.View_Holder_Class> {

    private ArrayList<Cuento> cuentoList;
    private int resource;
    private Context ctx;
    private Activity activity;

    public CuentoAdapter(ArrayList<Cuento> cuentoList, int resource, Context ctx, Activity activity) {
        this.cuentoList = cuentoList;
        this.resource = resource;
        this.ctx = ctx;
        this.activity = activity;
    }

    @NonNull
    @Override
    public View_Holder_Class onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new View_Holder_Class(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder_Class holder, int position) {
        Cuento cuento = cuentoList.get(position);
        holder.txtNombre.setText(cuento.getNombre());
        holder.txtAutor.setText(cuento.getAutor());
        holder.txtCategorias.setText(cuento.getCategorias());

        /*
        Glide.with(ctx)
                .load(evaluador.getImgjpg())
                .placeholder(R.drawable.unknown)
                .error(R.drawable.unknown)
                //.load("https://s22.postimg.cc/572fvlmg1/vlad-baranov-767980-unsplash.jpg")
                .into(holder.imgPerfil);
*/
        holder.imgDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Download download = new Download(v, ctx, activity, cuento.getUrl(), cuento.getNombre_Archivo());
                download.descargar();
                //Toast.makeText(ctx, "Descargar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cuentoList.size();
    }


    public class View_Holder_Class extends RecyclerView.ViewHolder{
        private TextView txtNombre;
        private TextView txtAutor;
        private TextView txtCategorias;
        private ImageView imgDownload;

        public View view;

        public View_Holder_Class(View view){
            super(view);
            this.imgDownload = (ImageView) view.findViewById(R.id.imgDownload);
            this.txtNombre = (TextView) view.findViewById(R.id.txtViewNombre);
            this.txtAutor = (TextView) view.findViewById(R.id.txtviewAutor);
            this.txtCategorias = (TextView) view.findViewById(R.id.txtCategorias);
        }
    }
}
