package shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DataFactory {


    val URL_ALL_APPS = BASE_URL + "1cjeDVcK15fmQfMFR9GPk-Vn3kshlPT-fOoivNMbM6s4/values/Sheet1!A2:E/"
    val URL_CAROUSEL_IMAGES = BASE_URL + "19up5GOYq4hVG99sfDgRmXZrjkosolZtiH858GC_CC7k/values/Sheet1!A2:E/"
    val URL_TOP_INTERNATIONAL = BASE_URL + "1SzIVZL0l_sZyYUmP2-lpv38sZGBGE4NhF5Mr2tQzNVs/values/Sheet1!A2:E/"
    val URL_INDIA = BASE_URL + "1Qzz66uLh0SgN5bgOVOOujeHR2hwp70mEChpcpZ02-u4/values/Sheet1!A2:E/"
    val URL_USA = BASE_URL + "1eSzMHeBNFGzejZa6ZWik8okXh18du7VNFhFNs8E-iQE/values/Sheet1!A2:E/"
    val URL_RUSSIA = BASE_URL + "1vg1IdR7Pm3faWNbtsPFedeqWUTOx2-i-dI0WjExoPb4/values/Sheet1!A2:E/"
    val URL_PAKISTAN = BASE_URL + "1PgVQJa3bXA4SkTn2I-rUgYgY838oqr6WRpkWDkCNBOc/values/Sheet1!A2:E/"
    val URL_CHINA = BASE_URL + "14dd_QuEb6EnvFbzNSsQVFNJP9lf86otzJYmqZCrGOJE/values/Sheet1!A2:E/"
    val URL_GERMANY = BASE_URL + "1w9RsBSk2eCXWrRQBJyUGTne9US9CDqljXwtlTYDlTzU/values/Sheet1!A2:E/"
    val URL_TURKEY = BASE_URL + "19YP9gRCRrOSONoOhKvVaCX0_NU8JYEhh9Tf7uwuQCrY/values/Sheet1!A2:E/"
    val URL_UAE = BASE_URL + "1FD307Pj8_AwuAo0jiLIlLgRWU_qw5Claw1WIwYpDhFI/values/Sheet1!A2:E/"
    val URL_ITALY = BASE_URL + "1icROXjlDRRJzKs0RDDN2ynpIXYy30bDqFsAU8PsUd-4/values/Sheet1!A2:E/"
    val URL_SWITZERLAND = BASE_URL + "1big-DGl8akYyPVnta2geu71dzOCZAYIELtdaTbqURQ4/values/Sheet1!A2:E/"
    val URL_CANADA = BASE_URL + "12hi5mu88JtX9Eqkz51RctvzhqbGOz1Ddg5JvRltBfQo/values/Sheet1!A2:E/"
    val URL_SINGAPORE = BASE_URL + "1nmyHhc8oLiqV9y5T5H8nQH9h2SqZQc0B73xhUhZRg7c/values/Sheet1!A2:E/"
    val URL_SOUTH_AFRICA = BASE_URL + "16VQo4QaWBJnEdHmrToy1f4cl-fporcLufd3-FNnpOTg/values/Sheet1!A2:E/"
    val URL_FRANCE = BASE_URL + "1ohFYSphdL-DVbTdhFuaqViDwqIVKVsRARtU8M3m4vM0/values/Sheet1!A2:E/"
    val URL_INDONECIA = BASE_URL + "1Y2O5X54s4mjnj8mrs5M6NGEkuY46moO7Qh_Lp6aWHNY/values/Sheet1!A2:E/"
    val URL_UK = BASE_URL + "1vB-JIQG0H91nHjl7G8WvXkuKmumof1zIEwbO9d1V7eM/values/Sheet1!A2:E/"
    val URL_JAPAN = BASE_URL + "1B0wGtPHQfmDGHLCS0Bf2okN6wNTPgYuIg-MEzeeLn4k/values/Sheet1!A2:E/"
    val URL_BRAZIL = BASE_URL + "1y8HM2xmqifSgtP3ZYDzJPJNvIuQ9ApJpoLzVy3UVAnI/values/Sheet1!A2:E/"
    val URL_NIGERIA = BASE_URL + "1BG9_HWRYJ7gI0T4uf34nUgLOdiWemCh5geVGG-fy2Yg/values/Sheet1!A2:E/"
    val URL_PORTUGAL = BASE_URL + "1FYi8x_bzUod9lqMD4YllcytNUMK14r3f2Y6p_7xPWFU/values/Sheet1!A2:E/"
    val URL_AUSTRALIA = BASE_URL + "1T-Ui9ReaAehnrSeRL_roL-3ueo2U49CPVRFFb16KTbg/values/Sheet1!A2:E/"
    val URL_GREECE = BASE_URL + "1mNi0qIf7J87pgl1d5fL4QEWaIE6jwBRqy8O9Qzv620U/values/Sheet1!A2:E/"
    val URL_CYPURS = BASE_URL + "1Lipgh92-HYqYledf9yeY7IYpO4O9yR3RgCttVN1DKVs/values/Sheet1!A2:E/"
    val URL_BELGIUM = BASE_URL + "1YLXw0mE58RjTNMceo3CcGwZ92g1ICBHNRg51-mICED4/values/Sheet1!A2:E/"
    val URL_MEXICO = BASE_URL + "1R4BcIp6bUTrIdt5iOUYHVFe7vVHefhoUMpccbSjklEA/values/Sheet1!A2:E/"
    val URL_SOUTH_KOREA = BASE_URL + "1T7ZRi_MSpY87YALFfxE92Gpi_lE1vNrkZtxpTPhhUlg/values/Sheet1!A2:E/"
    val URL_PHILIPPINES = BASE_URL + "1v1wGt4ncv670r5j4XdBWUQ0PrmuP12OdnE-ThF0mbY0/values/Sheet1!A2:E/"
    val URL_ARGENTINA = BASE_URL + "1kZ57GYkOw9su107qPk1udbGEmZ8w_Q_pt7UTa_hi77Y/values/Sheet1!A2:E/"
    val URL_HONG_KONG = BASE_URL + "1W562GnTv-xupxLMEA9yzOmRqEirwFRmuThCTGsxUOEY/values/Sheet1!A2:E/"
    val URL_SAUDI_ARABIA = BASE_URL + "1_AHwTboR2AfKYUdfiuo0e4nnwaCi4iUCi2m5SfDK2d8/values/Sheet1!A2:E/"
    val URL_KENYA = BASE_URL + "1ERbO4w4y9nLOnJXI5kT6NkNprkpP-C-52JTExfpsVSQ/values/Sheet1!A2:E/"
    val URL_DENMARK = BASE_URL + "1xAMezzRxJpy8_9RDJEunSOer0d4jQRhzwbg3S-wABCc/values/Sheet1!A2:E/"

    val URL_RENT_ALL_APPS = BASE_URL + "19up5GOYq4hVG99sfDgRmXZrjkosolZtiH858GC_CC7k/values/Sheet1!A2:E/"
    val URL_RENT_CAROUSEL_IMAGES = BASE_URL + "1cjeDVcK15fmQfMFR9GPk-Vn3kshlPT-fOoivNMbM6s4/values/Sheet1!A2:E/"
    val URL_RENT_EXPLORER_IMAGES = BASE_URL + "1SzIVZL0l_sZyYUmP2-lpv38sZGBGE4NhF5Mr2tQzNVs/values/Sheet1!A2:E/"

    val URL_NEWS = BASE_URL + "1EXRh07IGeiXOG1JUWtemGp6xtCXnwVEQrSFhesiX0kw/values/Sheet1!A2:E/"
    val URL_STOCK = BASE_URL + "1RG5gjNxHgaex0jBzneylojV94_2Nx25Qozy9zF9u5IA/values/Sheet1!A2:E/"
    val URL_CURRENCY = BASE_URL + "1yeQwlsfcc_3hgLEEULbD7IK4FtCWfFwbDxjao8-xGQo/values/Sheet1!A2:E/"
    val URL_CRYPTOCURRENCY = BASE_URL + "1KpGkUw7JXDMuYg3KsS6sQhm3AZ1vuIUlDM3-UPpUcuU/values/Sheet1!A2:E/"
    val URL_WEATHER_NEWS = BASE_URL + "1fB3Yy3i6izQybhHJhbZdZsFKnIi7KS4MqAVdHrxLCc8/values/Sheet1!A2:E/"
    val URL_WORLD_TOUR = BASE_URL + "1fB3Yy3i6izQybhHJhbZdZsFKnIi7KS4MqAVdHrxLCc8/values/Sheet1!A2:E/"
    val URL_LIVE_NEWS_CHANNELS = BASE_URL + "1moZsfGxYoBJrzSI4FLGSzlCgTo8Uiufmp4ppHt3CHgY/values/Sheet1!A2:E/"
    val URL_MEDICINE = BASE_URL + "1Qo31dfuPUmNa-Pd5vWMaxcidDSMRbhtE03wc8Z6ApoQ/values/Sheet1!A2:E/"
    val URL_HEALTH = BASE_URL + "1Jm6nChQHXH6zE9x7afocawOHEDeRIbjsI9f975-a2jg/values/Sheet1!A2:E/"
    val URL_FITNESS = BASE_URL + "1tpf4sZastciSSD9aOkOfAgS8XS_OKC6D0ENRPT-YxSs/values/Sheet1!A2:E/"
    val URL_HEALTHYFOOD = BASE_URL + "1kykyy8nh8Z72udd6qanWSFeA3TlIoZWpvpD6QXru0Qo/values/Sheet1!A2:E/"
    val URL_FOOD = BASE_URL + "1T3wq8Jd1QiTlIuLWPw6nt-V4y9aSImIyOfmZyglqR4Q/values/Sheet1!A2:E/"
    val URL_SHOPPING = BASE_URL + "1e1NP43-3K73b7kk-CQM41J_8EXmaEQjk6PjL_YVFx3Q/values/Sheet1!A2:E/"

    val URL_MOST_USEFUL_APPS = BASE_URL + "1cyp9bLtxqdbe_9zCvd8Igwnids4DffpMqo3M9ssRtgk/values/Sheet1!A2:E/"



    val URL_WEIGHT = BASE_URL + "1xo-dgBKr--VwRxTmaEMwXIJPw9Njn0KGLqt4958EB3Q/values/Sheet1!A2:E/"
    val URL_TEST = BASE_URL + "1BWJqfLtS7Rp5yyy3t9xnJdi8AGCYRo-_VQ1siXsu83k/values/Sheet1!A2:E/"
    val URL_SYMPTOMS = BASE_URL + "1npZ9KptW-6mnXN96XlCXHDETSieDrFIENcY8qpTyjQg/values/Sheet1!A2:E/"
    val URL_PREGNANCY_CALENDER = BASE_URL + "10tQK2GCZP1lWbeul-7QVnrWUoCsWaKIh0AyvTWx5QhE/values/Sheet1!A2:E/"
    val URL_PRECAUTIONS = BASE_URL + "1CqMG94ipgUDgeRXBfeFn3ws_EJ6AafGthceQLfdVmf8/values/Sheet1!A2:E/"
    val URL_MISCARRIAGE = BASE_URL + "1i_cI9XqOBnIwAuI8qBg6g3DSC_o65CuMU8JxwC9zsPI/values/Sheet1!A2:E/"
    val URL_EXERCIES = BASE_URL + "1fW45GI95Se9_Dwtabf8bSg9wUYSlr7LfcYJmqHun3Ro/values/Sheet1!A2:E/"
    val URL_DUEDATE = BASE_URL + "1oFapDs461a1cwa4S7hMl4gpR-D50b6LWAVHYrRwZLL8/values/Sheet1!A2:E/"
    val URL_DIET = BASE_URL + "1L73hrbhGKyw0uOoYLrGbqT3SSHoQqpQ9xLF5ClZijTk/values/Sheet1!A2:E/"
    val URL_CALCULATOR = BASE_URL + "1Ioe81i4B5-ASx2HpRxKbog0IYd5W2U5ZHvOOOYvC4tQ/values/Sheet1!A2:E/"
    val URL_AFTER = BASE_URL + "1d3i0u1oF0UixklFJEZtU6LJ6pBpgP4fl7jI6brMqhos/values/Sheet1!A2:E/"
    val URL_ADVICE = BASE_URL + "1Y1d599EdzIm8BQRZIiUEdgsn9LvrzUJqR1JNlLXGRnI/values/Sheet1!A2:E/"

    val URL_Poems = BASE_URL + "1Ju4MseNZq7K5YLX8CQsOpv0zSNl5jg3hx0MBtocHA1s/values/Sheet1!A2:E/"

    val URL_LOVE = BASE_URL + "1tyrHnVJ92SmU8NlkgmTUQKexO5kBhWkK7mtNJ_6oVjQ/values/Sheet1!A2:E/"
    val URL_HURT = BASE_URL + "1xCHTyNVIhLR9GEXUBo61dbs-WEc7pvKtfQUkrfjCOUA/values/Sheet1!A2:E/"
    val URL_ATTITUDE = BASE_URL + "1Llby50HDuqsSTxyQwzTbmqdppZF01QydFqBAsbwx6ZY/values/Sheet1!A2:E/"
    val URL_GHALIB = BASE_URL + "1VeqTmzuFEoz2qU1GSurLHQTzZDIC7zfOH6cPSN9Drfc/values/Sheet1!A2:E/"
    val URL_FUNNY = BASE_URL + "1jfXMthutR2Y7BjGkfuCa50CpkgCWfE2_qs3mnHG-RV0/values/Sheet1!A2:E/"
    val URL_FRIENDSHIP = BASE_URL + "1LLl_ACdhqPhtkL7PpNKZ9JkPMX3omNbfLRjsDjRbDQM/values/Sheet1!A2:E/"


    //labtest
    val URL_BOOKLAB = BASE_URL + "1NLIiBZk7A6WrAx_CD9ctlvhqGheFssJL_tHoICf5FpM/values/Sheet1!A2:E/"
    val URL_TESTRESULT = BASE_URL + "14eqkWQ2w7yWw5oYNDIcJyjvEp-58y5stiW0nm98htnY/values/Sheet1!A2:E/"



    val KEY = "AIzaSyCANMG2wzSKUeM8AbqQ14Zj48VK5-cBQzI"



//    val URL_ALL_APPS = BASE_URL + "13Xg13-BQ1yQea2g5C95xtHqQkWKRkHfX4PvjaEYqn8I/values/Sheet1!A2:F/"
//    val URL_CAROUSEL_IMAGES = BASE_URL + "1P91BnfdAOvsLh9QgYQVo5WAMPS-df9U1vKCuuc0TbDQ/values/Sheet1!A2:E/"
    val URL_TRENDING_DATA = BASE_URL + "1ToBlwN9pm4JPPS-e2KKJSRVbadDRBWMOOLzwV9udjuo/values/Sheet1!A2:E/"
    val URL_DEALS_DATA = BASE_URL + "1UG9ZrSR-lEuTgV1zb0yM2pYEdrucTzERXoMBYgxql1E/values/Sheet1!A2:F/"
//category fragment

    val URL_SUPER_MART = BASE_URL + "1Pb0vJsPG16rki9QtF5OCyMmBdgaY0Wgk09jJb0ddv5A/values/Sheet1!A2:E/"
    val URL_GROCERIES = BASE_URL + "1pC9fx0R3_TGG5mCBPh2yl0_2Vr_Yywwq_Ojy_1Qatxk/values/Sheet1!A2:E/"
    val URL_MEDICINES = BASE_URL + "17ttzAoxxs8rxGFHenQin3aXICwHucZtGcK6dMKTFwzA/values/Sheet1!A2:E/"
    val URL_SUPPLEMENTS = BASE_URL + "1N67-w11DMwUfjZw-6pvfKAcz-Pcm-zfR-5VqoqpuZU8/values/Sheet1!A2:E/"
    val URL_ELECTRONICS = BASE_URL + "1QE9ecuwghMRtqJFjdyP4nZ8J3frF-A-CgxmWN424b94/values/Sheet1!A2:E/"
    val URL_BEAUTY = BASE_URL + "1ZyMVKi-6YSt-_vTL18oq9cTE1eJ4WL23n8AvKHV575o/values/Sheet1!A2:E/"
    val URL_JEWELLERY = BASE_URL + "1ianEwf6Kyg_6s7Nf8h4rGuZNTl3txgP98zhQ2G8lRDk/values/Sheet1!A2:E/"
    val URL_KITCHEN_APPLIANCES = BASE_URL + "1qEDi_ZrxGxtf0XyxPgF8uajafpLFQpMEBbhccn-Bqeg/values/Sheet1!A2:E/"
    val URL_KIDS_LIFESTYLE = BASE_URL + "1l1xn8wZ3xasK_dnZG0vg9dxBK3GTvql9hnv1eAZUS_Q/values/Sheet1!A2:E/"
    val URL_BABY_TOYS = BASE_URL + "1CelxNRIll_tx1OuJ1fI_zMqPRchzFW8lqcnLg9zUjDI/values/Sheet1!A2:E/"
    val URL_LINGERIE = BASE_URL + "1wMz9aZK6QwqRQGmxIAgDlz17SCxUtBqvoHnogcDoQdw/values/Sheet1!A2:E/"
    val URL_MEN_INNER_WEAR = BASE_URL + "1fxzMXHgCNKiCBf_rSygl-G5NII0Cn50O9s2tMODZTCE/values/Sheet1!A2:E/"
    val URL_BOOKS = BASE_URL + "1MbdKtCpvxl-GnW0rRbNKqx8ZQV2kaAPSSOlrcCfe1-s/values/Sheet1!A2:E/"
    val URL_FOOTWEAR = BASE_URL + "1wqxy40TFnBy9F8pG6jNMq0fJ65-d7YNin7A-XzdiCDI/values/Sheet1!A2:E/"


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