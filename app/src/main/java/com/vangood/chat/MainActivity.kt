package com.vangood.chat

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import com.vangood.chat.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding  = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBottom()
        var videoviewone = binding.vRooomOne
        val uri:Uri = Uri.parse("android.resource://"+packageName+"/"+"raw/her01")
        videoviewone.setVideoURI(uri)
        videoviewone.setOnPreparedListener {
            videoviewone.start()
        }

    }

    fun setupBottom(){
        binding.bHome.setOnClickListener {

        }
        binding.bSearch.setOnClickListener {
            val intents = Intent(this,SearchActivity::class.java)
            startActivity(intents)

        }
        binding.bId.setOnClickListener {
            val intenth = Intent(this,LoginActivity::class.java)
            startActivity(intenth)
        }
        binding.bRoomOne.setOnClickListener {
            val intentro = Intent(this,RoomActivity::class.java)
            startActivity(intentro)
        }


    }

}
class NameContract:ActivityResultContract<Float,String>(){
    override fun createIntent(context: Context, input: Float?): Intent {
        TODO("Not yet implemented")
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String {
        TODO("Not yet implemented")
    }
}