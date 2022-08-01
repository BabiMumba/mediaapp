package com.babistone.kitumaini.model.util

import com.google.firebase.firestore.FirebaseFirestore

object FirestoreUtil {
    private val firestoreinstance :FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
}