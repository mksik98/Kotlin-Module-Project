package notes

class Menu {
    private val formatter = "%d.%s"
    val menuPos = mutableListOf<ArchiveMenu>()
    fun renderArchive(list:List<Archive>){

        menuPos.clear()
        menuPos.add(ArchiveMenu("Создать архив"))
        menuPos.addAll(list.map { ArchiveMenu(it.name) })
        menuPos.add(ArchiveMenu("Выход"))

        menuPos.forEachIndexed  { index, archiveMenu ->
            println(String.format(formatter,index,archiveMenu.title))
        }
    }

    fun renderNotes(list:List<Note>){
        menuPos.clear()
        menuPos.add(ArchiveMenu("Создать заметку"))
        menuPos.addAll(list.map { ArchiveMenu(it.title) })
        menuPos.add(ArchiveMenu("Назад"))

        menuPos.forEachIndexed  { index, archiveMenu ->
            println(String.format(formatter,index,archiveMenu.title))
        }
    }
}