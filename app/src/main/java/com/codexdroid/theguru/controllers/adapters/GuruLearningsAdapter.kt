package com.codexdroid.theguru.controllers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.codexdroid.theguru.R
import com.codexdroid.theguru.controllers.data_models.local.Learnings
import com.codexdroid.theguru.databinding.LayoutViewpagerLearningsBinding


class GuruLearningsAdapter(private val learnings: List<Learnings>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        try {
            DataBindingUtil.inflate<LayoutViewpagerLearningsBinding?>(LayoutInflater.from(container.context), R.layout.layout_viewpager_learnings, container, false).apply {
                this.idTextQuote.text = learnings[position].quote
                this.idTextAuthor.text = learnings[position].author
                container.addView(this.root)
                return this.root
            }
        }catch (ex: Exception) { ex.printStackTrace() }

        return container.rootView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        try { container.removeView(`object` as View)} catch (ex: Exception) {ex.printStackTrace() }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = learnings.size
}