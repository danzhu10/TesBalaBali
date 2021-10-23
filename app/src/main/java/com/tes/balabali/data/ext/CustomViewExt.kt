package com.tes.balabali.data.ext

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.tes.balabali.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

//fun LoadService<*>.setErrorText(message: String) {
//    if (message.isNotEmpty()) {
//        this.setCallBack(ErrorCallback::class.java) { _, view ->
//            view.findViewById<TextView>(R.id.error_text).text = message
//        }
//    }
//}
//
//fun LoadService<*>.setEmptyText(message: String) {
//    if (message.isNotEmpty()) {
//        this.setCallBack(EmptyCallback::class.java) { _, view ->
//            view.findViewById<TextView>(R.id.emptyText).text = message
//        }
//    }
//}
//
//fun LoadService<*>.showError(message: String = "") {
//    this.setErrorText(message)
//    this.showCallback(ErrorCallback::class.java)
//}
//
//fun LoadService<*>.showEmpty(message: String = "") {
//    this.setEmptyText(message)
//    this.showCallback(EmptyCallback::class.java)
//}
//
//fun LoadService<*>.showNoDownline(message: String = "") {
//    this.setEmptyText(message)
//    this.showCallback(EmptyCallback::class.java)
//}
//
//fun LoadService<*>.showLoading() {
//    this.showCallback(LoadingCallback::class.java)
//}

//fun loadServiceInit(view: View, callback: () -> Unit): LoadService<Any> {
//    val loadsir = LoadSir.getDefault().register(view) {
//
//        callback.invoke()
//    }
//    loadsir.showSuccess()
//    SettingUtil.setLoadingColor(R.color.colorPrimaryDark, loadsir)
//    return loadsir
//}

fun RecyclerView.init(
    layoutManger: RecyclerView.LayoutManager,
    bindAdapter: RecyclerView.Adapter<*>,
    isScroll: Boolean = true
): RecyclerView {
    layoutManager = layoutManger
    setHasFixedSize(true)
    adapter = bindAdapter
    isNestedScrollingEnabled = isScroll
    return this
}

fun RecyclerView.initFloatBtn(floatbtn: FloatingActionButton) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        @SuppressLint("RestrictedApi")
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (!canScrollVertically(-1)) {
                floatbtn.visibility = View.INVISIBLE
            }
        }
    })
//    floatbtn.backgroundTintList = SettingUtil.getOneColorStateList(appContext)
    floatbtn.setOnClickListener {
        val layoutManager = layoutManager as LinearLayoutManager
        if (layoutManager.findLastVisibleItemPosition() >= 40) {
            scrollToPosition(0)
        } else {
            smoothScrollToPosition(0)
        }
    }
}


fun SwipeRefreshLayout.init(onRefreshListener: () -> Unit) {
    this.run {
        setOnRefreshListener {
            onRefreshListener.invoke()
        }
//        setColorSchemeColors(SettingUtil.getColor(appContext))
    }
}

fun Toolbar.init(titleStr: String = ""): Toolbar {
//    setBackgroundColor(SettingUtil.getColor(appContext))
    title = titleStr
    return this
}

fun Toolbar.initClose(
    titleStr: String = "",
    backImg: Int = R.drawable.chevron_left,
    onBack: (toolbar: Toolbar) -> Unit
): Toolbar {
//    setBackgroundColor(SettingUtil.getColor(appContext))
    title = titleStr.toHtml()
    setNavigationIcon(backImg)
    setNavigationOnClickListener { onBack.invoke(this) }
    return this
}

fun Toolbar.initClose2(
    titleStr: String = "",
    backImg: Int = R.drawable.chevron_left,
    onBack: (toolbar: Toolbar) -> Unit
): Toolbar {
    title = titleStr.toHtml()
    setNavigationIcon(backImg)
    setNavigationOnClickListener { onBack.invoke(this) }
    return this
}

fun ViewPager2.init(
    fragment: Fragment,
    fragments: ArrayList<Fragment>,
    isUserInputEnabled: Boolean = true
): ViewPager2 {
    this.isUserInputEnabled = isUserInputEnabled
    adapter = object : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int) = fragments[position]
        override fun getItemCount() = fragments.size
    }
    return this
}

fun ViewPager2.initMain(fragment: Fragment): ViewPager2 {
//    this.isUserInputEnabled = false
//    this.offscreenPageLimit = 4
//    adapter = object : FragmentStateAdapter(fragment) {
//        override fun createFragment(position: Int): Fragment {
//            when (position) {
//                0 -> {
//                    return TicketFragment()
//                }
//                else -> {
//                    return ProfileFragment()
//                }
//            }
//        }
//
//        override fun getItemCount() = 5
//    }
    return this
}

fun BottomNavigationView.init(navigationItemSelectedAction: (Int) -> Unit): BottomNavigationView {
//    enableAnimation(true)
//    enableShiftingMode(false)
//    enableItemShiftingMode(true)
//    itemIconTintList = SettingUtil.getColorStateList(SettingUtil.getColor(appContext))
//    itemTextColor = SettingUtil.getColorStateList(appContext)
//    setTextSize(12F)
//    setTextVisibility(false)
    setOnNavigationItemSelectedListener {
        navigationItemSelectedAction.invoke(it.itemId)
        true
    }
    return this
}

fun hideSoftKeyboard(activity: Activity?) {
    activity?.let { act ->
        val view = act.currentFocus
        view?.let {
            val inputMethodManager =
                act.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}

//fun <T> loadListData(
//    data: ListDataUiState<T>,
//    baseQuickAdapter: BaseQuickAdapter<T, *>,
//    loadService: LoadService<*>,
//    recyclerView: SwipeRecyclerView,
//    swipeRefreshLayout: SwipeRefreshLayout,
//    emptyText:String=""
//) {
//    swipeRefreshLayout.isRefreshing = false
//    recyclerView.loadMoreFinish(data.isEmpty, data.hasMore)
//    if (data.isSuccess) {
//        when {
//            data.isFirstEmpty -> {
//                loadService.showEmpty(emptyText)
//            }
//            data.isRefresh -> {
//                baseQuickAdapter.setList(data.listData)
//                loadService.showSuccess()
//            }
//            else -> {
//                baseQuickAdapter.addData(data.listData)
//                loadService.showSuccess()
//            }
//        }
//    } else {
//        if (data.isRefresh) {
//            loadService.showError(data.errMessage)
//        } else {
//            recyclerView.loadMoreError(0, data.errMessage)
//        }
//    }
//}
