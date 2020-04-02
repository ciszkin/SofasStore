package by.tms.sofasstore.networking

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create


object ApiFactory {
    private const val BASE_URL = "https://sheets.googleapis.com/v4/spreadsheets/1HK4eDZLIeRpmm15LTUa0ErQEqfd1hSwQhh1PnzzfV3c/"
    const val KEY = "AIzaSyBzg-h7SZRMDlnTwCXu3xIlV4Fm8aqkQHE"
    const val VALUE_RENDER_OPTION = "UNFORMATTED_VALUE"
    const val MAJOR_DIMENSION = "COLUMNS"

    fun getSofasApi() : SofasApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create()
    }
}