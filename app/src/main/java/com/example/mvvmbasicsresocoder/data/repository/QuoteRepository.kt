package com.example.mvvmbasicsresocoder.data.repository

import androidx.lifecycle.LiveData
import com.example.mvvmbasicsresocoder.data.model.Quote

interface QuoteRepository {
    fun addQuote(quote: Quote)
    fun getQuotes(): LiveData<List<Quote>>
}