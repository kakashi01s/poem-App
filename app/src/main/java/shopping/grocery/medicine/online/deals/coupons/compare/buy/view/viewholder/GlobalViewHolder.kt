package shopping.grocery.medicine.online.deals.coupons.compare.buy.view.viewholder

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import shopping.grocery.medicine.online.deals.coupons.compare.buy.R
import shopping.grocery.medicine.online.deals.coupons.compare.buy.base.viewholder.BaseViewHolder
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.listener.CategoryStoresItemClickListener
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.listener.category.CountryItemClickListener

class GlobalViewHolder (itemView: View) : BaseViewHolder<List<String>, CountryItemClickListener<List<String>>>(itemView) {


    var ivPortalImage: ImageView? = null
    var tvPortalName: TextView? = null
    var cvPortal: CardView? = null

    init {
        ivPortalImage = itemView.findViewById(R.id.ivAllAppsPortal)
        tvPortalName = itemView.findViewById(R.id.tvPortalName)
        cvPortal = itemView.findViewById(R.id.cvAllAppsPortal)
    }



    override fun onBind(item: List<String>, listener: CountryItemClickListener<List<String>>?) {

        Glide.with(ivPortalImage!!.context)
            .load(item[3])
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(ivPortalImage!!)

        tvPortalName?.setText(item.get(1))

        cvPortal?.setOnClickListener {
            Log.d("TAG", "onAllCardClick: "+item.get(1))
            listener?.CategoryStoresCardClick(item)
        }

    }

}