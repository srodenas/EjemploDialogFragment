package com.santi.pmdm.virgen.mydialoglogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.santi.pmdm.virgen.mydialoglogin.Controller.Controller
import com.santi.pmdm.virgen.mydialoglogin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var controller : Controller



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init() //inicializo la clase
    }


    private fun init(){
        controller = Controller(this)
        controller.setActionEvent()
    }
}