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
import android.util.Patterns

// Remove unused imports since on real project those can lead to a failure of the Pull request
class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    // this should work... instead of calling it everytime you need
//    private val sharedPreferences by lazy {
//        baseContext.getSharedPreferences("MY_SHARED_PREFS", MODE_PRIVATE)
//    }

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
            // shared preferences can be initialized with by lazy at the top of the class so you dont need to call it everytime
        val sharedPreferences = baseContext.getSharedPreferences("MY_SHARED_PREFS", MODE_PRIVATE)
        val editor = sharedPreferences.edit().apply {

            // remember the key should be how you are going to find the value or the object in shared preferences
            putString(binding.editTextEmail.text.toString(), "Email")
        }.apply()
    }
    }
    private fun isValidPassword(password: String?): Boolean {
        // this can be moved to companion object since it is constant
        // val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{4,}$"

        val pattern: Pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher: Matcher = pattern.matcher(password)

        return matcher.matches()
    }

    // I think here is an error when trying to validate the email...
    // you should be checking the EMAIL_PATTERN no PASSWORD_PATTERN
    fun isValidEmail(password2: String?): Boolean {
        // this pattern can be moved to companion object
        // val PASSWORD_PATTERN2 = "^(?=.*[@])(?=.*[.]).{4,}$"

        // you have these local variable that can be initialized at the same time
        val pattern: Pattern = Pattern.compile(PASSWORD_PATTERN2)
        val matcher2: Matcher = pattern.matcher(password2)

        // you can use the Patterns.EMAIL_ADDRESS to match the email
        // This class is provided by android to do email validations
        return Patterns.EMAIL_ADDRESS.matcher(password2).matches()
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

                    // here remember to call .show() in order to show the toast
                    Toast.makeText(
                        applicationContext,
                        "Password does not meet conditions",
                        Toast.LENGTH_SHORT
                    ).show()

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

    // nice approach for the text watcher
    // in kotlin we have these 'object' when we want to have the implementation of an interface
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

                // here we usually return null value as default instead of using "NOT_EXIST"
                // it is easier to check for null and makes more sense for a no existing email
                val em_mail = sharedPreferences.getString(binding.editTextEmail.text.toString(),"NOT_EXIST")


                // nice approach to
                if (em_mail == "NOT_EXIST") {
                    CLK = 1

                } else {
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
        const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{4,}$"
        const val PASSWORD_PATTERN2 = "^(?=.*[@])(?=.*[.]).{4,}$"
        const val CLEAR_TEXT = ""
        var CLK = 0
    }
}