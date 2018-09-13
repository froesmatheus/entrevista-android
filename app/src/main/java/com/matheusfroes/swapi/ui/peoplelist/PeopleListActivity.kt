package com.matheusfroes.swapi.ui.peoplelist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.matheusfroes.swapi.R
import com.matheusfroes.swapi.extra.appInjector
import com.matheusfroes.swapi.extra.toast
import com.matheusfroes.swapi.extra.viewModelProvider
import com.matheusfroes.swapi.ui.EndlessScrollListener
import com.matheusfroes.swapi.ui.favorites.BookmarkedPeopleActivity
import com.matheusfroes.swapi.ui.persondetail.PersonDetailActivity
import com.matheusfroes.swapi.ui.searchpeople.SearchPeopleActivity
import kotlinx.android.synthetic.main.activity_people_list.*
import javax.inject.Inject

class PeopleListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PeopleListViewModel

    private val adapter = PeopleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people_list)
        appInjector.inject(this)

        viewModel = viewModelProvider(viewModelFactory)

        viewModel.getPeople().observe(this, Observer { people ->
            if (people != null) {
                adapter.items = people
            }
        })

        viewModel.fetchPeople()

        adapter.personClickEvent = { personId ->
            PersonDetailActivity.start(this, personId)
        }

        adapter.toggleBookmarkPersonEvent = { personId ->
            viewModel.toggleBookmark(personId)
        }

        viewModel.bookmarkEvent.observe(this, Observer { bookmarkEvent ->
            toast(bookmarkEvent?.message)
        })

        val layoutManager = LinearLayoutManager(this)
        rvPeople.layoutManager = layoutManager
        rvPeople.adapter = adapter

        rvPeople.addOnScrollListener(object : EndlessScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.fetchPeople(page)
                Log.d("SWAPI", "Page = $page")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bookmarked -> {
                startActivity(Intent(this, BookmarkedPeopleActivity::class.java))
            }
            R.id.search -> {
                startActivity(Intent(this, SearchPeopleActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
