package by.tms.sofasstore.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import by.tms.sofasstore.R
import by.tms.sofasstore.Repository
import by.tms.sofasstore.database.Sofa
import by.tms.sofasstore.utilites.OnItemAction
import by.tms.sofasstore.utilites.SortBy
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

var sofas = ArrayList<Sofa>()

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var spinnerAdapter: ArrayAdapter<SortBy>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerAdapter = ArrayAdapter<SortBy>(this,
            R.layout.spinner_text, SortBy.values())

        sortSpinner.adapter = spinnerAdapter
        sortSpinner.onItemSelectedListener = this

        val repo = Repository.getInstance(this)

        CoroutineScope(Dispatchers.IO).launch {
            val list = repo.fillCatalog()
            withContext(Dispatchers.Main) {
                catalog.adapter = ScaleInAnimationAdapter(
                    SofaAdapter(
                        list,
                        R.layout.sofa_item_grid,
                        OnItemAction.ADD_ITEM
                    )
                ).apply {
                    setDuration(1000)
                    setInterpolator(OvershootInterpolator())
                    setFirstOnly(false)
                }

                catalog.layoutManager = GridLayoutManager(this@MainActivity, 2)
                catalog.setHasFixedSize(true)
                progressBar.visibility = View.INVISIBLE
            }
        }

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
                sofas.sortByDescending { it.price }
                catalog.adapter?.notifyDataSetChanged()
            }
            SortBy.LOW_TO_HIGH -> {
                sofas.sortBy { it.price }
                catalog.adapter?.notifyDataSetChanged()
            }
            SortBy.NAME_AZ -> {
                sofas.sortBy { it.name }
                catalog.adapter?.notifyDataSetChanged()
            }
            SortBy.NAME_ZA -> {
                sofas.sortByDescending { it.name }
                catalog.adapter?.notifyDataSetChanged()
            }
        }
    }
}
