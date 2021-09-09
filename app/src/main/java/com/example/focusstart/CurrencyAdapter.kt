package com.example.focusstart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.focusstart.databinding.ItemLayoutBinding
import com.example.focusstart.model.Currency

class CurrencyAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    var currency = mutableListOf<Currency>()

    fun setCurrencyList(currency: List<Currency>) {
        this.currency = currency.toMutableList()
        notifyDataSetChanged()
    }

    inner class CurrencyViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = currency[position]
                    listener.onItemClick(item)
                }
            }
        }

        fun bind(currency: Currency) {
            binding.apply {
                currencyName.text = currency.Name
                currencyValue.text = currency.Value.toString()
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
        val currentItem = currency[position]

        holder.bind(currentItem)
    }

    override fun getItemCount() = currency.size
}