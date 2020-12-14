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
    val URL_INDIA= BASE_URL+"1Sw9TRb-QXYRiVr0mLqEQgt2e4jlMvkAaGBvy6Dws9dQ/values/Sheet1!A2:E/"
    val URL_RUSSIA= BASE_URL+"1JIh5rHlijJRidBoQXOay-KPwS2m4YckhxN2qB6SQh94/values/Sheet1!A2:E/"
    val URL_PAKISTAN= BASE_URL+"1pjuaQ6cDQt1KCZVylzckhPdkrJ3tJkx7RPWehis81zg/values/Sheet1!A2:E/"
    val URL_CHINA= BASE_URL+"14wmPXXCU2oRyPNp4kJUnAuo6J4ziVs2pRYGeHmq0eHg/values/Sheet1!A2:E/"
    val URL_GERMANY= BASE_URL+"1iqYV2HGVdUEzXWO2O72BtbOv7FSo0O27yqi9f-CGVOg/values/Sheet1!A2:E/"
    val URL_TURKEY= BASE_URL+"1e7Vis671g_udhfh1-RoQ-_VlmQIUHE-WV7lS5NRhgks/values/Sheet1!A2:E/"
    val URL_UAE= BASE_URL+"1JzTpUJbw16mylSIeLC2iZStgUAOCogPYK6boM9AZEdk/values/Sheet1!A2:E/"
    val URL_USA= BASE_URL+"1lptekCtOr2_F09OjjZ6yc8iteyqm50q5l7905JjKonc/values/Sheet1!A2:E/"
    val URL_ITALY= BASE_URL+"1ibrq8ZLD_nLGfSgVv_EGN7tsSLieRRgX4aq6BrjjaQo/values/Sheet1!A2:E/"
    val URL_SWITZERLAND= BASE_URL+"1GLjen72To70fdMmCWWe2wehh4xwWTgnDPydy9fN6MIs/values/Sheet1!A2:E/"
    val URL_CANADA= BASE_URL+"1MD5Yn5BYXltYNxXLTxeIwx01NDlH0MeCknanXxcYhRY/values/Sheet1!A2:E/"
    val URL_SINGAPORE= BASE_URL+"1HeBWrMcEFbko_gLGRLTqAce4cBHmT6fxS2enLttfOkM/values/Sheet1!A2:E/"
    val URL_SOUTH_AFRICA= BASE_URL+"1O2gH9Q5RgIpAr_UG0tSaCBuY7lBwRMcGMIvbhcSqesk/values/Sheet1!A2:E/"
    val URL_FRANCE= BASE_URL+"1pKvUmWEl4xHVv2PK7S44YA70gTo3CKPaTF84xkXivos/values/Sheet1!A2:E/"
    val URL_INDONESIA= BASE_URL+"1-wAMeMNT3tMBwV_y-ZKysE0dsG99eeVAy9cB-6UG66Y/values/Sheet1!A2:E/"
    val URL_UK= BASE_URL+"1WhRv_Dx582FLu_9Mm_pkUpfTEJsggCMXbhdyH74OZO4/values/Sheet1!A2:E/"
    val URL_JAPAN= BASE_URL+"1PqPSVjmHSjkrEkRBKDs8SowQsxebfndlYaOy9gwhlak/values/Sheet1!A2:E/"
    val URL_BRAZIL= BASE_URL+"1cQAglnc1aKTfDWT2y2Z286sZnsFcz0n3jzT33qSbPJU/values/Sheet1!A2:E/"
    val URL_NIGERIA= BASE_URL+"1TUx4R3ESLQl7crqJqEoE5nYNXfrJQSpc2WNk_O73O6k/values/Sheet1!A2:E/"
    val URL_PORTUGUL= BASE_URL+"1Fx8_LoMLTghKA-G-cwMy-kq4OIhse6xuoJ7vm59Zu_w/values/Sheet1!A2:E/"
    val URL_AUSTRALIA= BASE_URL+"1FHMyGV1nI-uKP6-_8Kcdfncyasgwi1NPBwQEc8RQQCQ/values/Sheet1!A2:E/"
    val URL_GREECE= BASE_URL+"1bCKNpvhI9OXA55RRioMRvqYbmX2vgdqhD19bh3gV2Tw/values/Sheet1!A2:E/"
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