package com.omerfpekgoz.udemy_61_uygulama_sozluk_volleykutuphanesi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetayActivity extends AppCompatActivity {

    private TextView txtDetayIngilizce;
    private TextView txtDetayTurkce;

    private Kelimeler kelime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        txtDetayIngilizce=findViewById(R.id.txtDetayIngilizce);
        txtDetayTurkce=findViewById(R.id.txtDetayTurkce);

        kelime= (Kelimeler) getIntent().getSerializableExtra("kelime");

        txtDetayIngilizce.setText(kelime.getIngilizce());
        txtDetayTurkce.setText(kelime.getTurkce());
    }
}
