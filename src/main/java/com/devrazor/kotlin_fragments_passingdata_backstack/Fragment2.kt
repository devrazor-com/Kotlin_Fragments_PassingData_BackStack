package com.devrazor.kotlin_fragments_passingdata_backstack;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class Fragment2 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_2, container, false)
        val mytext = root.findViewById<View>(R.id.textView) as TextView

        val iText = arguments?.getString("show_text")
        if (iText != null) {
            mytext.text = iText
        }else mytext.text = "Default Text in Fragment 2"


        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_2, container, false)

        return root
    }


}