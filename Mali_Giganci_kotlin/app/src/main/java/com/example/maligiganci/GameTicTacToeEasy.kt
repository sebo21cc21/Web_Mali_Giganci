package com.example.maligiganci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.maligiganci.databinding.ActivityGameTicTacToeBinding

class GameTicTacToeEasy : AppCompatActivity() {

    enum class Turn {
        NOUGHT,
        CROSS
    }

    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

    private var crossesScore = 0
    private var noughtsScore = 0

    private var boardList = mutableListOf<Button>()

    private lateinit var binding: ActivityGameTicTacToeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameTicTacToeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()


        if (firstTurn == Turn.CROSS) {
            // Jeśli pierwszy ruch należy do komputera, wykonaj jego ruch po 1 sekundzie
            //dodanie do planszy znaku w randomowym miejscu
            Handler().postDelayed({
                val randomButton = getEmptyRandomButton()
                if (randomButton != null) {
                    addToBoard(randomButton)
                }
            }, 1000)
        }
    }

    private fun initBoard() {
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)
    }

    fun boardTapped(view: View) {
        if (view !is Button)
            return

        // Użytkownik wykonuje ruch
        addToBoard(view)

        //sprawdzenie warunku zwycięstwa
        if (checkForVictory(NOUGHT)) {
            noughtsScore++
            result("Wygrywasz!")
        } else if (fullBoard()) {
            result("Remis")
        } else {
            // Komputer wykonuje ruch po 1 sekundzie
            Handler().postDelayed({
                val randomButton = getEmptyRandomButton()
                if (randomButton != null) {
                    addToBoard(randomButton)

                    if (checkForVictory(CROSS)) {
                        crossesScore++
                        result("Komputer wygrywa!")
                    } else if (fullBoard()) {
                        result("Remis")
                    }
                }
            }, 1000)
        }
    }

    private fun checkForVictory(symbol: String): Boolean {
        // Sprawdzenie poziomych linii zwycięstwa
        if (match(binding.a1, symbol) && match(binding.a2, symbol) && match(binding.a3, symbol))
            return true
        if (match(binding.b1, symbol) && match(binding.b2, symbol) && match(binding.b3, symbol))
            return true
        if (match(binding.c1, symbol) && match(binding.c2, symbol) && match(binding.c3, symbol))
            return true

        // Sprawdzenie pionowych linii zwycięstwa
        if (match(binding.a1, symbol) && match(binding.b1, symbol) && match(binding.c1, symbol))
            return true
        if (match(binding.a2, symbol) && match(binding.b2, symbol) && match(binding.c2, symbol))
            return true
        if (match(binding.a3, symbol) && match(binding.b3, symbol) && match(binding.c3, symbol))
            return true

        // Sprawdzenie diagonalnych linii zwycięstwa
        if (match(binding.a1, symbol) && match(binding.b2, symbol) && match(binding.c3, symbol))
            return true
        if (match(binding.a3, symbol) && match(binding.b2, symbol) && match(binding.c1, symbol))
            return true

        return false
    }

    private fun match(button: Button, symbol: String): Boolean = button.text == symbol

    private fun result(title: String) {
        val message = "\nTy $noughtsScore\n\nKomputer $crossesScore"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Zagraj ponownie") { _, _ ->
                resetBoard()
            }
            .setCancelable(false)
            .show()
    }

    //zamiana kolejności zaczynania gry i reset planszy
    private fun resetBoard() {
        for (button in boardList) {
            button.text = ""
        }

        if (firstTurn == Turn.NOUGHT)
            firstTurn = Turn.CROSS
        else if (firstTurn == Turn.CROSS)
            firstTurn = Turn.NOUGHT

        currentTurn = firstTurn

        if (currentTurn == Turn.CROSS) {
            // Jeśli pierwszy ruch należy do komputera, wykonaj jego ruch po 1 sekundzie
            Handler().postDelayed({
                val randomButton = getEmptyRandomButton()
                if (randomButton != null) {
                    addToBoard(randomButton)
                }
            }, 1000)
        }
    }

    private fun fullBoard(): Boolean {
        for (button in boardList) {
            if (button.text == "")
                return false
        }
        return true
    }

    private fun addToBoard(button: Button) {
        if (button.text != "")
            return

        if (currentTurn == Turn.NOUGHT) {
            button.text = NOUGHT
            currentTurn = Turn.CROSS
            binding.turnTV.text = "Kolej komputera"
        } else if (currentTurn == Turn.CROSS) {
            button.text = CROSS
            currentTurn = Turn.NOUGHT
            binding.turnTV.text = "Twoja kolej"

        }
    }

    private fun getEmptyRandomButton(): Button? {
        val emptyButtons = boardList.filter { it.text.isEmpty() } //lista przycisków które są puste na planszy
        return emptyButtons.randomOrNull()
    }

    companion object {
        const val NOUGHT = "O"
        const val CROSS = "X"
    }
}
