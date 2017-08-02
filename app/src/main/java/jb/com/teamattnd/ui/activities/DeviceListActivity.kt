package jb.com.teamattnd.ui.activities

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import jb.com.teamattnd.R
import kotlinx.android.synthetic.main.activity_device_list.*

class DeviceListActivity : AppCompatActivity() {

    /**
     * Return Intent extra
     */


    companion object {
        /**
         * Return Intent extra
         */
        public val EXTRA_DEVICE_ADDRESS = "device_address"
        private val TAG = "DeviceListActivity"
        var mNewDeviceArrayAdapter = null as ArrayAdapter<String>?
        var mBluetoothAdapter = null as BluetoothAdapter?

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, DeviceListActivity::class.java)

            return intent
        }


    }

    private var mDeviceClickListener = object : AdapterView.OnItemClickListener {
        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            mBluetoothAdapter!!.cancelDiscovery()

            var info = (p1 as TextView).getText().toString()
            var address = info.substring(info.length - 17)

            var intent = Intent()
            intent.putExtra(EXTRA_DEVICE_ADDRESS, address);

            setResult(Activity.RESULT_OK, intent)
            finish()

        }
    }
    private val mReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND == action) {
                // Get the BluetoothDevice object from the Intent
                val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                // If it's already paired, skip it, because it's been listed already
                if (device.bondState != BluetoothDevice.BOND_BONDED) {
                    mNewDeviceArrayAdapter!!.add(device.name + "\n" + device.address)
                }
                // When discovery is finished, change the Activity title
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED == action) {
                setProgressBarIndeterminateVisibility(false)
                setTitle(R.string.select_device)
                if (mNewDeviceArrayAdapter!!.count == 0) {
                    val noDevices = resources.getText(R.string.none_found).toString()
                    mNewDeviceArrayAdapter!!.add(noDevices)
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS)

        setContentView(R.layout.activity_device_list)
        setResult(Activity.RESULT_CANCELED)


        button_scan.setOnClickListener( View.OnClickListener { view-> doDiscovery()
                view.visibility =View.GONE
        })


        var pairedDeviceArrayAdapter = ArrayAdapter<String>(this, R.layout.device_name)

        mNewDeviceArrayAdapter = ArrayAdapter(this, R.layout.device_name)

        paired_devices.adapter = pairedDeviceArrayAdapter
        paired_devices.onItemClickListener = mDeviceClickListener

        new_devices.adapter = mNewDeviceArrayAdapter
        new_devices.onItemClickListener = mDeviceClickListener

        var filter = IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter)

        filter = IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, filter)

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        var pairedDevices = mBluetoothAdapter!!.bondedDevices as Set<BluetoothDevice>

        if (pairedDevices.size > 0) {
            title_paired_devices.visibility = View.VISIBLE
            for (device in pairedDevices) {
                pairedDeviceArrayAdapter.add(device.name + "\n" + device.address)
            }
        } else {
            var noDevice = resources.getText(R.string.none_paired).toString()
            pairedDeviceArrayAdapter.add(noDevice)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        if (mBluetoothAdapter != null)
            mBluetoothAdapter!!.cancelDiscovery()

        unregisterReceiver(mReceiver)
    }

    fun doDiscovery() {
        setProgressBarIndeterminateVisibility(true)
        setTitle(getString(R.string.scanning))
        title_new_devices.visibility = View.VISIBLE

        if (mBluetoothAdapter!!.isDiscovering) {
            mBluetoothAdapter!!.cancelDiscovery()
        }

        mBluetoothAdapter!!.startDiscovery()

    }
}
