package com.example.mvvmbasicsresocoder.data.db

class FakeDatabase : Database {

    override val quoteDao: QuoteDao = FakeQuoteDao()

}