package com.hgwxr.gestures

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.View.DRAG_FLAG_GLOBAL
import android.view.animation.Animation
import android.view.animation.Animation.INFINITE
import android.view.animation.Animation.REVERSE
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_gestures.*

class GesturesActivity : AppCompatActivity() {
    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, GesturesActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private fun  vibrator(time: Long =200){
        val vibrator =
           getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(time,200))
            }else{
                vibrator.vibrate(time)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestures)
        val dragShadowBuilder = View.DragShadowBuilder(dragView)
        dragView.setOnLongClickListener{
            Log.e("DragEvent","OnLongClick")
            vibrator()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dragView.visibility=View.GONE
                dragView.startDragAndDrop(null, dragShadowBuilder,null , DRAG_FLAG_GLOBAL)
            } else {
                dragView.startDrag(null, dragShadowBuilder, null, DRAG_FLAG_GLOBAL)
            }
        }
        dragTargetView.setOnDragListener { v, event ->
            Log.e("DragEvent","ACTION_DRAG_STARTED")
            event?.let {
                when (event.action) {
                    DragEvent.ACTION_DRAG_STARTED -> {
                        Log.e("DragEvent","ACTION_DRAG_STARTED")
                        //拖拽开始
//                        val layoutParams = dragView.layoutParams
//                        val animation = RotateAnimation(-10.0f, 10.0f,(layoutParams.width/2).toFloat(),(layoutParams.height/2).toFloat())
//                        animation.duration=100
//                        animation.repeatCount= INFINITE
//                        animation.repeatMode= REVERSE
//                        dragView?.startAnimation(animation)
                    }
                    DragEvent.ACTION_DRAG_ENTERED -> {
                        //拖拽进入区域
                        v.setBackgroundColor(Color.YELLOW)
                        Log.e("DragEvent","ACTION_DRAG_ENTERED")
                    }
                    DragEvent.ACTION_DRAG_LOCATION -> {
                        Log.e("DragEvent","ACTION_DRAG_LOCATION")
                        v.setBackgroundColor(Color.RED)
                        //拖拽进入区域后，仍在拖拽
                    }
                    DragEvent.ACTION_DROP -> {
                        Log.e("DragEvent","ACTION_DROP")

                        //在区域内放开
                    }
                    DragEvent.ACTION_DRAG_EXITED -> {
                        Log.e("DragEvent","ACTION_DRAG_EXITED")
                        v.setBackgroundColor(Color.BLUE)
                        //离开区域
                    }
                    DragEvent.ACTION_DRAG_ENDED -> {
                        Log.e("DragEvent","ACTION_DRAG_ENDED")
                        dragView.visibility=View.VISIBLE
                        v.setBackgroundColor(Color.BLUE)
                        val animation = dragView.animation
                        animation?.cancel()
                        //结束
                    }
                    else -> {
                    }
                }
            }
            true
        }
    }
}
