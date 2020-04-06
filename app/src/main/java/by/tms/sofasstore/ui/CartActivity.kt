package by.tms.sofasstore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import by.tms.sofasstore.Cart
import by.tms.sofasstore.utilites.OnItemAction
import by.tms.sofasstore.R
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import kotlinx.android.synthetic.main.cart_layout.*

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_layout)

        val cartAdapter = SofaAdapter(
            Cart.instance.getList(),
            R.layout.sofa_item_list,
            OnItemAction.REMOVE_ITEM
        )
        cartAdapter.total = totalValueTextView

        cartList.adapter = cartAdapter
        cartList.layoutManager = LinearLayoutManager(this)
        cartList.itemAnimator = ScaleInAnimator().apply {
            removeDuration = 500
        }

        totalValueTextView.text = Cart.instance.getTotal().toString()
    }
}
