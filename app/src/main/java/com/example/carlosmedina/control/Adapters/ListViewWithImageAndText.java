package com.example.carlosmedina.control.Adapters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.carlosmedina.control.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListViewWithImageAndText extends AppCompatActivity {

    // Array of strings for ListView Title
    String[] listviewTitle = new String[]{
            " Grupo 1", " Grupo 2", " Grupo 3", " Grupo 4",
            " Grupo 5", " Grupo 6", " Grupo 7", " Grupo 8",
    };


    int[] listviewImage = new int[]{
            R.drawable.ic_account_circle_black_24dp, R.drawable.ic_account_circle_black_24dp, R.drawable.ic_account_circle_black_24dp, R.drawable.ic_account_circle_black_24dp,
            R.drawable.ic_account_circle_black_24dp, R.drawable.ic_account_circle_black_24dp, R.drawable.ic_account_circle_black_24dp, R.drawable.ic_account_circle_black_24dp,
    };

    String[] listviewShortDescription = new String[]{
            "Descripción del grupo", "Descripción del grupo", "Descripción del grupo", "Descripción del grupo",
            "Descripción del grupo", "Descripción del grupo", "Descripción del grupo", "Descripción del grupo",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_with_image_and_text);

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 8; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.activity_lista_grupos, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);
    }
}
