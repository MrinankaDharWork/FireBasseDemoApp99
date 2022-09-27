package com.example.firebassedemoapp.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.firebassedemoapp.R
import com.example.firebassedemoapp.databinding.ForgotDialogLayoutBinding


class ForgotPassDialog: DialogFragment() {

    private var _binding : ForgotDialogLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        _binding = ForgotDialogLayoutBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.forgot_dialog_title))
        builder.setMessage(getString(R.string.forgot_dialog_message))
        builder.setView(binding.root)

        binding.send.setOnClickListener {
            val enterEmail = binding.enteredText.text.toString()
            if (TextUtils.isEmpty(enterEmail) || ("@" !in enterEmail)){
                binding.enteredText.error = ""
                Toast.makeText(requireContext(),"Enter a valid Email",Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(requireContext(),"Email was Sent",Toast.LENGTH_SHORT).show()
                dialog?.cancel()
            }

        }
        binding.cancel.setOnClickListener {
            Toast.makeText(requireContext(),"Operation Cancelled",Toast.LENGTH_SHORT).show()
            dialog?.cancel()
        }




        return builder.create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}

