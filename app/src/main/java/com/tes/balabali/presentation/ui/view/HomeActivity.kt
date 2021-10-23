package com.tes.balabali.presentation.ui.view

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.tes.balabali.R
import com.tes.balabali.data.ext.*
import com.tes.balabali.databinding.ActivityHomeBinding
import com.tes.balabali.domain.model.ItemModel
import com.tes.balabali.presentation.adapter.SearchListAdapter
import com.tes.balabali.presentation.ui.viewmodel.GithubViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), SearchListAdapter.RecyclerViewClickListener {

    private val viewModel by viewModel<GithubViewModel>()
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: SearchListAdapter
    lateinit var dataList: ArrayList<ItemModel>
    var searchKey = ""
    var sort = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initObserver()
    }

    override fun onResume() {
        super.onResume()
        hideSoftKeyboard(this)
    }

    private fun initView() {
        setSupportActionBar(binding.included.toolbar)
        dataList = ArrayList()
        adapter = SearchListAdapter(dataList)
        binding.recyclerView.init(LinearLayoutManager(this), adapter)
            .addItemDecoration(
                SpaceItemDecoration(
                    dp2px(1f),
                    dp2px(2f)
                )
            )
        adapter.listener = this
        binding.swipeRefresh.init {
            viewModel.searchRepo(searchKey, sort)
        }
        binding.sortFB.setOnClickListener {
            val myItems = listOf("forks", "stars")
            MaterialDialog(this).show {
                title = "Sort by"
                listItems(items = myItems) { dialog, index, text ->
                    viewModel.searchRepo(searchKey, text.toString())
                }
            }
        }
    }

    private fun swipeRefresh(isRefresh: Boolean) {
        binding.swipeRefresh.isRefreshing = isRefresh
    }

    private fun initObserver() {
        viewModel.githubData.observe(this, {
            it.handleResult({
                swipeRefresh(false)
                if (it.total_count > 0) {
                    dataList.clear()
                    dataList.addAll(it.items)
                    adapter.notifyDataSetChanged()
                    binding.recyclerView.visible()
                    binding.sortFB.visible()
                    binding.contentText.gone()
                    binding.layoutText.gone()
                } else {
                    binding.recyclerView.gone()
                    binding.sortFB.gone()
                    binding.contentText.visible()
                    binding.contentText.text = "No Data Found"
                }
            }, {
                showError()
            }, {
                showError()
            })
        })
    }

    private fun showError() {
        binding.recyclerView.gone()
        binding.sortFB.gone()
        binding.contentText.visible()
        swipeRefresh(false)
        binding.contentText.text = "There is an Error"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        val searchView = menu.findItem(R.id.actionSearch)?.actionView as SearchView
        searchView.run {
            maxWidth = Integer.MAX_VALUE
            queryHint = "Search"
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { queryStr ->
                        if (queryStr.isNotEmpty())
                            searchKey = queryStr
                        viewModel.searchRepo(searchKey, sort)
                        swipeRefresh(true)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchKey = newText!!
                    return false
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun dp2px(dpValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    override fun onItemClicked(data: ItemModel) {
        val intent = Intent()
            .putExtra("model", data)

        apply {
            startActivity(
                Intent(this, DetailActivity::class.java)
                    .putExtras(
                        intent
                    )
            )
        }
    }

}