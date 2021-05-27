package shopping.grocery.medicine.online.deals.coupons.compare.buy.viewpager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.fragment.CategoryFragment
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.fragment.CategoryFragment.Companion.newInstance
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.fragment.FragmentHome
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.fragment.DealFragment
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.fragment.GlobalFragment

class AppPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    val NUM_ITEMS = 3;

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> {
                DealFragment.newInstance(position, "Deals")
            }
            1 -> {
                FragmentHome.newInstance(position, "Home")
            }
            2 -> {
                CategoryFragment.newInstance(position, "Category")
            }

            else -> FragmentHome.newInstance(position, "Home")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title : String? = null;
        if (position == 0) {
            title = "Deals"
        } else if (position == 1) {
            title = "Home"
        }
        else if (position == 2) {
            title = "Category"
        }
         return title
    }
}