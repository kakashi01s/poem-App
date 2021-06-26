package shayri.status.gif.love.sad.wallpaper.boys.map.data.giphy


import com.google.gson.annotations.SerializedName

data class OriginalMp4(
    val height: String,
    val width: String,
    @SerializedName("mp4_size")
    val mp4Size: String,
    val mp4: String
)