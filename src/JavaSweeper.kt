import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

class JavaSweeper: JFrame() {

    private lateinit var panel: JPanel
    private val cols = 15
    private val rows = 1
    private val imageSize = 50

    init {
        initPanel()
        initFrame()
    }

    private fun initPanel() {
        panel = JPanel()
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
}

fun main() {
    JavaSweeper()
}