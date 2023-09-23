package com.example.a9x9_tic_tac_toe.layout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.GridLayout

class ScalableGridLayout(
    context: Context,
    attrs: AttributeSet,
//    sc: ScaleGestureDetector = ScaleGestureDetector(context, ScaleListener())
) : GridLayout(context, attrs) {

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            Log.d("ScaleListener","--------onScale---------")
            scaleFactor *= detector.scaleFactor

            // Limit the scale factor to a minimum and maximum value
            scaleFactor = scaleFactor.coerceIn(0.5f, 2.0f)

            // Apply the scale factor to the GridLayout
            this@ScalableGridLayout.scaleX = scaleFactor
            this@ScalableGridLayout.scaleY = scaleFactor

            return true
        }
    }

    private val scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f

    init {
        scaleGestureDetector = ScaleGestureDetector(context, ScaleListener())
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d("MainActivity", "---------on touch is working ?---------")
        // Pass the touch event to ScaleGestureDetector
        scaleGestureDetector.onTouchEvent(event)
        return true
    }
}