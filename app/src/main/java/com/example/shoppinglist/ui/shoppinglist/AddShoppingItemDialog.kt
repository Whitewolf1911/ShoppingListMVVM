package com.example.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.databinding.DialogAddShoppingItemBinding

class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DialogAddShoppingItemBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        binding.apply {
            tvAdd.setOnClickListener {
                val name = etName.text.toString()
                val amount = etAmount.text.toString()

                if (name.isEmpty() || amount.isEmpty()) {
                    Toast.makeText(context, "Please enter all the information", Toast.LENGTH_LONG)
                        .show()
                    return@setOnClickListener
                }
                val item = ShoppingItem(name, amount.toInt())
                addDialogListener.onAddButtonClicked(item)
                dismiss()

            }
            tvCancel.setOnClickListener {
                cancel()
            }
        }

    }

}