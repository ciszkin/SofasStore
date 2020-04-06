package by.tms.sofasstore.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import by.tms.sofasstore.Cart
import by.tms.sofasstore.R
import by.tms.sofasstore.database.Sofa
import by.tms.sofasstore.utilites.OnItemAction
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.sofa_item_grid.view.*

class SofaAdapter(private val list: MutableList<Sofa>, private val layout: Int, private val activity: OnItemAction) :
    RecyclerView.Adapter<SofaAdapter.SofaViewHolder>() {

    class SofaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    var total: TextView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SofaViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return SofaViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SofaViewHolder, position: Int) {
        val item = holder.itemView
        item.apply {
            Picasso.get().load(list[position].image).into(sofaImage)
            sofaName.text = list[position].name
            sofaPrice.text = list[position].price.toString()
            setOnClickListener {
                MaterialAlertDialogBuilder(this.context)
                    .setTitle(activity.messageText)
                    .setMessage(
                        "Name: ${list[position].name}, price: \$${list[position].price}"
                    )
                    .setPositiveButton(
                        activity.buttonText
                    ) { _, _ ->
                        activity.act(list, position)
                        if(activity == OnItemAction.REMOVE_ITEM) {
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, itemCount)
                        }
                        Toast.makeText(
                            this.context,
                            activity.toastText,
                            Toast.LENGTH_SHORT
                        ).show()

                        if(total != null) total?.text = Cart.instance.getTotal().toString()

                    }
                    .setNegativeButton(this.context.getString(R.string.cancel), null)
                    .show()
            }
        }
    }
}
