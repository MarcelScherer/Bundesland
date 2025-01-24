package com.example.bundesland

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText

class Landkarte : AppCompatActivity() {

    private var currentPair: Pair<String, Int>? = null
    private var oldPair: Pair<String, Int>? = null

    private lateinit var imageView: ImageView
    private lateinit var editText: TextInputEditText
    private lateinit var buttonLoesen: Button
    private lateinit var buttonBack: Button
    private lateinit var buttonErg : Button

    private val pairsofFlags = listOf(
        Pair("Bayern", R.drawable.bayern),
        Pair("Berlin", R.drawable.berlin),
        Pair("Brandenburg", R.drawable.brandenburg),
        Pair("Baden-Württemberg", R.drawable.bw),
        Pair("Hamburg", R.drawable.hamburg),
        Pair("Hessen", R.drawable.hessen),
        Pair("Mecklenburg-Vorpommern", R.drawable.mbvp),
        Pair("Niedersachsen", R.drawable.niedersachsen),
        Pair("Nordrhein-Westfalen", R.drawable.nrw),
        Pair("Rheinland-Pfalz", R.drawable.rlp),
        Pair("Sachsen", R.drawable.sachsen),
        Pair("Sachsen-Anhalt", R.drawable.sachsenanhalt),
        Pair("Thüringen", R.drawable.tueringen),
        // ... weitere Elemente
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_landkarte)

        // Views aus dem Layout einlesen
        imageView = findViewById(R.id.imageView)
        editText = findViewById(R.id.editText)
        buttonLoesen = findViewById(R.id.button1)
        buttonBack = findViewById(R.id.button2)
        buttonErg = findViewById(R.id.loesen)

        showNewPair()

        buttonLoesen.setOnClickListener {
            checkAnswer()
        }

        buttonErg.setOnClickListener {
            getAnswer()
        }

        buttonBack.setOnClickListener {
            finish()
        }

    }

    private fun showNewPair() {
        pairsofFlags.random().also { currentPair = it }
        if (currentPair == oldPair) {
            showNewPair()
        } else {
            oldPair = currentPair
        }

        currentPair?.second?.let { imageResId ->
            imageView.setImageDrawable(ContextCompat.getDrawable(this, imageResId))
        }
        editText.text?.clear()
    }

    private fun checkAnswer() {
        val userAnswer = editText.text.toString().trim().lowercase()
        if (userAnswer == currentPair?.first.toString().lowercase()) {
            Toast.makeText(this, "Richtig!", Toast.LENGTH_SHORT).show()
            showNewPair()
        } else {
            Toast.makeText(this, "Falsch!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getAnswer() {
        Toast.makeText(this, currentPair?.first.toString(), Toast.LENGTH_SHORT).show()
        showNewPair()
    }

}