package com.example.administator.footbalapi.Api

import java.net.URL

class Api {


    fun doRequest(url : String) : String{
        return URL(url).readText()
    }
}