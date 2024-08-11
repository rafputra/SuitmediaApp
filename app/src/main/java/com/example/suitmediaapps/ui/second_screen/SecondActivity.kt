package com.example.suitmediaapps.ui.second_screen

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmediaapps.databinding.ActivitySecondBinding
import com.example.suitmediaapps.ui.first_screen.MainActivity
import com.example.suitmediaapps.ui.third_screen.ThirdActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnNext.setOnClickListener { chooseUser() }
        innitAction()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun chooseUser() {
        val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
    }

    private fun innitAction() {
        val name = intent.getStringExtra("USERNAME")
        val fullname = intent.getStringExtra("FULLNAME")

        if (name != null) {
            binding.tvName.text = name
        } else {

        }

        if (fullname != null) {
            binding.tvSelectedUname.text = fullname
        } else {

        }
    }

    companion object {
        const val USER_ID = "userId"
    }
}