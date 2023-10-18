package com.idstar.test.mynews.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.idstar.test.core.R
import com.idstar.test.core.data.Resource
import com.idstar.test.core.ui.NewsResourceAdapter
import com.idstar.test.core.utils.Utils.handleProgressAndError
import com.idstar.test.mynews.databinding.ActivityNewsResourceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsResourceActivity : AppCompatActivity() {
    private lateinit var binding:ActivityNewsResourceBinding
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsResourceAdapter: NewsResourceAdapter

    companion object {
        const val NEWS_CATEGORY = "news_category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsResourceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        newsResourceAdapter  = NewsResourceAdapter()
        menu()
        val categoryValue = intent?.getIntExtra(NEWS_CATEGORY, 0)
        if (categoryValue != null) {
            title = getString(categoryValue)
            getNewsResources(categoryValue)
        }
    }

    private  fun menu() {
        val menuHost: MenuHost = this
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menu.clear()
                menuInflater.inflate(R.menu.menu_search, menu)
                val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
                val searchView = menu.findItem(R.id.action_search)?.actionView as SearchView
                searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
                searchView.queryHint = resources.getString(R.string.cari_disini)
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        if (query.isNotEmpty()) {
                            newsResourceAdapter.filter.filter(query)
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        newsResourceAdapter.filter.filter(newText)
                        return false
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    else -> false
                }
            }
        }, this , Lifecycle.State.RESUMED)
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun getNewsResources(category: Int) {
        when(category) {
            R.string.category_all -> viewModel.getNewsResources()
            else -> viewModel.getNewsResourceByCategory(getString(category).lowercase())
        }

        viewModel.resultValueNewsResource?.observe(this@NewsResourceActivity){result ->
            when(result) {
                is Resource.Loading -> handleProgressAndError(this@NewsResourceActivity, true)
                is Resource.Success -> {
                    binding.viewError.root.isVisible = false
                    newsResourceAdapter.setData(result.data)
                }
                is Resource.Error -> handleProgressAndError(this@NewsResourceActivity, false )
                else -> handleProgressAndError(this@NewsResourceActivity, false )
            }
        }
        newsResourceAdapter.onItemClick = {data ->
            val intent = Intent(this@NewsResourceActivity, NewsArticleActivity::class.java)
            intent.putExtra(NewsArticleActivity.EXTRA_DATA, data)
            startActivity(intent)
        }

        with(binding.rvNews) {
            layoutManager = LinearLayoutManager(this@NewsResourceActivity)
            setHasFixedSize(true)
            adapter = newsResourceAdapter
        }
    }
}