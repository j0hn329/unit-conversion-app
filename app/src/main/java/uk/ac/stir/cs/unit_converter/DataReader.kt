package uk.ac.stir.cs.unit_converter

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import uk.ac.stir.cs.unit_converter.DataContract.DataEntry
import java.util.*

class DataReader(context: Context?) {
    /**
     * Class to read from the database,
     * Passes in the selection criteria, columns desired and grouping order
     */

    private val db: SQLiteDatabase
    fun curse(
        columns: Array<String?>?,
        selection: String?,
        selectionArgs: Array<String?>?,
        groupBy: String?
    ): ArrayList<String> {
        val cursor = db.query(
            DataEntry.TABLE_NAME,  // The name of the table to query
            columns,  // The array of columns to return
            selection,  // The columns for the WHERE clause
            selectionArgs,  // The values for the WHERE clause
            groupBy, // The grouping order
            null,
            null
        )

        // The value of a column can be read using this cursor
        val items = ArrayList<String>()
        while (cursor.moveToNext()) {
            val item = cursor.getString(
                cursor.getColumnIndexOrThrow(columns!![0])
            ) // Passing the index position of the column desired
            items.add(item)
        }
        cursor.close() // Calling close so the cursor can release its resources
        return items
    }

    // Initialising the data helper class
    init {
        val dbHelper = DataHelper(context)
        db = dbHelper.writableDatabase
    }
}