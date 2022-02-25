package uk.ac.stir.cs.unit_converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uk.ac.stir.cs.unit_converter.DataContract.DataEntry

class Page1Fragment : Fragment() {
    private lateinit var reader: DataReader
    private lateinit var spinnerType: Spinner
    private lateinit var spinnerUnitFrom: Spinner
    private lateinit var spinnerUnitTo: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reader = DataReader(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.unit_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialising view model
        val model = ViewModelProvider(requireActivity()).get(
            FragmentViewModel::class.java
        )

        // Spinner elements
        spinnerType = view.findViewById(R.id.unit_type)
        spinnerUnitFrom = view.findViewById(R.id.unitFrom)
        spinnerUnitTo = view.findViewById(R.id.unitTo)

        // Listener that awaits changes in the unit from spinner
        spinnerUnitFrom.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                model.setPos1(position) // Set the current position of the unitFrom in view model
                model.setUnitFrom(
                    spinnerUnitFrom.adapter.getItem(position).toString()
                ) // Set the unitFrom at the selected position as a String
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        // Listener that awaits changes in the unit to spinner
        spinnerUnitTo.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                model.setPos2(position) // Set the current position of the unitTo in view model
                model.setUnitTo(
                    spinnerUnitTo.adapter.getItem(position).toString()
                ) // Set the unitTo at the selected position as a String
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        // Listener that awaits changes in the unit category spinner
        spinnerType.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Load the unit to/from spinner data that correspond to the selected position in the unit type spinner
                populateSubSpinnerData(position)
                model.setType(position) // Set the current position of the unit category in view model
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        populateSpinnerData() // Loading unit type spinner data from database
    }

    /**
     * Function to load the unit category spinner data from database
     * */
    private fun populateSpinnerData() {
        // Specifies which column from the database to use
        val projection = arrayOf<String?>(
            DataEntry.COLUMN_TYPE
        )

        // Calling the curse method from DataReader with projection and grouping order arguments
        val type = reader.curse(projection, null, null, DataEntry.COLUMN_TYPE)

        // Creating the adapter for the spinner
        val dataAdapter = ArrayAdapter(
            context!!,
            android.R.layout.simple_spinner_dropdown_item, type
        )

        // Attaching adapter to the unit category spinner
        spinnerType.adapter = dataAdapter
    }

    /**
     * Function to load the individual unit spinner data from database
     * */
    private fun populateSubSpinnerData(position: Int) {
        // Specifies which column from the database to use
        val projection = arrayOf<String?>(
            DataEntry.COLUMN_CONVERSION_UNIT1
        )

        // Filtering results WHERE the units selected are from the same category as selected in the first spinner
        val selection = DataEntry.COLUMN_TYPE + " = ?"
        val selectionArgs = arrayOf<String?>(
            spinnerType.adapter.getItem(position).toString()
        )

        // Calling the curse method from DataReader with all four arguments
        val unit =
            reader.curse(projection, selection, selectionArgs, DataEntry.COLUMN_CONVERSION_UNIT1)

        // Creating the adapter for the spinner
        val dataAdapter = ArrayAdapter(
            context!!,
            android.R.layout.simple_spinner_dropdown_item, unit
        )

        // Attaching adapter to both unit spinners
        spinnerUnitFrom.adapter = dataAdapter
        spinnerUnitTo.adapter = dataAdapter
    }
}