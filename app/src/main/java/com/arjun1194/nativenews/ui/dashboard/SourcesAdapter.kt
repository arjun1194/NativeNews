package com.arjun1194.nativenews.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.arjun1194.nativenews.R
import com.arjun1194.nativenews.data.model.Source
import com.arjun1194.nativenews.databinding.FragmentDashboardBinding
import com.arjun1194.nativenews.databinding.SourceItemBinding

class SourcesAdapter : RecyclerView.Adapter<SourcesAdapter.SourceViewHolder>() {

    private var items: List<Source> = ArrayList()

    fun setItems(list: List<Source>) {
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.source_item, parent, false)
        return SourceViewHolder(SourceItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class SourceViewHolder(private val binding: SourceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Source) {
            binding.source = item.apply {
                category = category.capitalize()
            }
            binding.button.setOnClickListener {
                val action =
                    DashboardFragmentDirections.actionNavigationDashboardToArticleDetail(item.url)
                it.findNavController().navigate(action)
            }
        }
    }

}