package shayri.status.gif.love.sad.wallpaper.boys.map.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.data.DataFactory

class ShayriViewModel : ViewModel() {
    var loveLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var hurtLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var ghalibLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var attitudeLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var funnyLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var friendshipLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()

    private var context: Context? = null
    var compositeDisposable: CompositeDisposable? = null

    fun loadData(){
        Log.d("TAG", "loadData: ")
        compositeDisposable = CompositeDisposable()
        fetchlove()
        fetchhurt()
        fetchghalib()
        fetchattitude()
        fetchfriendship()
        fetchfunny()
    }

    private fun fetchlove() {
        Log.d("TAG", "fetchTrendingData: ")

        val dataService by lazy {
            DataFactory.create()
        }

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_LOVE, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchTrendingData Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchTrendingData Response ${t.getValues()}")
                changeloveDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun changeloveDataSet(t: List<List<String>>?) {
        loveLiveData.value = t
    }

    private fun fetchfunny() {
        Log.d("TAG", "fetchTrendingData: ")

        val dataService by lazy {
            DataFactory.create()
        }

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_FUNNY, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchTrendingData Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchTrendingData Response ${t.getValues()}")
                changefunnyDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun changefunnyDataSet(t: List<List<String>>?) {
        funnyLiveData.value = t
    }

    private fun fetchattitude() {
        Log.d("TAG", "fetchTrendingData: ")

        val dataService by lazy {
            DataFactory.create()
        }

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_ATTITUDE, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchTrendingData Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchTrendingData Response ${t.getValues()}")
                changeattitudeDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun changeattitudeDataSet(t: List<List<String>>?) {
        attitudeLiveData.value = t
    }

    private fun fetchfriendship() {
        Log.d("TAG", "fetchTrendingData: ")

        val dataService by lazy {
            DataFactory.create()
        }

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_FRIENDSHIP, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchTrendingData Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchTrendingData Response ${t.getValues()}")
                changefriendshipDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun changefriendshipDataSet(t: List<List<String>>?) {
        friendshipLiveData.value = t
    }

    private fun fetchghalib() {
        Log.d("TAG", "fetchTrendingData: ")

        val dataService by lazy {
            DataFactory.create()
        }

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_GHALIB, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchTrendingData Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchTrendingData Response ${t.getValues()}")
                changeghalibDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun changeghalibDataSet(t: List<List<String>>?) {
        ghalibLiveData.value = t
    }

    private fun fetchhurt() {
        Log.d("TAG", "fetchTrendingData: ")

        val dataService by lazy {
            DataFactory.create()
        }

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_HURT, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchTrendingData Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchTrendingData Response ${t.getValues()}")
                changehurtDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun changehurtDataSet(t: List<List<String>>?) {
        hurtLiveData.value = t
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