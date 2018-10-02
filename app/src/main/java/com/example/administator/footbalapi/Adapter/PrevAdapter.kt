package com.example.administator.footbalapi.Adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.administator.footbalapi.Model.Match
import com.example.administator.footbalapi.R
import java.text.SimpleDateFormat
import java.util.*

class PrevAdapter(private val match : List<Match>,
                  private val listener : (Match) -> Unit) : RecyclerView.Adapter<PrevAdapter.PrevViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrevViewHolder {
    return PrevViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.match_item_list,parent,false))
    }

    override fun getItemCount(): Int {
        return match.size
    }

    override fun onBindViewHolder(holder: PrevViewHolder, position: Int) {
        holder.bindItem(match[position],listener)
    }

    class PrevViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateMatch = view.findViewById<TextView>(R.id.date_match)
        val teamOne = view.findViewById<TextView>(R.id.team_one_title_match)
        val teamTwo = view.findViewById<TextView>(R.id.team_two_title_match)
        val teamOneScore = view.findViewById<TextView>(R.id.team_one_point_match)
        val teamTwoScore = view.findViewById<TextView>(R.id.team_two_point_match)

        @SuppressLint("SimpleDateFormat")
        fun bindItem(match: Match, listener: (Match) -> Unit){
            val oldSdf = SimpleDateFormat("yyyy-MM-dd")
            val newSdf = SimpleDateFormat("EEEE, d MMM yyyy", Locale.getDefault())
            val newDate = newSdf.format(oldSdf.parse(match.dateEvent))
            dateMatch.text = newDate
            teamOne.text = match.strHomeTeam
            teamTwo.text = match.strAwayTeam
            teamOneScore.text = match.intHomeScore
            teamTwoScore.text = match.intAwayScore
            itemView.setOnClickListener{
                listener(match)
            }
        }
    }

}


