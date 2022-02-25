package uk.ac.stir.cs.unit_converter

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import uk.ac.stir.cs.unit_converter.DataContract.DataEntry

// Creating the database
private const val CREATE_CONVERSION_TABLE =
    "CREATE TABLE IF NOT EXISTS " + DataEntry.TABLE_NAME + " (" +
            BaseColumns._ID + " INTEGER PRIMARY KEY," +
            DataEntry.COLUMN_CONVERSION_UNIT1 + " TEXT," +
            DataEntry.COLUMN_CONVERSION_UNIT2 + " TEXT," +
            DataEntry.COLUMN_CONVERSION_NUMBER + " TEXT," +
            DataEntry.COLUMN_TYPE + " TEXT)"

class DataHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_CONVERSION_TABLE)
        populateDatabase(db) // Calling the method in order to fill the database
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    companion object {
        const val DATABASE_NAME = "converter_database"
        const val DATABASE_VERSION = 1
    }

    // Database query that gets the multiplier number to be used in the unit conversion
    fun getMultiplier(db: SQLiteDatabase, unitFrom: String, unitTo: String): Double {
        val multiplier: Double
        val statement = db.compileStatement(
            "select " + DataEntry.COLUMN_CONVERSION_NUMBER + " from " +
                    DataEntry.TABLE_NAME + " where " +
                    DataEntry.COLUMN_CONVERSION_UNIT1 + " = '" + unitFrom + "' and " +
                    DataEntry.COLUMN_CONVERSION_UNIT2 + " = '" + unitTo + "';"
        )
        val stringQuery = statement.simpleQueryForString()
        multiplier = stringQuery.toDouble()
        return multiplier
    }

    // Method to insert data into the database by passing ContentValue object into the insert() method
    private fun populateDatabase(db: SQLiteDatabase) {
        val dValue1 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Kilometre")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Mile")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.621371")
            put(DataEntry.COLUMN_TYPE, "Distance")
        }
        db.insert(DataEntry.TABLE_NAME, null, dValue1)

        val dValue2 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Kilometre")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Metre")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "1000")
            put(DataEntry.COLUMN_TYPE, "Distance")
        }
        db.insert(DataEntry.TABLE_NAME, null, dValue2)

        val dValue3 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Kilometre")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Yard")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "1093.61")
            put(DataEntry.COLUMN_TYPE, "Distance")
        }
        db.insert(DataEntry.TABLE_NAME, null, dValue3)

        val dValue4 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Metre")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Kilometre")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.001")
            put(DataEntry.COLUMN_TYPE, "Distance")
        }
        db.insert(DataEntry.TABLE_NAME, null, dValue4)

        val dValue5 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Metre")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Mile")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.000621371")
            put(DataEntry.COLUMN_TYPE, "Distance")
        }
        db.insert(DataEntry.TABLE_NAME, null, dValue5)

        val dValue6 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Metre")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Yard")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "1.09361")
            put(DataEntry.COLUMN_TYPE, "Distance")
        }
        db.insert(DataEntry.TABLE_NAME, null, dValue6)

        val dValue7 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Yard")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Metre")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.9144")
            put(DataEntry.COLUMN_TYPE, "Distance")
        }
        db.insert(DataEntry.TABLE_NAME, null, dValue7)

        val dValue8 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Yard")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Mile")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.000568182")
            put(DataEntry.COLUMN_TYPE, "Distance")
        }
        db.insert(DataEntry.TABLE_NAME, null, dValue8)

        val dValue9 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Yard")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Kilometre")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.0009144")
            put(DataEntry.COLUMN_TYPE, "Distance")
        }
        db.insert(DataEntry.TABLE_NAME, null, dValue9)

        val dValue10 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Mile")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Yard")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "1760")
            put(DataEntry.COLUMN_TYPE, "Distance")
        }
        db.insert(DataEntry.TABLE_NAME, null, dValue10)

        val dValue11 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Mile")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Kilometre")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "1.60934")
            put(DataEntry.COLUMN_TYPE, "Distance")
        }
        db.insert(DataEntry.TABLE_NAME, null, dValue11)

        val dValue12 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Mile")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Metre")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "1609.34")
            put(DataEntry.COLUMN_TYPE, "Distance")
        }
        db.insert(DataEntry.TABLE_NAME, null, dValue12)


        val vValue1 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Litre")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Millilitre")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "1000")
            put(DataEntry.COLUMN_TYPE, "Volume")
        }
        db.insert(DataEntry.TABLE_NAME, null, vValue1)

        val vValue2 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Litre")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Gallon")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.219969")
            put(DataEntry.COLUMN_TYPE, "Volume")
        }
        db.insert(DataEntry.TABLE_NAME, null, vValue2)

        val vValue3 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Litre")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Pint")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "1.75975")
            put(DataEntry.COLUMN_TYPE, "Volume")
        }
        db.insert(DataEntry.TABLE_NAME, null, vValue3)

        val vValue4 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Millilitre")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Litre")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.001")
            put(DataEntry.COLUMN_TYPE, "Volume")
        }
        db.insert(DataEntry.TABLE_NAME, null, vValue4)

        val vValue5 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Millilitre")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Gallon")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.000219969")
            put(DataEntry.COLUMN_TYPE, "Volume")
        }
        db.insert(DataEntry.TABLE_NAME, null, vValue5)

        val vValue6 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Millilitre")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Pint")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.00175975")
            put(DataEntry.COLUMN_TYPE, "Volume")
        }
        db.insert(DataEntry.TABLE_NAME, null, vValue6)

        val vValue7 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Gallon")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Millilitre")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "4546.09")
            put(DataEntry.COLUMN_TYPE, "Volume")
        }
        db.insert(DataEntry.TABLE_NAME, null, vValue7)

        val vValue8 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Gallon")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Litre")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "4.54609")
            put(DataEntry.COLUMN_TYPE, "Volume")
        }
        db.insert(DataEntry.TABLE_NAME, null, vValue8)

        val vValue9 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Gallon")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Pint")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "8")
            put(DataEntry.COLUMN_TYPE, "Volume")
        }
        db.insert(DataEntry.TABLE_NAME, null, vValue9)

        val vValue10 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Pint")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Litre")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.568261")
            put(DataEntry.COLUMN_TYPE, "Volume")
        }
        db.insert(DataEntry.TABLE_NAME, null, vValue10)

        val vValue11 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Pint")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Millilitre")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "568.261")
            put(DataEntry.COLUMN_TYPE, "Volume")
        }
        db.insert(DataEntry.TABLE_NAME, null, vValue11)

        val vValue12 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Pint")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Gallon")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.125")
            put(DataEntry.COLUMN_TYPE, "Volume")
        }
        db.insert(DataEntry.TABLE_NAME, null, vValue12)


        val mValue1 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Kilogram")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Gram")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "1000")
            put(DataEntry.COLUMN_TYPE, "Mass")
        }
        db.insert(DataEntry.TABLE_NAME, null, mValue1)

        val mValue2 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Kilogram")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Pound")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "2.20462")
            put(DataEntry.COLUMN_TYPE, "Mass")
        }
        db.insert(DataEntry.TABLE_NAME, null, mValue2)

        val mValue3 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Kilogram")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Stone")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.157473")
            put(DataEntry.COLUMN_TYPE, "Mass")
        }
        db.insert(DataEntry.TABLE_NAME, null, mValue3)

        val mValue4 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Gram")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Kilogram")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.001")
            put(DataEntry.COLUMN_TYPE, "Mass")
        }
        db.insert(DataEntry.TABLE_NAME, null, mValue4)

        val mValue5 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Gram")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Pound")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.00220462")
            put(DataEntry.COLUMN_TYPE, "Mass")
        }
        db.insert(DataEntry.TABLE_NAME, null, mValue5)

        val mValue6 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Gram")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Stone")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.000157473")
            put(DataEntry.COLUMN_TYPE, "Mass")
        }
        db.insert(DataEntry.TABLE_NAME, null, mValue6)

        val mValue7 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Pound")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Kilogram")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.453592")
            put(DataEntry.COLUMN_TYPE, "Mass")
        }
        db.insert(DataEntry.TABLE_NAME, null, mValue7)

        val mValue8 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Pound")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Gram")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "453.592")
            put(DataEntry.COLUMN_TYPE, "Mass")
        }
        db.insert(DataEntry.TABLE_NAME, null, mValue8)

        val mValue9 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Pound")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Stone")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.0714286")
            put(DataEntry.COLUMN_TYPE, "Mass")
        }
        db.insert(DataEntry.TABLE_NAME, null, mValue9)

        val mValue10 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Stone")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Kilogram")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "6.35029")
            put(DataEntry.COLUMN_TYPE, "Mass")
        }
        db.insert(DataEntry.TABLE_NAME, null, mValue10)

        val mValue11 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Stone")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Gram")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "6350.29")
            put(DataEntry.COLUMN_TYPE, "Mass")
        }
        db.insert(DataEntry.TABLE_NAME, null, mValue11)

        val mValue12 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Stone")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Pound")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "14")
            put(DataEntry.COLUMN_TYPE, "Mass")
        }
        db.insert(DataEntry.TABLE_NAME, null, mValue12)


        val tValue1 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Second")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Minute")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.0166667")
            put(DataEntry.COLUMN_TYPE, "Time")
        }
        db.insert(DataEntry.TABLE_NAME, null, tValue1)

        val tValue2 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Second")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Hour")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.000277778")
            put(DataEntry.COLUMN_TYPE, "Time")
        }
        db.insert(DataEntry.TABLE_NAME, null, tValue2)

        val tValue3 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Second")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Day")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "1.1574e-5")
            put(DataEntry.COLUMN_TYPE, "Time")
        }
        db.insert(DataEntry.TABLE_NAME, null, tValue3)

        val tValue4 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Minute")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Second")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "60")
            put(DataEntry.COLUMN_TYPE, "Time")
        }
        db.insert(DataEntry.TABLE_NAME, null, tValue4)

        val tValue5 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Minute")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Hour")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.0166667")
            put(DataEntry.COLUMN_TYPE, "Time")
        }
        db.insert(DataEntry.TABLE_NAME, null, tValue5)

        val tValue6 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Minute")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Day")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.000694444")
            put(DataEntry.COLUMN_TYPE, "Time")
        }
        db.insert(DataEntry.TABLE_NAME, null, tValue6)

        val tValue7 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Hour")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Minute")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "60")
            put(DataEntry.COLUMN_TYPE, "Time")
        }
        db.insert(DataEntry.TABLE_NAME, null, tValue7)

        val tValue8 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Hour")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Second")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "3600")
            put(DataEntry.COLUMN_TYPE, "Time")
        }
        db.insert(DataEntry.TABLE_NAME, null, tValue8)

        val tValue9 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Hour")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Day")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "0.0416667")
            put(DataEntry.COLUMN_TYPE, "Time")
        }
        db.insert(DataEntry.TABLE_NAME, null, tValue9)

        val tValue10 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Day")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Second")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "86400")
            put(DataEntry.COLUMN_TYPE, "Time")
        }
        db.insert(DataEntry.TABLE_NAME, null, tValue10)

        val tValue11 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Day")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Minute")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "1440")
            put(DataEntry.COLUMN_TYPE, "Time")
        }
        db.insert(DataEntry.TABLE_NAME, null, tValue11)

        val tValue12 = ContentValues().apply {
            put(DataEntry.COLUMN_CONVERSION_UNIT1, "Day")
            put(DataEntry.COLUMN_CONVERSION_UNIT2, "Hour")
            put(DataEntry.COLUMN_CONVERSION_NUMBER, "24")
            put(DataEntry.COLUMN_TYPE, "Time")
        }
        db.insert(DataEntry.TABLE_NAME, null, tValue12)
    }
}