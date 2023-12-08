package com.luanabarbosa.verity.githubapi.home.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.luanabarbosa.verity.githubapi.R
import com.luanabarbosa.verity.githubapi.databinding.GithubHomeUserItemBinding
import com.luanabarbosa.verity.githubapi.home.data.model.GithubUsersListModel
import com.luanabarbosa.verity.toolkit.extensions.load
import com.luanabarbosa.verity.toolkit.extensions.setValueOrDefault

class GithubHomeUserAdapter(
    private val userList: List<GithubUsersListModel>,
    private val context: Context
    ) : RecyclerView.Adapter<GithubHomeUserAdapter.ViewHolder>() {

    class ViewHolder(itemView: GithubHomeUserItemBinding) : RecyclerView.ViewHolder(itemView.root) {

        val username: TextView = itemView.userName
        val avatarImg: ImageView = itemView.userAvatar
        val userUrl: TextView = itemView.userUrl
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GithubHomeUserItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        with(user) {
            holder.avatarImg.load(avatarImg)
            holder.username.setValueOrDefault(context.getString(R.string.home_txt_name, username), context.getString(R.string.home_txt_empty_name))
            holder.userUrl.setValueOrDefault(context.getString(R.string.home_txt_link, htmlUrl), EMPTY)
        }
    }

    override fun getItemCount(): Int = userList.size

    companion object{
        const val EMPTY =""
    }
}
