package shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.data

import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.model.AllAppsModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface DataService {
    @GET
    fun fetchAllApps(@Url url: String, @Query("key") key: String): Observable<AllAppsModel>

}