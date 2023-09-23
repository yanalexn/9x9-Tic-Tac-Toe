package com.example.a9x9_tic_tac_toe.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a9x9_tic_tac_toe.GRID_SIZE
import com.example.a9x9_tic_tac_toe.R
import com.example.a9x9_tic_tac_toe.databinding.ActivityMainBinding
import com.example.a9x9_tic_tac_toe.layout.ScalableGridLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var gridLayout: ScalableGridLayout
//    private lateinit var scaleGestureDetector: ScaleGestureDetector
//    private var scaleFactor = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gridLayout = findViewById(R.id.gridLayout)

        generateButtons()

//        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())
    }

//    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
//        Log.d("MainActivity", "---------on touch is working ?---------")
//        // Pass the touch event to ScaleGestureDetector
////        when (event.action) {
////            MotionEvent.ACTION_MOVE
////        }
//        scaleGestureDetector.onTouchEvent(event)
//        return true
//    }

    private fun generateButtons() {
        val buttons = Array(GRID_SIZE) { arrayOfNulls<Button>(GRID_SIZE) }

        for (i in 0 until GRID_SIZE) {
            for (j in 0 until GRID_SIZE) {
                val button = Button(this)

                val layoutParams = GridLayout.LayoutParams()
                layoutParams.width = 0
                layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT
                layoutParams.rowSpec = GridLayout.spec(i, 1f) // Equal row weight
                layoutParams.columnSpec = GridLayout.spec(j, 1f) // Equal column weight
                button.layoutParams = layoutParams

                button.text = "${i * GRID_SIZE + j + 1}"
                button.setOnClickListener(buttonClickListener)
                buttons[i][j] = button
                binding.gridLayout.addView(button)
            }
        }
    }

    private val buttonClickListener = View.OnClickListener { view ->
        view as Button
        Toast.makeText(this, "you tapped on button ${view.text}", Toast.LENGTH_SHORT)
            .show()
    }

//    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
//        override fun onScale(detector: ScaleGestureDetector): Boolean {
//            scaleFactor *= detector.scaleFactor
//
//            // Limit the scale factor to a minimum and maximum value
//            scaleFactor = scaleFactor.coerceIn(0.5f, 2.0f)
//
//            // Apply the scale factor to the GridLayout
//            gridLayout.scaleX = scaleFactor
//            gridLayout.scaleY = scaleFactor
//
//            return true
//        }
//    }
}