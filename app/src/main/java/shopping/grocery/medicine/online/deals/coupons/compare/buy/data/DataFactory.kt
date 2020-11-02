package shopping.grocery.medicine.online.deals.coupons.compare.buy.data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DataFactory {

    val URL_ALL_APPS = BASE_URL + "13Xg13-BQ1yQea2g5C95xtHqQkWKRkHfX4PvjaEYqn8I/values/Sheet1!A2:E/"
    val URL_CAROUSEL_IMAGES = BASE_URL + "1P91BnfdAOvsLh9QgYQVo5WAMPS-df9U1vKCuuc0TbDQ/values/Sheet1!A2:E/"
    val URL_TRENDING_DATA = BASE_URL + "1ToBlwN9pm4JPPS-e2KKJSRVbadDRBWMOOLzwV9udjuo/values/Sheet1!A2:E/"
    val URL_DEALS_DATA = BASE_URL + "1UG9ZrSR-lEuTgV1zb0yM2pYEdrucTzERXoMBYgxql1E/values/Sheet1!A2:F/"
    val URL_SUPER_MART = BASE_URL + "1SbahrVVK5vLlbtOmXVaAQ-Wu1XGBgnKm7Jba3hVtCQQ/values/Sheet1!A2:E/"
    val URL_GROCERIES = BASE_URL + "185LuxhBUdcLoYeLW-ccvJqLbmGJvMuD9w6cWBcB2zmc/values/Sheet1!A2:E/"
    val URL_MEDICINES = BASE_URL + "1xbD4KUC7w2RGI7NAt0bHqK0dcBGaHEUe0BFYQ1MGGEM/values/Sheet1!A2:E/"
    val URL_SUPPLEMENTS = BASE_URL + "1EnsB2WMUckve7ZDJoJXz2jf94YhZ2zgdwJ_Z-jRxBls/values/Sheet1!A2:E/"
    val URL_ELECTRONICS = BASE_URL + "1sOUNYrEpueSvHjNePepso6g82LBXiZOTvYgIaEe6VRQ/values/Sheet1!A2:E/"
    val URL_BEAUTY = BASE_URL + "1pL9ykk83M5SaoW3E5i6__fvUGEu5l5hA8AM_IvUpwiw/values/Sheet1!A2:E/"
    val URL_JEWELLERY = BASE_URL + "1gp-XjaR8N6rEppQ94eigyZldOqLP1NwOHlG-W8HsEls/values/Sheet1!A2:E/"
    val URL_KITCHEN_APPLIANCES = BASE_URL + "1phfZA0KytaN0xlBlxCC4EGtUFk-oUwrcZLwv-v0V6yw/values/Sheet1!A2:E/"
    val URL_KIDS_LIFESTYLE = BASE_URL + "1IstfLiD74mnX7tmhX_SN86rdtmGqgVui-RLaaa-RK_s/values/Sheet1!A2:E/"
    val URL_BABY_TOYS = BASE_URL + "1fQTSMqtZm-kZSH2J2phaLcvVhKa7zNA--H9P-gRCiTo/values/Sheet1!A2:E/"
    val URL_LINGERIE = BASE_URL + "1Q0JBQZrHGXu3qweHagaZUoqObxedRb62PlRZ7VOl84A/values/Sheet1!A2:E/"
    val URL_MEN_INNER_WEAR = BASE_URL + "16J4Y9iQYUwTRC9WfKRpSnBHVfRce7EryAAxMWI8nN9Y/values/Sheet1!A2:E/"
    val URL_BOOKS = BASE_URL + "1Z8SBvM18gKSL-3Qi1Q38P3ZiR5IlbES1rhx2-7mDp7k/values/Sheet1!A2:E/"
    val URL_FOOTWEAR = BASE_URL + "1LT8o9_K99NwenzLfDESJApP90KMrfX1bQpym4oq8Ebo/values/Sheet1!A2:E/"
    val KEY = "AIzaSyDdX1pz2bBXYlZFHqlAWUI83v0_j2xOuFg"

    companion object{
        private val BASE_URL = "https://sheets.googleapis.com/v4/spreadsheets/"

        fun create(): DataService? {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(DataService::class.java)
        }
    }
}