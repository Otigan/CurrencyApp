package com.example.focusstart.features.converter

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.focusstart.R
import com.example.focusstart.databinding.FragmentConverterBinding
import java.text.DecimalFormat


class ConverterFragment : Fragment(R.layout.fragment_converter) {

    private val args by navArgs<ConverterFragmentArgs>()
    var _binding: FragmentConverterBinding? = null
    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentConverterBinding.bind(view)

        binding.btnConvert.setOnClickListener {
            if (checkSum(binding.ammToConvert)) {
                val sum = binding.ammToConvert.text.toString()
                val result = args.currency.Value.times(sum.toLong())
                val dec = DecimalFormat("#,###.##")
                val res = dec.format(result)
                binding.textViewConvResult.text = res
                binding.ammToConvert.onEditorAction(EditorInfo.IME_ACTION_DONE)
            }

        }

    }


    fun checkSum(editText: EditText): Boolean {
        val sum = editText.text.toString()
        editText.requestFocus()
        return if (sum.isEmpty()) {
            Toast.makeText(context, "Введите сумму в рублях", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}