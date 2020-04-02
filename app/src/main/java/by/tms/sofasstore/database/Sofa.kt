package by.tms.sofasstore.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "newSofas")
data class Sofa(val name: String, val price: Double, val image: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    override fun toString(): String {
        return name
    }
}