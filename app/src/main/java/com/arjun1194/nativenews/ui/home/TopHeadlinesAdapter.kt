package com.arjun1194.nativenews.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.arjun1194.nativenews.R
import com.arjun1194.nativenews.data.model.Article
import com.arjun1194.nativenews.databinding.ArticleItemBinding
import com.arjun1194.nativenews.utils.load
import com.arjun1194.nativenews.utils.toDate
import com.bumptech.glide.Glide
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TopHeadlinesAdapter : RecyclerView.Adapter<TopHeadlinesAdapter.TopHeadlinesViewHolder>() {

    private var items: List<Article> = ArrayList()

    fun setItems(list: List<Article>) {
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHeadlinesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return TopHeadlinesViewHolder(ArticleItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: TopHeadlinesViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int = items.size

    inner class TopHeadlinesViewHolder(private val binding: ArticleItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Article, position: Int) {
            binding.data = item.apply {
                publishedAt = publishedAt.toDate()
            }
            item.urlToImage?.let { binding.articleImage.load(it) }
            binding.card.setOnClickListener{
                val action = HomeFragmentDirections.actionNavigationHomeToArticleDetail(item.url)
                it.findNavController().navigate(action)
            }
        }

    }
}


