package com.matheusfroes.swapi.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "pending_bookmarks")
data class PendingBookmark(
        @PrimaryKey
        val personId: Int
)