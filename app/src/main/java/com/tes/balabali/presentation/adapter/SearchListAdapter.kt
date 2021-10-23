package com.tes.balabali.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tes.balabali.R
import com.tes.balabali.appContext
import com.tes.balabali.databinding.ItemRepoBinding
import com.tes.balabali.domain.model.ItemModel

class SearchListAdapter(var dataList: ArrayList<ItemModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var listener: RecyclerViewClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> holder.bind(dataList[position])
        }

        holder.itemView.setOnClickListener {
            listener.onItemClicked(dataList[position])
        }
    }

    inner class ItemViewHolder(private val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemModel: ItemModel) {
            with(binding) {
                username.text = itemModel.owner.login
                title.text = itemModel.name
                description.text = itemModel.description
                starCount.text = itemModel.stargazers_count.toString()
                language.text = itemModel.language
                forksCount.text = itemModel.forks.toString()
                issue.text = "Issues ${itemModel.open_issues}"
                watcherCount.text = itemModel.watchers.toString()

                Glide.with(appContext)
                    .load(itemModel.owner.avatar_url)
                    .placeholder(R.drawable.circle)
                    .error(R.drawable.circle)
                    .apply(RequestOptions.circleCropTransform())
                    .into(avatar)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    interface RecyclerViewClickListener {
        fun onItemClicked(data: ItemModel)

    }

}
