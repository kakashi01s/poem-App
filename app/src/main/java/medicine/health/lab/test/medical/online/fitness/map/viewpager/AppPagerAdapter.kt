package medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import medicine.health.lab.test.medical.online.fitness.map.view.fragment.LabtestFragment
import medicine.health.lab.test.medical.online.fitness.map.view.fragment.PregnancyFragment
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.fragment.CategoryFragment
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.fragment.FragmentHome

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
                PregnancyFragment.newInstance(position, "Pregnancy")
            }
            3 -> {
                LabtestFragment.newInstance(position, "Lab Test")
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
            title = "Pregnancy"
        } else if (position == 3) {
            title = "Lab Test"
        }
        return title
    }
}