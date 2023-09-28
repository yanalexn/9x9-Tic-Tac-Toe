package com.example.a9x9_tic_tac_toe.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import com.example.a9x9_tic_tac_toe.R
import com.example.a9x9_tic_tac_toe.databinding.ActivityMainBinding
import com.example.a9x9_tic_tac_toe.engine.Player

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun btnClick(view: View) {
        val selectedBtn = view as Button
//        var cellId = 0
//        when (btnSelected.id) {
//            binding.btn1.id ->
//        }
        Log.d("btnClick", "${selectedBtn.id}")
        playGame(selectedBtn)
    }

    var activePlayer = Player.PLAYER_1
    val cellsSelectedByPlayer1 = mutableListOf<Int>()
    val cellsSelectedByPlayer2 = mutableListOf<Int>()

    private fun playGame(selectedBtn: Button) {
        if (activePlayer == Player.PLAYER_1) {
            selectedBtn.text = "X"
            selectedBtn.backgroundTintList = getColorStateList(R.color.blue)
            cellsSelectedByPlayer1.add(selectedBtn.id)
            activePlayer = Player.PLAYER_2
        } else {
            selectedBtn.text = "O"
            selectedBtn.backgroundTintList = getColorStateList(R.color.darkGreen)
            cellsSelectedByPlayer2.add(selectedBtn.id)
            activePlayer = Player.PLAYER_1
        }
        selectedBtn.isEnabled = false
        checkWinner()
    }

    private fun checkWinner() {
        val winner = if (cellsSelectedByPlayer1.contains(binding.btn1.id)
            && cellsSelectedByPlayer1.contains(binding.btn2.id)
            && cellsSelectedByPlayer1.contains(binding.btn3.id)
        ) Player.PLAYER_1
        else if (cellsSelectedByPlayer2.contains(binding.btn1.id)
            && cellsSelectedByPlayer2.contains(binding.btn2.id)
            && cellsSelectedByPlayer2.contains(binding.btn3.id)
        ) Player.PLAYER_2
        else if (cellsSelectedByPlayer1.contains(binding.btn4.id)
            && cellsSelectedByPlayer1.contains(binding.btn5.id)
            && cellsSelectedByPlayer1.contains(binding.btn6.id)
        ) Player.PLAYER_1
        else if (cellsSelectedByPlayer2.contains(binding.btn4.id)
            && cellsSelectedByPlayer2.contains(binding.btn5.id)
            && cellsSelectedByPlayer2.contains(binding.btn6.id)
        ) Player.PLAYER_2
        else if (cellsSelectedByPlayer1.contains(binding.btn7.id)
            && cellsSelectedByPlayer1.contains(binding.btn8.id)
            && cellsSelectedByPlayer1.contains(binding.btn9.id)
        ) Player.PLAYER_1
        else if (cellsSelectedByPlayer2.contains(binding.btn7.id)
            && cellsSelectedByPlayer2.contains(binding.btn8.id)
            && cellsSelectedByPlayer2.contains(binding.btn9.id)
        ) Player.PLAYER_2
        else if (cellsSelectedByPlayer1.contains(binding.btn1.id)
            && cellsSelectedByPlayer1.contains(binding.btn4.id)
            && cellsSelectedByPlayer1.contains(binding.btn7.id)
        ) Player.PLAYER_1
        else if (cellsSelectedByPlayer2.contains(binding.btn1.id)
            && cellsSelectedByPlayer2.contains(binding.btn4.id)
            && cellsSelectedByPlayer2.contains(binding.btn7.id)
        ) Player.PLAYER_2
        else if (cellsSelectedByPlayer1.contains(binding.btn2.id)
            && cellsSelectedByPlayer1.contains(binding.btn5.id)
            && cellsSelectedByPlayer1.contains(binding.btn8.id)
        ) Player.PLAYER_1
        else if (cellsSelectedByPlayer2.contains(binding.btn2.id)
            && cellsSelectedByPlayer2.contains(binding.btn5.id)
            && cellsSelectedByPlayer2.contains(binding.btn8.id)
        ) Player.PLAYER_2
        else if (cellsSelectedByPlayer1.contains(binding.btn3.id)
            && cellsSelectedByPlayer1.contains(binding.btn6.id)
            && cellsSelectedByPlayer1.contains(binding.btn9.id)
        ) Player.PLAYER_1
        else if (cellsSelectedByPlayer2.contains(binding.btn3.id)
            && cellsSelectedByPlayer2.contains(binding.btn6.id)
            && cellsSelectedByPlayer2.contains(binding.btn9.id)
        ) Player.PLAYER_2
        else if (cellsSelectedByPlayer1.contains(binding.btn1.id)
            && cellsSelectedByPlayer1.contains(binding.btn5.id)
            && cellsSelectedByPlayer1.contains(binding.btn9.id)
        ) Player.PLAYER_1
        else if (cellsSelectedByPlayer2.contains(binding.btn1.id)
            && cellsSelectedByPlayer2.contains(binding.btn5.id)
            && cellsSelectedByPlayer2.contains(binding.btn9.id)
        ) Player.PLAYER_2
        else if (cellsSelectedByPlayer1.contains(binding.btn3.id)
            && cellsSelectedByPlayer1.contains(binding.btn5.id)
            && cellsSelectedByPlayer1.contains(binding.btn7.id)
        ) Player.PLAYER_1
        else if (cellsSelectedByPlayer2.contains(binding.btn3.id)
            && cellsSelectedByPlayer2.contains(binding.btn5.id)
            && cellsSelectedByPlayer2.contains(binding.btn7.id)
        ) Player.PLAYER_2
        else if ((cellsSelectedByPlayer1.size == 5 && cellsSelectedByPlayer2.size == 4)
            || (cellsSelectedByPlayer1.size == 4 && cellsSelectedByPlayer2.size == 5)
        ) 0
        else Any()

        when (winner) {
            Player.PLAYER_1 -> {
                Toast.makeText(this, "Player 1 wins the game", LENGTH_LONG)
                    .show()
                restartGame()
            }
            Player.PLAYER_2 -> {
                Toast.makeText(this, "Player 2 wins the game", LENGTH_LONG)
                    .show()
                restartGame()
            }
            0 -> {
                Toast.makeText(this, "It's a draw", LENGTH_LONG)
                    .show()
                restartGame()
            }
        }
    }

    private fun restartGame() {
        activePlayer = Player.PLAYER_1
        cellsSelectedByPlayer1.clear()
        cellsSelectedByPlayer2.clear()

        clearButton(binding.btn1)
        clearButton(binding.btn2)
        clearButton(binding.btn3)
        clearButton(binding.btn4)
        clearButton(binding.btn5)
        clearButton(binding.btn6)
        clearButton(binding.btn7)
        clearButton(binding.btn8)
        clearButton(binding.btn9)
    }

    private fun clearButton(button: Button) {
        button.text = ""
        button.backgroundTintList = getColorStateList(R.color.white)
        button.isEnabled = true
    }


    /*    private lateinit var binding: ActivityMainBinding
        private lateinit var gridLayout: ScalableGridLayout

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            gridLayout = findViewById(R.id.gridLayout)

            generateButtons()
        }

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
        }*/
}