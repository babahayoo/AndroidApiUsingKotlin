package com.example.administator.footbalapi.Presenter

import com.example.administator.footbalapi.Api.Api
import com.example.administator.footbalapi.Api.TheSportDBApi
import com.example.administator.footbalapi.Model.MatchResponse
import com.example.administator.footbalapi.View.Interface.MainView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view : MainView,
                    private val apiRepository : Api,
                    private val gson: Gson) {
    fun getMatchList(typeMatch : String?){
        view.showLoading()
        doAsync{
            val data = gson.fromJson(apiRepository
                            .doRequest(TheSportDBApi.getMatch(typeMatch)),
                            MatchResponse::class.java

            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }
}