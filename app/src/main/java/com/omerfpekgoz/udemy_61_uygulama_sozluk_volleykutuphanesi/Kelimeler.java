package com.omerfpekgoz.udemy_61_uygulama_sozluk_volleykutuphanesi;

import java.io.Serializable;

public class Kelimeler implements Serializable {        //Nesne taşırken  Serializable  olması gerekir

    private int kelime_id;
    private String ingilizce;
    private String turkce;

    public Kelimeler() {
    }

    public Kelimeler(int kelime_id, String ingilizce, String turkce) {
        this.kelime_id = kelime_id;
        this.ingilizce = ingilizce;
        this.turkce = turkce;
    }

    public int getKelime_id() {
        return kelime_id;
    }

    public void setKelime_id(int kelime_id) {
        this.kelime_id = kelime_id;
    }

    public String getIngilizce() {
        return ingilizce;
    }

    public void setIngilizce(String ingilizce) {
        this.ingilizce = ingilizce;
    }

    public String getTurkce() {
        return turkce;
    }

    public void setTurkce(String turkce) {
        this.turkce = turkce;
    }
}
