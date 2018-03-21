package com.example.user.snakegameuas;

import android.content.SharedPreferences;

/**
 * Created by USER on 2/26/2018.
 */

public class GameManager {

    public static GameManager INSTANCE = new GameManager();

    private SharedPreferences PREF;

    private GameManager(){}

    public SharedPreferences getPREF() {
        return PREF;
    }

    public void setPREF(SharedPreferences PREF) {
        this.PREF = PREF;
    }

    public void updateScore(int score){
        SharedPreferences.Editor editor = PREF.edit();
        editor.putInt("score",score);
//        editor.remove("score");
        editor.commit();
    }

    public int getScore(){
        return PREF.getInt("score",0);
    }
}
