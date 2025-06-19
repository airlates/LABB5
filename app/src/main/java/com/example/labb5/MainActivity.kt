package com.example.labb5

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var editTextAmount: EditText
    private lateinit var checkBox3Months: CheckBox
    private lateinit var checkBox6Months: CheckBox
    private lateinit var checkBox1Year: CheckBox
    private lateinit var buttonCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        editTextAmount = findViewById(R.id.editTextText)
        checkBox3Months = findViewById(R.id.checkBox)
        checkBox6Months = findViewById(R.id.checkBox2)
        checkBox1Year = findViewById(R.id.checkBox3)
        buttonCalculate = findViewById(R.id.button)

        buttonCalculate.setOnClickListener {
            calculateDeposit()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

    private fun calculateDeposit() {
        val amount = editTextAmount.text.toString().toDoubleOrNull()

        if (amount != null) {
            var totalInterest = 0.0

            if (checkBox3Months.isChecked) {
                totalInterest += 0.03
            }
            if (checkBox6Months.isChecked) {
                totalInterest += 0.05
            }
            if (checkBox1Year.isChecked) {
                totalInterest += 0.09
            }

            // Если ни один чекбокс не выбран, показываем сообщение об ошибке
            if (totalInterest == 0.0) {
                // Здесь можно добавить Toast или AlertDialog для уведомления пользователя
                return
            }

            val totalAmount = amount * (1 + totalInterest)
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("TOTAL_AMOUNT", totalAmount)
            }
            startActivity(intent)
        }
    }
}