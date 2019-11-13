package com.hgwxr.gametiantianshuqianlibrary.tiantianshuqian;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hgwxr.gametiantianshuqianlibrary.R;

public class MenuActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuactivity);
	}
	
	public void kaishishuqian(View view)
	{
		Intent intent=new Intent();
		intent.setClass(this, TianTianShuQianActivity.class);
		startActivity(intent);
	}
	public void chakanpaihangbang(View view)
	{
		Intent intent=new Intent();
		intent.setClass(this, TianTianShuQianActivity.class);
		startActivity(intent);
	}
	public void gengduoyouxi(View view)
	{
		Intent intent=new Intent();
		intent.setClass(this, TianTianShuQianActivity.class);
		startActivity(intent);
	}

}
