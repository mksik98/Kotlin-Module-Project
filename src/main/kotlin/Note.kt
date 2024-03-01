package notes

data class Note(val title : String ,val text : String){
    override fun toString(): String {
        return "$title\n$text"
    }
}