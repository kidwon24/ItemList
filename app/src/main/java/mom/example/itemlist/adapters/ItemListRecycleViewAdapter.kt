package mom.example.itemlist.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.FrameLayout
import android.widget.TextView
import mom.example.itemlist.R
import mom.example.itemlist.RecourceTypes
import mom.example.itemlist.Util
import mom.example.itemlist.model.Pokemon
import com.mikhaellopez.circularimageview.CircularImageView

class ItemListRecycleViewAdapter(val context: Context, val items: ArrayList<Pokemon>) :
        RecyclerView.Adapter<ItemListRecycleViewAdapter.ItemViewHolder>() {

    val util = Util()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        if (items.isEmpty() || holder == null)
            return
        val item = items[position]
        val drawableName = item.imageName.substring(0, item.imageName.lastIndexOf('.'))
        val itemImgId = context.resources.getIdentifier(drawableName,
                RecourceTypes.DRAWABLE.getNameSmall(), context.packageName)
        holder.imageView.setImageResource(itemImgId)
        holder.typeView.text = item.type.toString()
        holder.nameView.text = item.name

        holder.view.setOnClickListener {


            val listItemHeight = it.measuredHeight
            val toolBarHeight = (it.parent as RecyclerView).y
            val scalingView = it.rootView.findViewById<View>(R.id.growView)
            val screenHeight = (scalingView.parent as FrameLayout).measuredHeight

            scalingView.y = it.y + toolBarHeight
            scalingView.layoutParams.height = listItemHeight
            scalingView.layoutParams = scalingView.layoutParams


            val pivots = arrayListOf(0.777f)
            val itemScale = screenHeight.toFloat()/listItemHeight


            val scaleAnime = ScaleAnimation(1f, 1f, 1f, itemScale,
                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, pivots[0])
            scaleAnime.setDuration(400);
            scaleAnime.setFillAfter(true);
            scalingView.startAnimation(scaleAnime);


            scaleAnime.setAnimationListener( object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {}
                override fun onAnimationStart(p0: Animation?) {}

                override fun onAnimationEnd(anime: Animation) {
                    println(scalingView)
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }



    inner class ItemViewHolder(val view: View ) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<CircularImageView>(R.id.itemPicCI)
        val typeView = view.findViewById<TextView>(R.id.itemTypeTV)
        val nameView = view.findViewById<TextView>(R.id.itemNameTV)
    }
}