package com.luanabarbosa.verity.githubapi.profile.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.luanabarbosa.verity.githubapi.databinding.GithubReposItemBinding
import com.luanabarbosa.verity.githubapi.profile.data.model.GithubProfileRepoListModel

class GithubReposProfileAdapter(
    private val userList: List<GithubProfileRepoListModel>,
) : RecyclerView.Adapter<GithubReposProfileAdapter.ViewHolder>() {

    class ViewHolder(itemView: GithubReposItemBinding) : RecyclerView.ViewHolder(itemView.root) {

        val nameRepo: TextView = itemView.nameRepo
        val descriptionRepo: TextView = itemView.descriptionRepo
        val forkCountRepo: TextView = itemView.forkCountRepo
        val startsCountRepo: TextView = itemView.startsCountRepo
        val languageRepo: TextView = itemView.languageRepo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GithubReposItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.nameRepo.text = user.nameRepo
        holder.descriptionRepo.text = user.description
        holder.forkCountRepo.text = user.forksCount
        holder.startsCountRepo.text = user.stargazersCount
        holder.languageRepo.text = user.language
    }

    override fun getItemCount(): Int = userList.size

}
