package com.example.hw2

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.Intent.createChooser
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hw2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // get data named 'telNum' & display toast with the data
    val requestLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        Toast.makeText(this, it.data?.getStringExtra("telNum"),Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("ITM","onCreated Called!")

        // When click the 'CALL' button,
        binding.btnSay.setOnClickListener{

            // save input text which received by user
            val input = binding.editText.text

            // save implict intent(ACTION_DIAL)
            val call = Intent(Intent.ACTION_DIAL).apply{
                // pass data to telephone app
                data = Uri.parse("tel:${input}")
                // put data named 'telNum'
                intent.putExtra("telNum","$input")

            }.run{
                // app-chooser dialog
                createChooser(this,"Which app to use?")
            }

            try{
                // start SubActivity which producing the result
                requestLauncher.launch(call)

            } catch (e: ActivityNotFoundException){
                // Define what your app should do if no activity can handle the intent.
                Log.d("ITM","no apps found!")
            }

        }
    }
}
