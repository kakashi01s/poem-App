package shopping.grocery.medicine.online.deals.coupons.compare.buy.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.chauthai.swipereveallayout.ViewBinderHelper
import shopping.grocery.medicine.online.deals.coupons.compare.buy.R

class BookmarkAdapter(var context: Context, var list: ArrayList<String>) :
    RecyclerView.Adapter<BookmarkAdapter.SwipeViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeViewHolder {
        val m = LayoutInflater.from(parent.context).inflate(R.layout.bookmark_layout, parent, false)
        return SwipeViewHolder(m)
    }

    override fun onBindViewHolder(holder: SwipeViewHolder, position: Int) {



        ViewBinderHelper().setOpenOnlyOne(true)
        ViewBinderHelper().bind(holder.swipeRevealLayout, list[position][1].toString())
        ViewBinderHelper().closeLayout(list[position][1].toString())

        holder.text.text = list[position][1].toString()

//        Glide.with(context).load(list[position].bookmarkUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class SwipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.bkmark_image)
        val text: TextView = itemView.findViewById(R.id.bkmark_text)
        val delete: TextView = itemView.findViewById(R.id.delete)
        val more: TextView = itemView.findViewById(R.id.more)
        val swipeRevealLayout: SwipeRevealLayout = itemView.findViewById(R.id.swipe_layout)


    }

}