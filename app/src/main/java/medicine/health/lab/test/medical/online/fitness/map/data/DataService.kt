package medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.data

import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.model.AllAppsModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface DataService {
    @GET
    fun fetchAllApps(@Url url: String, @Query("key") key: String): Observable<AllAppsModel>

}