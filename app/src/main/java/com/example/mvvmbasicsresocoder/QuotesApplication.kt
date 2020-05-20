package com.example.mvvmbasicsresocoder

import android.app.Application
import com.example.mvvmbasicsresocoder.data.db.Database
import com.example.mvvmbasicsresocoder.data.db.FakeDatabase
import com.example.mvvmbasicsresocoder.data.db.QuoteDao
import com.example.mvvmbasicsresocoder.data.model.Quote
import com.example.mvvmbasicsresocoder.data.repository.QuoteRepository
import com.example.mvvmbasicsresocoder.data.repository.QuoteRepositoryImplementation
import com.example.mvvmbasicsresocoder.ui.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class QuotesApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        bind<Database>() with singleton { FakeDatabase() }
        bind<QuoteDao>() with singleton { instance<Database>().quoteDao }
        bind<QuoteRepository>() with singleton { QuoteRepositoryImplementation(instance<QuoteDao>())}
        bind() from provider { QuotesViewModelFactory(instance<QuoteRepository>()) }
    }
}