package com.example.loginpage



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class HomeActivity : AppCompatActivity() {

    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tv_Id = findViewById<TextView>(R.id.tv_id)
        val tv_Name = findViewById<TextView>(R.id.tv_name)
        val tv_age = findViewById<TextView>(R.id.tv_age)
        val tv_mbti = findViewById<TextView>(R.id.tv_mbti)
        val endButton = findViewById<Button>(R.id.end_button)
        val profile_image = findViewById<ImageView>(R.id.profileImage)

        val id = intent.getStringExtra("id").toString()
        val pw = intent.getStringExtra("pw").toString()
        val name = intent.getStringExtra("name").toString()
        var age = (20..30).random().toString()
        var mbtiArray: Array<String> = (arrayOf("INFJ", "INFP", "ISFJ", "ISFP",
                                                "ISTP", "ISTJ", "INTP", "INTJ",
                                                "ENTP", "ESTJ", "ESTP", "ENFP",
                                                "ESFJ", "ENTJ", "ENFJ", "ESFP"))
        var mbtiRan = mbtiArray[Random.nextInt(15)]
        var imageArray = intArrayOf(
            R.drawable.profile_image_1,
            R.drawable.profile_image_2,
            R.drawable.profile_image_3,
            R.drawable.profile_image_4,
            R.drawable.profile_image_5
        )
        var imageRan = Random.nextInt(4)

        Log.d("Home id : ", id)
        Log.d("pw : ", pw)
        Log.d("name : ", name)
        tv_Id.setText(id)
        tv_Name.setText(name)
        tv_age.setText(age)
        tv_mbti.setText(mbtiRan)
//        profile_image.setImageDrawable(drawable)
        profile_image.setImageResource(imageArray[imageRan])

        endButton.setOnClickListener {
            finish()
        }

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    result ->
                if (result.resultCode == RESULT_OK) {
                    val id = result.data?.getStringExtra("id") ?: ""
                    val pw = result.data?.getStringExtra("pw") ?: ""
                    val name = result.data?.getStringExtra("name") ?: ""

                    tv_Id.setText(id)
                    tv_Name.setText(name)

                }
            }
    }
}