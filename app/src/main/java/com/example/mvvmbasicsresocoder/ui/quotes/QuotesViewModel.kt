package com.example.mvvmbasicsresocoder.ui.quotes

import androidx.lifecycle.ViewModel
import com.example.mvvmbasicsresocoder.data.Quote
import com.example.mvvmbasicsresocoder.data.QuoteRepository

class QuotesViewModel(private val quoteRepository: QuoteRepository): ViewModel() {

    fun getQuotes() = quoteRepository.getQuotes()

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)

}