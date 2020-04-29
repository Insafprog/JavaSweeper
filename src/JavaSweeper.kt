import sweeper.Box
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Image
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

class JavaSweeper: JFrame() {

    private lateinit var panel: JPanel
    private val cols = 15
    private val rows = 1
    private val imageSize = 50

    init {
        setImages()
        initPanel()
        initFrame()
    }

    private fun initPanel() {
        panel = object : JPanel() {
            override fun paintComponent(g: Graphics?) {
                super.paintComponent(g)
                g?.run {
                    Box.values().forEach { box ->
                        drawImage(box.image as Image, box.ordinal * imageSize, 0, this@JavaSweeper)
                    }
                }
            }
        }
        panel.preferredSize = Dimension(cols * imageSize, rows * imageSize)
        add(panel)
    }

    private fun initFrame() {
        pack()
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        title = "Java Sweeper"
        setLocationRelativeTo(null)
        isResizable = false
        isVisible = true
    }

    private fun setImages() {
        Box.values().forEach { it.image = getImage(it.name.toLowerCase()) }
    }

    private fun getImage (name: String): Image {
        val fileName = "img/$name.png"
        val icon = ImageIcon(javaClass.getResource(fileName))
        return icon.image
    }
}

fun main() {
    JavaSweeper()
}