package com.devrazor.kotlin_fragments_passingdata_backstack;


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class Fragment1 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmentExists: Boolean
        val root = inflater.inflate(R.layout.fragment_1, container, false)
        val btn = root.findViewById<View>(R.id.button3) as Button
        val myedittext = root.findViewById<View>(R.id.myEditTextFragment1) as EditText
        btn.setOnClickListener {

            fragmentExists = false

            val fragments = fragmentManager?.fragments
            if (fragments != null) {
                for (fragment in fragments) {
                    if (fragment.tag == "FRAGMENT_2"){
                        fragmentExists = true
                    }
                }
            }

            val args = Bundle()
            args.putString("show_text", myedittext.text.toString())
            val context = activity as AppCompatActivity

            if (fragmentExists) {

                val fragmentManager: FragmentManager = context.supportFragmentManager

                val fragment = fragmentManager?.findFragmentByTag("FRAGMENT_2")
                if (fragment != null) {
                    fragment.arguments = args
                    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.detach(fragment)
                    fragmentTransaction.attach(fragment)
                    fragmentTransaction.commit()
                }

            }else{

                    val fragment = Fragment2()
                    fragment.arguments = args

                    //Let's add this fragment to the activity state
                    val fragmentTransaction = fragmentManager?.beginTransaction()
                    if (fragmentTransaction != null){

                        fragmentTransaction.add(R.id.host, fragment, "FRAGMENT_2")

                        //Add this transaction to the back stack. This means that the transaction will be remembered after it is committed
                        // This way we can reverse its operation when later popped off the stack

                        fragmentTransaction.addToBackStack(null)
                        fragmentTransaction.commit()
                        fragmentExists = true
                    }
            }


        }


        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_1, container, false)

        return root

    }

}