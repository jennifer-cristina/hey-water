package com.senai.heywater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var edit_weight: EditText
    private lateinit var edit_age: EditText
    private lateinit var bt_calculate: Button
    private lateinit var txt_result_ml: TextView
    private lateinit var ic_reset_dice: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
    }
}