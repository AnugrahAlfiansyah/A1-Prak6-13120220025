package com.example.a1_prak6_13120220025;

import org.json.JSONException;
import org.json.JSONObject;

public class Mahasiswa {
    private String stb;
    private String nama;
    private int angkatan;

    private JSONObject jsonObject;
    private Mahasiswa mahasiswa;

    public Mahasiswa(String stb, String nama, int angkatan){
        this.stb = stb;
        this.nama = nama;
        this.angkatan = angkatan;
    }

    public int getAngkatan() {
        return angkatan;
    }

    public String getNama() {
        return nama;
    }

    public String getStb() {
        return stb;
    }

    public JSONObject toJSON() throws JSONException{
        jsonObject = new JSONObject();
        jsonObject.put("stb", stb);
        jsonObject.put("nama", nama);
        jsonObject.put("angkatan", angkatan);
        return jsonObject;
    }
}
