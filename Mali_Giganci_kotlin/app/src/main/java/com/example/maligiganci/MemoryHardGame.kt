package com.example.maligiganci

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase


class MemoryHardGame : AppCompatActivity() {
    private var point = 0
    private var numberOfGame = 0
    private var numOn = 1
    private var buttonCount = 0
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
        setContentView(R.layout.activity_memory_hard_game)

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
                val databaseReference = FirebaseDatabase.getInstance().getReference("blockBaby/Memory/Hard")
                databaseReference.child("point").setValue(point)
            } else {
                Handler().postDelayed({
                    c1.setBackgroundResource(R.drawable.x)
                    c2.setBackgroundResource(R.drawable.x)
                    c1.isEnabled = true
                    c2.isEnabled = true
                }, 400)
                Handler().postDelayed({
                    c1.text = ""
                    c2.text = ""                }, 400)

            }
        }

        fun resetGame() {
            point = 0
            pointsTextView.text = "Points: $point"
            solution()
            for (card in cards) {
                card.setBackgroundResource(R.drawable.question)
                card.isEnabled = true
            }
        }

        for (card in cards) {
            card.setOnClickListener {
                if (card.text != "") {
                    if (numOn == 1) {
                        numOn = 2
                        c1 = card
                        card.setBackgroundResource(getCardDrawable(card.text.toString()))
                    } else if (c1 != card) {
                        c2 = card
                        card.setBackgroundResource(getCardDrawable(card.text.toString()))
                        check()
                        numOn = 1
                    }

                    // Zwiększanie wartości zmiennej buttonCount o 1
                    buttonCount++
                    // Sprawdzenie, czy buttonCount wynosi 16
                    if (buttonCount == 16) {
                        showPointsDialog()
                    }
                }
            }
        }
    }

    private fun getCardDrawable(cardText: String): Int {
        return when (cardText) {
            "1" -> R.drawable.usa
            "2" -> R.drawable.canada
            "3" -> R.drawable.china
            "4" -> R.drawable.indie
            "5" -> R.drawable.italy
            "6" -> R.drawable.japan
            "7" -> R.drawable.spain
            "8" -> R.drawable.anglia
            else -> R.drawable.question
        }
    }

    fun resetGame() {
        point = 0
        buttonCount =0
        numberOfGame += 1
        val databaseReference = FirebaseDatabase.getInstance().getReference("blockBaby/Memory/Hard")
        databaseReference.child("numberOfGames").setValue(numberOfGame)
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
            card.setBackgroundResource(R.drawable.question)
            card.isEnabled = true
        }
    }
    fun getButtonCount(): Int {
        return buttonCount
    }
    private fun showPointsDialog() {
        val points = point
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Uzyskane punkty")
            .setMessage("Ilość zdobytych punktów: $points")
            .setPositiveButton("Restartuj grę", DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
                resetGame()
            })
            .create()
            .show()
    }

}