package com.babistone.kitumaini.fragement

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.babistone.kitumaini.R
import com.babistone.kitumaini.SigninActivity
import com.babistone.kitumaini.model.util.FirestoreUtil
import com.babistone.kitumaini.model.util.Storage
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.data.model.User
import kotlinx.android.synthetic.main.fragment_mon_compte.*
import kotlinx.android.synthetic.main.fragment_mon_compte.view.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.newTask
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.startActivityForResult
import java.io.ByteArrayOutputStream


class MonCompte : Fragment() {

    private val RC_SELECT_IMAGE =2
    private lateinit var selectedImage:ByteArray
    private var picturejustChanged = false

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_mon_compte, container, false)

        view.apply {

            imageUprofil.setOnClickListener {
                val intent = Intent().apply {
                    type = "image/*"
                    action = Intent.ACTION_GET_CONTENT
                    putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg","image/png"))
                }
                startActivityForResult(Intent.createChooser(intent,"select image"),RC_SELECT_IMAGE)
            }
            btn_save.setOnClickListener {
                if (::selectedImage.isInitialized)
                    Storage.unploadProfilPhoto(selectedImage) {imagepath ->
                        FirestoreUtil.updateCurrentUser(editext_name.text.toString(),
                        edittxt_bio.text.toString(),imagepath
                            )
                    }else
                        FirestoreUtil.updateCurrentUser(editext_name.text.toString(),

                            edittxt_bio.text.toString(),null
                            )

            }
            btn_deconnecter.setOnClickListener {
                AuthUI.getInstance()
                    .signOut(this@MonCompte.context!!)
                    .addOnSuccessListener {
                        startActivity(intentFor<SigninActivity>().newTask().clearTask())
                    }
            }

          }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SELECT_IMAGE && resultCode == Activity.RESULT_OK && data != null && data.data != null){
            val selectedImagepath = data.data
            val selectedimageBmp = MediaStore.Images.Media.getBitmap(activity?.contentResolver,selectedImagepath)

            val outputStream = ByteArrayOutputStream()
            selectedimageBmp.compress(Bitmap.CompressFormat.JPEG,90,outputStream)
            selectedImage = outputStream.toByteArray()
            //TODO: lead picture
            picturejustChanged = true
        }

    }

    override fun onStart() {
        super.onStart()
        FirestoreUtil.getCurrentUser {user ->
            if (this@MonCompte.isVisible){
                editext_name.setText(user.name)
                edittxt_bio.setText(user.name)
                if (!picturejustChanged && user.profipath != null )
            }
        }
    }

}