package com.hgwxr.rekotlin.actions

import android.util.Log
import java.util.concurrent.TimeUnit

val queue = arrayListOf<Int>()
const val delay:Long=5
fun  aaa(aa:String){
    Log.e("A:", " push  任务 1")

}
open class A {
    var running:Boolean=false
    fun push(item: Int){
        queue.add(item)
        Log.e("A:", " push  任务 :$item $running")
        if (!running) {
            running=true
            val thread = getRunnable(item)
            thread.start()
        }
    }

    private fun getRunnable(item: Int): Thread {
        return Thread {
            Log.e("A:", " 执行5秒内的集合第一个 任务 :$item")
            TimeUnit.SECONDS.sleep(delay)
            if ( queue.size>1){
                val get = queue[queue.size - 1]
                Log.e("A:", "执行5秒内的集合最后一个 任务 :$get")
            }else{
                Log.e("A:", "执行5秒内的无任务 :$item")
            }
            queue.clear()
            running = false
        }
    }

    open fun aP(s: String) {
        Log.e("A:", "error")
        Log.e("A:", "error 11 ${s}")
    }
}