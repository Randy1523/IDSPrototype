package com.example.myapplication

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class Bluetooth : AppCompatActivity() {
    var bluetoothAdapt: BluetoothAdapter? = null
    lateinit var pairedDevice: Set<BluetoothDevice>
    val REQUEST_ENABLE_BLUETOOTH = 1

    val refresh: Button = findViewById(R.id.bluetoothRefreshBut)
    val list: ListView = findViewById(R.id.bluetoothList)

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bluetooth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bluetoothAdapt = getSystemService(BluetoothManager::class.java).adapter
        if(bluetoothAdapt == null){
            Toast.makeText(this, "no Bluetooth", Toast.LENGTH_SHORT).show()
            return
        }
//        if(!bluetoothAdapt!!.isEnabled){
//            val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
//            startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BLUETOOTH)
//        }



        refresh.setOnClickListener{pairDevice()}

    }

    companion object{
        val EXTRA_ADDRESS: String = "Device_address"
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun pairDevice(){
        pairedDevice = bluetoothAdapt!!.bondedDevices
        val list : ArrayList<BluetoothDevice> = ArrayList()

        if(!pairedDevice.isEmpty()){
            for(device: BluetoothDevice in pairedDevice){
                list.add(device)
                Log.i("device", ""+device)
            }
        }
        else{
            Toast.makeText(this, "no Devices", Toast.LENGTH_SHORT).show()

        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,list)

//        this.list.adapter = adapter
//        this.list.onItemClickListener
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if(requestCode == REQUEST_ENABLE_BLUETOOTH){
//            if(resultCode == RESULT_OK){
//                if(bluetoothAdapt!!.isEnabled){
//                    Toast.makeText(this, "bluetooth enabled", Toast.LENGTH_SHORT).show()
//
//                }
//                else{
//                    Toast.makeText(this, "bluetooth disabled", Toast.LENGTH_SHORT).show()
//
//                }
//            }
//            else if(resultCode == RESULT_CANCELED){
//                print("Bluetooth cancelled")
//            }
//        }
//    }
}