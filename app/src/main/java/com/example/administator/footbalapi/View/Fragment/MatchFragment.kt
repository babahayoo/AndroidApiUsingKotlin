package com.example.administator.footbalapi.View.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.administator.footbalapi.Adapter.PrevAdapter
import com.example.administator.footbalapi.Api.Api
import com.example.administator.footbalapi.Model.Match
import com.example.administator.footbalapi.Presenter.MainPresenter
import com.example.administator.footbalapi.R
import com.example.administator.footbalapi.View.Activity.DetailActivity
import com.example.administator.footbalapi.View.Interface.MainView
import com.example.administator.footbalapi.invisible
import com.example.administator.footbalapi.visible
import com.google.gson.Gson
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity


class MatchFragment : Fragment() , MainView {

    private lateinit var rvPrevMatch : RecyclerView
    private lateinit var prevAdapter : PrevAdapter
    private lateinit var pbPrevMatch : ProgressBar
    private lateinit var presenter : MainPresenter
    private var match : MutableList<Match> = mutableListOf()

    override fun showLoading() {
        pbPrevMatch.visible()
    }

    override fun hideLoading() {
        pbPrevMatch.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        match.clear()
        match.addAll(data)
        prevAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvPrevMatch = view.findViewById(R.id.prev_rv_match)
        pbPrevMatch = view.findViewById(R.id.prev_progress_bar)
        prevAdapter = PrevAdapter(match){
            startActivity<DetailActivity>("match" to it)
        }
        rvPrevMatch.layoutManager = LinearLayoutManager(ctx)
        rvPrevMatch.adapter =prevAdapter

        val request = Api()
        val gson = Gson()

        presenter = MainPresenter(this,request,gson)
        presenter.getMatchList(arguments?.getString("typeMatch"))

    }

    companion object {
        @JvmStatic
        fun newInstance(state : String) =
                MatchFragment().apply {
                    val args = Bundle()
                    args.putString("typeMatch",state)
                    val fragment = MatchFragment()
                    fragment.arguments = args
                    return fragment
                }
    }


}
