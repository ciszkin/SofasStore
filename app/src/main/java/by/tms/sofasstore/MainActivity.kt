package by.tms.sofasstore

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private var spinnerAdapter: ArrayAdapter<SortBy>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerAdapter = ArrayAdapter<SortBy>(this, R.layout.spinner_text, SortBy.values())

        sortSpinner.adapter = spinnerAdapter
        sortSpinner.onItemSelectedListener = this


        val catalogAdapter = SofaAdapter(CatalogCollection.instance.sofas, R.layout.sofa_item_grid, OnItemActivity.ADD_ITEM)

        catalog.adapter = catalogAdapter
        catalog.layoutManager = GridLayoutManager(this, 2)
        catalog.setHasFixedSize(true)

        goToCartButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            goToCartButton.id -> {
                startActivity(Intent(this, CartActivity::class.java))
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }


    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p0?.selectedItem) {
            SortBy.HIGH_TO_LOW -> {
                CatalogCollection.instance.sofas.sortByDescending { it.price }
                catalog.adapter?.notifyDataSetChanged()
            }
            SortBy.LOW_TO_HIGH -> {
                CatalogCollection.instance.sofas.sortBy { it.price }
                catalog.adapter?.notifyDataSetChanged()
            }
            SortBy.NAME_AZ -> {
                CatalogCollection.instance.sofas.sortBy { it.name }
                catalog.adapter?.notifyDataSetChanged()
            }
            SortBy.NAME_ZA -> {
                CatalogCollection.instance.sofas.sortByDescending { it.name }
                catalog.adapter?.notifyDataSetChanged()
            }
        }
    }
}
