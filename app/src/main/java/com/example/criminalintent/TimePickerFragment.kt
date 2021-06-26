package com.example.criminalintent

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_TIME = "time"

class TimePickerFragment: DialogFragment() {

    /*
     * for the CrimeFragment
     */
    interface Callbacks {
        fun onTimeSelected(date: Date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return super.onCreateDialog(savedInstanceState)

        val date = arguments?.getSerializable(ARG_TIME) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val resultDate: Date = GregorianCalendar(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    hourOfDay,
                    minute
            ).time

            targetFragment?.let { fragment ->
                (fragment as Callbacks).onTimeSelected(resultDate)
            }
        }

        return TimePickerDialog(
                requireContext(),
                timeListener,
                hour,
                minute,
                true
        )
    }


    companion object {
        fun newInstance(date: Date): TimePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_TIME, date)
            }
            return TimePickerFragment().apply {
                arguments = args
            }
        }
    }
}