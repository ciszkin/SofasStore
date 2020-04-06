package by.tms.sofasstore

import by.tms.sofasstore.database.Sofa

class Cart {
    private val list = ArrayList<Sofa>()
    private var total = 0.0

    fun addSofa(sofa: Sofa) : Boolean{
        return if(list.contains(sofa)) {
            false
        } else {
            list.add(sofa)
            total = list.sumByDouble { it.price }
            true
        }
    }

    fun removeSofa(sofa: Sofa): Boolean {
        return if(list.contains(sofa)) {
            list.remove(sofa)
            total = list.sumByDouble { it.price }
            true
        } else {
            false
        }
    }

    fun getList() : MutableList<Sofa> {
        return list
    }

    fun getTotal() : Double {
        return total
    }

    companion object {
        val instance = Cart()
    }
}