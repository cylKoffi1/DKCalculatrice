package com.example.dkcalculatrice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    lateinit var etDisplay: EditText
    private var lastNumeric = false
    private var lastDot = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //widgets
        val ecran = findViewById<EditText>(R.id.ecran)

        val un = findViewById<Button>(R.id.btnun)
        val deux = findViewById<Button>(R.id.btndeux)
        val trois = findViewById<Button>(R.id.btntrois)
        val quatre = findViewById<Button>(R.id.btnqtre)
        val cinq = findViewById<Button>(R.id.btncinq)
        val six = findViewById<Button>(R.id.btnsix)
        val sept = findViewById<Button>(R.id.btnsept)
        val huit = findViewById<Button>(R.id.btnhuit)
        val neuf = findViewById<Button>(R.id.btnneuf)
        val zero = findViewById<Button>(R.id.btnzero)

        val c = findViewById<Button>(R.id.btnClear)
        val del = findViewById<Button>(R.id.btnDel)
        val modulo = findViewById<Button>(R.id.btnModulo)
        val div = findViewById<Button>(R.id.btndiv)

        val egale = findViewById<Button>(R.id.btnegale)
        val point = findViewById<Button>(R.id.btnpoint)
        val plusmoin = findViewById<Button>(R.id.plusmoin)
        val multi = findViewById<Button>(R.id.btnmulti)
        val moin = findViewById<Button>(R.id.btnmoins)
        val plus = findViewById<Button>(R.id.btnPlus)





        //Fonctions


        // Modifier l'ecran
        fun ecrire(value: String) {
            val currentText = ecran.text.toString()
            ecran.setText(currentText + value)
        }





// touches (0-9)
        un.setOnClickListener { ecrire("1") }
        deux.setOnClickListener { ecrire("2") }
        trois.setOnClickListener { ecrire("3") }
        quatre.setOnClickListener { ecrire("4") }
        cinq.setOnClickListener { ecrire("5") }
        six.setOnClickListener { ecrire("6") }
        sept.setOnClickListener { ecrire("7") }
        huit.setOnClickListener { ecrire("8") }
        neuf.setOnClickListener { ecrire("9") }
        zero.setOnClickListener { ecrire("0") }

// bouton point
        point.setOnClickListener { ecrire(".") }

// listes operations
        plus.setOnClickListener { ecrire("+") }
        moin.setOnClickListener { ecrire("-") }
        multi.setOnClickListener { ecrire("*") }
        div.setOnClickListener { ecrire("/") }
        modulo.setOnClickListener { ecrire("%") }

// boutton egale
        egale.setOnClickListener {
        }

// boutton clear
        c.setOnClickListener {
            ecran.setText("")
        }

// boutton del
        del.setOnClickListener {
            val currentText = ecran.text.toString()
            if (currentText.isNotEmpty()) {
                ecran.setText(currentText.substring(0, currentText.length - 1))
            }
        }

        plusmoin.setOnClickListener {


        }









    }


}