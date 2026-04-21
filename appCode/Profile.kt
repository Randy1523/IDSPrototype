package com.example.test

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Profile : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signi = findViewById<EditText>(R.id.signature)
        val profilepic: ImageView = findViewById(R.id.profpic)

//        val file = File("sig.txt")
//
//            if (!file.exists()) {
//                file.createNewFile()
//            }
//
//            val sig = file.readLines()
//
//            signi.setText(sig.toString())

            val launchGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
             result ->
                if(result.resultCode == RESULT_OK){
                   profilepic.setImageBitmap(
                    MediaStore.Images.Media.getBitmap(applicationContext.contentResolver, result.data!!.data)
                   )
                }
            }

            profilepic.setOnClickListener {
                val openGallery = Intent(Intent.ACTION_GET_CONTENT).setType("images/*")
                launchGallery.launch(openGallery)
            }
//
    }
//


//        fun updateSignature() {
//            val signi = findViewById<EditText>(R.id.signature)
//            val newSig: String = signi.text.toString()
//            val file = File("sig.txt")
//
//            if (!file.exists()) {
//                file.createNewFile()
//            }
//
//            file.writeText(newSig)
//
//        }
    }
