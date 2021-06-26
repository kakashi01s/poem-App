package shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.fragment.CategoryFragment
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.fragment.FragmentHome
import shayri.status.gif.love.sad.wallpaper.boys.map.view.fragment.*

class AppPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    val NUM_ITEMS = 4;
    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FragmentHome.newInstance(position, "Home")
            }
            1 -> {
                CategoryFragment.newInstance(position, "Category")
            }
            2 -> {
                PoemmFragment.newInstance(position, "Poem")
            }
            3 -> {
                GifFragment.newInstance(position, "Lab Test")
            }
            else -> FragmentHome.newInstance(position, "Home")
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