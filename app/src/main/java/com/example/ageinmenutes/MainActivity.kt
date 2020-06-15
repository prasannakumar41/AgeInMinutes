package com.example.ageinmenutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)

        }
    }

    fun clickDatePicker(view : View){
        val myClaender = Calendar.getInstance()
        val year = myClaender.get(Calendar.YEAR)
        val month = myClaender.get(Calendar.MONTH)
        val day = myClaender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
                view,selectedYear,selectedMonth,selectedDayOfMonth ->
            Toast.makeText(this, "", Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            tvSelectedDate.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

            val theDate =  sdf.parse(selectedDate)

            val selctedDateInMinutes = theDate!!.time/60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time/60000

            val differenceInMinutes = currentDateInMinutes - selctedDateInMinutes
            val differenceInDays = differenceInMinutes/(60*24)
            val differenceInYears = differenceInDays/365

            tvSelectedDateInMinutes.setText(differenceInMinutes.toString()+" Total Minutes")
            tvSelectedDateInDays.setText(differenceInDays.toString()+" Total Days Count")
            tvSelectedDateInYears.setText(differenceInYears.toString()+" Years")
        },
            year,
            month,
            day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}