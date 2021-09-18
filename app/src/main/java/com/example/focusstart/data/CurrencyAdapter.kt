package com.example.focusstart.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.focusstart.R
import com.example.focusstart.databinding.ItemLayoutBinding
import com.example.focusstart.model.Currency

class CurrencyAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Currency, CurrencyAdapter.CurrencyViewHolder>(CurrencyComparator()) {


    inner class CurrencyViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    listener.onItemClick(item)
                }
            }
        }

        fun bind(currency: Currency) {
            binding.apply {
                currencyName.text = this.root.context.resources.getString(
                    R.string.currency_name,
                    currency.Nominal,
                    currency.Name
                )
                currencyValue.text = this.root.context.resources.getQuantityString(
                    R.plurals.russian_plural,
                    currency.Value.toInt(),
                    currency.Value
                )
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: Currency)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val item = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CurrencyViewHolder(item)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.bind(currentItem)
    }

    class CurrencyComparator : DiffUtil.ItemCallback<Currency>() {

        override fun areItemsTheSame(oldItem: Currency, newItem: Currency) =
            oldItem.ID == newItem.ID

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency) =
            oldItem == newItem
    }


}