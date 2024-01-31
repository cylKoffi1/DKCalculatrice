package com.example.dkcalculatrice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    lateinit var etDisplay: EditText
    private var lastNumeric = false
    private var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etDisplay = findViewById(R.id.etDisplay)

        // Numbers
        val btnNumbers = listOf(R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine)
        btnNumbers.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                onDigitClick((it as Button).text.toString())
            }
        }

        // Operations
        val btnOperations = listOf(R.id.btnPlus, R.id.btnModulo, R.id.btnMultiply, R.id.btnDivide, R.id.btnMinus)
        btnOperations.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                onOperationClick((it as Button).text.toString())
            }
        }

        findViewById<Button>(R.id.btnDot).setOnClickListener {
            onDotClick()
        }

        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            onEqualsClick()
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            onClearClick()
        }

        findViewById<Button>(R.id.btnNegate).setOnClickListener {
            onNegateClick()
        }

        findViewById<Button>(R.id.btnDel).setOnClickListener {
            onDeleteClick()
        }
    }

    private fun onDigitClick(digit: String) {
        if (lastNumeric) {
            etDisplay.append(digit)
        } else {
            etDisplay.text.clear()
            etDisplay.append(digit)
            lastNumeric = true
        }
        lastDot = false
    }

    private fun onOperationClick(operation: String) {
        if (lastNumeric && !lastDot) {
            etDisplay.append(operation)
            lastNumeric = false
        }
    }

    private fun onDotClick() {
        if (lastNumeric && !lastDot) {
            etDisplay.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    private fun onEqualsClick() {
        if (lastNumeric && !lastDot) {
            evaluateExpression()
            lastDot = false
        }
    }

    private fun onClearClick() {
        etDisplay.text.clear()
        lastNumeric = false
        lastDot = false
    }

    private fun onNegateClick() {
        val currentText = etDisplay.text.toString()
        if (currentText.isNotEmpty() && currentText.last().isDigit()) {
            etDisplay.text.clear()
            val negatedValue = if (currentText.startsWith("-")) {
                currentText.substring(1)
            } else {
                "-$currentText"
            }
            etDisplay.append(negatedValue)
        }
    }

    private fun onDeleteClick() {
        val currentText = etDisplay.text.toString()
        if (currentText.isNotEmpty()) {
            etDisplay.setText(currentText.dropLast(1))
        }
    }

    private fun evaluateExpression() {
        val text = etDisplay.text.toString()
        val expression = ExpressionBuilder(text).build()
        val result = expression.evaluate()
        etDisplay.text.clear()
        etDisplay.append(result.toString())
    }
}
