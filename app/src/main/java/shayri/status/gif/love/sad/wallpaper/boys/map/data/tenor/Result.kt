package shayri.status.gif.love.sad.wallpaper.boys.map.data.tenor


import com.google.gson.annotations.SerializedName

data class Result(
    val id: String,
    val title: String,
    @SerializedName("h1_title")
    val h1Title: String,
    val media: List<Media>,
    @SerializedName("bg_color")
    val bgColor: String,
    val created: Double,
    val itemurl: String,
    val url: String,
    val tags: List<Any>,
    val flags: List<Any>,
    val shares: Int,
    val hasaudio: Boolean,
    val hascaption: Boolean,
    @SerializedName("source_id")
    val sourceId: String,
    val composite: Any
)