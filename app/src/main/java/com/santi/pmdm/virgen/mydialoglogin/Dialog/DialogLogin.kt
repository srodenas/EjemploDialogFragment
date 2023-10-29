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

class DialogLogin (
    val controller : Controller

        ): DialogFragment() {

    lateinit var userName: String
    lateinit var password : String
    lateinit var listener : onLoginInterface



    override fun onAttach(context: Context){
        super.onAttach(context)
        listener = controller as onLoginInterface
    }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
      //Tengo que cambiar activity por myActivity
      return activity?.let {
          // Build the dialog and set up the button click handlers
          val builder = AlertDialog.Builder(it)
          val inflater = requireActivity().layoutInflater;

          // Inflate and set the layout for the dialog
          // Pass null as the parent view because its going in the dialog layout
          val viewDialogLogin = inflater.inflate(R.layout.dialog_login, null)
          builder.setView(viewDialogLogin)

          builder.setMessage("Datos Login")
              .setPositiveButton("Aceptas los datos",
                  DialogInterface.OnClickListener { dialog, id ->
                      // Send the positive button event back to the host activity
                      val binding = DialogLoginBinding.bind(viewDialogLogin)
                      userName = binding.username.text.toString()
                      password = binding.password.text.toString()
                      listener.onDialogPositiveClick(this)

                  })
              .setNegativeButton("Cancelar",
                  DialogInterface.OnClickListener { dialog, id ->
                      // Send the negative button event back to the host activity
                      listener.onDialogNegativeClick(this)
                  })

          builder.create()
      } ?: throw IllegalStateException("Activity cannot be null")
  }

}