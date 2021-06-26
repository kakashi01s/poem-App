package shayri.status.gif.love.sad.wallpaper.boys.map.data.giphy


import com.google.gson.annotations.SerializedName

data class GiphyData(
    val `data`: List<Data>,
    val pagination: Pagination,
    val meta: Meta
)