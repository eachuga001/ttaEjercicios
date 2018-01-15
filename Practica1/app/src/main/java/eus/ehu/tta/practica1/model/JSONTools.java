package eus.ehu.tta.practica1.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edwin on 3/01/18.
 */

public class JSONTools {

    public User getUserFromJson(String stringJson){
        User usuario = new User();

        JSONObject json = null;
        try {
            json = new JSONObject(stringJson);
            usuario.setId(json.getInt("id"));
            usuario.setUser(json.getString("user"));
            usuario.setLessonNumber(json.getInt("lessonNumber"));
            usuario.setLessonTittle(json.getString("lessonTitle"));
            usuario.setNextTest(json.getInt("nextTest"));
            usuario.setNextExercise(json.getInt("nextExercise"));

            return usuario;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*public Frase getFrase(String stringJson){
        Frase frase = new Frase();
        try {
            JSONObject json = new JSONObject(stringJson);

            frase.setIdFrases(json.getInt("idFrases"));
            frase.setFraseEsp(json.getString("fraseEsp"));
            frase.setFraseEng(json.getString("fraseEng"));
            frase.setAudio(json.getString("audio"));
            frase.setSituacion(json.getString("situacion"));
            frase.setTipo(json.getString("tipo"));
            frase.setUsuarioAns(json.getInt("usuAns"));
            frase.setUsuarioAsk(json.getInt("usuAsk"));
            frase.setAnsUser(json.getString("ansUser"));
            frase.setAskUser(json.getString("askUser"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return frase;
    }


    public List<Frase> getFrasesFromJson(String arrayJson){
        List<Frase> lista = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(arrayJson);
            for(int i=0;i<array.length();i++){
                JSONObject json = array.getJSONObject(i);
                Frase frase = new Frase();

                frase.setIdFrases(json.getInt("idFrases"));
                frase.setFraseEsp(json.getString("fraseEsp"));
                frase.setFraseEng(json.getString("fraseEng"));
                frase.setAudio(json.getString("audio"));
                frase.setSituacion(json.getString("situacion"));
                frase.setTipo(json.getString("tipo"));
                frase.setUsuarioAns(json.getInt("usuAns"));
                frase.setUsuarioAsk(json.getInt("usuAsk"));
                frase.setAnsUser(json.getString("ansUser"));
                frase.setAskUser(json.getString("askUser"));

                lista.add(frase);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return lista;
    }

    public String getJsonFromFrase(Frase frase){
        JSONObject fraseJson = new JSONObject();

        try {
            //fraseJson.put("idFrases",frase.getIdFrases());
            fraseJson.put("fraseEsp",frase.getFraseEsp());
            fraseJson.put("fraseEng",frase.getFraseEng());
            fraseJson.put("audio",frase.getAudio());
            fraseJson.put("situacion",frase.getSituacion());
            fraseJson.put("ftipo",frase.getTipo());
            fraseJson.put("usuAns",frase.getUsuarioAns());
            fraseJson.put("usuAsk",frase.getUsuarioAsk());
            fraseJson.put("ansUser",frase.getAnsUser());
            fraseJson.put("askUser",frase.getAskUser());


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return fraseJson.toString();
    }*/
}
