package by.tms.sofasstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.cart_layout.*

class CartActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var cartAdapter: ArrayAdapter<Sofa>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_layout)

        updateAdapter()
        cartList.onItemClickListener = this

        totalValueTextView.text = Cart.instance.getTotal().toString()
    }

    private fun updateAdapter() {
        cartAdapter = ArrayAdapter<Sofa>(this, R.layout.sofa_item_list, Cart.instance.getList())
        cartList.adapter = cartAdapter
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.remove_sofa_from_cart))
            .setMessage(
                "Name: ${(p0?.getItemAtPosition(p2) as Sofa).name}, price: \$${(p0.getItemAtPosition(
                    p2
                ) as Sofa).price}"
            )
            .setPositiveButton(
                getString(R.string.remove)
            ) { dialogInterface, i ->

                Toast.makeText(
                    this,
                    "Sofa \"${(p0.getItemAtPosition(p2) as Sofa).name}\" removed from cart!",
                    Toast.LENGTH_SHORT
                ).show()
                Cart.instance.removeSofa(p0.getItemAtPosition(p2) as Sofa)
                totalValueTextView.text = Cart.instance.getTotal().toString()
                updateAdapter()
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .setIcon((p0.getItemAtPosition(p2) as Sofa).image)
            .show()
    }
}
