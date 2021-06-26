package shayri.status.gif.love.sad.wallpaper.boys.map.data.giphy


import com.google.gson.annotations.SerializedName

data class Images(
    val original: Original,
    val downsized: Downsized,
    @SerializedName("downsized_large")
    val downsizedLarge: DownsizedLarge,
    @SerializedName("downsized_medium")
    val downsizedMedium: DownsizedMedium,
    @SerializedName("downsized_small")
    val downsizedSmall: DownsizedSmall,
    @SerializedName("downsized_still")
    val downsizedStill: DownsizedStill,
    @SerializedName("fixed_height")
    val fixedHeight: FixedHeight,
    @SerializedName("fixed_height_downsampled")
    val fixedHeightDownsampled: FixedHeightDownsampled,
    @SerializedName("fixed_height_small")
    val fixedHeightSmall: FixedHeightSmall,
    @SerializedName("fixed_height_small_still")
    val fixedHeightSmallStill: FixedHeightSmallStill,
    @SerializedName("fixed_height_still")
    val fixedHeightStill: FixedHeightStill,
    @SerializedName("fixed_width")
    val fixedWidth: FixedWidth,
    @SerializedName("fixed_width_downsampled")
    val fixedWidthDownsampled: FixedWidthDownsampled,
    @SerializedName("fixed_width_small")
    val fixedWidthSmall: FixedWidthSmall,
    @SerializedName("fixed_width_small_still")
    val fixedWidthSmallStill: FixedWidthSmallStill,
    @SerializedName("fixed_width_still")
    val fixedWidthStill: FixedWidthStill,
    val looping: Looping,
    @SerializedName("original_still")
    val originalStill: OriginalStill,
    @SerializedName("original_mp4")
    val originalMp4: OriginalMp4,
    val preview: Preview,
    @SerializedName("preview_gif")
    val previewGif: PreviewGif,
    @SerializedName("preview_webp")
    val previewWebp: PreviewWebp,
    @SerializedName("480w_still")
    val wStill: WStill
)