package com.example.user.snakegame;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.user.snakegame.engine.GameEngine;
import com.example.user.snakegame.enums.Direction;
import com.example.user.snakegame.enums.GameState;
import com.example.user.snakegame.views.SnakeView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    private GameEngine gameEngine;
    private SnakeView snakeView;
    private final Handler handler = new Handler();
    private final long updateDelay = 125;
    private float prevX,prevY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameEngine = new GameEngine();
        gameEngine.initGame();
        snakeView = (SnakeView) findViewById(R.id.snakeView);
//        mengimplement method overide yang dibawah
        snakeView.setOnTouchListener(this);
        StartUpdateHandler();
    }

    private void StartUpdateHandler(){
//        untuk melakukan update setiap beberapa detik
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameEngine.Update();

                if(gameEngine.getCurrentGameState() == GameState.Running){
                    handler.postDelayed(this,updateDelay );
                }
                if (gameEngine.getCurrentGameState()==GameState.Lost){
                    OnGameLost();
                }
                snakeView.setSnakeViewMap(gameEngine.getMap());
//        invalidate untuk merefresh tampilan/redraw view
                snakeView.invalidate();
            }
        },updateDelay);
    }
//    context itu untuk tahu dimana run activity nya
    private void OnGameLost(){
//        Toast membuat pesan kecil dibawah
        Toast.makeText(this,"You Lost.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
//                ketika pertama kali disentuh
                prevX=event.getX();
                prevY=event.getY();
                break;
            case MotionEvent.ACTION_UP:
//                ketika dilepas
                float newX = event.getX();
                float newY = event.getY();
//                calculate where we swiped
                if(Math.abs(newX-prevX)>Math.abs(newY-prevY)){
//                    Left or right
                    if(newX>prevX){
//                        Right
                        gameEngine.UpdateDirection(Direction.East);
                    }else{
//                        Left
                        gameEngine.UpdateDirection(Direction.West);
                    }
                }else{
//                    Up or down
//                    Koordinat Y 0 dimulai dari atas
                    if(newY>prevY){
//                        Down
                        gameEngine.UpdateDirection(Direction.South);
                    }else{
//                        Up
                        gameEngine.UpdateDirection(Direction.North);
                    }
                }
                break;
        }
        return true;
    }
}
