package uk.ac.stir.cs.unit_converter

import android.provider.BaseColumns

/**
 * Companion class that specifies the layout of the database and how it is organised
 * Container for the constants that define the name and columns of the database
 */
object DataContract {
    object DataEntry : BaseColumns {
        const val TABLE_NAME = "converter_table"
        const val COLUMN_CONVERSION_UNIT1 = "unit1"
        const val COLUMN_CONVERSION_UNIT2 = "unit2"
        const val COLUMN_TYPE = "type"
        const val COLUMN_CONVERSION_NUMBER = "multiplier"
    }
}