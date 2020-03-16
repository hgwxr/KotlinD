package com.hgwxr.kotlintest

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.concurrent.TimeUnit


fun startService(activity: MainActivity){
    val intent = Intent(activity, MyService::class.java)
    activity.startService(intent)
}
class MyService : Service() {
    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {
        Thread{
            kotlin.run {
                var count=0
               while (true){
                   TimeUnit.SECONDS.sleep(1)
                   Log.e("AG","onCreate $count")
                   count++
               }
            }
        }.start()
        return START_NOT_STICKY
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
