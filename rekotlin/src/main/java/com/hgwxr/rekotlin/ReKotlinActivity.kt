package com.hgwxr.rekotlin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.os.postDelayed
import com.hgwxr.rekotlin.actions.A
import com.hgwxr.rekotlin.actions.B
import com.hgwxr.rekotlin.actions.CounterActionDecrease
import com.hgwxr.rekotlin.actions.CounterActionIncrease
import com.hgwxr.rekotlin.redurces.counterReducer
import com.hgwxr.rekotlin.state.AppState
import kotlinx.android.synthetic.main.activity_re_kotlin.*
import tw.geothings.rekotlin.Store
import tw.geothings.rekotlin.StoreSubscriber

val mainStore = Store(
    reducer = ::counterReducer,
    state = null
)
class ReKotlinActivity : AppCompatActivity(), StoreSubscriber<AppState> {
    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, ReKotlinActivity::class.java)
            activity.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_re_kotlin)

        val handler = Handler()
        btnUp.setOnClickListener {
            val a1:A=B()
            val f1=A::aP
            f1(B(),"adda")
            Log.e("A:","error ${f1.name}")



            val array = arrayListOf<Int>()
            for (i in 0..10){
                array.add(i)
            }
            val a = A()

            array.forEach {
                val t:Long= (it*1000).toLong()
                handler.postDelayed({
                    a.push(it)
                },t)
            }
//            mainStore.dispatch(CounterActionIncrease())
        }
        btnDown.setOnClickListener {
            mainStore.dispatch(CounterActionDecrease())
        }
        mainStore.subscribe(this)
    }

    override fun newState(state: AppState) {
        tvCount.text = "${state.counter}"
    }
}
