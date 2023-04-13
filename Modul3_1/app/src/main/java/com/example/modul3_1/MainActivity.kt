package com.example.modul3_1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //inisialisasi
        val tmblPindah = findViewById<Button>(R.id.btnPindah)
        val tmblUpload = findViewById<Button>(R.id.btnUpload)

        //membuat listener ketika tombol diklik
        tmblPindah.setOnClickListener{
            Intent(this, TargetActivity::class.java).also{
                startActivity(it)
            }
        }
        //listener membuka galeri
        tmblUpload.setOnClickListener{
            Intent(Intent.ACTION_GET_CONTENT).also{
                it.type = "image/*"
                startActivityForResult(it, 0)
            }
        }
        }

    // mengambil data dari galeri
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 0){
            val uri = data?.data
            val img = findViewById<ImageView>(R.id.imgUpload)
            img.setImageURI(uri)
        }
    }
}
