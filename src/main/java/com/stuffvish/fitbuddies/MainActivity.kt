package com.stuffvish.fitbuddies

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDateTV : TextView? = null
    private var resultTV : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateButton = findViewById<Button>(R.id.selectDateButton)
        selectedDateTV = findViewById(R.id.DateTextView)
        resultTV = findViewById(R.id.ResultTextView)

        dateButton.setOnClickListener {
            datePicker()
        }
    }

    private fun datePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val date = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, {
                _, selectedYear, selectedMonth, selectedDate ->

            Toast.makeText(this, "Date is $selectedDate Month is ${selectedMonth+1} and year is $selectedYear", Toast.LENGTH_LONG).show()

            val finalSelectedDate = "${selectedDate}-${selectedMonth+1}-${selectedYear}"

            selectedDateTV?.text = finalSelectedDate

            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(finalSelectedDate)

            theDate?.let {
                val selectedDateInMinute = theDate.time/60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                currentDate?.let{

                    val currentDateInMinute = currentDate.time/60000
                    resultTV?.text = (currentDateInMinute - selectedDateInMinute).toString()
                }
            }
        },
        year, month, date)

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}