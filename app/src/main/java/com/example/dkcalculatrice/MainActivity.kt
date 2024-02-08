package com.example.dkcalculatrice

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var nbr1:String=""
        var nbr2:String=""
        var operation:String=""
        var currentText:String=""


        //widgets
        val ecran = findViewById<EditText>(R.id.ecran)
        // Désactiver le clavier logiciel pour le champ de texte
        ecran.showSoftInputOnFocus = false


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





        // Fonction egale
        fun egale() {
            if (nbr1.isNotEmpty() && nbr2.isNotEmpty() && operation.isNotEmpty()) {
                var res: Int = when (operation) {
                    "+" -> nbr1.toInt() + nbr2.toInt()
                    "-" -> nbr1.toInt() - nbr2.toInt()
                    "*" -> nbr1.toInt() * nbr2.toInt()
                    "%" -> nbr1.toInt() % nbr2.toInt()
                    "/" -> {
                        if (nbr2.toInt() != 0) {
                            nbr1.toInt() / nbr2.toInt()
                        } else {
                            // Division par zéro
                            ecran.setText("Erreur")
                            nbr1=""
                            nbr2 = ""
                            operation = ""
                            currentText = ""
                            return
                        }
                    }
                    else -> 0
                }

                // Afficher le résultat sur l'écran
                ecran.setText(res.toString())

                // Copier le résultat dans le presse-papiers
                val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("Résultat du calcul", res.toString())
                clipboardManager.setPrimaryClip(clipData)

                // Mettre à jour les valeurs de nbr1, nbr2 et operation
                nbr1 = res.toString()
                nbr2 = ""
                operation = ""
            }
        }



        // Modifier l'ecran
        fun ecrire(value: String) {

            if (operation==""){
                nbr1=currentText+value
                ecran.setText(nbr1)


            }else{

                nbr2+=value
                ecran.setText(nbr1+operation+nbr2)

            }
        }







        fun operation(op: String){

            //nbr1=0, currentText=content1 , nbr2=0, op=""

            if (nbr1!=""){
                if (nbr2!="" &&operation!=""){
                    // nbr1=content1, currentText=content1 , nbr2=content2, op="op"
                    //calculer first op et revenir à la ligne
                    egale()
                    operation=op
                    ecran.setText(nbr1+operation)
                }
                else{
                    operation=op
                    ecran.setText(nbr1+op)

                }

            }
            if (nbr1=="" && op=="-"){
                currentText=op
                ecran.setText(currentText)
            }






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
      //  point.setOnClickListener { ecrire(".") }

// listes operations
        plus.setOnClickListener { operation("+") }
        moin.setOnClickListener { operation("-") }
        multi.setOnClickListener { operation("*") }
        div.setOnClickListener { operation("/") }
        modulo.setOnClickListener { operation("%") }






// boutton egale
        egale.setOnClickListener {
            egale()
        }





// boutton clear
        c.setOnClickListener {
            ecran.setText("")
            nbr1=""
            nbr2=""
            currentText=""
            operation=""
        }

// boutton del
        del.setOnClickListener {
            //cas 1: nbr1 nbr2 et operation
            //cas2 nbr1 et operaation
            //cas3 nbr1

            if (nbr1!=""){

                if (operation!=""){

                    if (nbr2!=""){
                        //cas1
                        nbr2=nbr2.substring(0, nbr2.length - 1)
                    }else{

                        //cas2
                        operation=""

                    }
                }

                else{
                    //Cas3
                    nbr1=nbr1.substring(0, nbr1.length - 1)

                }
            }


            val currentText = ecran.text.toString()
            if (currentText.isNotEmpty()) {
                ecran.setText(currentText.substring(0, currentText.length - 1))
            }
        }

        plusmoin.setOnClickListener {
if (nbr1!="" && operation==""){

    nbr1=(nbr1.toInt()*(-1)).toString()
    ecran.setText("$nbr1")

}

            if (nbr2!=""){
    nbr2=(nbr2.toInt()*(-1)).toString()
    ecran.setText("$nbr1 $operation  $nbr2 ")

}

        }




    }


}