package com.kevin.simplecrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kevin.simplecrud.R
import com.kevin.simplecrud.Guru.GuruActivity
import com.kevin.simplecrud.Inventaris.InventarisActivity
import com.kevin.simplecrud.Pegawai.PegawaiActivity
import com.kevin.simplecrud.User.UserActivity

class BerandaActivity : AppCompatActivity() {

    private lateinit var btnStudent: Button
    private lateinit var btnPegawai: Button
    private lateinit var btnInventaris: Button
    private lateinit var btnGuru: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        supportActionBar?.hide()

        btnStudent = findViewById(R.id.btnUser)
        btnStudent.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }

        btnPegawai = findViewById(R.id.btnPegawai)
        btnPegawai.setOnClickListener {
            startActivity(Intent(this, PegawaiActivity::class.java))
        }

        btnInventaris = findViewById(R.id.btnInventaris)
        btnInventaris.setOnClickListener {
            startActivity(Intent(this, InventarisActivity::class.java))
        }

        btnGuru = findViewById(R.id.btnGuru)
        btnGuru.setOnClickListener {
            startActivity(Intent(this, GuruActivity::class.java))
        }
    }
}
