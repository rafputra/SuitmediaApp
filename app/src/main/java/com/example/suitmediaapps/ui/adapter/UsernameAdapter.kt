package com.example.suitmediaapps.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmediaapps.data.DataItem
import com.example.suitmediaapps.databinding.CardUsernameBinding
import com.example.suitmediaapps.ui.second_screen.SecondActivity

class UsernameAdapter(private var userList: List<DataItem>) : RecyclerView.Adapter<UsernameAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = CardUsernameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = userList.size

    class UserViewHolder(var binding: CardUsernameBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(story: DataItem) {
            Glide.with(itemView.context)
                .load(story.avatar)
                .into(binding.imgProfile)
            binding.tvFirstname.text = story.firstName
            binding.tvLastname.text = story.lastName
            binding.tvEmail.text = story.email

            itemView.setOnClickListener {
                val firstName = binding.tvFirstname.text.toString()
                val lastName = binding.tvLastname.text.toString()
                val fullName = "$firstName $lastName"

                if (fullName.isNotEmpty()) {
                    val intent = Intent(itemView.context, SecondActivity::class.java)
                    intent.putExtra("FULLNAME", fullName)
                    itemView.context.startActivity(intent)
                } else {
                    val intent = Intent(itemView.context, SecondActivity::class.java)
                    intent.putExtra(SecondActivity.USER_ID, story.id)

                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            itemView.context as Activity,
                            Pair(binding.imgProfile, "photo"),
                            Pair(binding.tvFirstname, "name")
                        )

                    itemView.context.startActivity(intent, optionsCompat.toBundle())
                }
            }
        }
    }
}