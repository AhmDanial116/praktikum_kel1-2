package com.app.praktikum_kel1_2.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.praktikum_kel1_2.components.NoteCard
import com.app.praktikum_kel1_2.model.response.NoteItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(notes: List<NoteItem>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "📒 Daftar Notes",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        NoteList(
            notes = notes,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Composable
fun NoteList(notes: List<NoteItem>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(notes) { note ->
            NoteCard(note)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNotesScreen() {
    val dummyNotes = listOf(
        NoteItem(
            nm_lengkap = "Danial",
            id_notes = "1",
            id_user = "user1",
            title = "📘 Belajar Compose",
            content = "Kita akan belajar membuat UI dengan Jetpack Compose.",
            created_at = "",
            updated_at = ""
        ),
        NoteItem(
            nm_lengkap = "Admin",
            id_notes = "2",
            id_user = "user2",
            title = "🚀 Authentication",
            content = "Kita akan bahas tentang JWT dan login session.",
            created_at = "",
            updated_at = ""
        )
    )

    NotesScreen(notes = dummyNotes)
}
