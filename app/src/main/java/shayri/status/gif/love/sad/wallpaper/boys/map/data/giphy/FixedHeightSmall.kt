package shayri.status.gif.love.sad.wallpaper.boys.map.data.giphy


import com.google.gson.annotations.SerializedName

data class FixedHeightSmall(
    val height: String,
    val width: String,
    val size: String,
    val url: String,
    @SerializedName("mp4_size")
    val mp4Size: String,
    val mp4: String,
    @SerializedName("webp_size")
    val webpSize: String,
    val webp: String
)