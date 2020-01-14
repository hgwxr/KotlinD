package com.hgwxr.gestures.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GestureDetectorCompat
import androidx.customview.widget.ViewDragHelper
import kotlin.math.roundToInt

private const val DEBUG_TAG = "Drag1View-Gestures"

class Drag1View @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener {
    private var rect: Rect = Rect()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mDetector: GestureDetectorCompat = GestureDetectorCompat(context, this)

    init {
        mDetector.setOnDoubleTapListener(this)

    }

    var viewDragHelper: ViewDragHelper? = null
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rect.set(0, 0, width, height)
//        layoutParams.height=h*3
//        layoutParams=layoutParams
        paint.color = Color.RED
        paint.isDither = true
        val colorArray: IntArray = intArrayOf(Color.RED, Color.YELLOW)
        val gradientDrawable =
            GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colorArray)
         viewDragHelper =
            ViewDragHelper.create(parent as ViewGroup, object : ViewDragHelper.Callback() {

                override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                    // 右侧边界
                    if (left > width - child.measuredWidth) {
                        return width - child.measuredWidth
                    }
                    // 左侧边界
                    else if (left < 0) {
                        return 0;
                    }
                    return left

                }

                /**
                 * 处理竖直方向的滑动
                 */
                override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                    //顶部边界
                    if (top > height - child.measuredHeight) {
                        return height - child.measuredHeight
                    }
                    //底部边界
                    else if (top < 0) {
                        return 0
                    }
                    return top
                }

                override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                    //todo
                    return true
                }

                override fun onViewPositionChanged(
                    changedView: View,
                    left: Int,
                    top: Int,
                    dx: Int,
                    dy: Int
                ) {
                    Log.i(DEBUG_TAG, "onViewPositionChanged: $dx,$dy")
                    super.onViewPositionChanged(changedView, left, top, dx, dy)
                }
            })
        background = gradientDrawable
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        canvas?.drawRect(rect, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
//        return if (mDetector.onTouchEvent(event)) {
            viewDragHelper?.processTouchEvent(event)
           return true
//        } else {
//            super.onTouchEvent(event)
//        }
    }

    override fun onDown(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDown: $event")
        return true
    }

    override fun onFling(
        event1: MotionEvent,
        event2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        Log.d(DEBUG_TAG, "onFling: $event1 $event2")
        return true
    }

    override fun onLongPress(event: MotionEvent) {
        Log.d(DEBUG_TAG, "onLongPress: $event")
    }

    override fun onScroll(
        event1: MotionEvent,
        event2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        Log.d(DEBUG_TAG, "onScroll: $distanceX $distanceY  ")
//        val parent = parent as ViewGroup
//        parent.scrollBy(distanceX.toInt(), distanceY.toInt())

        return true
    }

    override fun onShowPress(event: MotionEvent) {
        Log.d(DEBUG_TAG, "onShowPress: $event")
    }

    override fun onSingleTapUp(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onSingleTapUp: $event")
        return true
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDoubleTap: $event")
        return true
    }

    override fun onDoubleTapEvent(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: $event")
        return true
    }

    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: $event")
        return true
    }
}