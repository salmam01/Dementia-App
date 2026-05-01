package com.example.dementiaapp.repository.features

import com.example.dementiaapp.domain.models.DiaryEntry
import java.time.LocalDate
import java.time.LocalTime

interface DiaryRepository {
    fun getEntryForDate(date: LocalDate): DiaryEntry?
    fun getEntryById(id: String): DiaryEntry?

    fun addEntry(entry: DiaryEntry)
    fun editEntry(entry: DiaryEntry)
    fun deleteEntry(entry: DiaryEntry)
}

class DiaryRepositoryImpl: DiaryRepository {
    override fun getEntryForDate(
        date: LocalDate
    ): DiaryEntry? {
        return diaryEntries.firstOrNull { entry ->
            entry.date.isEqual(date)
        }
    }

    override fun getEntryById(id: String): DiaryEntry? {
        return diaryEntries.firstOrNull { it.id == id}
    }

    override fun addEntry(entry: DiaryEntry) {
        val newId = (diaryEntries.maxOfOrNull { it.id.toInt() } ?: 0) + 1
        val newEntry = entry.copy(id = newId.toString())

        diaryEntries.add(newEntry)
    }

    override fun editEntry(entry: DiaryEntry) {
        val index = diaryEntries.indexOfFirst { it.id == entry.id }
        if (index != -1) {
            diaryEntries[index] = entry
        }
    }

    override fun deleteEntry(entry: DiaryEntry) {
        diaryEntries.removeAll { it.id == entry.id }
    }

    private val diaryEntries = mutableListOf(
        DiaryEntry(
            id = "1",
            title = "Relaxing Walk",
            content = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. ",
            date = LocalDate.now().minusDays(1),
            time = LocalTime.of(11, 43),
            images = listOf(
                "https://www.vets4pets.com/pet-health-advice/cat-advice/anxiety-in-cats/",
                "https://www.four-paws.org/our-stories/publications-guides/a-cats-personality",
                "https://www.scottishspca.org/advice/cats/what-can-cats-eat/",
                "https://images.ctfassets.net/100cwma5ubtt/1GiiFUhJfnfFaV9apeWqYo/24d806d058e8892474c72197daec2486/FS_1440x810_cat-entertainment_7-reasons-you-should-adopt-a-cat.jpg?fm=webp&w=1200&q=50"
            )
        ),
        DiaryEntry(
            id = "2",
            title = "Example 2",
            content = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. ",
            date = LocalDate.now().plusDays(1),
            time = LocalTime.of(19, 12),
            images = emptyList()
        ),
        DiaryEntry(
            id = "3",
            title = "Example 3",
            content = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. ",
            date = LocalDate.now(),
            time = LocalTime.of(16, 39),
            images = listOf(
                "https://www.vets4pets.com/pet-health-advice/cat-advice/anxiety-in-cats/",
                "https://www.four-paws.org/our-stories/publications-guides/a-cats-personality",
                "https://www.scottishspca.org/advice/cats/what-can-cats-eat/",
                "https://images.ctfassets.net/100cwma5ubtt/1GiiFUhJfnfFaV9apeWqYo/24d806d058e8892474c72197daec2486/FS_1440x810_cat-entertainment_7-reasons-you-should-adopt-a-cat.jpg?fm=webp&w=1200&q=50"
            )
        )
    )
}