package shayri.status.gif.love.sad.wallpaper.boys.map.view

import android.app.WallpaperManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import java.io.IOException

class FullimageActivity : AppCompatActivity() {
    var imgurl : String? = null
    var fullimg : PhotoView? = null
    var setwallpaper : Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullimage)

        fullimg = findViewById(R.id.full_image)
        setwallpaper = findViewById(R.id.set_wallpaper)
        InitData()
        Glide.with(this)
            .load(imgurl)
            .into(fullimg!!)

        setwallpaper?.setOnClickListener {
            val wallpaperManager : WallpaperManager = WallpaperManager.getInstance(this)
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val bitmap : Bitmap = fullimg!!.drawable.toBitmap()
                    withContext(Dispatchers.IO){wallpaperManager.setBitmap(bitmap)}
                    Toast.makeText(this@FullimageActivity,"Wallpaper Set Successfully",Toast.LENGTH_SHORT).show()
                }
                catch (e: IOException){
                    Toast.makeText(this@FullimageActivity,"error",Toast.LENGTH_SHORT).show()
                    Log.d("wallpaperset", e.localizedMessage)
                }
            }

        }
    }

    private fun InitData() {
        val bundle: Bundle? = intent.extras
        imgurl = bundle?.getString("url")
        Log.d("TAG", "initData: " + bundle?.getString("url"))
    }
}