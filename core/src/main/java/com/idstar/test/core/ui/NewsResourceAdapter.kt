package com.idstar.test.core.ui

import android.annotation.SuppressLint
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.idstar.test.core.R
import com.idstar.test.core.databinding.RvNewsResourceBinding
import com.idstar.test.core.domain.model.NewsResourceModel
import java.util.Locale

class NewsResourceAdapter : RecyclerView.Adapter<NewsResourceAdapter.ListViewHolder>(), Filterable {
    private var listData = ArrayList<NewsResourceModel>()
    private var listFiltered = ArrayList<NewsResourceModel>()
    var onItemClick: ((NewsResourceModel) -> Unit)? = null


    fun setData(newListData: List<NewsResourceModel>?) {
        if (newListData == null) return
        listData.clear()
        listFiltered.clear()
        listData.addAll(newListData)
        listFiltered.addAll(listData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_news_resource, parent, false))

    override fun getItemCount() = if (listData.size > 10) 10 else listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RvNewsResourceBinding.bind(itemView)
        fun bind(data: NewsResourceModel) {
            with(binding) {
                tvName.text  = data.name
                tvCategory.text = data.category
                tvInfo.text = "Language: ${data.language} - Country: ${data.country}"
                tvDesc.text = data.description

                actionExpanded.setOnClickListener {
                    data.expandible = !data.expandible!!
                    if(data.expandible == true){
                        TransitionManager.beginDelayedTransition(binding.container, Fade())
                        expandedView.visibility = View.VISIBLE
                    } else {
                        TransitionManager.beginDelayedTransition(binding.container, Fade())
                        expandedView.visibility = View.GONE
                    }
                    val drawableId = if(data.expandible == true) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                    actionExpanded.setImageResource(drawableId)
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[absoluteAdapterPosition])
            }
        }
    }

    override fun getFilter(): Filter {
        return contactFilter
    }

    private val contactFilter: Filter = object : Filter() {
        @SuppressLint("DefaultLocale")
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<NewsResourceModel> = java.util.ArrayList()
            if (constraint.isEmpty()) {
                filteredList.clear()
                filteredList.addAll(listFiltered)
            } else {
                filteredList.clear()
                val filterPattern = constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                for (item in listFiltered) {
                    if (item.name?.lowercase()?.contains(filterPattern) == true) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            listData.clear()
            listData.addAll(results.values as java.util.ArrayList<NewsResourceModel>)
            notifyDataSetChanged()
        }
    }
}


