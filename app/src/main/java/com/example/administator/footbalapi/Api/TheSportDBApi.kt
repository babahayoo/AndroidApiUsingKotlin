package com.example.administator.footbalapi.Api

import android.net.Uri
import com.example.administator.footbalapi.BuildConfig

object TheSportDBApi {
    fun getMatch(typeMatch : String?) : String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TDSP_API_KEY)
                .appendPath(typeMatch)
                .appendQueryParameter("id","4328")
                .build()
                .toString()
    }

    fun getTeam(idTeam : String?) : String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TDSP_API_KEY)
                .appendPath("lookupteam.php")
                .appendQueryParameter("id",idTeam)
                .build()
                .toString()
    }
}