package com.example.user.snakegameuas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Menu extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button btnStartGame,btnHighScore;
    private int difficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        GameManager.INSTANCE.setPREF(this.getSharedPreferences("highscore", Context.MODE_PRIVATE));
        btnStartGame = (Button) findViewById(R.id.btnStartGame);
        btnHighScore = (Button) findViewById(R.id.btnHighScore);
        Spinner spinner = findViewById(R.id.spiDiffculty);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.difficulty,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent( Menu.this, MainActivity.class);
                intent.putExtra("DIFFICULTY",difficulty);
                startActivity(intent);
            }
        });
        btnHighScore.setText(String.valueOf(GameManager.INSTANCE.getScore()));
    }

//    Ketika balik ke activity menu lagi maka set text dari preference sekali lagi
    @Override
    protected void onResume() {
        super.onResume();
        btnHighScore.setText(String.valueOf(GameManager.INSTANCE.getScore()));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getSelectedItem().toString();
        if(text.equals("Easy")){
            difficulty=125;
        }else if(text.equals("Medium")){
            difficulty=100;
        }else{
            difficulty=75;
        };
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
