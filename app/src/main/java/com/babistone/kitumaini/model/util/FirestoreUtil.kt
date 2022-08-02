package com.babistone.kitumaini.model.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

object FirestoreUtil {
    private val firestoreinstance :FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private val currentUserDocRef: DocumentReference
    get() = firestoreinstance.document("usera/${FirebaseAuth.getInstance().uid
    
        ?:throw NullPointerException("UID is null.")}")
}