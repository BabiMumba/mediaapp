package com.babistone.kitumaini

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.babistone.kitumaini.R
import com.babistone.kitumaini.model.util.FirestoreUtil
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import kotlinx.android.synthetic.main.activity_signin.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.longSnackbar

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN){
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode== Activity.RESULT_OK){
                val progresssdialog = indeterminateProgressDialog("setting up your account")
                FirestoreUtil.initCurrentUserIffirstTime {
                    startActivity(intentFor<HomeActivity>().newTask().clearTask())
                    progresssdialog.dismiss()
                }


            }
            else if (resultCode == Activity.RESULT_CANCELED){
                if (response == null) return
                when(response.error?.errorCode){
                    ErrorCodes.NO_NETWORK ->
                        longSnackbar(linearlayout,"pas d'internet")
                    ErrorCodes.UNKNOWN_ERROR ->
                        longSnackbar(linearlayout,"Erreur inconnue")
                }

            }

        }
    }
}