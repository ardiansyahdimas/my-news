package com.idstar.test.mynews.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.idstar.test.core.ui.CategoryAdapter
import com.idstar.test.core.utils.Config
import com.idstar.test.mynews.R
import com.idstar.test.mynews.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryAdapter = CategoryAdapter()
        categoryAdapter.setData(Config.dummyCategory)

        categoryAdapter.onItemClick = {data ->
            val intent = Intent(this@HomeActivity, NewsResourceActivity::class.java)
            intent.putExtra(NewsResourceActivity.NEWS_CATEGORY, data.name)
            startActivity(intent)
        }

        with(binding.rvCategory) {
            layoutManager = GridLayoutManager(this@HomeActivity, 2)
            setHasFixedSize(true)
            adapter = categoryAdapter
        }
    }
}