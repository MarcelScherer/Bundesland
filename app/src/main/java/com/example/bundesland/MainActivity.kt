package com.example.bundesland

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var buttonAnswer : Button
    private lateinit var buttonKarte : Button
    private lateinit var buttonKarteBayern : Button

    private val pairs = listOf(
        Pair("Baden-Württemberg", "Stuttgart"),
        Pair("Bayern", "München"),
        Pair("Berlin", "Berlin"),
        Pair("Brandenburg", "Potsdam"),
        Pair("Bremen", "Bremen"),
        Pair("Hamburg", "Hamburg"),
        Pair("Hessen", "Wiesbaden"),
        Pair("Mecklenburg-Vorpommern", "Schwerin"),
        Pair("Niedersachsen", "Hannover"),
        Pair("Nordrhein-Westfalen", "Düsseldorf"),
        Pair("Rheinland-Pfalz", "Mainz"),
        Pair("Saarland", "Saarbrücken"),
        Pair("Sachsen", "Dresden"),
        Pair("Sachsen-Anhalt", "Magdeburg"),
        Pair("Schleswig-Holstein", "Kiel"),
        Pair("Thüringen", "Erfurt"),
        Pair("Stuttgart", "Baden-Württemberg"),
        Pair("München", "Bayern"),
        Pair("Potsdam", "Brandenburg"),
        Pair("Wiesbaden", "Hessen"),
        Pair("Schwerin", "Mecklenburg-Vorpommern"),
        Pair("Hannover", "Niedersachsen"),
        Pair("Düsseldorf", "Nordrhein-Westfalen"),
        Pair("Mainz", "Rheinland-Pfalz"),
        Pair("Saarbrücken", "Saarland"),
        Pair("Dresden", "Sachsen"),
        Pair("Magdeburg", "Sachsen-Anhalt"),
        Pair("Kiel", "Schleswig-Holstein"),
        Pair("Erfurt", "Thüringen")
        // ... weitere Paare
    )

    private var currentPair: Pair<String, String>? = null
    private var oldPair: Pair<String, String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        buttonAnswer = findViewById(R.id.loesungButton)
        buttonKarte = findViewById(R.id.karteButton)
        buttonKarteBayern = findViewById(R.id.karte_bayern_Button)

        showNewPair()

        button.setOnClickListener {
            checkAnswer()
        }

        buttonAnswer.setOnClickListener {
            getAnswer()
        }

        buttonKarte.setOnClickListener {
            val intent = Intent(this, Landkarte::class.java)
            startActivity(intent)
        }


        buttonKarteBayern.setOnClickListener {
            val intent = Intent(this, Bayern::class.java)
            startActivity(intent)
        }


    }

    private fun showNewPair() {
        currentPair = pairs.random()
        if (currentPair == oldPair) {
            showNewPair()
        } else {
            oldPair = currentPair
        }
        textView.text = currentPair?.first
        editText.text.clear()
    }

    private fun checkAnswer() {
        val userAnswer = editText.text.toString().trim().lowercase()
        if (userAnswer == currentPair?.second.toString().lowercase()) {
            Toast.makeText(this, "Richtig!", Toast.LENGTH_SHORT).show()
            showNewPair()
        } else {
            Toast.makeText(this, "Falsch!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getAnswer() {
            Toast.makeText(this, currentPair?.second.toString(), Toast.LENGTH_SHORT).show()
            showNewPair()
    }
}