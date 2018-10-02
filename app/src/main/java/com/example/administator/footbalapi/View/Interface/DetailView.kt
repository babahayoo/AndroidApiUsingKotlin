package com.example.administator.footbalapi.View.Interface

import com.example.administator.footbalapi.Model.Team

interface DetailView {
    fun showTeamOneIcon(data : List<Team>)
    fun showTeamTwoIcon(data : List<Team>)
    fun initData()
    fun showLoading()
    fun hideLoading()
}