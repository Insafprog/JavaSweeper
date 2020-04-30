package sweeper

import java.util.*

object Ranges {
    var size: Coord = Coord(0, 0)
        private set
    private val allCoords = mutableListOf<Coord>()
    private val random = Random()

    fun setSize(size: Coord) {
        Ranges.size = size
        for (x in 0 until size.x) {
            for (y in 0 until size.y) {
                allCoords.add(Coord(x, y))
            }
        }
    }

    fun inRange(coord: Coord) = coord.x in 0 until size.x && coord.y in 0 until size.y

    val isAllCoords: List<Coord>
        get() = allCoords.toList()

    val randomCoord: Coord
        get() = Coord(random.nextInt(size.x), random.nextInt(size.y))

    fun getCoordsAround(coord: Coord): List<Coord> {
        var around: Coord
        val list = mutableListOf<Coord>()
        for (x in coord.x - 1 .. coord.x + 1) {
            for (y in coord.y - 1 .. coord.y + 1) {
                around = Coord(x, y)
                if (inRange(around)) {
                    if (around != coord)
                        list.add(around)
                }
            }
        }
        return list
    }
}