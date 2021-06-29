package shayri.status.gif.love.sad.wallpaper.boys.map.view.apiService

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import shayri.status.gif.love.sad.wallpaper.boys.map.data.giphy.GiphyData
import shayri.status.gif.love.sad.wallpaper.boys.map.data.tenor.TenorData


const val BASE_URL_GIF = "https://api.giphy.com"
interface GifInterface {
    @GET("/v1/gifs/search?api_key=z0BwuPB28KRiZffV0mmn9so7ntuNVHQS&limit=25&offset=0&rating=g&lang=en")
    fun getGif(@Query("q") q: String) : Call<GiphyData>


}
object gifService {
    val GifInstance : GifInterface
    init {
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_GIF)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        GifInstance = retrofit.create(GifInterface::class.java)
    }
}