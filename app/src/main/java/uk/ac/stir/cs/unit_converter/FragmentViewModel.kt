package uk.ac.stir.cs.unit_converter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentViewModel : ViewModel() {
    /**
     * Helper class for UI controller responsible for preparing data
     * The data the view model holds is immediately available to the next fragment
     */

    // The String text of the selected unit to be converted from
    private var unitFrom = MutableLiveData<String>()

    // The String text of the selected unit to be converted to
    private val unitTo = MutableLiveData<String>()

    private var type = 0 // number of unit category
    private var pos1 = 0 // position of unit from
    private var pos2 = 0 // position of unit to

    /**
     * @param type of unit as an Integer
     */
    fun setType(type: Int) {
        this.type = type
    }

    /**
     * @param pos1 set the position of the unit to be converted from
     */
    fun setPos1(pos1: Int) {
        this.pos1 = pos1
    }

    /**
     * @param pos2 set the position of the unit to be converted to
     */
    fun setPos2(pos2: Int) {
        this.pos2 = pos2
    }

    /**
     * @return the unit to be converted from as a String
     */
    fun getUnitFrom(): LiveData<String> {
        return unitFrom
    }

    /**
     * @return the unit to be converted to as a String
     */
    fun getUnitTo(): LiveData<String> {
        return unitTo
    }

    /**
     * @param unitFrom set the unit to be converted from
     */
    fun setUnitFrom(unitFrom: String?) {
        this.unitFrom.value = unitFrom
    }

    /**
     * @param unitTo set the unit to be converted to
     */
    fun setUnitTo(unitTo: String?) {
        this.unitTo.value = unitTo
    }
}