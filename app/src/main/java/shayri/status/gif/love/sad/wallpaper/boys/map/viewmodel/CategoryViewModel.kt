package shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.ringtone
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.data.DataFactory
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.data.DataService

class CategoryViewModel : ViewModel() {

    var healthLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var sportsLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var medicineLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var healthyfoodLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var fitnessLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var mostUsefulAppsData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var foodData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var shoppingData: MutableLiveData<List<List<String>>?> = MutableLiveData()


    private var context: Context? = null
    var compositeDisposable: CompositeDisposable? = null

    fun loadData() {
        Log.d("TAG", "loadData: ")
        compositeDisposable = CompositeDisposable()
        fetchSports()

        fetchmedicine()
        fetchhealth()
        fetchfitness()
        fetchhealthyfood()
        fetchMostUsefulApps()
        fetchfood()
        fetchshopping()
    }

    private fun fetchshopping() {
        Log.d("TAG", "fetchshopping: ")
        val ringtone: ringtone? = ringtone.get()
        val dataService: DataService? = ringtone!!.getDataService()


        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_SHOPPING, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchSports Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchSports Response ${t.getValues()}")
                changeSportsDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchfood() {
        Log.d("TAG", "fetchfood: ")
        val ringtone: ringtone? = ringtone.get()
        val dataService: DataService? = ringtone!!.getDataService()


        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_FOOD, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchSports Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchSports Response ${t.getValues()}")
                changeSportsDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchSports() {
        Log.d("TAG", "fetchSports: ")
        val ringtone: ringtone? = ringtone.get()
        val dataService: DataService? = ringtone!!.getDataService()


        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_NEWS, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchSports Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchSports Response ${t.getValues()}")
                changeSportsDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }


    private fun fetchfitness() {
        Log.d("TAG", "fetchEntertainment: ")

        val ringtone: ringtone? = ringtone.get()
        val dataService: DataService? = ringtone!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_FITNESS, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchEntertainment Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchEntertainment Response ${t.getValues()}")
                changefitnessDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchhealth() {
        Log.d("TAG", "fetchPolitics: ")

        val ringtone: ringtone? = ringtone.get()
        val dataService: DataService? = ringtone!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_HEALTH, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchPolitics Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchPolitics Response ${t.getValues()}")
                changehealthDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchhealthyfood() {
        Log.d("TAG", "fetchWeather: ")

        val ringtone: ringtone? = ringtone.get()
        val dataService: DataService? = ringtone!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_HEALTHYFOOD, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchWeather Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchWeather Response ${t.getValues()}")
                changehealthyfoodDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchmedicine() {
        Log.d("TAG", "fetchHealth: ")

        val ringtone: ringtone? = ringtone.get()
        val dataService: DataService? = ringtone!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_MEDICINE, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchHealth Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchHealth Response ${t.getValues()}")
                changemedicineDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchMostUsefulApps() {
        Log.d("TAG", "fetchMostUsefullApps: ")
        val ringtone: ringtone? = ringtone.get()

        val dataService by lazy {
            DataFactory.create()
        }

        val disposable: Disposable?
        disposable =
            dataService?.fetchAllApps(DataFactory().URL_MOST_USEFUL_APPS, DataFactory().KEY)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.doOnError(Consumer { t ->
                    Log.d("TAG", "fetchMostUsefulApps Error ${t.localizedMessage}")
                })
                ?.subscribe(Consumer { t ->
                    Log.d("TAG", "fetchMostUsefulApps Response ${t.getValues()}")
                    changeMostUsefulAppsSet(t.getValues())
                })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }


    fun changeSportsDataSet(allAppsList: List<List<String>>?) {
        sportsLiveData.value = allAppsList
    }

    fun changehealthDataSet(allAppsList: List<List<String>>?) {
        healthLiveData.value = allAppsList
    }

    fun changehealthyfoodDataSet(allAppsList: List<List<String>>?) {
        healthyfoodLiveData.value = allAppsList
    }

    fun changemedicineDataSet(allAppsList: List<List<String>>?) {
        medicineLiveData.value = allAppsList
    }

    fun changefitnessDataSet(allAppsList: List<List<String>>?) {
        fitnessLiveData.value = allAppsList
    }

    fun changeMostUsefulAppsSet(allAppsList: List<List<String>>?) {
        mostUsefulAppsData.value = allAppsList
    }


    private fun unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
            compositeDisposable!!.dispose()
        }
    }

    fun reset() {
        unSubscribeFromObservable()
        compositeDisposable = null
        context = null
    }
}
//    var superMartLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
//    var groceriesLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
//    var medicinesLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
//    var supplementsLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
//    var electronicsLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
//    var beautyLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
//    var jewelleryLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
//    var kitchenAppliancesLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
//    var kidsLifestyleLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
//    var babyToysLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
//    var lingerieLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
//    var menInnerWearLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
//    var booksLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
//    var footwearLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
//    private var context: Context? = null
//    var compositeDisposable: CompositeDisposable? = null
//
//    fun loadData(){
//        Log.d("TAG", "loadData: ")
//        compositeDisposable = CompositeDisposable()
//        fetchSuperMart()
//        fetchGroceries()
//        fetchMedicines()
//        fetchSupplements()
//        fetchElectronics()
//        fetchBeauty()
//        fetchJewellery()
//        fetchKitchenAppliances()
//        fetchKidsLifestyle()
//        fetchBabyToys()
//        fetchLingerie()
//        fetchMenInnerWear()
//        fetchBooks()
//        fetchFootwear()
//    }
//
//    private fun fetchSuperMart(){
//        Log.d("TAG", "fetchSuperMart: ")
//        val singleton: Singleton? = Singleton.get()
//        val dataService: DataService? = singleton!!.getDataService()
//
//
//        val disposable: Disposable?
//        disposable = dataService?.fetchAllApps(DataFactory().URL_SUPER_MART, DataFactory().KEY)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.doOnError(Consumer { t ->
//                Log.d("TAG", "fetchSuperMart Error ${t.localizedMessage}")
//            })
//            ?.subscribe(Consumer { t ->
//                Log.d("TAG", "fetchSuperMart Response ${t.getValues()}")
//                changeSuperMartDataSet(t.getValues())
//            })
//
//        if (disposable != null) {
//            compositeDisposable?.add(disposable)
//        }
//    }
//
//    private fun fetchGroceries(){
//        Log.d("TAG", "fetchSuperMart: ")
//
//        val singleton: Singleton? = Singleton.get()
//        val dataService: DataService? = singleton!!.getDataService()
//
//        val disposable: Disposable?
//        disposable = dataService?.fetchAllApps(DataFactory().URL_GROCERIES, DataFactory().KEY)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.doOnError(Consumer { t ->
//                Log.d("TAG", "fetchGroceries Error ${t.localizedMessage}")
//            })
//            ?.subscribe(Consumer { t ->
//                Log.d("TAG", "fetchGroceries Response ${t.getValues()}")
//                changeGroceriesDataSet(t.getValues())
//            })
//
//        if (disposable != null) {
//            compositeDisposable?.add(disposable)
//        }
//    }
//
//    private fun fetchMedicines(){
//        Log.d("TAG", "fetchMedicines: ")
//
//        val singleton: Singleton? = Singleton.get()
//        val dataService: DataService? = singleton!!.getDataService()
//
//        val disposable: Disposable?
//        disposable = dataService?.fetchAllApps(DataFactory().URL_MEDICINES, DataFactory().KEY)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.doOnError(Consumer { t ->
//                Log.d("TAG", "fetchMedicines Error ${t.localizedMessage}")
//            })
//            ?.subscribe(Consumer { t ->
//                Log.d("TAG", "fetchMedicines Response ${t.getValues()}")
//                changeMedicinesDataSet(t.getValues())
//            })
//
//        if (disposable != null) {
//            compositeDisposable?.add(disposable)
//        }
//    }
//
//    private fun fetchSupplements(){
//        Log.d("TAG", "fetchSupplements: ")
//
//        val singleton: Singleton? = Singleton.get()
//        val dataService: DataService? = singleton!!.getDataService()
//
//        val disposable: Disposable?
//        disposable = dataService?.fetchAllApps(DataFactory().URL_SUPPLEMENTS, DataFactory().KEY)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.doOnError(Consumer { t ->
//                Log.d("TAG", "fetchSupplements Error ${t.localizedMessage}")
//            })
//            ?.subscribe(Consumer { t ->
//                Log.d("TAG", "fetchSupplements Response ${t.getValues()}")
//                changeSupplementsDataSet(t.getValues())
//            })
//
//        if (disposable != null) {
//            compositeDisposable?.add(disposable)
//        }
//    }
//
//    private fun fetchElectronics(){
//        Log.d("TAG", "fetchElectronics: ")
//
//        val singleton: Singleton? = Singleton.get()
//        val dataService: DataService? = singleton!!.getDataService()
//
//        val disposable: Disposable?
//        disposable = dataService?.fetchAllApps(DataFactory().URL_ELECTRONICS, DataFactory().KEY)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.doOnError(Consumer { t ->
//                Log.d("TAG", "fetchElectronics Error ${t.localizedMessage}")
//            })
//            ?.subscribe(Consumer { t ->
//                Log.d("TAG", "fetchElectronics Response ${t.getValues()}")
//                changeElectronicsDataSet(t.getValues())
//            })
//
//        if (disposable != null) {
//            compositeDisposable?.add(disposable)
//        }
//    }
//
//    private fun fetchBeauty(){
//        Log.d("TAG", "fetchBeauty: ")
//
//        val singleton: Singleton? = Singleton.get()
//        val dataService: DataService? = singleton!!.getDataService()
//
//        val disposable: Disposable?
//        disposable = dataService?.fetchAllApps(DataFactory().URL_BEAUTY, DataFactory().KEY)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.doOnError(Consumer { t ->
//                Log.d("TAG", "fetchBeauty Error ${t.localizedMessage}")
//            })
//            ?.subscribe(Consumer { t ->
//                Log.d("TAG", "fetchBeauty Response ${t.getValues()}")
//                changeBeautyDataSet(t.getValues())
//            })
//
//        if (disposable != null) {
//            compositeDisposable?.add(disposable)
//        }
//    }
//
//    private fun fetchJewellery(){
//        Log.d("TAG", "fetchJewellery: ")
//
//        val singleton: Singleton? = Singleton.get()
//        val dataService: DataService? = singleton!!.getDataService()
//
//        val disposable: Disposable?
//        disposable = dataService?.fetchAllApps(DataFactory().URL_JEWELLERY, DataFactory().KEY)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.doOnError(Consumer { t ->
//                Log.d("TAG", "fetchJewellery Error ${t.localizedMessage}")
//            })
//            ?.subscribe(Consumer { t ->
//                Log.d("TAG", "fetchJewellery Response ${t.getValues()}")
//                changeJewelleryDataSet(t.getValues())
//            })
//
//        if (disposable != null) {
//            compositeDisposable?.add(disposable)
//        }
//    }
//
//    private fun fetchKitchenAppliances(){
//        Log.d("TAG", "fetchKitchenAppliances: ")
//
//        val singleton: Singleton? = Singleton.get()
//        val dataService: DataService? = singleton!!.getDataService()
//
//        val disposable: Disposable?
//        disposable = dataService?.fetchAllApps(
//            DataFactory().URL_KITCHEN_APPLIANCES,
//            DataFactory().KEY
//        )
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.doOnError(Consumer { t ->
//                Log.d("TAG", "fetchKitchenAppliances Error ${t.localizedMessage}")
//            })
//            ?.subscribe(Consumer { t ->
//                Log.d("TAG", "fetchKitchenAppliances Response ${t.getValues()}")
//                changeKitchenAppliancesDataSet(t.getValues())
//            })
//
//        if (disposable != null) {
//            compositeDisposable?.add(disposable)
//        }
//    }
//
//    private fun fetchKidsLifestyle(){
//        Log.d("TAG", "fetchKidsLifestyle: ")
//
//        val singleton: Singleton? = Singleton.get()
//        val dataService: DataService? = singleton!!.getDataService()
//
//        val disposable: Disposable?
//        disposable = dataService?.fetchAllApps(DataFactory().URL_KIDS_LIFESTYLE, DataFactory().KEY)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.doOnError(Consumer { t ->
//                Log.d("TAG", "fetchKidsLifestyle Error ${t.localizedMessage}")
//            })
//            ?.subscribe(Consumer { t ->
//                Log.d("TAG", "fetchKidsLifestyle Response ${t.getValues()}")
//                changeKidsLifestyleDataSet(t.getValues())
//            })
//
//        if (disposable != null) {
//            compositeDisposable?.add(disposable)
//        }
//    }
//
//    private fun fetchBabyToys(){
//        Log.d("TAG", "fetchBabyToys: ")
//
//        val singleton: Singleton? = Singleton.get()
//        val dataService: DataService? = singleton!!.getDataService()
//
//        val disposable: Disposable?
//        disposable = dataService?.fetchAllApps(DataFactory().URL_BABY_TOYS, DataFactory().KEY)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.doOnError(Consumer { t ->
//                Log.d("TAG", "fetchBabyToys Error ${t.localizedMessage}")
//            })
//            ?.subscribe(Consumer { t ->
//                Log.d("TAG", "fetchBabyToys Response ${t.getValues()}")
//                changeBabyToysDataSet(t.getValues())
//            })
//
//        if (disposable != null) {
//            compositeDisposable?.add(disposable)
//        }
//    }
//
//    private fun fetchLingerie(){
//        Log.d("TAG", "fetchLingerie: ")
//
//        val singleton: Singleton? = Singleton.get()
//        val dataService: DataService? = singleton!!.getDataService()
//
//        val disposable: Disposable?
//        disposable = dataService?.fetchAllApps(DataFactory().URL_LINGERIE, DataFactory().KEY)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.doOnError(Consumer { t ->
//                Log.d("TAG", "fetchLingerie Error ${t.localizedMessage}")
//            })
//            ?.subscribe(Consumer { t ->
//                Log.d("TAG", "fetchLingerie Response ${t.getValues()}")
//                changeLingerieDataSet(t.getValues())
//            })
//
//        if (disposable != null) {
//            compositeDisposable?.add(disposable)
//        }
//    }
//
//    private fun fetchMenInnerWear(){
//        Log.d("TAG", "fetchMenInnerWear: ")
//
//        val singleton: Singleton? = Singleton.get()
//        val dataService: DataService? = singleton!!.getDataService()
//
//        val disposable: Disposable?
//        disposable = dataService?.fetchAllApps(DataFactory().URL_MEN_INNER_WEAR, DataFactory().KEY)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.doOnError(Consumer { t ->
//                Log.d("TAG", "fetchMenInnerWear Error ${t.localizedMessage}")
//            })
//            ?.subscribe(Consumer { t ->
//                Log.d("TAG", "fetchMenInnerWear Response ${t.getValues()}")
//                changeMenInnerWearDataSet(t.getValues())
//            })
//
//        if (disposable != null) {
//            compositeDisposable?.add(disposable)
//        }
//    }
//
//    private fun fetchBooks(){
//        Log.d("TAG", "fetchBooks: ")
//
//        val singleton: Singleton? = Singleton.get()
//        val dataService: DataService? = singleton!!.getDataService()
//
//        val disposable: Disposable?
//        disposable = dataService?.fetchAllApps(DataFactory().URL_BOOKS, DataFactory().KEY)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.doOnError(Consumer { t ->
//                Log.d("TAG", "fetchBooks Error ${t.localizedMessage}")
//            })
//            ?.subscribe(Consumer { t ->
//                Log.d("TAG", "fetchBooks Response ${t.getValues()}")
//                changeBooksDataSet(t.getValues())
//            })
//
//        if (disposable != null) {
//            compositeDisposable?.add(disposable)
//        }
//    }
//
//    private fun fetchFootwear(){
//        Log.d("TAG", "fetchFootwear: ")
//
//        val singleton: Singleton? = Singleton.get()
//        val dataService: DataService? = singleton!!.getDataService()
//
//        val disposable: Disposable?
//        disposable = dataService?.fetchAllApps(DataFactory().URL_FOOTWEAR, DataFactory().KEY)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.doOnError(Consumer { t ->
//                Log.d("TAG", "fetchFootwear Error ${t.localizedMessage}")
//            })
//            ?.subscribe(Consumer { t ->
//                Log.d("TAG", "fetchFootwear Response ${t.getValues()}")
//                changeFootwearDataSet(t.getValues())
//            })
//
//        if (disposable != null) {
//            compositeDisposable?.add(disposable)
//        }
//    }
//
//
//
//
//    fun changeSuperMartDataSet(allAppsList: List<List<String>>?){
//        superMartLiveData.value = allAppsList
//    }
//
//    fun changeGroceriesDataSet(allAppsList: List<List<String>>?){
//        groceriesLiveData.value = allAppsList
//    }
//
//    fun changeMedicinesDataSet(allAppsList: List<List<String>>?){
//        medicinesLiveData.value = allAppsList
//    }
//
//    fun changeSupplementsDataSet(allAppsList: List<List<String>>?){
//        supplementsLiveData.value = allAppsList
//    }
//
//    fun changeElectronicsDataSet(allAppsList: List<List<String>>?){
//        electronicsLiveData.value = allAppsList
//    }
//
//    fun changeBeautyDataSet(allAppsList: List<List<String>>?){
//        beautyLiveData.value = allAppsList
//    }
//
//    fun changeJewelleryDataSet(allAppsList: List<List<String>>?){
//        jewelleryLiveData.value = allAppsList
//    }
//
//    fun changeKitchenAppliancesDataSet(allAppsList: List<List<String>>?){
//        kitchenAppliancesLiveData.value = allAppsList
//    }
//
//    fun changeKidsLifestyleDataSet(allAppsList: List<List<String>>?){
//        kidsLifestyleLiveData.value = allAppsList
//    }
//
//    fun changeBabyToysDataSet(allAppsList: List<List<String>>?){
//        babyToysLiveData.value = allAppsList
//    }
//
//    fun changeLingerieDataSet(allAppsList: List<List<String>>?){
//        lingerieLiveData.value = allAppsList
//    }
//    fun changeMenInnerWearDataSet(allAppsList: List<List<String>>?){
//        menInnerWearLiveData.value = allAppsList
//    }
//
//    fun changeBooksDataSet(allAppsList: List<List<String>>?){
//        booksLiveData.value = allAppsList
//    }
//
//    fun changeFootwearDataSet(allAppsList: List<List<String>>?){
//        footwearLiveData.value = allAppsList
//    }
//
//
//
//    private fun unSubscribeFromObservable() {
//        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
//            compositeDisposable!!.dispose()
//        }
//    }
//
//    fun reset() {
//        unSubscribeFromObservable()
//        compositeDisposable = null
//        context = null
//    }
//
//
