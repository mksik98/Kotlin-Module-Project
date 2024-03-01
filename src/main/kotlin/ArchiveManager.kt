package notes

import java.util.Scanner

class ArchiveManager {
    fun createArchive(
        scanner: Scanner,
        selectedArchiveIndex: Int,
        changeStatus: (state: MenuInfo) -> Unit,
        addNote: (note: Note) -> Unit,
        addArchive: (archive: Archive) -> Unit
    ) {
        when {
            selectedArchiveIndex < 0 -> {
                println("Введите название архива")
                val archiveName = scanner.nextLine()
                if (archiveName.isNotBlank()) {
                    addArchive(Archive(archiveName, mutableListOf()))
                    changeStatus(MenuInfo.ARCHIVE)
                } else {
                    println("Название архива не может быть пустым.")
                }
            }

            selectedArchiveIndex >= 0 -> {
                println("Введите название заметки")
                val name = scanner.nextLine()

                if (name.isBlank()) {
                    println("Имя заметки не может быть пустым.")
                } else {
                    var text: String
                    while (true) {
                        println("Введите текст:")
                        text = scanner.nextLine()
                        if (text.isNotBlank()) {
                            break
                        } else {
                            println("Текст заметки не может быть пустым. Попробуйте снова.")
                        }
                    }
                    addNote(Note(name, text))
                    changeStatus(MenuInfo.NOTE)


                }
            }
        }
    }
}


