package com.example.administator.footbalapi.View.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.administator.footbalapi.Api.Api
import com.example.administator.footbalapi.Model.Match
import com.example.administator.footbalapi.Model.Team
import com.example.administator.footbalapi.Presenter.DetailPresenter
import com.example.administator.footbalapi.Presenter.MainPresenter
import com.example.administator.footbalapi.R
import com.example.administator.footbalapi.View.Interface.DetailView
import com.example.administator.footbalapi.invisible
import com.example.administator.footbalapi.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var matchModel : Match
    private lateinit var presenter : DetailPresenter

    override fun showTeamOneIcon(data: List<Team>) {
        Picasso.get().load(data[0].strTeamBadge).into(iv_team_one_badge)
    }

    override fun showTeamTwoIcon(data: List<Team>) {
        Picasso.get().load(data[0].strTeamBadge).into(iv_team_two_badge)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun initData() {
        val oldSdf = SimpleDateFormat("yyyy-MM-dd")
        val newSdf = SimpleDateFormat("EEEE, d MMM yyyy" , Locale.getDefault())
        val newDate = newSdf.format(oldSdf.parse(matchModel.dateEvent))
        supportActionBar?.title = matchModel.strHomeTeam?.toString() + " vs " + matchModel.strAwayTeam?.toString()
        date_match.text = newDate
        team_one_title_match.text = matchModel.strHomeTeam?.toString()
        team_two_title_match.text = matchModel.strAwayTeam?.toString()
        team_one_point_match.text = matchModel.intHomeScore?.toString()
        team_two_point_match.text = matchModel.intAwayScore?.toString()
        team_one_goal.text = matchModel.strHomeGoalDetails?.toString()
        team_two_goal.text = matchModel.strAwayGoalDetails?.toString()
        team_one_shoots.text = matchModel.intHomeShots?.toString()
        team_two_shoots.text = matchModel.intAwayShots?.toString()
        team_one_red_card.text = matchModel.strHomeRedCards?.toString()
        team_two_red_card.text = matchModel.strAwayRedCards?.toString()
        team_one_yellow_card.text = matchModel.strHomeYellowCards?.toString()
        team_two_yellow_card.text = matchModel.strAwayYellowCards?.toString()
        team_one_goal_keeper.text = matchModel.strHomeLineupGoalkeeper?.toString()
        team_two_goal_keeper.text = matchModel.strAwayLineupGoalkeeper?.toString()
        team_one_defense.text = matchModel.strHomeLineupDefense?.toString()
        team_two_defense.text = matchModel.strAwayLineupDefense?.toString()
        team_one_midfield.text = matchModel.strHomeLineupMidfield?.toString()
        team_two_midfield.text = matchModel.strAwayLineupMidfield?.toString()
        team_one_forward.text = matchModel.strHomeLineupForward?.toString()
        team_two_forward.text = matchModel.strAwayLineupForward?.toString()
        team_one_subtituties.text = matchModel.strHomeLineupSubstitutes?.toString()
        team_two_subtituties.text = matchModel.strAwayLineupSubstitutes?.toString()
    }

    override fun showLoading() {
        team_one_pb.visible()
        team_two_pb.visible()
    }

    override fun hideLoading() {
        team_one_pb.invisible()
        team_two_pb.invisible()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val request = Api()
        val gson = Gson()
        matchModel = intent.getParcelableExtra("match")
        initData()

        presenter = DetailPresenter(this,request,gson)
        presenter.getTeamOneIcon(matchModel.idHomeTeam.toString())
        presenter.getTeamTwoIcon(matchModel.idAwayTeam.toString())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
