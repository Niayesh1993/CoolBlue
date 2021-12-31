package xyz.zohre.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.ui.R
import kotlinx.android.synthetic.main.item_product.view.*
import xyz.zohre.data.model.Products
import xyz.zohre.presentation.adapter.BaseRecyclerAdapter
import xyz.zohre.presentation.adapter.BaseViewHolder
import xyz.zohre.presentation.bindImage


class ProductRecyclerAdapter:
    BaseRecyclerAdapter<Products,
            ProductRecyclerAdapter.ViewHolder,
            BaseViewHolder.OnItemClickListener<Products>>(){

    override fun viewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.item_product,
                parent,
                false
            ),
            this
        )
    }

    override fun onBindView(holder: ViewHolder?, position: Int) {
        holder?.bind(data[position])
    }

    class ViewHolder(itemView: View,
                     adapter: ProductRecyclerAdapter,)
        : BaseViewHolder<Products>(itemView, adapter){
        override fun bind(t: Products) {
            t.productName.also { itemView.name.text = it }
            t.reviewInformation.reviewSummary.reviewAverage.toString()
                .also { itemView.reviewAverage.text = it }
            t.salesPriceIncVat.toString().also { itemView.salesPrice.text = it + "$" }
            bindImage(
                imageUrl = t.productImage,
                imageView = itemView.image,
                listener = object : RequestListener<Drawable> {

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: com.bumptech.glide.load.DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                }
            )

        }

    }
}