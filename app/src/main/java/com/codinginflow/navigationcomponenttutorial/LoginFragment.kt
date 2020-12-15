package com.codinginflow.navigationcomponenttutorial

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {
    // gets the sent args to login fragment
    private val args: LoginFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //*** do not forget adding <nav-graph android:value="@navigation/nav_graph"/> in
        // the manifest file inside activity tag to enable deep linking ***
        val userNameDeepLink = args.username
        // no worries the app will not crash when an null value is add as
        // editText support null fields (will be just empty editText)
        ed_username.setText(userNameDeepLink)

        button_confirm.setOnClickListener {
            val username = ed_username.text.toString()
            val password = ed_password.text.toString()
            val action =
                LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(username, password)
            findNavController().navigate(action)
        }
    }
}