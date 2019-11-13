package com.hgwxr.kotlintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hgwxr.kotlintest.adapter.DataAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menu_container.layoutManager=LinearLayoutManager(this)
        menu_container.adapter=DataAdapter(this)
    }
}
