package com.example.administator.footbalapi.View.Interface

import com.example.administator.footbalapi.Model.Match

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data : List<Match>)
}