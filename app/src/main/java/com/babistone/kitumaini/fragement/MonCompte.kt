package com.babistone.kitumaini.fragement

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.babistone.kitumaini.R
import kotlinx.android.synthetic.main.fragment_mon_compte.view.*
import org.jetbrains.anko.support.v4.startActivityForResult


class MonCompte : Fragment() {

    private val RC_SELECT_IMAGE =2
    private lateinit var selectedImage:ByteArray
    private var picturejustChanged = false

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
          }
        return view
    }

}