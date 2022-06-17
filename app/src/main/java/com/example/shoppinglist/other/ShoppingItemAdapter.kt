package com.example.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.databinding.ShoppingItemBinding
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) :
    RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemViewHolder>() {

    inner class ShoppingItemViewHolder(val binding: ShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ShoppingItemBinding.inflate(layoutInflater, parent, false)
        return ShoppingItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val curShoppingItem = items[position]

        holder.binding.apply {
            tvName.text = curShoppingItem.name
            tvAmount.text = curShoppingItem.amount.toString()

            ivDelete.setOnClickListener {
                viewModel.delete(curShoppingItem)
            }
            ivPlus.setOnClickListener {
                curShoppingItem.amount++
                viewModel.upsert(curShoppingItem)
            }
            ivMinus.setOnClickListener {
                if (curShoppingItem.amount > 0) {
                    curShoppingItem.amount--
                    viewModel.upsert(curShoppingItem)
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}