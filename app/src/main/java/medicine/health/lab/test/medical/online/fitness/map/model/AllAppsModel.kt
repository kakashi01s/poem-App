package medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class AllAppsModel: Serializable {
    @SerializedName("range")
    @Expose
    private var range: String? = null

    @SerializedName("majorDimension")
    @Expose
    private var majorDimension: String? = null

    @SerializedName("values")
    @Expose
    private var values: List<List<String>>? = null

    fun getRange(): String? {
        return range
    }

    fun setRange(range: String?) {
        this.range = range
    }

    fun getMajorDimension(): String? {
        return majorDimension
    }

    fun setMajorDimension(majorDimension: String?) {
        this.majorDimension = majorDimension
    }

    fun getValues(): List<List<String>>? {
        return values
    }

    fun setValues(values: List<List<String>>?) {
        this.values = values
    }

}