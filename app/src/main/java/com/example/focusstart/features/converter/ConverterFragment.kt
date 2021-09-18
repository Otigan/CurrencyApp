package com.example.focusstart.features.converter

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.focusstart.R
import com.example.focusstart.databinding.FragmentConverterBinding
import java.text.DecimalFormat


class ConverterFragment : Fragment(R.layout.fragment_converter) {

    private val args by navArgs<ConverterFragmentArgs>()
    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentConverterBinding.bind(view)

        val rate = getString(
            R.string.converter_rate,
            1.div(args.currency.Value.div(args.currency.Nominal)),
            args.currency.CharCode
        )
        binding.apply {
            textViewConvResult.text = savedInstanceState?.getString("CONVERTED")
            currencyTo.text = args.currency.CharCode
            currencyRate.paintFlags = currencyRate.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            currencyRate.text = rate
        }

        binding.textFieldSum.editText?.doAfterTextChanged {
            val sum = binding.textFieldSum.editText?.text.toString()
            if (sum.isNotEmpty()) {
                var result = args.currency.Value.div(args.currency.Nominal)
                result = 1.div(result)
                result = result.times(sum.toDouble())
                val dec = DecimalFormat("#,###.###")
                val res: String = getString(
                    R.string.converter_result,
                    dec.format(result),
                    args.currency.CharCode
                )
                binding.textViewConvResult.text = res
            } else {
                binding.textViewConvResult.text = ""
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("CONVERTED", binding.textViewConvResult.text.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}