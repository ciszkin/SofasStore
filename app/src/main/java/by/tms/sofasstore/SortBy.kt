package by.tms.sofasstore

enum class SortBy(private val text: String) {
    HIGH_TO_LOW("Expensive first"),
    LOW_TO_HIGH("Cheap first"),
    NAME_AZ("Names A-Z"),
    NAME_ZA("Names Z-A");

    override fun toString(): String {
        return text
    }
}