package com.vangood.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.vangood.chat.databinding.ActivityRoomBinding
import okhttp3.*
import okio.ByteString
import java.util.concurrent.TimeUnit

class RoomActivity : AppCompatActivity() {
    private val TAG = RoomActivity::class.java.simpleName
    lateinit var binding:ActivityRoomBinding
    lateinit var websocket :WebSocket
    val userloginname = "TestNoSign"
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRoomBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //WebSocket
        val client = OkHttpClient.Builder()
            .readTimeout(5,TimeUnit.SECONDS)
            .build()
        val request = Request.Builder()
            .url("wss://lott-dev.lottcube.asia/ws/chat/chat:app_test?nickname=$userloginname")
            .build()
        websocket = client.newWebSocket(request,object :WebSocketListener(){
            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
                Log.d(TAG, "onClosed: ")
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosing(webSocket, code, reason)
                Log.d(TAG, "onClosing: ")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                Log.d(TAG, "onFailure: ")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                Log.d(TAG, "onMessage: $text ")
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                super.onMessage(webSocket, bytes)
                Log.d(TAG, "onMessage: ${bytes.hex()}")
            }

            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                Log.d(TAG, "onOpen: ")
            }
        })
        binding.bLadesi.setOnClickListener {
            val message = binding.tnLadesi.text.toString()
            websocket.send(Gson().toJson(Message("N",message)))
            binding.tnShowLadesi.setText(message)
        }
        binding.bOutroom.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}
data class Message(val action:String,val content:String)