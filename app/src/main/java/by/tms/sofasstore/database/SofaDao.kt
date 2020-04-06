package by.tms.sofasstore.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SofaDao {
    @Insert
    suspend fun addSofa(sofas: List<Sofa>)

    @Query("SELECT * FROM newSofas")
    suspend fun getAllSofas() : List<Sofa>
}

