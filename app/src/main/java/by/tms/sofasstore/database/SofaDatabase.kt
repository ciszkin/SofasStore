package by.tms.sofasstore.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Sofa::class], version = 1)
abstract class SofaDatabase : RoomDatabase() {

    abstract fun getSofaDao() : SofaDao
}