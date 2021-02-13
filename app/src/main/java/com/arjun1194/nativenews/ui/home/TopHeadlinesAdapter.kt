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
        return TopHeadlinesViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopHeadlinesViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int = items.size

    inner class TopHeadlinesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val image: ImageView = view.findViewById(R.id.articleImage)
        private val title: TextView = view.findViewById(R.id.articleTitle)
        private val publishedAt: TextView = view.findViewById(R.id.articlePublishedAt)
        private val author: TextView = view.findViewById(R.id.articleAuthor)
        fun bind(item: Article, position: Int) {
            Glide.with(view.context).load(item.urlToImage).into(image)
            title.text = item.title
            publishedAt.text = item.publishedAt.toDate()
            author.text = item.author
            view.rootView.setOnClickListener{
                val action = HomeFragmentDirections.actionNavigationHomeToArticleDetail(item.url)
                view.findNavController().navigate(action)
            }
        }

    }
}


