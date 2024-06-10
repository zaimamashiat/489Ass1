package com.example.cse489ass1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var takaTextView: TextView
    private var currentTakaValue: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        takaTextView = findViewById(R.id.textView8)

        val numberButtonIds = intArrayOf(
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9,
            R.id.button10,
            R.id.button11
        )

        // Set click listeners for number buttons
        numberButtonIds.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener {
                val buttonText = (it as Button).text.toString()
                currentTakaValue += buttonText
                takaTextView.setText(currentTakaValue)
                Log.d("MainActivity", "Current Taka Value: $currentTakaValue")
                updating(divideAmount(Integer.valueOf(currentTakaValue)))
            }
        }

        // Set click listener for clear button
        findViewById<Button>(R.id.button4).setOnClickListener {
            currentTakaValue = ""
            takaTextView.text = "Taka: $currentTakaValue"
            Log.d("MainActivity", "Cleared Taka Value")
            updating(divideAmount(Integer.valueOf(currentTakaValue)))



        //
        }

    }
    private fun divideAmount(amount: Int): Map<Int, Int> {
        val denominations = arrayOf(500, 100, 50, 20, 10, 5, 2, 1)
        var remainingAmount = amount
        val counts = mutableMapOf<Int, Int>()

        for (denomination in denominations) {
            val count = remainingAmount / denomination
            if (count > 0) {
                counts[denomination] = count
                remainingAmount %= denomination
            }
        }

        return counts
    }
    private fun updating(count: Map<Int, Int>) {
        val allTextViews = arrayOf<TextView>(
            findViewById(R.id.textView9),
            findViewById(R.id.textView1),
            findViewById(R.id.textView7),
            findViewById(R.id.textView2),
            findViewById(R.id.textView6),
            findViewById(R.id.textView3),
            findViewById(R.id.textView4),
            findViewById(R.id.textView5)
        )

        val denominations = arrayOf(500, 100, 50, 20, 10, 5, 2, 1)
        val bin = arrayOf<String>("500:", "100:", "50:", "20:", "10:", "5:", "2:", "1:")

        for (i in denominations.indices) {
            val denomination = denominations[i]
            val count = count[denomination] ?: 0
            allTextViews[i].text = bin[i] + count.toString()
        }
    }


}




