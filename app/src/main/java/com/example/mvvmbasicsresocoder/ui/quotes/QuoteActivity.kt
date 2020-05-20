package com.example.mvvmbasicsresocoder.ui.quotes

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmbasicsresocoder.R
import com.example.mvvmbasicsresocoder.data.model.Quote
import kotlinx.android.synthetic.main.activity_quote.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import java.lang.StringBuilder

class QuoteActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()

    private final var PREFS_NAME: String = "prefs"
    private final var PREF_DARK_THEME: String = "dark_theme"

    private val viewModelFactory: QuotesViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {

        var preferences: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        var useDarkTheme: Boolean = preferences.getBoolean(PREF_DARK_THEME, false)

        if (useDarkTheme){
            setTheme(R.style.AppTheme_Dark)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote)
        initializeUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater: MenuInflater = menuInflater
        if(getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getBoolean(PREF_DARK_THEME,  false)){
            menuInflater.inflate( R.menu.light_mode_button, menu)
        } else {
            menuInflater.inflate( R.menu.dark_mode_button, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        toggleTheme(!getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getBoolean(PREF_DARK_THEME,  false))
        return true
    }


    private fun initializeUI() {

        // Use ViewModelProviders class to create / get already created QuotesViewModel
        // for this view (activity)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(QuotesViewModel::class.java)

        // Observing LiveData from the QuotesViewModel which in turn observes
        // LiveData from the repository, which observes LiveData from the DAO
        viewModel.getQuotes().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }
            textView_quotes.text = stringBuilder.toString()
        })

        // When button is clicked, instantiate a Quote and add it to DB through the ViewModel
        button_add_quote.setOnClickListener {
            if(validateEditText(editText_quote, "Write a quote") &&  validateEditText(editText_author, "Write the quote author name")){
            val quote = Quote(
                editText_quote.text.toString(),
                editText_author.text.toString()
            )
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

    private fun toggleTheme(isDarkThemeActive: Boolean){
        var editor: SharedPreferences.Editor = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
        editor.putBoolean(PREF_DARK_THEME, isDarkThemeActive);
        editor.apply()

        var intent: Intent = getIntent()
        finish();

        startActivity(intent)
    }

}
