package africa.digitalhusters.composewhatsapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.Locale.getDefault
import java.util.Random

data class ChatItem(
    val id: String,
    val contactName: String,
    val lastMessage: String,
    val timestamp: String,
    val profilePictureUrl: String?,
    val unreadMessageCount: Int = 0,
    val isGroupChat: Boolean = false,
    val isFavourite: Boolean = false,
    val hasNewUpdates: Boolean = false,
    val participants: List<String>? = null,
)

@RequiresApi(Build.VERSION_CODES.O)
fun generateRandomChats(count: Int): List<ChatItem> {
    val random = Random()
    val names = listOf(
        "Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Henry", "Ivy",
        "Jack", "Kelly", "Liam", "Mia", "Noah", "Olivia", "Peter", "Quinn", "Riley", "Sophia",
        "Thomas", "Ursula", "Victor", "Wendy", "Xavier", "Yara", "Zack"
    )
    val lastNames = listOf(
        "Smith", "Johnson", "Williams","", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez",
        "Martinez", "Hernandez", "Lopez", "Gonzalez","", "Wilson", "Anderson", "Thomas", "Taylor",
        "Moore", "Jackson","", "Martin", "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez",
        "Clark", "Lewis", "Robinson", "Walker",""
    )

    val messages = listOf(
        "Hey!", "How are you?", "What's up?", "See you later!", "Good morning!",
        "Good night!", "Long time no see!", "I'm busy.", "Let's chat!", "Call me back!",
        "Meeting at 3 PM", "Project update", "Weekend plans?", "Movie night?", "Dinner tonight?",
        "The quick brown fox jumps over the lazy dog. This is a classic pangram, a sentence that uses every letter of the alphabet. It's often used for testing typewriters or fonts, and now, apparently, for generating long messages for Kotlin examples.",
        "This is a very long message designed to test how text wrapping and ellipsis work in Jetpack Compose. It's important to consider how your UI handles long text strings, especially on different screen sizes and orientations.  We need to make sure the text is readable and doesn't break the layout.",
        "Compose is a modern toolkit for building native Android UI. It simplifies and accelerates UI development with less code, powerful tools, and intuitive Kotlin APIs.  This long text is just a filler to see how it behaves in a `Text` composable.",
        "Testing, testing, one two three.  Is this thing on?  This message is intentionally long and repetitive to simulate a real-world scenario where a user might enter a large amount of text.  We want to ensure the UI remains responsive and handles the text gracefully.",
        "Kotlin is a modern, statically typed programming language that runs on the Java Virtual Machine (JVM) and can also be compiled to JavaScript or native code.  It's a great language for Android development, and this message is just a long string to demonstrate its capabilities.",
        "The standard library provides a rich set of functions for working with collections, strings, and other data structures.  Let's see how well it handles a very, very long string like this one.  It should be able to process it efficiently and without any issues.",
        "UI design is crucial for creating a positive user experience.  A well-designed UI is intuitive, easy to use, and visually appealing.  This long message is just a placeholder to test the limits of our UI components and make sure they can handle various text lengths.",
        "Android development is constantly evolving, with new tools and technologies being released regularly.  It's important to stay up-to-date with the latest trends and best practices.  And sometimes, you just need a really long string to test things out.",
        "This is the tenth and final long message in our test set.  We've covered a variety of topics, from pangrams to UI design to Android development.  Hopefully, these messages have been helpful in demonstrating how to handle long text strings in Kotlin and Jetpack Compose.",
        "The best way to learn Android development is by doing.  Start with small projects and gradually increase the complexity.  Don't be afraid to experiment and try new things.  And remember, there are plenty of resources available online to help you along the way.",
        "Jetpack Compose uses a declarative approach to UI development, which means you describe what the UI should look like, and Compose takes care of updating the view hierarchy. This makes it easier to build complex UIs and reduces the amount of boilerplate code you have to write.",
        "State management is a crucial aspect of Android development.  There are various approaches to state management, such as ViewModel, LiveData, and StateFlow.  Choosing the right state management solution depends on the complexity of your application.",
        "Testing is an essential part of the software development process.  Writing unit tests helps you catch bugs early and ensures that your code works as expected.  It's also a good way to document your code and make it easier to understand.",
        "Debugging can be a challenging but rewarding process.  Learning how to use debugging tools effectively can save you a lot of time and frustration.  It's also important to be able to read and understand error messages.",
        "Optimization is key to creating performant Android applications.  There are various techniques you can use to optimize your code, such as reducing the number of views, using efficient data structures, and minimizing network requests.",
        "Accessibility is an important consideration when developing Android apps.  Making your app accessible to users with disabilities can significantly improve their experience.  There are various guidelines and tools available to help you make your app accessible.",
        "Internationalization is the process of adapting your app to different languages and cultures.  This involves translating your app's text and ensuring that your app handles different date and time formats correctly.",
        "Security is a critical aspect of Android development.  It's important to protect user data and prevent unauthorized access.  There are various security best practices you should follow when developing Android apps.",
        "The Android community is a vibrant and supportive community.  There are many online forums, groups, and conferences where you can connect with other Android developers and learn from their experience.",
        "Just checking in", "Happy birthday!", "Congratulations!", "Thank you!", "You're welcome!",
        "Great to hear from you!", "I'll be there soon!", "Sounds good!", "I'm running late.",
        "Traffic is terrible!", "What's the address?", "Can you send me the link?",
        "Did you get my message?", "I have a question.", "Let's catch up!"
    )

    return List(count) {
        val message = messages[random.nextInt(messages.size)]
        val timestamp = LocalDateTime.now().minusDays(random.nextInt(7).toLong())
            .minusHours(random.nextInt(24).toLong()).minusMinutes(random.nextInt(60).toLong())
        val profilePicture = if (random.nextBoolean()) generateRandomImageUrl() else null
        val unreadCount = random.nextInt(5)

        val isGroup = random.nextBoolean()
        val groupNames = generateRandomGroupNames(10)

        val name = if (isGroup) {
            groupNames[random.nextInt(groupNames.size)]
        } else {
            names[random.nextInt(names.size)] + " " + lastNames[random.nextInt(lastNames.size)]
        }

        val participantsList = if (isGroup) {
            val numParticipants = random.nextInt(5) + 2
            names.shuffled().take(numParticipants)
        } else {
            null
        }

        ChatItem(
            contactName = name,
            lastMessage = message,
            timestamp = formatLocalDateTime(timestamp),
            profilePictureUrl = profilePicture,
            unreadMessageCount = unreadCount,
            isGroupChat = isGroup,
            participants = participantsList,
            id = random.nextInt().toString(),
            hasNewUpdates = random.nextBoolean(),
            isFavourite = random.nextBoolean()
        )
    }
}

fun generateRandomImageUrl(): String {
    val random = Random()
    val id = random.nextInt(1084)
    return "https://picsum.photos/id/$id/200/300"
}

fun generateRandomGroupNames(count: Int): List<String> {
    val random = Random()
    val prefixes = listOf(
        "The", "Awesome","Super","Mega","Cool","Fantastic","Incredible","Dynamic","United",
        "Creative"
    )
    val suffixes = listOf(
        "Squad","Team","Crew","Group","Gang","Alliance","Circle","Collective","Legion","Force"
    )
    val nouns = listOf(
        "Coders","Gamers","Friends","Explorers","Innovators","Achievers","Dreamers","Warriors",
        "Thinkers","Creators"
    )

    return List(count) {
        val prefix = prefixes[random.nextInt(prefixes.size)]
        val suffix = suffixes[random.nextInt(suffixes.size)]
        val noun = nouns[random.nextInt(nouns.size)]

        when (random.nextInt(3)) { // Three possible formats
            0 -> "$prefix $noun $suffix" // "The Coders Squad"
            1 -> "$noun $suffix"       // "Coders Squad"
            2 -> "$prefix $noun"       // "The Coders"
            else -> "$prefix $noun $suffix" // Default (shouldn't happen)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatLocalDateTime(
    dateTime: LocalDateTime,
    locale: Locale = getDefault(),
): String {
    val now = LocalDateTime.now()
    val today = now.toLocalDate()
    val date = dateTime.toLocalDate()

    return when {
        date == today -> {
            val formatter =
                DateTimeFormatter.ofPattern("HH:mm", locale) // Time only (24-hour format)
            dateTime.format(formatter)
        }

        date.isEqual(today.minusDays(1)) -> "Yesterday"
        date.isBefore(today.minusDays(1)) -> {
            val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", locale) // YYYY/MM/DD format
            dateTime.format(formatter)
        }

        else -> {
            // Handle future dates if needed.  For this requirement, we'll just use YYYY/MM/DD
            val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", locale)
            dateTime.format(formatter)
        }
    }
}