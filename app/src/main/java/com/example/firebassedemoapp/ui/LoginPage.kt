package com.example.firebassedemoapp.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.firebassedemoapp.R
import com.example.firebassedemoapp.databinding.FragmentLoginPageBinding
import com.example.firebassedemoapp.databinding.FragmentWelcomePageBinding
import com.google.firebase.auth.FirebaseAuth


class LoginPage : Fragment(R.layout.fragment_login_page) {

    private var _binding: FragmentLoginPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val enteredEmail = binding.emailInput.text.toString()
            val enteredPassword = binding.passwordInput.text.toString()
            var enteredValidEmailCheck = false
            var enteredValidPasswordCheck = false
            auth = FirebaseAuth.getInstance()
            navController = it.findNavController()


            if ((TextUtils.isEmpty(enteredEmail)) || ("@" !in enteredEmail)) {
                binding.emailInput.error = "Enter a valid Email id"
            } else{
                enteredValidEmailCheck = true
            }

            if ((TextUtils.isEmpty(enteredPassword)) || (enteredPassword.length<6) ){
                binding.passwordInput.error = "Enter a valid 6 Char password"
            } else{
                enteredValidPasswordCheck = true
            }


            if (enteredValidEmailCheck && enteredValidPasswordCheck){

                loginUser(enteredEmail,enteredPassword)


            }

        }

        binding.resetpassword.setOnClickListener{
            val enteredEmail = binding.emailInput.text.toString()

            if ((TextUtils.isEmpty(enteredEmail)) || ("@" !in enteredEmail)) {
                binding.emailInput.error = "Enter a valid Email id"
            } else{
                //auth.sendPasswordResetEmail(enteredEmail).addOnCompleteListener {
                Toast.makeText(requireContext(),"Email sent successfully", Toast.LENGTH_LONG).show()
                TODO("Implement reset password method")
                //}
            }


        }
    }

    private fun loginUser(enteredEmail: String, enteredPassword: String) {
        auth.signInWithEmailAndPassword(enteredEmail,enteredPassword).addOnCompleteListener {
            if (it.isSuccessful){
                navController.navigate(R.id.action_loginPage_to_mainPage)
                Toast.makeText(requireContext(),"Logged in Successfully", Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(requireContext(),"Incorrect Credentials", Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}


