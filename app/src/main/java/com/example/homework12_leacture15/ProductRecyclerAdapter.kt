package com.example.homework12_leacture15

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework12_leacture15.databinding.ItemForProductHomeBinding

class ProductRecyclerAdapter(private val listener: ItemClickListener) : ListAdapter<Product, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {

        const val Item_Type_1 = 1
        const val Item_Type_2 = 2

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ProductViewHolder(private val binding: ItemForProductHomeBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(product: Product){
            binding.img.setImageResource(product.imageList[0])
            binding.tvName.text = product.name

            binding.img.setOnClickListener{
                listener.onItemClicked(product)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return item.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == Item_Type_1){
            return ProductViewHolder(
                ItemForProductHomeBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
            )
        }else{

            return ProductViewHolder(
                ItemForProductHomeBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = getItem(position)
        if(holder is ProductViewHolder)holder.bind(product)
    }
}

interface ItemClickListener {
    fun onItemClicked(product:Product)
}