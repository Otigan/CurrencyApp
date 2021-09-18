package com.example.focusstart.features.rate

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.focusstart.R
import com.example.focusstart.data.CurrencyAdapter
import com.example.focusstart.databinding.FragmentCurrencyBinding
import com.example.focusstart.model.Currency
import com.example.focusstart.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyFragment : Fragment(R.layout.fragment_currency),
    CurrencyAdapter.OnItemClickListener {

    private val currencyViewModel by viewModels<CurrencyViewModel>()
    private var _binding: FragmentCurrencyBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CurrencyAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCurrencyBinding.bind(view)

        adapter = CurrencyAdapter(this)

        binding.apply {
            recyclerViewCurrency.apply {
                setHasFixedSize(true)
                this.adapter = this@CurrencyFragment.adapter
            }
            currencyViewModel.currencyRate.observe(viewLifecycleOwner) { result ->
                adapter.submitList(result.data)

                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                textViewError.text = result.error?.localizedMessage
            }
        }
        setHasOptionsMenu(true)
    }

    override fun onItemClick(item: Currency) {
        val action =
            CurrencyFragmentDirections.actionCurrencyFragmentToConverterFragment(item)
        findNavController().navigate(action)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.currency_rate_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_refresh -> {
                binding.apply {
                    currencyViewModel.currencyRate.observe(viewLifecycleOwner) { result ->
                        adapter.submitList(result.data)
                        recyclerViewCurrency.isVisible = result is Resource.Success
                        progressBar.isVisible =
                            result is Resource.Loading && result.data.isNullOrEmpty()
                        textViewError.isVisible =
                            result is Resource.Error && result.data.isNullOrEmpty()
                        textViewError.text = result.error?.localizedMessage

                        val check = result is Resource.Success

                        if (check) {
                            Toast.makeText(context, "Обновлений пока нет", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}