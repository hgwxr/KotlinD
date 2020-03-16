package com.hgwxr.kotlintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.hgwxr.kotlintest.adapter.DataAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.net.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("AG","onCreate")
        menu_container.layoutManager=LinearLayoutManager(this)
        menu_container.adapter=DataAdapter(this)
//        startService(this)
    }


}
