package com.hgwxr.gameplane;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class GameHomeActivity extends Activity implements Button.OnClickListener {
    public static void start(Activity activity){
        Intent intent = new Intent(activity, GameHomeActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if(viewId == R.id.btnGame){
            startGame();
        }
    }

    public void startGame(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}