package com.hgwxr.gametiantianshuqianlibrary.tiantianshuqian;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hgwxr.gametiantianshuqianlibrary.R;
import com.hgwxr.gametiantianshuqianlibrary.view.GameView;
import com.hgwxr.gametiantianshuqianlibrary.view.TimeInterface;


public class TianTianShuQianActivity extends Activity implements TimeInterface {

	public static void start(Activity activity){
		Intent intent = new Intent(activity, TianTianShuQianActivity.class);
		activity.startActivity(intent);
	}
	TextView tv_time;
	TextView tv_mony;
	GameView gameView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tian_tian_main);
		tv_time=(TextView)this.findViewById(R.id.txt_time);
		tv_mony=(TextView)this.findViewById(R.id.txt_allprice);
		gameView=(GameView)findViewById(R.id.gameview);
		gameView.setInterface(this);
		updateMony(0.0f);
		
	}
	
	public void Refresh(View view)
	{
		
		if(gameView.setMusicState())
		{
			((ImageButton)view).setImageResource(R.drawable.music_btn_open);
		}
		else
		{
			((ImageButton)view).setImageResource(R.drawable.music_btn_close);
		}
		
	}

	@Override
	public void updateTime(int longTime) {
		
		tv_time.setText(""+longTime);
	}

	@Override
	public void updateMony(float mony) {
	
		DecimalFormat decimalFormat = new DecimalFormat(".00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
		String p = decimalFormat.format(mony);// format 返回的是字符串
		tv_mony.setText(p+"元");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(gameView!=null)
		{
			gameView.DestreyView();
		}
	}
	@Override
	public void gameOver(float mony) {

		Intent intent=new Intent();
		intent.setClass(this, ResultActivity.class);
		intent.putExtra("mony", ""+mony);
		startActivity(intent);
		finish();
		
		
	}
}
