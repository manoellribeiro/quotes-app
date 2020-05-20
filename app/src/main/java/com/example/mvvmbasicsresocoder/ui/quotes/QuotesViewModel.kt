package com.example.mvvmbasicsresocoder.ui.quotes

import androidx.lifecycle.ViewModel
import com.example.mvvmbasicsresocoder.data.model.Quote
import com.example.mvvmbasicsresocoder.data.repository.QuoteRepository
import com.example.mvvmbasicsresocoder.data.repository.QuoteRepositoryImplementation

class QuotesViewModel(private val quoteRepository: QuoteRepository): ViewModel() {

    fun getQuotes() = quoteRepository.getQuotes()

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)

}