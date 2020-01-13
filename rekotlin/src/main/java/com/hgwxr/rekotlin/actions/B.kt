package com.hgwxr.rekotlin.actions

import android.util.Log

class B : A() {
    override fun aP(s: String) {
        Log.e("B:", "error")
        Log.e("B:", "error 11 ${s}")
    }
}