package com.example.focusstart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.focusstart.databinding.FragmentCurrencyBinding
import com.example.focusstart.model.Currency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyFragment() : Fragment(R.layout.fragment_currency),
    CurrencyAdapter.OnItemClickListener {

    private val currencyViewModel by viewModels<CurrencyViewModel>()
    private var _binding: FragmentCurrencyBinding? = null
    private val binding get() = _binding!!
    private val adapter = CurrencyAdapter(this)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCurrencyBinding.bind(view)

        binding.apply {
            recyclerViewCurrency.apply {
                setHasFixedSize(true)
                adapter = this@CurrencyFragment.adapter

            }
        }
        currencyViewModel.currencyRate.observe(viewLifecycleOwner, {
            adapter.setCurrencyList(it)
        })

        currencyViewModel.getRate()

    }

    override fun onItemClick(item: Currency) {
        val action = CurrencyFragmentDirections.actionCurrencyFragmentToConverterFragment(item)
        findNavController().navigate(action)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}