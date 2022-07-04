package com.senai.heywater

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.senai.heywater.model.CalculateDailyIntake
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var edit_weight: EditText
    private lateinit var edit_age: EditText
    private lateinit var bt_calculate: Button
    private lateinit var txt_result_ml: TextView
    private lateinit var ic_reset_dice: ImageView
    private lateinit var bt_reminder: Button
    private lateinit var bt_alarm: Button
    private lateinit var txt_hour: TextView
    private lateinit var txt_minute: TextView

    private lateinit var calculate_daily_intake: CalculateDailyIntake
    private var result_ml = 0.0

    lateinit var timePickerDialog: TimePickerDialog
    lateinit var calendar: Calendar
    var current_time = 0
    var current_minutes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        startComponent()
        calculate_daily_intake = CalculateDailyIntake()

        bt_calculate.setOnClickListener{
            if (edit_weight.text.toString().isEmpty()){
                Toast.makeText(this, R.string.toast_information_weight, Toast.LENGTH_SHORT ).show()

            }else if(edit_age.text.toString().isEmpty()){
                Toast.makeText(this, R.string.toast_information_age, Toast.LENGTH_SHORT ).show()
            }else{
                val weight = edit_weight.text.toString().toDouble()
                val age = edit_age.text.toString().toInt()
                calculate_daily_intake.CalculateTotalMl(weight, age)
                result_ml = calculate_daily_intake.ResultMl()
                val format = NumberFormat.getNumberInstance(Locale("pt", "BR"))
                format.isGroupingUsed = false
                txt_result_ml.text = format.format(result_ml) + " " + "ml"
            }
        }

        ic_reset_dice.setOnClickListener {

            val alert_dialog = AlertDialog.Builder(this)
            alert_dialog.setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_desc)
                .setPositiveButton("Ok", {dialogInterface, i ->
                    edit_weight.setText("")
                    edit_age.setText("")
                    txt_result_ml.text = ""
                })
            alert_dialog.setNegativeButton("Cancel", {dialogInterface, i ->

            })
            val dialog = alert_dialog.create()
            dialog.show()
        }

        bt_reminder.setOnClickListener{

            calendar = Calendar.getInstance()
            current_time = calendar.get(Calendar.HOUR_OF_DAY)
            current_minutes = calendar.get(Calendar.MINUTE)
            timePickerDialog = TimePickerDialog(this, {timePicker: TimePicker, hourOfDay: Int, minutes: Int ->
                txt_hour.text = String.format("%02d", hourOfDay)
                txt_minute.text = String.format("%02d", minutes)
            }, current_time, current_minutes, true)
            timePickerDialog.show()
        }

        bt_alarm.setOnClickListener{

            if(!txt_hour.text.toString().isEmpty() && !txt_minute.text.toString().isEmpty()){
                val intent = Intent(AlarmClock.ACTION_SET_ALARM)
                intent.putExtra(AlarmClock.EXTRA_HOUR, txt_hour.text.toString().toInt())
                intent.putExtra(AlarmClock.EXTRA_MINUTES, txt_minute.text.toString().toInt())
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, getString(R.string.alarm_message))
                startActivity(intent)

                if(intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
            }
        }

    }
    private fun startComponent(){
        edit_weight = findViewById(R.id.edit_weight)
        edit_age = findViewById(R.id.edit_age)
        bt_calculate = findViewById(R.id.bt_calculate)
        txt_result_ml = findViewById(R.id.txt_result_ml)
        ic_reset_dice = findViewById(R.id.ic_refresh)
        bt_reminder = findViewById(R.id.bt_define_reminder)
        bt_alarm = findViewById(R.id.bt_alarm)
        txt_hour = findViewById(R.id.txt_hour)
        txt_minute = findViewById(R.id.txt_minute)
    }

}