package by.tms.sofasstore.utilites

import by.tms.sofasstore.database.Sofa
import by.tms.sofasstore.Cart

enum class OnItemAction(
    val messageText: String,
    val toastText: String,
    val buttonText: String,
    val act: (MutableList<Sofa>, Int) -> Boolean
) {
    ADD_ITEM("Add sofa to cart?",
        "Sofa added to cart!",
        "ADD",
        { list: MutableList<Sofa>, position: Int ->
            Cart.instance.addSofa(list[position])
        }),
    REMOVE_ITEM("Remove sofa fom cart?",
        "Sofa removed from list!",
        "Remove",
        { list: MutableList<Sofa>, position: Int ->
            Cart.instance.removeSofa(list[position])
        })
}