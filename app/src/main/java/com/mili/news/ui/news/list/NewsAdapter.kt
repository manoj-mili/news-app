package com.mili.news.ui.news.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mili.news.R
import com.mili.news.data.entities.NewsArticle
import com.mili.news.databinding.ViewNewsItemBinding
import timber.log.Timber

class NewsAdapter(val action: NewsClickInterface) : ListAdapter<NewsArticle, NewsAdapter.ViewHolder>(NewsDiffCallBack()) {

    class NewsDiffCallBack : DiffUtil.ItemCallback<NewsArticle>() {
        override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(private val binding: ViewNewsItemBinding, private val action: NewsClickInterface) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsArticle) {
            binding.news = news
            binding.action = action
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, action)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}