package com.example.oriol.pokedex;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Oriol on 15/02/2017.
 */

public class Menu extends ListActivity {
    DataBaseHandler dbh;
    private ListView lv;
    ArrayList array_list;
    ArrayList<byte[]> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        dbh= new DataBaseHandler(this);
        array_list = dbh.getAllData();
        images= dbh.getAllImages();

        setListAdapter(new IconicAdapter());
    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        Intent i=new Intent(this,Info.class);
        Bundle dataBundle = new Bundle();
        dataBundle.putInt("id",position+1);
        i.putExtras(dataBundle);
        startActivity(i);
    }

    class IconicAdapter extends ArrayAdapter<String> {
        IconicAdapter() {
            super(Menu.this, R.layout.row, R.id.label, array_list);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View row = super.getView(position, convertView, parent);

            ImageView icon = (ImageView) row.findViewById(R.id.icon);

            byte[] byteArray= images.get(position);

            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            icon.setImageBitmap(bmp);


            return (row);
        }

    }
}
