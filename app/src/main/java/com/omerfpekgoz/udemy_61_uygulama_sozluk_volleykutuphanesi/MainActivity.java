package com.omerfpekgoz.udemy_61_uygulama_sozluk_volleykutuphanesi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//<item name="windowActionBar">false</item>
//<item name="windowNoTitle">true</item>
//Kendi toolbar ekledik ve arama özelliği ekledik

//app:actionViewClass="androidx.appcompat.widget.SearchView"
//Bunu da menu tasarımdaki kod kısmına ekledik

//BU KODU MANİFES E YAZILMASI GEREKİR HTTP SORUNU GİDERMEK İÇİN
//UNUTMA
//android:usesCleartextTraffic="true">
public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {   //Searc için implement ettik

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private ArrayList<Kelimeler> kelimelerList;
    private KelimelerAdapter kelimelerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.recyclerView);

        toolbar.setTitle("Sözlük Uygulaması");
        setSupportActionBar(toolbar);  //Toolabr etkin hale geldi


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));  //Liner dedik alt alta görünecek

        tumKelimeler();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.menuArama);  //Manu arama ya tıklanıldıpında

       // SearchView searchView= (SearchView) MenuItemCompat.getActionView(menuItem);  //Bu tanımlama güncel değil.Aşağı daki güncel
        SearchView searchView= (SearchView) menuItem.getActionView();  //Güncel olan budur

        searchView.setOnQueryTextListener(this);   //this bu sınıfa bağla demek
        return  super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onQueryTextSubmit(String query) {   //Kelime girildiği yani gönderildiği zaman arama yapar.Kelime yazıp enter a basarsak

        Log.e("Gönderilen Arama",query);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {   //Harf girildiği zaman arama yapar

        Log.e("Harf Girdikçe Arama",newText);
        kelimeAra(newText);

        return false;
    }

    public void tumKelimeler(){
        String url="http://www.omerfpekgoz.cf/sozluk/tum_kelimeler.php";

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               kelimelerList=new ArrayList<>();  //Kelimeler listesini sıfırladık
                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray kelimeler=jsonObject.getJSONArray("kelimeler");
                    for (int i=0;i<kelimeler.length();i++){

                        JSONObject kelime=kelimeler.getJSONObject(i);

                        int kelime_id=kelime.getInt("kelime_id");
                        String ingilizce=kelime.getString("ingilizce");
                        String turkce=kelime.getString("turkce");

                        Kelimeler gelenKelime=new Kelimeler(kelime_id,ingilizce,turkce);

                        kelimelerList.add(gelenKelime);

                    }

                    //kelimelerAdapter.notifyDataSetChanged();

                    kelimelerAdapter=new KelimelerAdapter(MainActivity.this,kelimelerList);
                    recyclerView.setAdapter(kelimelerAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });



        Volley.newRequestQueue(MainActivity.this).add(stringRequest);

    }

    public void kelimeAra(final String aranankelime){
        String url="http://www.omerfpekgoz.cf/sozluk/kelime_ara.php";

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Cevap",response);

                kelimelerList=new ArrayList<>();  //Kelimeler listesini sıfırladık
                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray kelimeler=jsonObject.getJSONArray("kelimeler");
                    for (int i=0;i<kelimeler.length();i++){

                        JSONObject kelime=kelimeler.getJSONObject(i);

                        int kelime_id=kelime.getInt("kelime_id");
                        String ingilizce=kelime.getString("ingilizce");
                        String turkce=kelime.getString("turkce");

                        Kelimeler gelenKelime=new Kelimeler(kelime_id,ingilizce,turkce);

                        kelimelerList.add(gelenKelime);
                    }

                    kelimelerAdapter=new KelimelerAdapter(MainActivity.this,kelimelerList);
                    recyclerView.setAdapter(kelimelerAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();
                params.put("ingilizce",aranankelime);


                return params;
            }
        };
        Volley.newRequestQueue(MainActivity.this).add(stringRequest);




    }

}
