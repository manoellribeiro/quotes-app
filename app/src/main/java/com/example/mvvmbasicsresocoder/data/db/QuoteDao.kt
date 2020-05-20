package com.example.mvvmbasicsresocoder.data.db

import androidx.lifecycle.LiveData
import com.example.mvvmbasicsresocoder.data.model.Quote

interface QuoteDao {
    fun addQuote(quote: Quote)
    fun getQuotes(): LiveData<List<Quote>>
}