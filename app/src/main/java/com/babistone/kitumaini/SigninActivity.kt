package com.babistone.kitumaini

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.babistone.kitumaini.R
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activity_signin.*
import org.jetbrains.anko.startActivityForResult

class SigninActivity : AppCompatActivity() {
    private val RC_SIGN_IN = 1

    private val siginiprovides = listOf(AuthUI.IdpConfig.EmailBuilder()
        .setAllowNewAccounts(true)
        .setRequireName(true)
        .build()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        acount_sign_in.setOnClickListener {
            val intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(siginiprovides)
                .setLogo(R.drawable.ic_launcher)
                .build()
            startActivityForResult(intent,RC_SIGN_IN)

        }
    }
}