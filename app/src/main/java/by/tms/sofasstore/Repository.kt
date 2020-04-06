package by.tms.sofasstore

import android.content.Context
import androidx.room.Room
import by.tms.sofasstore.database.SofaDatabase
import by.tms.sofasstore.database.Sofa
import by.tms.sofasstore.networking.ApiFactory
import by.tms.sofasstore.ui.sofas
import kotlinx.coroutines.*

class Repository(context: Context) {

    private val database by lazy {
        Room.databaseBuilder(context, SofaDatabase::class.java, "SofasDb").build()
    }

    suspend fun fillCatalog(): ArrayList<Sofa> {
        var sofasList: ArrayList<Sofa>
        database.getSofaDao().apply {
            sofasList = database.getSofaDao().getAllSofas() as ArrayList<Sofa>
            if (sofasList.isEmpty()) {

                val data = ApiFactory.getSofasApi().getSofas(
                    ApiFactory.KEY,
                    ApiFactory.VALUE_RENDER_OPTION
                ).await()

                data.body()?.values?.forEach {
                    val sofa = Sofa(it[0] as String, it[1] as Double, it[2] as String)
                    sofasList.add(sofa)
                    database.getSofaDao().addSofa(mutableListOf(sofa))
                }
            }
        }

        sofas = sofasList

        return sofasList
    }

    companion object {
        fun getInstance(context: Context) = Repository(context)
    }
}