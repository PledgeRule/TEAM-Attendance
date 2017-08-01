package jb.com.teamattnd.ui.fragment

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.icu.lang.UCharacter
import android.os.Bundle

import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.view.*
import android.widget.Toast
import jb.com.teamattnd.R
import jb.com.teamattnd.ui.acitvities.DeviceListActivity
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Created by sandeep.singh on 7/25/2017.
 */
class HomeFragment :ListFragment(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        if (mBluetoothAdapter==null){
            Toast.makeText(activity,"Bluetooth is not available",Toast.LENGTH_SHORT).show()
            activity.finish()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)


    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onStart() {
        super.onStart()
        if (!mBluetoothAdapter!!.isEnabled){
            val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.bluetooth_menu,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
         super.onOptionsItemSelected(item)

        when(item!!.itemId){
            R.id.secure_connect_scan-> {


                val intentSecure = DeviceListActivity.newIntent(activity)
                activity.startActivityForResult(intentSecure, REQUEST_CONNECT_DEVICE_SECURE)
                println ("Secure connection")
            }
            R.id.insecure_connect_scan-> {

                val intentSecure = DeviceListActivity.newIntent(activity)
                activity.startActivityForResult(intentSecure, REQUEST_CONNECT_DEVICE_INSECURE)
                println(" In Secure connection")}
            R.id.discoverable->{
                ensureDiscoverable()
                println("make discoverable")}
        }

        return true
    }

    private fun ensureDiscoverable() {
        if (mBluetoothAdapter?.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            val discoverableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
            startActivity(discoverableIntent)
        }
    }

    private var mBluetoothAdapter =null as BluetoothAdapter?

    companion object {
        private val  REQUEST_CONNECT_DEVICE_SECURE = 1
        private val  REQUEST_CONNECT_DEVICE_INSECURE = 2
        private val  REQUEST_ENABLE_BT = 3
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println(requestCode)
    }

}