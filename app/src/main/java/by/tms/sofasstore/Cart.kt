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

    fun selectSofa(sofa: Sofa) : Boolean {
        return if(list.contains(sofa)) {
            currentSofa = sofa
            true
        } else {
            false
        }
    }

    companion object {
        val instance = Cart()
    }
}