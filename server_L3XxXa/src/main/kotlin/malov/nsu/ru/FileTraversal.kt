package malov.nsu.ru

import java.io.File
import java.nio.file.Files

class FileTraversal {
    fun showAllFiles(): String {
        val files: ArrayList<File> = ArrayList()
        val stringBuilder = StringBuilder()
        File("./files/").walk().forEach {
            //  println(it)
            files.add(it)
            stringBuilder.append(it.name)
            stringBuilder.append("\n")
        }
        return stringBuilder.toString()
    }
}