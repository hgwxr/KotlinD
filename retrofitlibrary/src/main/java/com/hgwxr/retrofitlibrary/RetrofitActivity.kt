package com.hgwxr.retrofitlibrary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hgwxr.retrofitlibrary.net.Api
import kotlinx.android.synthetic.main.activity_retrofit.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class RetrofitActivity : AppCompatActivity() {
    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, RetrofitActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        start_net.setOnClickListener {
            val start = System.currentTimeMillis()
            println("========>$start")
            launch(Dispatchers.Main) {
                netRequestNew(start)
            }
            println("========>next")
//            netRequestOld(start)
        }
    }

    private suspend fun netRequestNew(start: Long) {
        val response = Api.getGitHubService().listRepos("hgwxr").await()
        println("========>${System.currentTimeMillis() - start} ")
        println("========>$response")
        text_container.text = response
    }

    private fun netRequestOld(start: Long) {
        Api.getGitHubService().listRepos("hgwxr").enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
                println("${System.currentTimeMillis() - start} ")
                val message = response.body()
                println("========>$message")
                runOnUiThread {
                    text_container.text = message
                }
            }
        })
    }
}

private fun launch(
    main: MainCoroutineDispatcher,
    function: suspend () -> Unit
) {
    GlobalScope.launch(main) { function() }
}




