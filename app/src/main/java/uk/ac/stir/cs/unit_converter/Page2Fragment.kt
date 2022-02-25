package uk.ac.stir.cs.unit_converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.text.DecimalFormat

class Page2Fragment : Fragment() {
    private lateinit var editUnit1: EditText
    private lateinit var editUnit2: EditText
    private lateinit var unitFrom: String
    private lateinit var unitTo: String

    private var helper: DataHelper? = null

    // Limiting conversion to six decimal points
    private val df = DecimalFormat("###.######")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        helper = DataHelper(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.unit_conversion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialising view model
        val model = ViewModelProvider(requireActivity()).get(
            FragmentViewModel::class.java
        )

        // Observer for textUnit1, listens for changes in the view model
        // Displays the unit to convert from selected in the first fragment
        model.getUnitFrom().observe(viewLifecycleOwner, { unit1 ->
            val textUnit1 = view.findViewById<TextView>(R.id.textUnitFrom)
            textUnit1.text = unit1
        })

        // Observer for textUnit2, listens for changes in the view model
        // Displays the unit to convert to selected in the first fragment
        model.getUnitTo().observe(viewLifecycleOwner, { unit2 ->
            val textUnit2 = view.findViewById<TextView>(R.id.textUnitTo)
            textUnit2.text = unit2
        })

        // Observers that gather the selected to/from units as Strings
        // Assigns selected values to unitFrom/unitTo variables
        model.getUnitFrom().observe(requireActivity(), { from -> unitFrom = from })
        model.getUnitTo().observe(requireActivity(), { to -> unitTo = to })

        // Text elements
        editUnit1 = view.findViewById(R.id.editTextUnit1)
        editUnit2 = view.findViewById(R.id.editTextUnit2)

        // Disables text views so they can't be edited
        editUnit1.isEnabled = false
        editUnit2.isEnabled = false

        // Initialising calculator style input buttons
        val buttonNo0 = view.findViewById<Button>(R.id.b0)
        val buttonNo1 = view.findViewById<Button>(R.id.b1)
        val buttonNo2 = view.findViewById<Button>(R.id.b2)
        val buttonNo3 = view.findViewById<Button>(R.id.b3)
        val buttonNo4 = view.findViewById<Button>(R.id.b4)
        val buttonNo5 = view.findViewById<Button>(R.id.b5)
        val buttonNo6 = view.findViewById<Button>(R.id.b6)
        val buttonNo7 = view.findViewById<Button>(R.id.b7)
        val buttonNo8 = view.findViewById<Button>(R.id.b8)
        val buttonNo9 = view.findViewById<Button>(R.id.b9)
        val buttonDel = view.findViewById<Button>(R.id.b10)
        val buttonDec = view.findViewById<Button>(R.id.b11)
        val buttonClr = view.findViewById<Button>(R.id.b12)
        val buttonCon = view.findViewById<Button>(R.id.b13)

        // Assigning listeners to each button that trigger when clicked
        buttonNo0.setOnClickListener { editUnit1.append("0") }
        buttonNo1.setOnClickListener { editUnit1.append("1") }
        buttonNo2.setOnClickListener { editUnit1.append("2") }
        buttonNo3.setOnClickListener { editUnit1.append("3") }
        buttonNo4.setOnClickListener { editUnit1.append("4") }
        buttonNo5.setOnClickListener { editUnit1.append("5") }
        buttonNo6.setOnClickListener { editUnit1.append("6") }
        buttonNo7.setOnClickListener { editUnit1.append("7") }
        buttonNo8.setOnClickListener { editUnit1.append("8") }
        buttonNo9.setOnClickListener { editUnit1.append("9") }
        buttonDec.setOnClickListener { editUnit1.append(".") }

        // Button that clears both text views when clicked
        buttonClr.setOnClickListener {
            editUnit1.setText("")
            editUnit2.setText("")
        }

        // Button that removes one input element each time it is clicked
        buttonDel.setOnClickListener {
            if (editUnit1.length() != 0) editUnit1.text
                .delete(editUnit1.length() - 1, editUnit1.length())
        }

        // Button that converts selected unit to target unit
        buttonCon.setOnClickListener {

            // If the same unit is selected for both the multiplier is 1
            val multiplier: Double = if (unitFrom == unitTo) {
                1.0
            } else {
                helper!!.getMultiplier(
                    helper!!.writableDatabase,
                    unitFrom,
                    unitTo
                ) // Database is queried to gather the appropriate multiplier
            }

            // Toast message displayed if the convert button is clicked and there is no user input
            // Or the input is a single decimal point
            if (editUnit1.text.toString() == "" || editUnit1.text.toString() == ".") {
                val text: CharSequence = "Invalid Conversion Format!"
                val duration = Toast.LENGTH_SHORT
                Toast.makeText(activity!!.applicationContext, text, duration).show()

                // Views are cleared
                editUnit1.setText("")
                editUnit2.setText("")
            } else {
                // Get the value entered for conversion as a String
                val conversionUnit = editUnit1.text.toString()
                editUnit2.setText(
                    df.format(
                        multiplier * conversionUnit.toDouble()
                    ) // Multiplies the value entered as a Double with the multiplier gathered from the database
                ) // The value this operation returns is set in the second edit text field which represents the converted unit
            }
        }
    }
}