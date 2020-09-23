package com.idea.group.iplato.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.idea.group.iplato.R
import com.idea.group.iplato.repositories.RocketRepo
import com.idea.group.iplato.viewModels.RocketsViewModel
import com.idea.group.iplato.views.adapters.RocketsAdapter
import com.idea.group.iplato.repositories.DatabaseRepository
import com.idea.group.iplato.services.BaseNetworkService
import com.idea.group.iplato.viewModels.factories.RocketsModelFactory

class MainActivity : AppCompatActivity() {
    private var list: RecyclerView? = null
    private var pullToRefresh: SwipeRefreshLayout? = null
    private var spinner: ProgressBar? = null
    private var viewModel: RocketsViewModel? = null
    private var layoutManager: LinearLayoutManager? = null
    private var adapter: RocketsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        uiSetUp()
        viewModelSetUp()
    }

    private fun viewModelSetUp() {
        viewModel =
            RocketsModelFactory(RocketRepo(BaseNetworkService()), DatabaseRepository()).create(
                RocketsViewModel::class.java
            )
        viewModel?.rockets?.observe(this, Observer {
            adapter?.addItem(it)
        })

        viewModel?.showError?.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel?.showLoading?.observe(this, Observer {
            if (it){
                spinner?.visibility = View.VISIBLE
                pullToRefresh?.isRefreshing = false
            }
            else
                spinner?.visibility = View.GONE
        })

        viewModel?.shouldClearExistingItems?.observe(this, Observer {
            adapter?.clearItems()
        })

        viewModel?.getItemsFromStart()

    }

    private fun uiSetUp() {
        spinner = findViewById(R.id.progress_bar)
        spinner?.visibility = View.GONE

        pullToRefresh = findViewById(R.id.main_swipe_to_refresh)
        pullToRefresh?.setOnRefreshListener {
            viewModel?.getItemsFromStart()
        }

        adapter = RocketsAdapter()

        list = findViewById(R.id.main_recycler_view)
        layoutManager = LinearLayoutManager(this)
        list?.layoutManager = layoutManager
        list?.adapter = adapter
        list?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                checkIfMoreItemsNeeded()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        checkIfMoreItemsNeeded()
    }

    fun checkIfMoreItemsNeeded(){
        adapter?.let {
            layoutManager?.let { layoutManagerUnwrapped ->
                if (layoutManagerUnwrapped.findLastVisibleItemPosition() == it.itemCount - 1) {
                    viewModel?.getItems(it.itemCount)
                }
            }
        }
    }
}