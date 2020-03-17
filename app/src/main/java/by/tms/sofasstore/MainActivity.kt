package by.tms.sofasstore

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener,
    AdapterView.OnItemClickListener {

    private var spinnerAdapter: ArrayAdapter<SortBy>? = null
    private var catalogAdapter: ArrayAdapter<Sofa>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerAdapter = ArrayAdapter<SortBy>(this, R.layout.spinner_text, SortBy.values())

        sortSpinner.adapter = spinnerAdapter
        sortSpinner.onItemSelectedListener = this

        catalogAdapter =
            ArrayAdapter<Sofa>(this, R.layout.sofa_item_grid, CatalogCollection.instance.sofas)

        catalog.adapter = catalogAdapter
        catalog.onItemClickListener = this

        goToCartButton.setOnClickListener(this)
        //addToCartButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            goToCartButton.id -> {
            }
//            addToCartButton.id -> {
//                val selectedSofa = CatalogCollection.instance.currentSofa
//                if (selectedSofa != null) {
//                    Cart.instance.addSofa(selectedSofa)
//                    Toast.makeText(
//                        this,
//                        "Sofa ${selectedSofa.name} added to cart!",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                } else {
//                    Toast.makeText(this, "Nothing to add!", Toast.LENGTH_SHORT).show()
//                }
//            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }


    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p0?.selectedItem) {
            SortBy.HIGH_TO_LOW -> {
                catalogAdapter?.sort { sofa, sofa2 ->
                    when {
                        sofa.price > sofa2.price -> -1
                        sofa.price == sofa2.price -> 0
                        sofa.price < sofa2.price -> 1
                        else -> 100
                    }
                }
            }
            SortBy.LOW_TO_HIGH -> {
                catalogAdapter?.sort { sofa, sofa2 ->
                    when {
                        sofa.price > sofa2.price -> 1
                        sofa.price == sofa2.price -> 0
                        sofa.price < sofa2.price -> -1
                        else -> 100
                    }
                }
            }
            SortBy.NAME_AZ -> {
                catalogAdapter?.sort { sofa, sofa2 ->
                    when {
                        sofa.name > sofa2.name -> 1
                        sofa.name == sofa2.name -> 0
                        sofa.name < sofa2.name -> -1
                        else -> 100
                    }
                }
            }
            SortBy.NAME_ZA -> {
                catalogAdapter?.sort { sofa, sofa2 ->
                    when {
                        sofa.name > sofa2.name -> -1
                        sofa.name == sofa2.name -> 0
                        sofa.name < sofa2.name -> 1
                        else -> 100
                    }
                }
            }
        }

    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Add sofa to cart?")
            .setMessage(
                "Name: ${(p0?.getItemAtPosition(p2) as Sofa).name}, price: \$${(p0.getItemAtPosition(
                    p2
                ) as Sofa).price}"
            )
            .setPositiveButton(
                "Ok"
            ) { dialogInterface, i ->

                Cart.instance.addSofa(p0.getItemAtPosition(p2) as Sofa)
                Toast.makeText(
                    this,
                    "Sofa \"${(p0.getItemAtPosition(p2) as Sofa).name}\" added to cart!",
                    Toast.LENGTH_SHORT
                ).show()

            }
            .setNegativeButton("Cancel", null)
            .setIcon((p0.getItemAtPosition(p2) as Sofa).image)
            .show()
//        CatalogCollection.instance.currentSofa = p0?.getItemAtPosition(p2) as Sofa
    }
}
