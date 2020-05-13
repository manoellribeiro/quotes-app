package com.example.mvvmbasicsresocoder.ui.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmbasicsresocoder.R
import com.example.mvvmbasicsresocoder.data.Quote
import com.example.mvvmbasicsresocoder.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_quote.*
import java.lang.StringBuilder

class QuoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote)
        initializeUI()
    }

    private fun initializeUI() {
        val factory = InjectorUtils.provideQuotesViewModelFactory()
        val viewModel = ViewModelProvider(this, factory).get(QuotesViewModel::class.java)

        viewModel.getQuotes().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }
            textView_quotes.text = stringBuilder.toString()
        })


        button_add_quote.setOnClickListener {
            if(validateEditText(editText_quote, "Write a quote") &&  validateEditText(editText_author, "Write the quote author name")){
            val quote = Quote(editText_quote.text.toString(), editText_author.text.toString())
            viewModel.addQuote(quote)
            editText_quote.setText("")
            editText_author.setText("")
            }
        }
    }

    private fun validateEditText(editText: EditText, errorMessage: String): Boolean {
        var textInput: String = editText.text.toString()
        if (textInput.isEmpty()) {
            editText.setError(errorMessage)
            return false
        } else {
            editText.setError(null);
            return true
        }
    }

}
