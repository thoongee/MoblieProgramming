package com.example.hw2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.hw2.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("ITM","Sub: onCreate() called!")

        // get intent from caller activity
        // substring : Save telNum excluding 'tel:' included in front of data
        val telNum = intent.data.toString().substring(4,)

        // deliver value message to caller activity
        intent.putExtra("telNum","You can't call to $telNum !!!!")

        setResult(RESULT_OK,intent)

        finish() // finish SubActivity
    }
}