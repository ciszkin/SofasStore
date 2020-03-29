package by.tms.sofasstore

class CatalogCollection {
//    val sofas = ArrayList<Sofa>()
    var currentSofa : Sofa? = null
    var sofas = mutableListOf(
    Sofa("Apollo", 1500.0, R.drawable.apollo),
    Sofa("Charm", 700.0, R.drawable.charm),
    Sofa("Club", 550.0, R.drawable.club),
    Sofa("Conza", 1020.0, R.drawable.conza),
    Sofa("Corona", 3000.0, R.drawable.corona),
    Sofa("Daisy", 2100.0, R.drawable.daisy),
    Sofa("Easton", 970.0, R.drawable.easton),
    Sofa("FRIHETEN", 510.0, R.drawable.friheten),
    Sofa("GUBI", 830.0, R.drawable.gubi),
    Sofa("Jazz", 700.0, R.drawable.jazz),
    Sofa("LANDSKRONA", 2200.0, R.drawable.landskrona),
    Sofa("Lagoon", 760.0, R.drawable.lagoon),
    Sofa("Misha", 350.0, R.drawable.misha),
    Sofa("PebbleGray", 570.0, R.drawable.pebble_gray),
    Sofa("SantaFe", 425.0, R.drawable.santa_fe),
    Sofa("Sperry", 680.0, R.drawable.sperry),
    Sofa("Zinc", 500.0, R.drawable.zinc)
)
    companion object {
        val instance = CatalogCollection()
    }
}