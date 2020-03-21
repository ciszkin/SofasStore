package by.tms.sofasstore

class Cart {
    private val list = ArrayList<Sofa>()
    var currentSofa : Sofa? = null

    fun addSofa(sofa: Sofa) : Boolean{
        return if(list.contains(sofa)) {
            false
        } else {
            list.add(sofa)
            true
        }
    }

    fun removeSofa(sofa: Sofa): Boolean {
        return if(list.contains(sofa)) {
            list.remove(sofa)
            true
        } else {
            false
        }
    }

    fun getList() : List<Sofa> {
        return list
    }

    fun getTotal() : Double {
        return if(list.isNotEmpty()) list.sumByDouble { it.price } else 0.0
    }

    companion object {
        val instance = Cart()
    }
}