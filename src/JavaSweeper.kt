import sweeper.*
import sweeper.Box
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Image
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*

class JavaSweeper: JFrame() {

    private val game: Game
    private lateinit var panel: JPanel
    private lateinit var label: JLabel
    private val cols = 9
    private val rows = 9
    private val bombs = 10
    private val imageSize = 50

    init {
        game = Game(cols, rows, bombs)
        game.start()
        Ranges.setSize(Coord(cols, rows))
        setImages()
        initLabel()
        initPanel()
        initFrame()
    }

    private fun initPanel() {
        panel = object : JPanel() {
            override fun paintComponent(g: Graphics?) {
                super.paintComponent(g)
                g?.run {
                    Ranges.isAllCoords.forEach { coord ->
                        game.getBox(coord)?.let {
                            drawImage(
                                it.image as Image,
                                coord.x * imageSize,
                                coord.y * imageSize,
                                this@JavaSweeper
                            )
                        }
                    }
                }
            }
        }

        panel.addMouseListener(object : MouseAdapter() {
            override fun mousePressed(e: MouseEvent?) {
                e?: return
                val x = e.x / imageSize
                val y = e.y / imageSize
                val coord = Coord(x, y)
                when(e.button) {
                    MouseEvent.BUTTON1 -> game.pressLeftButton(coord)
                    MouseEvent.BUTTON3 -> game.pressRightButton(coord)
                    MouseEvent.BUTTON2 -> game.start()
                }
                label.text = getMessage()
                panel.repaint()
            }
        })

        panel.preferredSize = Dimension(Ranges.size.x * imageSize, Ranges.size.y * imageSize)
        add(panel)
    }

    private fun getMessage(): String {
        return when(game.state) {
            GameState.PLAYED -> "Think twice"
            GameState.BOMBED -> "YOU LOSE! BIG BA-DA-BOOM"
            GameState.WINNER -> "CONGRATULATIONS!"
        }
    }

    private fun initLabel() {
        label = JLabel("Welcome!")
        add(label, BorderLayout.SOUTH)
    }

    private fun initFrame() {
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        title = "Java Sweeper"
        isResizable = false
        isVisible = true
        pack()
        setLocationRelativeTo(null)
        iconImage = getImage("icon")
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

fun main(args: Array<String>) {
    JavaSweeper()
}