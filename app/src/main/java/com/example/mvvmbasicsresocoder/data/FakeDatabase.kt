package com.example.mvvmbasicsresocoder.data

class FakeDatabase private constructor() {

    var quoteDao = FakeQuoteDao()
        private set


    companion object{  //If you need a function or a property to be tied to a class rather than to instances of it you can declare it inside a companion object
        @Volatile private var instance: FakeDatabase? = null       // is used as an indicator to Java compiler and Thread that do not cache value of this variable and always read it from main memory.

        fun getInstance() =
                instance ?: synchronized(this){
                    instance ?: FakeDatabase().also { instance = it }
                }

    }

}