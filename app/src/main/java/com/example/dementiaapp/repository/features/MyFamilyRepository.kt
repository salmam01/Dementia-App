package com.example.dementiaapp.repository.features

import com.example.dementiaapp.domain.models.feature.Person
import java.time.LocalDate

interface MyFamilyRepository {
    fun getMyFamilyEntries(): List<Person>
    fun getEntryById(id: String): Person?

    fun addEntry(entry: Person)
    fun editEntry(entry: Person)
    fun deleteEntry(entry: Person)
}

class MyFamilyRepositoryImpl: MyFamilyRepository {
    override fun getMyFamilyEntries(): List<Person> {
        return myFamilyEntries
    }

    override fun getEntryById(id: String): Person? {
        return myFamilyEntries.firstOrNull { it.id == id }
    }

    override fun addEntry(entry: Person) {
        val newId = (myFamilyEntries.maxOfOrNull { it.id.toInt() } ?: 0) + 1
        val newEntry = entry.copy(id = newId.toString())

        myFamilyEntries.add(newEntry)
    }

    override fun editEntry(entry: Person) {
        val index = myFamilyEntries.indexOfFirst { it.id == entry.id }
        if (index != -1) {
            myFamilyEntries[index] = entry
        }
    }

    override fun deleteEntry(entry: Person) {
        myFamilyEntries.removeAll { it.id == entry.id }
    }

    private val myFamilyEntries = mutableListOf(
        Person(
            id = "1",
            relationShip = "Granddaughter",
            fullName = "Celine Baker",
            nickName = "Celly",
            birthday = LocalDate.of(2002, 5, 14),
            address = "12 Rosewood Lane, Vienna",
            number = "+43 660 1234567",
            favouriteColour = "Lavender",
            favouriteFood = "Spaghetti Bolognese",
            favouriteMusic = "Taylor Swift",
            favouriteBook = "Pride and Prejudice",
            hobbies = "Painting, baking, and hiking"
        ),
        Person(
            id = "2",
            relationShip = "Son",
            fullName = "Michael Baker",
            nickName = "Mike",
            birthday = LocalDate.of(1975, 11, 3),
            address = "45 Oak Street, Vienna",
            number = "+43 664 2345678",
            favouriteColour = "Navy Blue",
            favouriteFood = "Grilled Salmon",
            favouriteMusic = "The Beatles",
            favouriteBook = "The Hobbit",
            hobbies = "Gardening, chess, and cycling"
        ),
        Person(
            id = "3",
            relationShip = "Daughter",
            fullName = "Sarah Baker",
            nickName = "Sally",
            birthday = LocalDate.of(1978, 8, 22),
            address = "78 Maple Avenue, Vienna",
            number = "+43 699 3456789",
            favouriteColour = "Turquoise",
            favouriteFood = "Chicken Curry",
            favouriteMusic = "Adele",
            favouriteBook = "To Kill a Mockingbird",
            hobbies = "Knitting, reading, and yoga"
        ),
        Person(
            id = "4",
            relationShip = "Best Friend",
            fullName = "Margaret Wilson",
            nickName = "Maggie",
            birthday = LocalDate.of(1948, 2, 10),
            address = "9 Willow Court, Vienna",
            number = "+43 676 4567890",
            favouriteColour = "Emerald Green",
            favouriteFood = "Apple Pie",
            favouriteMusic = "Frank Sinatra",
            favouriteBook = "Jane Eyre",
            hobbies = "Birdwatching, puzzles, and sewing"
        )
    )
}