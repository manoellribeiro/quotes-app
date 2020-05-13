package com.example.mvvmbasicsresocoder.data

class QuoteRepository private constructor(private val quoteDao: FakeQuoteDao) {

    fun addQuote(quote: Quote){
        quoteDao.addQuote(quote)
    }

    fun getQuotes() = quoteDao.getQuotes()

    companion object{  //If you need a function or a property to be tied to a class rather than to instances of it you can declare it inside a companion object
        @Volatile private var instance: QuoteRepository? = null       // is used as an indicator to Java compiler and Thread that do not cache value of this variable and always read it from main memory.

        fun getInstance(quoteDao: FakeQuoteDao) =
            instance ?: synchronized(this){
                instance ?: QuoteRepository(quoteDao).also { instance = it }
            }

    }
}