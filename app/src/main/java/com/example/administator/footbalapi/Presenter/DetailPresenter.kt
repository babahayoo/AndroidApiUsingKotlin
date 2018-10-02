package com.example.administator.footbalapi.Presenter

import com.example.administator.footbalapi.Api.Api
import com.example.administator.footbalapi.Api.TheSportDBApi
import com.example.administator.footbalapi.Model.Team
import com.example.administator.footbalapi.Model.TeamResponse
import com.example.administator.footbalapi.View.Interface.DetailView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(private val view : DetailView,
                      private val apiRepository : Api,
                      private val gson: Gson) {

    fun getTeamOneIcon(idTeam: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                            .doRequest(TheSportDBApi.getTeam(idTeam)),
                            TeamResponse::class.java
            )

            uiThread {
                view.showTeamOneIcon(data.teams)
            }
        }
    }

    fun getTeamTwoIcon(idTeam: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeam(idTeam)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamTwoIcon(data.teams)
            }
        }
    }
}