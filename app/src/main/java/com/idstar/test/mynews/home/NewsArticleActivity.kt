package com.idstar.test.mynews.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Build
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
import androidx.recyclerview.widget.RecyclerView
import com.idstar.test.core.R
import com.idstar.test.core.data.Resource
import com.idstar.test.core.domain.model.NewsResourceModel
import com.idstar.test.core.ui.NewsArticleAdapter
import com.idstar.test.core.utils.Utils
import com.idstar.test.mynews.databinding.ActivityNewsArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsArticleBinding
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsArticleAdapter: NewsArticleAdapter
    private var currentPage = 1

    companion object {
        const val EXTRA_DATA  = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        newsArticleAdapter = NewsArticleAdapter()
        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(EXTRA_DATA, NewsResourceModel::class.java)
        } else {
            intent?.getParcelableExtra(EXTRA_DATA)
        }

        menu()
        title = data?.name

        getNewsArticles(data?.id.toString(), currentPage)
        setupRecyclerView(data?.id.toString())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }


    private fun getNewsArticles(sourceId: String, page:Int) {
        viewModel.getNewsArticlesBySourceId(sourceId, page)
        viewModel.resultValueNewsArticles?.observe(this@NewsArticleActivity){result ->
            when(result) {
                is Resource.Loading -> Utils.handleProgressAndError(this@NewsArticleActivity, true)
                is Resource.Success -> {
                    binding.viewError.root.isVisible = false
                    if (page > 1) {
                        result.data?.forEach {
                            newsArticleAdapter.addItem(it)
                        }
                    } else {
                        newsArticleAdapter.setData(result.data)
                    }
                }
                is Resource.Error -> Utils.handleProgressAndError(this@NewsArticleActivity, false)
                else -> Utils.handleProgressAndError(this@NewsArticleActivity, false)
            }
        }

        newsArticleAdapter.onItemClick = {data ->
            val intent = Intent(this@NewsArticleActivity, DetailNewsArticleActivity::class.java)
            intent.putExtra(DetailNewsArticleActivity.EXTRA_DATA, data)
            startActivity(intent)
        }

        if (page < 2) {
            with(binding.rvArticles) {
                layoutManager = LinearLayoutManager(this@NewsArticleActivity)
                setHasFixedSize(true)
                adapter = newsArticleAdapter
            }
        }
    }

    private fun setupRecyclerView(sourceId: String) {
        binding.rvArticles.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItem) >= totalItemCount) {
                    currentPage++
                    getNewsArticles(sourceId, currentPage)
                }
            }
        })
    }

    private  fun menu(){
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
                            newsArticleAdapter.filter.filter(query)
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        newsArticleAdapter.filter.filter(newText)
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
}