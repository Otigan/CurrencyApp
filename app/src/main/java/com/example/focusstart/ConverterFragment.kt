package com.example.focusstart

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.focusstart.databinding.FragmentConverterBinding


class ConverterFragment : Fragment(R.layout.fragment_converter) {

    private val args by navArgs<ConverterFragmentArgs>()
    var _binding: FragmentConverterBinding? = null
    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentConverterBinding.bind(view)

        binding.btnConvert.setOnClickListener {
            checkSum(binding.ammToConvert)

        }

    }


    fun checkSum(editText: EditText) {
        val sum = editText.text.toString()
        if (sum.isEmpty()) {
            Toast.makeText(context, "Введите сумму в рублях", Toast.LENGTH_SHORT).show()
        } else {
            val result = args.currency.Value * sum.toInt()
            binding.textViewConvResult.text = result.toString()
            editText.requestFocus()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}