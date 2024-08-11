package com.example.suitmediaapps.ui.third_screen

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmediaapps.api.ApiConfig
import com.example.suitmediaapps.data.ResponseUsername
import com.example.suitmediaapps.databinding.ActivityThirdBinding
import com.example.suitmediaapps.ui.adapter.UsernameAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var usernameAdapter: UsernameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setAllStory()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setAllStory() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvUsername.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUsername.addItemDecoration(itemDecoration)

        val call = ApiConfig.apiService.getUsers()
        call.enqueue(object : Callback<ResponseUsername> {
            override fun onResponse(call: Call<ResponseUsername>, response: Response<ResponseUsername>) {
                if (response.isSuccessful) {
                    response.body()?.data?.let { users ->
                        val nonNullUsers = users.filterNotNull()
                        usernameAdapter = UsernameAdapter(nonNullUsers)
                        binding.rvUsername.adapter = usernameAdapter
                    }
                } else {
                    Toast.makeText(this@ThirdActivity, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseUsername>, t: Throwable) {
                Toast.makeText(this@ThirdActivity, "Failed: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("ThirdActivity", "API call failed: ${t.message}", t)
            }
        })
    }
}