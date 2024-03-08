package ikhwan.hanif.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var inputField: EditText

    private var bilangan1: Double = 0.0
    private var bilangan2: Double = 0.0
    private var operator: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputField = findViewById(R.id.inputField)

        val buttons: List<Button> = listOf(
            findViewById(R.id.btn0), findViewById(R.id.btn1), findViewById(R.id.btn2),
            findViewById(R.id.btn3), findViewById(R.id.btn4), findViewById(R.id.btn5),
            findViewById(R.id.btn6), findViewById(R.id.btn7), findViewById(R.id.btn8),
            findViewById(R.id.btn9), findViewById(R.id.btnDot), findViewById(R.id.btnClear),
            findViewById(R.id.btnPlus), findViewById(R.id.btnMinus), findViewById(R.id.btnMultiply),
            findViewById(R.id.btnDivide), findViewById(R.id.btnPower), findViewById(R.id.btnModulus),
            findViewById(R.id.btnEquals)
        )

        for (button in buttons) {
            button.setOnClickListener { onButtonClick(it) }
        }
    }

    private fun onButtonClick(view: View) {
        when (view.id) {
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot -> appendInput(view.tag.toString())

            R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide,
            R.id.btnPower, R.id.btnModulus -> setOperator(view.tag.toString())

            R.id.btnClear -> clearInput()

            R.id.btnEquals -> calculateResult()
        }
    }

    private fun appendInput(value: String) {
        inputField.append(value)
    }

    private fun setOperator(op: String) {
        bilangan1 = inputField.text.toString().toDouble()
        operator = op
        clearInput()
    }

    private fun clearInput() {
        inputField.text.clear()
    }

    private fun calculateResult() {
        bilangan2 = inputField.text.toString().toDouble()
        val result = when (operator) {
            "+" -> penjumlahan(bilangan1, bilangan2)
            "-" -> pengurangan(bilangan1, bilangan2)
            "*" -> perkalian(bilangan1, bilangan2)
            "/" -> pembagian(bilangan1, bilangan2)
            "^" -> pangkat(bilangan1, bilangan2)
            "%" -> modulus(bilangan1, bilangan2)
            else -> throw IllegalArgumentException("Operator tidak valid.")
        }

        clearInput()
        inputField.setText(result.toString())
    }

    private fun penjumlahan(a: Double, b: Double): Double {
        return a + b
    }

    private fun pengurangan(a: Double, b: Double): Double {
        return a - b
    }

    private fun perkalian(a: Double, b: Double): Double {
        return a * b
    }

    private fun pembagian(a: Double, b: Double): Double {
        if (b != 0.0) {
            return a / b
        } else {
            throw IllegalArgumentException("Pembagian oleh nol tidak valid.")
        }
    }

    private fun pangkat(a: Double, b: Double): Double {
        return a.pow(b)
    }

    private fun modulus(a: Double, b: Double): Double {
        return a % b
    }
}
