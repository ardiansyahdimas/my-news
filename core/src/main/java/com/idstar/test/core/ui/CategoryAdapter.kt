package com.idstar.test.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idstar.test.core.R
import com.idstar.test.core.databinding.RvCategoryBinding
import com.idstar.test.core.domain.model.CategoryModel

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ListViewHolder>() {
    private var listData = ArrayList<CategoryModel>()
    var onItemClick: ((CategoryModel) -> Unit)? = null


    fun setData(newListData: List<CategoryModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_category, parent, false))

    override fun getItemCount() = if (listData.size > 10) 10 else listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RvCategoryBinding.bind(itemView)
        fun bind(data: CategoryModel) {
            with(binding) {
                img.setImageResource(data.img)
                title.setText(data.name)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[absoluteAdapterPosition])
            }
        }
    }
}