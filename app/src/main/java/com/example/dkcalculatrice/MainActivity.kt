package com.example.dkcalculatrice
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var etDisplay: EditText
    private var currentInput = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etDisplay = findViewById(R.id.etDisplay)

        // Restaurer l'état après la rotation de l'écran
        if (savedInstanceState != null) {
            currentInput = StringBuilder(savedInstanceState.getString("currentInput") ?: "")
            updateDisplay()
        }

        // Assigner des listeners aux boutons
        val buttonIds = arrayOf(
            R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour,
            R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine,
            R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide, R.id.btnModulo,
            R.id.btnNegate, R.id.btnEquals, R.id.btnClear, R.id.btnDel
        )

        for (buttonId in buttonIds) {
            findViewById<Button>(buttonId).setOnClickListener { onButtonClick(it) }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("currentInput", currentInput.toString())
    }

    private fun onButtonClick(view: View) {
        when (view.id) {
            R.id.btnEquals -> evaluateExpression()
            R.id.btnClear -> clearDisplay()
            R.id.btnDel -> deleteLastCharacter()
            R.id.btnNegate -> negateLastNumber()
            else -> appendToDisplay((view as Button).text.toString())
        }
    }

    private fun appendToDisplay(value: String) {
        currentInput.append(value)
        updateDisplay()
    }

    private fun updateDisplay() {
        etDisplay.text = currentInput.toString().toEditable()
    }

    private fun CharSequence.toEditable(): Editable =
        Editable.Factory.getInstance().newEditable(this)

    private fun clearDisplay() {
        currentInput.clear()
        updateDisplay()
    }

    private fun deleteLastCharacter() {
        if (currentInput.isNotEmpty()) {
            currentInput.deleteCharAt(currentInput.length - 1)
            updateDisplay()
        }
    }

    private fun negateLastNumber() {
        if (currentInput.isNotEmpty()) {
            val lastChar = currentInput[currentInput.length - 1]
            if (lastChar.isDigit()) {
                currentInput.deleteCharAt(currentInput.length - 1)
                currentInput.insert(0, "-$lastChar")
                updateDisplay()
            }
        }
    }

    private fun evaluateExpression() {
        try {
            val result = ExpressionBuilder(currentInput.toString())
                .build()
                .evaluate()

            clearDisplay()
            currentInput.append(result)
            updateDisplay()

        } catch (e: ArithmeticException) {
            // Gérer les erreurs d'expression invalide ici
            clearDisplay()
            currentInput.append("Error")
            updateDisplay()
        }
    }
}
