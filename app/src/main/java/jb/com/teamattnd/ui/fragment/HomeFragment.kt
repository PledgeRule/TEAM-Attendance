package jb.com.teamattnd.ui.fragment

import android.icu.lang.UCharacter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.view.*
import jb.com.teamattnd.R

/**
 * Created by sandeep.singh on 7/25/2017.
 */
class HomeFragment :ListFragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)


    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.bluetooth_menu,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
         super.onOptionsItemSelected(item)

        when(item!!.itemId){
            R.id.secure_connect_scan-> println("Secure connection")
            R.id.insecure_connect_scan-> println(" In Secure connection")
            R.id.discoverable-> println("make discoverable")
        }

        return true
    }

}