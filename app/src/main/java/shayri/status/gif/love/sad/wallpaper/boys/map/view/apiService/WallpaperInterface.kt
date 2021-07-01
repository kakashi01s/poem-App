package shayri.status.gif.love.sad.wallpaper.boys.map.view.apiService

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import shayri.status.gif.love.sad.wallpaper.boys.map.model.wallpaper.WallpaperResponse

const val BASE_URL_wallpaper = "https://api.pexels.com"
const val YOUR_API_KEY = "563492ad6f91700001000001b6cd1e8758114230b79c57a5d693807b"

interface WallpaperInterface {
    @Headers("Authorization: $YOUR_API_KEY")
    @GET("/v1/search/?page=1&per_page=79")
    fun getWallpaper(@Query("query") query: String) : Call<WallpaperResponse>


}
object wallpaperService {
    val WallpaperInstance : WallpaperInterface
    init {
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_wallpaper)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        WallpaperInstance = retrofit.create(WallpaperInterface::class.java)
    }
}