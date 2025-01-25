package com.example.bundesland

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class Bayern : AppCompatActivity() {

    private var currentPair: Pair<String, Int>? = null
    private var oldPair: Pair<String, Int>? = null

    private lateinit var imageView: ImageView
    private lateinit var editText: TextInputEditText
    private lateinit var buttonLoesen: Button
    private lateinit var buttonBack: Button
    private lateinit var buttonErg : Button

    private val pairsofFlags = listOf(
        Pair("Aschaffenburg", R.drawable.aschaffenburg),
        Pair("Würzburg", R.drawable.wuerzburg),
        Pair("Nürnberg", R.drawable.nuernberg),
        Pair("Regensburg", R.drawable.regensburg),
        Pair("München", R.drawable.muenchen),

        Pair("Main", R.drawable.main),
        Pair("Fränkische Saale", R.drawable.fraenkische_saale),
        Pair("Saale", R.drawable.saale),
        Pair("Eger", R.drawable.eger),
        Pair("Altmühl", R.drawable.altmuehl),
        Pair("Naab", R.drawable.naab),
        Pair("Regen", R.drawable.regen),
        Pair("Donau", R.drawable.donau),
        Pair("Iller", R.drawable.iller),
        Pair("Lech", R.drawable.lech),
        Pair("Isar", R.drawable.isar),
        Pair("Inn", R.drawable.inn),
        Pair("Salzach", R.drawable.salzach),
        Pair("Tauber", R.drawable.tauber),
        // ... weitere Elemente
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bayern)

        // Views aus dem Layout einlesen
        imageView = findViewById(R.id.imageViewBayern)
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