package com.example.mvvmbasicsresocoder.utilities

import com.example.mvvmbasicsresocoder.data.FakeDatabase
import com.example.mvvmbasicsresocoder.data.QuoteRepository
import com.example.mvvmbasicsresocoder.ui.quotes.QuotesViewModelFactory

object InjectorUtils {

    fun provideQuotesViewModelFactory(): QuotesViewModelFactory {
        val quoteRepository = QuoteRepository.getInstance(FakeDatabase.getInstance().quoteDao)
        return QuotesViewModelFactory(quoteRepository)
    }

}