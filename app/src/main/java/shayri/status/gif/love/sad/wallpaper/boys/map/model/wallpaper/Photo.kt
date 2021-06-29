package shayri.status.gif.love.sad.wallpaper.boys.map.model.wallpaper


import com.google.gson.annotations.SerializedName

data class Photo(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    @SerializedName("photographer_url")
    val photographerUrl: String,
    @SerializedName("photographer_id")
    val photographerId: Int,
    @SerializedName("avg_color")
    val avgColor: String,
    val src: Src
)