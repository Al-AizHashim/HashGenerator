package alaiz.hashim.hashgenerator

import android.util.Log
import androidx.lifecycle.ViewModel
import java.security.MessageDigest

class HashViewModel:ViewModel (){
    fun getHash(plaintext:String, algorithm:String):String{
        val byte=MessageDigest.getInstance(algorithm).digest(plaintext.toByteArray())
        return toHexadecimal(byte)
    }
    fun toHexadecimal(byteArray: ByteArray):String{
        Log.d("hashViewModel",byteArray.joinToString(""){
            "%02x".format(it)
        } )
    return byteArray.joinToString(""){
        "%02x".format(it)
    }
    }
}