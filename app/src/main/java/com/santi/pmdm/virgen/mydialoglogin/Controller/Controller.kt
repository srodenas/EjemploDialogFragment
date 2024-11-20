package com.santi.pmdm.virgen.mydialoglogin.Controller

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.santi.pmdm.virgen.mydialoglogin.Dialog.DialogLogin
import com.santi.pmdm.virgen.mydialoglogin.Dialog.DialogLogin2
import com.santi.pmdm.virgen.mydialoglogin.Interface.onLoginInterface
import com.santi.pmdm.virgen.mydialoglogin.MainActivity

class Controller(var context : Context) : onLoginInterface {

    fun setActionEvent(){
        val myActivity = context as MainActivity  //guardamos el contexto de la Activity

        /*
        Mostramos el DialogFragment utilizando Interfaz.
         */
        myActivity.binding.btnLogin.setOnClickListener {
            initDialogWithInterface(myActivity)
        }

        /*
        Mostramos el DialogFragment utilizando lambdas.
         */
        myActivity.binding.btnLogin2.setOnClickListener{
            initDialogWithFun(myActivity)
        }
    }

    /*
    ******* Parte de utilización Interfaz dentro del DialogFragment ********
     */
    // Método utilizado para un ejemplo de DialogFragment con interfaz, sin lambda.
    private fun initDialogWithInterface(mA : MainActivity) {
        val dialog = DialogLogin(this)  //Recordamos que el this es el mismo controller que implementa de una interfaz.
        dialog.show(mA.supportFragmentManager, "Login con Interfaz")

    }

    /*
    Lógica al método invocado en caso de un onClick del botón positivo.
     */
    override fun onDialogPositiveClick(dialog: DialogFragment?) {
        val myDialogLogin= (dialog as DialogLogin)
        val myUserName = myDialogLogin.userName
        val myPassword = myDialogLogin.password
        val myActivity = context as MainActivity
        myActivity.binding.txtUser.text = "Usuario: $myUserName"
        myActivity.binding.txtPass.text = "Password: $myPassword"
        Toast.makeText(context, " Usuario: $myUserName y password: $myPassword", Toast.LENGTH_LONG).show()

    }


    /*
    Lógica al método invocado en caso de un onClick del botón negative.
     */
    override fun onDialogNegativeClick(dialog: DialogFragment?) {
        Toast.makeText(context, " Has cancelado el login", Toast.LENGTH_LONG).show()
    }




    /*
    ******* Parte de utilización de lambda dentro del DialogFragment ********
     */

    private fun initDialogWithFun(mA : MainActivity){
        val dialog = DialogLogin2(
            {
                userName, password ->
                renderize(userName, password)
            },
            {
                msgCancel(it)
            })
        dialog.show(mA.supportFragmentManager, "Login con Orden Superior")
    }

    private fun renderize(userName: String, password: String){
        val mA = context as MainActivity
        mA.binding.txtUser.text = "Usuario: $userName"
        mA.binding.txtPass.text = "Password: $password"
        Toast.makeText(context, " Usuario: $userName y password: $password", Toast.LENGTH_LONG).show()
    }

    private fun msgCancel(msg:String){
        Toast.makeText(context, " Has cancelado el login", Toast.LENGTH_LONG).show()

    }

}





/*  val obj = object : onLoginInterface{
             override fun onDialogPositiveClick(dialog: DialogFragment?) {
                 TODO("Not yet implemented")
             }

             override fun onDialogNegativeClick(dialog: DialogFragment?) {
                 TODO("Not yet implemented")
             }

         }
        */