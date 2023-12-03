package com.example.maligiganci

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView


class MemoryEasyGame : AppCompatActivity() {

    private var point = 0
    private var numOn = 1
    private var currentCardSet = 1
    private lateinit var c1: TextView
    private lateinit var c2: TextView
    private lateinit var card1: TextView
    private lateinit var card2: TextView
    private lateinit var card3: TextView
    private lateinit var card4: TextView
    private lateinit var card5: TextView
    private lateinit var card6: TextView
    private lateinit var card7: TextView
    private lateinit var card8: TextView
    private lateinit var card9: TextView
    private lateinit var card10: TextView
    private lateinit var card11: TextView
    private lateinit var card12: TextView
    private lateinit var card13: TextView
    private lateinit var card14: TextView
    private lateinit var card15: TextView
    private lateinit var card16: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_easy_game)

        // Initialize the card views
        card1 = findViewById(R.id.Card1)
        card2 = findViewById(R.id.Card2)
        card3 = findViewById(R.id.Card3)
        card4 = findViewById(R.id.Card4)
        card5 = findViewById(R.id.Card5)
        card6 = findViewById(R.id.Card6)
        card7 = findViewById(R.id.Card7)
        card8 = findViewById(R.id.Card8)
        card9 = findViewById(R.id.Card9)
        card10 = findViewById(R.id.Card10)
        card11 = findViewById(R.id.Card11)
        card12 = findViewById(R.id.Card12)
        card13 = findViewById(R.id.Card13)
        card14 = findViewById(R.id.Card14)
        card15 = findViewById(R.id.Card15)
        card16 = findViewById(R.id.Card16)

        val resetButton = findViewById<View>(R.id.reset)
        resetButton.setOnClickListener {
            resetGame()
        }
        val changeViewButton = findViewById<View>(R.id.changeViewButton)
        changeViewButton.setOnClickListener {
            changeCardView()
        }
        Game()
    }

    private fun Game() {
        val pointsTextView = findViewById<TextView>(R.id.points)
        val cards = arrayOf(
            card1, card2, card3, card4, card5, card6, card7, card8,
            card9, card10, card11, card12, card13, card14, card15, card16
        )

        fun solution() {
            card1.text = "1"; card15.text = "1"
            card2.text = "2"; card14.text = "2"
            card3.text = "3"; card12.text = "3"
            card4.text = "4"; card13.text = "4"
            card5.text = "5"; card16.text = "5"
            card6.text = "6"; card10.text = "6"
            card7.text = "7"; card11.text = "7"
            card8.text = "8"; card9.text = "8"
        }

        solution()

        fun check() {
            if (c1.text == c2.text) {
                c1.text = ""
                c2.text = ""
                point++
                pointsTextView.text = "Punkty: $point"

                if (point == 8) {
                        // Wygrana - wszystkie pary zostały odgadnięte
                        showResultDialog("Wygrana", "Gratulacje! Udało Ci się odgadnąć wszystkie pary!")
                    }

                if (point == 8) {
                    // Wygrana - wszystkie pary zostały odgadnięte
                    showResultDialog("Wygrana", "Gratulacje! Udało Ci się odgadnąć wszystkie pary!")
                }
            } else {
                Handler().postDelayed({
                    if (currentCardSet == 1){
                        c1.setBackgroundResource(R.drawable.camel)
                        c2.setBackgroundResource(R.drawable.camel)
                    }
                    else{
                        c1.setBackgroundResource(R.drawable.fruit)
                        c2.setBackgroundResource(R.drawable.fruit)
                    }
                    c1.isEnabled = true
                    c2.isEnabled = true
                }, 400)
            }
        }

        fun resetGame() {
            point = 0
            pointsTextView.text = "Points: $point"
            solution()
            for (card in cards) {
                if (currentCardSet == 1) {
                    card.setBackgroundResource(R.drawable.camel)
                }
                else{
                    card.setBackgroundResource(R.drawable.fruit)
                }
                card.isEnabled = true
            }
        }

        for (card in cards) {
            card.setOnClickListener {
                if (card.text != "") {
                    if (numOn == 1) {
                        numOn = 2
                        c1 = card
                        if (currentCardSet == 1) {
                            card.setBackgroundResource(getCardDrawable(card.text.toString()))
                        }
                        else{
                            card.setBackgroundResource(getCardDrawable2(card.text.toString()))
                        }
                    } else if (c1 != card) {
                        c2 = card
                        if (currentCardSet == 1) {
                            card.setBackgroundResource(getCardDrawable(card.text.toString()))
                        }
                        else{
                            card.setBackgroundResource(getCardDrawable2(card.text.toString()))
                        }
                        check()
                        numOn = 1
                    }
                }
            }
        }
    }

    private fun getCardDrawable(cardText: String): Int {
        return when (cardText) {
            "1" -> R.drawable.coala
            "2" -> R.drawable.bear
            "3" -> R.drawable.fox
            "4" -> R.drawable.lion
            "5" -> R.drawable.monkey
            "6" -> R.drawable.pancernik
            "7" -> R.drawable.panda
            "8" -> R.drawable.wolf
            else -> R.drawable.camel
        }
    }

    private fun getCardDrawable2(cardText: String): Int {
        return when (cardText) {
            "1" -> R.drawable.strawberry
            "2" -> R.drawable.apple
            "3" -> R.drawable.fish
            "4" -> R.drawable.mushroom
            "5" -> R.drawable.cherry
            "6" -> R.drawable.grapes
            "7" -> R.drawable.lemon
            "8" -> R.drawable.watermelon
            else -> R.drawable.fruit
        }
    }
    private fun showResultDialog(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Zagraj ponownie") { _, _ ->
                resetGame()
            }
            .setCancelable(false)
            .show()
    }
    fun changeCardView(){
        if (currentCardSet == 1){
            currentCardSet = 2
        }
        else {
            currentCardSet = 1
        }
        resetGame()
    }
    fun resetGame() {
        point = 0
        val pointsTextView = findViewById<TextView>(R.id.points)
        pointsTextView.text = "Punkty: $point"
        val cards = arrayOf(
            card1, card2, card3, card4, card5, card6, card7, card8,
            card9, card10, card11, card12, card13, card14, card15, card16
        )

        // Losowanie nowych wartości liczbowych do kart
        val numbers = mutableListOf("1", "2", "3", "4", "5", "6", "7", "8", "1", "2", "3", "4", "5", "6", "7", "8")
        numbers.shuffle()

        for (i in cards.indices) {
            val card = cards[i]
            card.text = numbers[i]
            if (currentCardSet == 1){
                card.setBackgroundResource(R.drawable.camel)
            }
            else {
                card.setBackgroundResource(R.drawable.fruit)
            }
            card.isEnabled = true
        }
    }
}