package com.example.quotesappcompose

import android.content.Context
import android.graphics.pdf.PdfDocument.Page
import androidx.compose.runtime.mutableStateOf
import com.example.quotesappcompose.model.Quote
import com.google.gson.Gson
import java.io.InputStream

object DataManager {

    var data = emptyArray<Quote>()

    var currentQuote : Quote? = null
    var currentPage = mutableStateOf(Pages.LISTING)
    var isDataLoaded = mutableStateOf(false)

    fun loadAssetsFromFile(context: Context) {
        val inputStream: InputStream = context.assets.open("quotes.json")
        val size :Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json, Array<Quote>::class.java)
        isDataLoaded.value = true

    }

    fun switchPages(quote: Quote?){
        if(currentPage.value == Pages.LISTING){
            currentQuote = quote
            currentPage.value = Pages.DETAIL

        }
        else{
            currentPage.value=Pages.LISTING
        }
    }
}