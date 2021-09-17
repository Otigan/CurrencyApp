package com.example.focusstart.features.converter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
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

        binding.textViewConvResult.text = savedInstanceState?.getString("CONVERTED")
        binding.currencyTo.text = args.currency.CharCode
        val rate = getString(
            R.string.converter_rate,
            args.currency.Nominal,
            args.currency.CharCode,
            args.currency.Value
        )
        binding.currencyRate.text = rate


        binding.textViewSum.editText?.doAfterTextChanged {
            val sum = binding.textViewSum.editText?.text.toString().trim()
            if (sum.isNotEmpty()) {
                var prom = args.currency.Value.div(args.currency.Nominal)
                prom = 1.0 / prom
                val result = prom.times(sum.toLong())
                val dec = DecimalFormat("#,###.##")
                val res = dec.format(result)
                binding.textViewConvResult.text = res
            } else {
                binding.textViewConvResult.text = ""
            }
        }
    }


    private fun closeSoftKeyboard(context: Context, v: View) {
        val iMm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        iMm.hideSoftInputFromWindow(v.windowToken, 0)
        v.clearFocus()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("CONVERTED", binding.textViewConvResult.text.toString())
    }

    private fun checkSum(editText: EditText): Boolean {
        val sum = editText.text.toString()
        return when {
            sum.isEmpty() -> {
                Toast.makeText(context, "Введите сумму в рублях", Toast.LENGTH_SHORT).show()
                false
            }
            sum.length > 19 -> {
                Toast.makeText(context, "Введите сумму меньше", Toast.LENGTH_SHORT).show()
                false
            }
            else -> {
                true
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}