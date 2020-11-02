package shopping.grocery.medicine.online.deals.coupons.compare.buy.view

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import shopping.grocery.medicine.online.deals.coupons.compare.buy.R
import shopping.grocery.medicine.online.deals.coupons.compare.buy.base.BaseActivity

class SplashActivity : BaseActivity() {
    override val bindingVariable: Int
        get() = 0
    override val layoutId: Int
        get() = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({ //                if(Pref.getInstance().getViewPager()){
            //                    openActivity(null, IntroSlider.class);
            //                }
            //                else {
            //                    openActivity(null, MainActivity.class);
            //                    finish();
            //                }
            openActivity(null, MainActivity::class.java)
            finish()
        }, 3000)


    }
}