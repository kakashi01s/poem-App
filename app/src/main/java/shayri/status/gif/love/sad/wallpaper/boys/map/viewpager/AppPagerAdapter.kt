package shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.fragment.CategoryFragment
import shayri.status.gif.love.sad.wallpaper.boys.map.view.fragment.*

class AppPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    val NUM_ITEMS = 4;
    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                PoemmFragment.newInstance(position, "Home")
            }
            1 -> {
                ShayriFragment.newInstance(position, "Category")
            }
            2 -> {
                CategoryFragment.newInstance(position, "Poem")
            }
            3 -> {
                GifFragment.newInstance(position, "Lab Test")
            }
            else -> PoemmFragment.newInstance(position, "Home")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null;
        if (position == 0) {
            title = "Home"
        } else if (position == 1) {
            title = "Tools"
        } else if (position == 2) {
            title = "Poem"
        } else if (position == 3) {
            title = "Lab Test"
        }
        return title
    }
}