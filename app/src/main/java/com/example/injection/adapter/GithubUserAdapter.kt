package com.example.injection.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.injection.R
import com.example.injection.databinding.ItemUserBinding
import com.example.injection.model.User

class GithubUserAdapter: RecyclerView.Adapter<GithubItemViewHolder>() {

    private var listOf = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubItemViewHolder {

        LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false).apply {
            return GithubItemViewHolder(this)
        }

    }

    override fun onBindViewHolder(holder: GithubItemViewHolder, position: Int) {
        listOf[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = listOf.size

    fun refresh(newList: List<User>) {
        listOf.addAll(newList)
        notifyDataSetChanged()
    }

}

class GithubItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val binding = ItemUserBinding.bind(itemView)

    fun bind(model: User) {
        binding.textView.text = model.login
        Glide.with(binding.root)
            .load(model.avatar)
            .into(binding.imageView)
    }

}