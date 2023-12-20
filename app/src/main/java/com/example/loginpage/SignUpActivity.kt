package com.example.loginpage

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts


class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val inputName = findViewById<EditText>(R.id.name_input)
        val inputId = findViewById<EditText>(R.id.id_input)
        val inputPw = findViewById<EditText>(R.id.pw_input)
        val regiButton = findViewById<Button>(R.id.register_button)

        regiButton.setOnClickListener {

            val name: String = inputName.text.toString()
            val id : String = inputId.text.toString()
            val pw : String = inputPw.text.toString()

            if (inputName.text.isEmpty() || inputId.text.isEmpty() || inputPw.text.isEmpty()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("id", id)
            intent.putExtra("pw", pw)

            Log.d("SignUp id : ", id)
            Log.d("pw : ", pw)
            Log.d("name : ", name)
            Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
//            startActivity(intent)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }


    }
}