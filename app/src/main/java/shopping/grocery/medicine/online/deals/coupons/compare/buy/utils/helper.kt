package shopping.grocery.medicine.online.deals.coupons.compare.buy.utils

import android.content.Context
import android.graphics.Color
import android.widget.Toast

class helper {

    fun Context.makeToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.view.setBackgroundColor(Color.parseColor("#53b9fb"))
        toast.show()
    }
}