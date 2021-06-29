package shayri.status.gif.love.sad.wallpaper.boys.map.model.wallpaper


import com.google.gson.annotations.SerializedName

data class WallpaperResponse(
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val photos: List<Photo>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("next_page")
    val nextPage: String
)