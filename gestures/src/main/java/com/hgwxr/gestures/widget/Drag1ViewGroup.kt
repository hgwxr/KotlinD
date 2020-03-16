package com.hgwxr.gestures.widget

import android.R.attr.factor
import android.content.Context
import android.graphics.Interpolator
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.animation.PathInterpolatorCompat
import androidx.customview.widget.ViewDragHelper
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator


class Drag1ViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mViewDragHelper: ViewDragHelper? = null

    init {

        val heightPixels = resources.displayMetrics.heightPixels/2
        mViewDragHelper = ViewDragHelper.create(this, object : ViewDragHelper.Callback() {
           var topT=100
            val create = LinearOutSlowInInterpolator()
            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                return true
            }

            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                Log.i("DEBUG_TAG", "clampViewPositionHorizontal: $left,$dx")
                return left
            }

            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                val interpolation = create.getInterpolation(top.toFloat())
                Log.i("DEBUG_TAG", "clampViewPositionVertical: $top,$interpolation")
                return interpolation.toInt()
            }
        })
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (ev == null) {
            return super.onInterceptTouchEvent(ev)
        } else {
            return mViewDragHelper == null ?: mViewDragHelper?.shouldInterceptTouchEvent(ev)
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            mViewDragHelper?.processTouchEvent(event)
        }
        return true
    }
}