package by.tms.sofasstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import jp.wasabeef.recyclerview.animators.FadeInAnimator
import kotlinx.android.synthetic.main.cart_layout.*

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_layout)

        val cartAdapter = SofaAdapter(Cart.instance.getList(), R.layout.sofa_item_list, OnItemActivity.REMOVE_ITEM)
        cartAdapter.setTextView(totalValueTextView)

        cartList.adapter = cartAdapter
        cartList.layoutManager = LinearLayoutManager(this)
        cartList.itemAnimator = FadeInAnimator()

        totalValueTextView.text = Cart.instance.getTotal().toString()
    }
}
