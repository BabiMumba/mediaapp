package com.babistone.kitumaini.model.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

object Storage {
    private val storageInstance: FirebaseStorage by lazy {  FirebaseStorage.getInstance()  }
    private val currentUserref: StorageReference
    get() = storageInstance.reference
        .child(FirebaseAuth.getInstance().uid ?: throw NullPointerException("UID is null"))
}