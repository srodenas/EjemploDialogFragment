package com.santi.pmdm.virgen.mydialoglogin.Dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.santi.pmdm.virgen.mydialoglogin.Controller.Controller
import com.santi.pmdm.virgen.mydialoglogin.Interface.onLoginInterface
import com.santi.pmdm.virgen.mydialoglogin.MainActivity
import com.santi.pmdm.virgen.mydialoglogin.R
import com.santi.pmdm.virgen.mydialoglogin.databinding.DialogLoginBinding

/*
   **Ejemplo de DialogFragment con Interfaz, para invocar a los listener y tratar
   **la lógica fuera de esta clase.
 */
class DialogLogin (
    val controller : Controller

        ): DialogFragment() {

    lateinit var userName: String
    lateinit var password : String
    lateinit var listener : onLoginInterface   //AQUÍ TENDRÉ LA REFERENCIA DEL CONTROLLER.



   /* override fun onAttach(context: Context){
        super.onAttach(context)
        listener = controller as onLoginInterface
    }*/

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
      listener = controller as onLoginInterface
      //Tengo que cambiar activity por myActivity
      return activity?.let {
          // Build the dialog and set up the button click handlers
          val builder = AlertDialog.Builder(it)
          //val inflater = requireActivity().layoutInflater
          val inflater = it.layoutInflater
          // Inflate and set the layout for the dialog
          // Pass null as the parent view because its going in the dialog layout
          val viewDialogLogin = inflater.inflate(R.layout.dialog_login, null) //vista inflada
          builder.setView(viewDialogLogin)  //seteo la vista inflada en el DialogFragment

          builder.setMessage("Datos Login")
              .setPositiveButton("Aceptas los datos",
                  DialogInterface.OnClickListener { dialog, id ->
                      // Send the positive button event back to the host activity
                      val binding = DialogLoginBinding.bind(viewDialogLogin)
                      userName = binding.username.text.toString()
                      password = binding.password.text.toString()
                      listener.onDialogPositiveClick(this)  //Hago lo que marca la interfaz.

                  })
              .setNegativeButton("Cancelar",
                  DialogInterface.OnClickListener { dialog, id ->
                      // Send the negative button event back to the host activity
                      listener.onDialogNegativeClick(this) //Hago lo que marca la interfaz.
                  })

          .create()
      } ?: throw IllegalStateException("Activity cannot be null")
  }

}