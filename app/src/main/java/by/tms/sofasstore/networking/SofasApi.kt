package by.tms.sofasstore.networking

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SofasApi {
    @GET("values/sofas!A:C")
    fun getSofas(
        @Query("key") key: String,
        @Query("valueRenderOption") valueRenderOption: String
    ) : Deferred<Response<Data>>
}