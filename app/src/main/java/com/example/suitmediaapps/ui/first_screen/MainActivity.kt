package com.example.suitmediaapps.ui.first_screen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmediaapps.databinding.ActivityMainBinding
import com.example.suitmediaapps.ui.second_screen.SecondActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener { nextButton() }
        binding.btnCheck.setOnClickListener { checkButton() }
        binding.imgProfile.setOnClickListener { startGallery() }
    }

    private fun nextButton() {
        val name = binding.etName.text.toString()
        if (name.isEmpty()) {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("USERNAME", name)
            startActivity(intent)
        }
    }

    private fun checkButton() {
        val inputText = binding.etPalindrome.text.toString()

        if (inputText.isEmpty()) {
            Toast.makeText(this, "Please type a word", Toast.LENGTH_SHORT).show()
        } else {
            val isPalindrome = isPalindrome(inputText)

            val message = if (isPalindrome) {
                "$inputText is a palindrome"
            } else {
                "$inputText is not a palindrome"
            }

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun isPalindrome(text: String): Boolean {
        val cleanedText = text.replace("\\s".toRegex(), "").toLowerCase()
        return cleanedText == cleanedText.reversed()
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "Empty Photos")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.imgProfile.setImageURI(it)
        }
    }
}