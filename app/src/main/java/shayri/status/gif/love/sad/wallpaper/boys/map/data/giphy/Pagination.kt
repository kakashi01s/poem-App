package shayri.status.gif.love.sad.wallpaper.boys.map.data.giphy


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("total_count")
    val totalCount: Int,
    val count: Int,
    val offset: Int
)