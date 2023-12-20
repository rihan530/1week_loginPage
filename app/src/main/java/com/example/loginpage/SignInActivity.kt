package com.example.loginpage

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class SignInActivity : AppCompatActivity() {

    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val inputId = findViewById<EditText>(R.id.id_input)
        val inputPw = findViewById<EditText>(R.id.pw_input)
        val inputName = findViewById<TextView>(R.id.inputName)
        val loginButton = findViewById<Button>(R.id.login_button)
        val regiButton = findViewById<Button>(R.id.register_button)

        loginButton.setOnClickListener {

            if (inputId.text.isEmpty()) {
                Toast.makeText(this, "아이디를 확인해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (inputPw.text.isEmpty()) {
                Toast.makeText(this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val id : String = inputId.text.toString()
            val pw : String = inputPw.text.toString()
            val name : String = inputName.text.toString()

            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("password", pw)
            intent.putExtra("name", name)

            Log.d("SignIn id : ", id)
            Log.d("pw : ", pw)
            Log.d("name : ", name)
            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
            setResult(Activity.RESULT_OK, intent)
            startActivity(intent)
        }

        regiButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
//            startActivity(intent)
            resultLauncher.launch(intent)
        }

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    result ->
                if (result.resultCode == RESULT_OK) {
                    val id = result.data?.getStringExtra("id") ?: ""
                    val pw = result.data?.getStringExtra("pw") ?: ""
                    val name = result.data?.getStringExtra("name") ?: ""

                    inputId.setText(id)
                    inputPw.setText(pw)
                    inputName.setText(name)

                }
            }
    }
}