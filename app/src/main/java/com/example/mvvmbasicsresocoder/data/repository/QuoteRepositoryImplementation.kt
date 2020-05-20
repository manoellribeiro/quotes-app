package com.example.mvvmbasicsresocoder.data.repository

import com.example.mvvmbasicsresocoder.data.db.FakeQuoteDao
import com.example.mvvmbasicsresocoder.data.db.QuoteDao
import com.example.mvvmbasicsresocoder.data.model.Quote

class QuoteRepositoryImplementation (private val quoteDao: QuoteDao) : QuoteRepository {

    override fun addQuote(quote: Quote){
        quoteDao.addQuote(quote)
    }

    override fun getQuotes() = quoteDao.getQuotes()

}