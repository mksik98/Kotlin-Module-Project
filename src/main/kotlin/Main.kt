package notes

import java.util.Scanner

fun main() {

    val scanner = Scanner(System.`in`)
    val archiveList = mutableListOf<Archive>()
    val menu = Menu()
    var menuInfo = MenuInfo.ARCHIVE
    var selectedArchiveIndex = -1
    var selectedNoteIndex = -1
    println("----ЗАМЕТКИ----")
    while (true) {
        when (menuInfo) {
            MenuInfo.ARCHIVE -> menu.renderArchive(archiveList)
            MenuInfo.NOTE -> menu.renderNotes(archiveList[selectedArchiveIndex].notes)
            MenuInfo.VIEW -> {
                if (selectedArchiveIndex >= 0 && selectedNoteIndex >= 0) {
                    println(archiveList[selectedArchiveIndex].notes[selectedNoteIndex])
                }
                scanner.nextLine()
                menuInfo = MenuInfo.NOTE
                continue
            }

            MenuInfo.CREATE -> {
                ArchiveManager().createArchive(
                    scanner = scanner,
                    selectedArchiveIndex = selectedArchiveIndex,
                    changeStatus = {
                        menuInfo = it
                    },
                    addNote = {
                        archiveList[selectedArchiveIndex].notes.add(it)
                    },
                    addArchive = {
                        archiveList.add(it)
                    }

                )
                continue
            }

        }

        var selectedIndex: Int?

        while (true) {

            val input = scanner.nextLine()

            try {
                selectedIndex = input.toInt()

                if (selectedIndex == 0) {
                    menuInfo = MenuInfo.CREATE
                    break
                } else if (selectedIndex < menu.menuPos.lastIndex && selectedIndex >= 0) {
                    when (menuInfo) {
                        MenuInfo.ARCHIVE -> {
                            selectedArchiveIndex = selectedIndex - 1
                            menuInfo = MenuInfo.NOTE
                        }

                        MenuInfo.NOTE -> {
                            selectedNoteIndex = selectedIndex - 1
                            menuInfo = MenuInfo.VIEW
                        }

                        else -> {}


                    }
                    break
                } else if (selectedIndex == menu.menuPos.lastIndex) {
                    when (menuInfo) {
                        MenuInfo.NOTE -> menuInfo = MenuInfo.ARCHIVE
                        else -> return
                    }
                    break
                } else {
                    println("Некорректный ввод. Пожалуйста, введите число из доступных опций.")
                }
            } catch (e: NumberFormatException) {
                println("Некорректный ввод. Пожалуйста, введите число.")
            }
            continue
        }

    }

}