package com.benjaminabdala.productsearcher.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.benjaminabdala.productsearcher.R
import com.benjaminabdala.productsearcher.data.entity.Product
import com.benjaminabdala.productsearcher.databinding.ProductCardViewLayoutBinding
import com.bumptech.glide.Glide

interface OnProductCardClicked {
    fun onProductCardClicked(productClicked: Product)
}

class ProductsFoundRecyclerViewAdapter(private val onProductCardClicked: OnProductCardClicked) :
    RecyclerView.Adapter<ProductsFoundRecyclerViewAdapter.ProductsFoundViewHolder>() {

    private var productsFound: List<Product> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsFoundViewHolder =
        ProductsFoundViewHolder(
            ProductCardViewLayoutBinding.inflate(
                LayoutInflater.from(parent.context)
            ),
            onProductCardClicked
        )

    override fun onBindViewHolder(holder: ProductsFoundViewHolder, position: Int) {
        holder.bind(productsFound[position])
    }

    override fun getItemCount(): Int = productsFound.size

    fun submitProductsFound(productsFound: List<Product>) {
        this.productsFound = productsFound
    }

    class ProductsFoundViewHolder(binding: ProductCardViewLayoutBinding, private val onProductCardClicked: OnProductCardClicked) :
        RecyclerView.ViewHolder(binding.root) {

        private val productCardBinding = binding

        fun bind(product: Product) {
            with(productCardBinding) {
                Glide.with(root.context)
                    .load(product.imageUrl)
                    .into(productCardViewImage)

                productCardViewTitle.text = product.title
                productCardViewPrice.text = productCardBinding.root.context.getString(R.string.product_card_view_price_text, product.price)
                productCardViewFreeShipping.text = productCardBinding.root.context.getString(R.string.product_card_view_free_shipping_text, if(product.freeShipping) "Si" else "No")
                productCardViewCityName.text = productCardBinding.root.context.getString(R.string.product_card_view_city_name_text, product.addressCityName)
                productCardBinding.root.setOnClickListener {
                    onProductCardClicked.onProductCardClicked(product)
                }
            }
        }
    }
}
