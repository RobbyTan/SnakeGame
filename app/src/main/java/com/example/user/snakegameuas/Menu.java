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
    private String mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        GameManager.INSTANCE.setPREF(this.getSharedPreferences("highscore", Context.MODE_PRIVATE));
        btnStartGame = (Button) findViewById(R.id.btnStartGame);
        btnHighScore = (Button) findViewById(R.id.btnHighScore);

        Spinner spinnerDifficulty = findViewById(R.id.spiDiffculty);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.difficulty,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapter1);
        spinnerDifficulty.setOnItemSelectedListener(this);

        Spinner spinnerMode = findViewById(R.id.spiMode);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.mode,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMode.setAdapter(adapter2);
        spinnerMode.setOnItemSelectedListener(this);

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent( Menu.this, MainActivity.class);
                intent.putExtra("DIFFICULTY",difficulty);
                intent.putExtra("MODE",mode);
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
        Spinner spinner = (Spinner) parent;
        String text = spinner.getSelectedItem().toString();
        if (spinner.getId() == R.id.spiDiffculty) {

            if (text.equals("EASY")) {
                difficulty = 125;
            } else if (text.equals("MEDIUM")) {
                difficulty = 100;
            } else {
                difficulty = 75;
            }
            ;
        }else if (spinner.getId() == R.id.spiMode){
            if(text.equals("WALLS")){
                mode = "WALLS";
            }else{
                mode = "WALL_THROUGH";
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
