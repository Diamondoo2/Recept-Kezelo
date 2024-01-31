package com.example.receptkezelo.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.receptkezelo.R
import com.example.receptkezelo.data.RendesItem
import com.example.receptkezelo.databinding.DialogNewListItemBinding

class NewItemDialogFragment(foodType: Int) : DialogFragment() {

    private val type = foodType
    interface NewItemDialogListener {
        fun onItemCreated(newItem: RendesItem)
    }

    private lateinit var listener: NewItemDialogListener

    private lateinit var binding: DialogNewListItemBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewItemDialogListener
            ?: throw RuntimeException("Activity must implement the NewShoppingItemDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogNewListItemBinding.inflate(LayoutInflater.from(context))

        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_item)
            .setView(binding.root)
            .setPositiveButton(R.string.button_ok) { dialogInterface, i ->
                if (isValid()) {
                    Log.d("ReceptKezelo", "Adatok mentve!")
                    listener.onItemCreated(getRendesItem())
                }
            }
            .setNegativeButton(R.string.button_cancel, null)
            .create()
    }

    private fun isValid() = binding.etName.text.isNotEmpty()

    private fun getRendesItem() = RendesItem(
        name = binding.etName.text.toString(),
        description = binding.etDescription.text.toString(),
        ingredients = binding.etIngredients.text.toString(),
        type = type
    )

    companion object {
        const val TAG = "NewItemDialogFragment"
    }
}