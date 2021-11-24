package com.example.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import android.widget.TextView
import com.example.loginapp.databinding.ActivityMain2Binding
import com.example.loginapp.databinding.ActivityMainBinding
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
    super.onResume()

        binding.editTextNumberPassword3.addTextChangedListener(textWatcher)
    }
    private fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }
    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            Log.d("Text_changed", "ITS LISTEed")
            if(binding.editTextNumberPassword3.text.length <8 && isValidPassword(binding.editTextNumberPassword3.text.toString() )){
                binding.imageView5.alpha = 1F


                while (binding.editTextNumberPassword3.text.toString() == binding.editTextNumberPassword4.text.toString()){

                }
            }
            else{
                Toast.makeText(applicationContext, "Password does not meet conditions", Toast.LENGTH_SHORT)
                Log.d("Text_Legnth", binding.editTextNumberPassword3.text.toString())
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            Log.d("Text_change", "ITS LISTENING")

        }
    }
    companion object{

    }
}