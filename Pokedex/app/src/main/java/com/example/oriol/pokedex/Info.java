package com.example.oriol.pokedex;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Oriol on 03/03/2017.
 */

public class Info extends AppCompatActivity {
    DataBaseHandler dbh;
    private Cursor res;
    ArrayList<byte[]> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_layout);

        dbh=new DataBaseHandler(this);

        Bundle extras = getIntent().getExtras();
        res= dbh.getData(extras.getInt("id"));

        images= dbh.getAllImages();

        ImageView iv= (ImageView) findViewById(R.id.image);
        byte[] byteArray= images.get(extras.getInt("id")-1);

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        iv.setImageBitmap(bmp);
        init();
    }



    public void init(){
        res.moveToFirst();
        TextView tv= (TextView) findViewById(R.id.Id);
        String ids;
        if(res.getInt(res.getColumnIndex("id"))<10){
         ids="00"+ res.getString(res.getColumnIndex("id"));
        }else if(res.getInt(res.getColumnIndex("id"))<100){
         ids="0"+res.getString(res.getColumnIndex("id"));
        }else{
          ids= res.getString(res.getColumnIndex("id"));
        }
        tv.setText("Nº "+  ids + " " + res.getString(res.getColumnIndex("name")));
        tv= (TextView) findViewById(R.id.Descripcion);
        tv.setText(res.getString(res.getColumnIndex("description")));
        ImageView iv= (ImageView) findViewById(R.id.type1);
        loadType(res.getString(res.getColumnIndex("type1")),iv);
        iv= (ImageView) findViewById(R.id.type2);
        loadType(res.getString(res.getColumnIndex("type2")),iv);
    }

    private void loadType(String type, ImageView iv){
        switch (type.toLowerCase()){
            case " steel":
                iv.setImageResource(R.drawable.acero);
                break;
            case " water":
                iv.setImageResource(R.drawable.agua);
                break;
            case " bug":
                iv.setImageResource(R.drawable.bicho);
                break;
            case " dragon":
                iv.setImageResource(R.drawable.dragon);
                break;
            case " electric":
                iv.setImageResource(R.drawable.electrico);
                break;
            case " ghost":
                iv.setImageResource(R.drawable.fantasma);
                break;
            case " fire":
                iv.setImageResource(R.drawable.fuego);
                break;
            case " ice":
                iv.setImageResource(R.drawable.hielo);
                break;
            case " fighting":
                iv.setImageResource(R.drawable.lucha);
                break;
            case " normal":
                iv.setImageResource(R.drawable.normal);
                break;
            case " grass":
                iv.setImageResource(R.drawable.planta);
                break;
            case " psychic":
                iv.setImageResource(R.drawable.psiquico);
                break;
            case " rock":
                iv.setImageResource(R.drawable.roca);
                break;
            case " ground":
                iv.setImageResource(R.drawable.tierra);
                break;
            case " poison":
                iv.setImageResource(R.drawable.veneno);
                break;
            case " flying":
                iv.setImageResource(R.drawable.volador);
                break;
            case " fairy":
                iv.setImageResource(R.drawable.normal);
                break;
        }
    }
}
