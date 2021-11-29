package com.example.loginapp

import android.R.attr.clickable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.loginapp.databinding.ActivityMain2Binding
import com.example.loginapp.databinding.ActivityMainBinding
import java.util.regex.Matcher
import java.util.regex.Pattern
import android.R.attr.name




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
        var click = 0
        binding.repeatPassword.setText(CLEAR_TEXT)
        binding.createPassword.setText(CLEAR_TEXT)
        binding.editTextEmail.setText(CLEAR_TEXT)


        binding.editTextEmail.addTextChangedListener(textWatcher2)
        binding.repeatPassword.addTextChangedListener(textWatcher)
        binding.imageView5.setOnClickListener(){
        val sharedPreferences = baseContext.getSharedPreferences("MY_SHARED_PREFS", MODE_PRIVATE)
        val editor = sharedPreferences.edit().apply(){
            putString(binding.editTextEmail.text.toString(), "Email")
        }.apply()
    }
    }
    private fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }
    fun isValidEmail(password2: String?): Boolean {
        val pattern: Pattern
        val matcher2: Matcher
        val PASSWORD_PATTERN2 = "^(?=.*[@])(?=.*[.]).{4,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN2)
        matcher2 = pattern.matcher(password2)
        return matcher2.matches()
    }
    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            Log.d("Text_changed", "ITS LISTEed")
                if (binding.repeatPassword.text.length >= 8 && isValidPassword(binding.repeatPassword.text.toString())) {
                    //binding.imageView5.alpha = .4F


                    if (binding.repeatPassword.text.toString() == binding.createPassword.text.toString()) {
                        Log.d("Text_Legnth", binding.repeatPassword.text.toString())
                        Log.d("Text_Legnth2", binding.createPassword.text.toString())

                        binding.imageView5.alpha = 1F
                        binding.CPG.visibility = View.VISIBLE
                        binding.RPG.visibility = View.VISIBLE
                        binding.RPR.visibility = View.INVISIBLE
                        binding.CPR.visibility = View.INVISIBLE
                        if(CLK == 1){
                            binding.imageView5.alpha =1F
                            binding.imageView5.isClickable = true
                        }
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Password does not meet conditions",
                        Toast.LENGTH_SHORT
                    )
                    binding.CPR.visibility = View.VISIBLE
                    binding.RPR.visibility = View.VISIBLE
                    binding.CPG.visibility = View.INVISIBLE
                    binding.RPG.visibility = View.INVISIBLE


                    binding.imageView5.alpha = .4F
                    Log.d("Text_Legnth", binding.repeatPassword.text.toString())
                }
            }



        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            Log.d("Text_change", "ITS LISTENING")

        }
    }
    private val textWatcher2 = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if(isValidEmail(binding.editTextEmail.text.toString())) {
                binding.EG.visibility = View.VISIBLE
                binding.ER.visibility = View.INVISIBLE
                binding.WarningBoarder.visibility = View.INVISIBLE
                binding.WarningTextEmail.visibility = View.INVISIBLE
                binding.redCross.visibility = View.INVISIBLE
                binding.WarningTextPass.visibility = View.INVISIBLE

                val sharedPreferences = baseContext.getSharedPreferences("MY_SHARED_PREFS", MODE_PRIVATE)
                val em_mail = sharedPreferences.getString(binding.editTextEmail.text.toString(),"NOT_EXIST")


                    if (em_mail == "NOT_EXIST") {
                        CLK = 1

                    }
                else{
                        binding.ER.visibility = View.VISIBLE
                        binding.EG.visibility = View.INVISIBLE
                        binding.WarningBoarder.visibility = View.VISIBLE
                        binding.WarningTextEmail.visibility = View.VISIBLE
                        binding.redCross.visibility = View.VISIBLE
                        binding.WarningTextPass.visibility = View.INVISIBLE
                    }

            }
            else{
                binding.ER.visibility = View.VISIBLE
                binding.EG.visibility = View.INVISIBLE
                binding.WarningBoarder.visibility = View.VISIBLE
                binding.WarningTextEmail.visibility = View.VISIBLE
                binding.redCross.visibility = View.VISIBLE
                binding.WarningTextPass.visibility = View.INVISIBLE
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }


    }


    companion object{
    const val CLEAR_TEXT = ""
        var CLK = 0
    }
}