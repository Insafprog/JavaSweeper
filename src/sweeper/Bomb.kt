package sweeper

import kotlin.math.min

class Bomb(totalBombs: Int) {
    private lateinit var bombMap: Matrix
    val totalBombs = min(totalBombs, Ranges.size.x * Ranges.size.y / 2)

    fun start() {
        bombMap = Matrix(Box.ZERO)
        repeat(totalBombs) {
            placeBomb()
        }
    }

    fun get(coord: Coord) = bombMap.get(coord)

    private fun placeBomb() {
        while (true) {
            val coord = Ranges.randomCoord
            if (Box.BOMB == bombMap.get(coord)) continue
            bombMap.set(coord, Box.BOMB)
            incNumbersAroundBomb(coord)
            break
        }
    }

    private fun incNumbersAroundBomb(coord: Coord) {
        for (around in Ranges.getCoordsAround(coord)) {
            bombMap.get(around)?.let {
                if (Box.BOMB != it)
                    bombMap.set(around, it.nextNumberBox)
            }
        }
    }
}
