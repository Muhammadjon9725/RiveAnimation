package com.example.riveanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import app.rive.runtime.kotlin.core.Rive
import com.example.riveanimation.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val stateMachineName = "State Machine 1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Rive.init(this)

        binding.email.setOnFocusChangeListener { v, b->
            if (b){
                binding.loginCharacter.controller.setBooleanState(stateMachineName,"Check",true)
            }else{
                binding.loginCharacter.controller.setBooleanState(stateMachineName,"Check",false)
            }

        }
        binding.password.setOnFocusChangeListener { v, b->
            if (b){
                binding.loginCharacter.controller.setBooleanState(stateMachineName,"hands_up",true)
            }else{
                binding.loginCharacter.controller.setBooleanState(stateMachineName,"hands_up",false)
            }

        }

        binding.email.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                try {
                    binding.loginCharacter.controller.setNumberState(stateMachineName,"Look",s!!.length.toFloat())
                }catch (e:Exception){}

            }
        })
        binding.button.setOnClickListener {

            binding.password.clearFocus()

            Handler(mainLooper).postDelayed({
                if (binding.email.text!!.isNotEmpty() && binding.password.text!!.isNotEmpty() &&
                    (binding.email.text.toString() == "qalesan9725@gmail.com" && binding.password.text.toString() =="12345678.uz")){
                    binding.loginCharacter.controller.fireState(stateMachineName,"success")
                    Toast.makeText(this, "Siz to'g'ri tanlov qildingiz", Toast.LENGTH_SHORT).show()
                    binding.email.text!!.clear()
                    binding.password.text!!.clear()
                }else{
                    binding.loginCharacter.controller.fireState(stateMachineName,"fail")
                    binding.email.text!!.clear()
                    binding.password.text!!.clear()
                    Toast.makeText(this, "Siz xato tanlov qildingiz", Toast.LENGTH_SHORT).show()
                }
            },2000)

        }





    }
}