package com.tes.balabali.presentation.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tes.balabali.R
import com.tes.balabali.appContext
import com.tes.balabali.data.ext.initClose
import com.tes.balabali.databinding.ActivityDetailBinding
import com.tes.balabali.domain.model.ItemModel
import com.tes.balabali.presentation.utils.DatetimeUtil.convertDate

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var itemModel: ItemModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.run {
            setSupportActionBar(this)
            initClose("Detail Repository") {
                finish()
            }
        }

        intent.extras!!.apply {
            itemModel = getParcelable("model")!!
            with(binding) {
                username.text = itemModel.owner.login
                repositoryTitle.text = itemModel.name
                starCount.text = itemModel.stargazers_count.toString()
                language.text = itemModel.language
                forksCount.text = itemModel.forks.toString()
                watcherCount.text = itemModel.watchers.toString()
                if (itemModel.created_at.isNotEmpty()) {
                    created.text = "Created at: ${convertDate(itemModel.created_at)}"
                }
                if (itemModel.updated_at.isNotEmpty()) {
                    updated.text = "Update at: ${convertDate(itemModel.updated_at)}"
                }

                Glide.with(appContext)
                    .load(itemModel.owner.avatar_url)
                    .placeholder(R.drawable.circle)
                    .error(R.drawable.circle)
                    .apply(RequestOptions.circleCropTransform())
                    .into(avatar)
            }
        }

        binding.profileWeb.setOnClickListener {
            openWebBrowser(itemModel.owner.html_url)
        }
        binding.repositoryWeb.setOnClickListener {
            openWebBrowser(itemModel.html_url)
        }
    }

    fun openWebBrowser(url: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}