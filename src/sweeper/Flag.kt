package sweeper

internal class Flag {
    private lateinit var flagMap: Matrix
    var countOfClosedBoxes = Ranges.size.x * Ranges.size.y
        private set

    fun start() {
        flagMap = Matrix(Box.CLOSED)
    }

    fun get(coord: Coord) = flagMap.get(coord)
    fun setOpenedToBox(coord: Coord) {
        flagMap.set(coord, Box.OPENED)
        countOfClosedBoxes--
    }

    fun setFlagedToBox(coord: Coord) {
        flagMap.set(coord, Box.FLAGED)
    }

    fun toggleFlagedToBox(coord: Coord) {
        when(flagMap.get(coord)) {
            Box.FLAGED -> setClosedToBox(coord)
            Box.CLOSED -> setFlagedToBox(coord)
            else -> return
        }
    }

    private fun setClosedToBox(coord: Coord) {
        flagMap.set(coord, Box.CLOSED)
    }

    fun setBombedToBox(coord: Coord) {
        flagMap.set(coord, Box.BOMBED)
    }

    fun setOpenedToClosedBombBox(coord: Coord) {
        if(flagMap.get(coord) == Box.CLOSED)
            flagMap.set(coord, Box.OPENED)
    }

    fun setNobombToFlagedSafeBox(coord: Coord) {
        if(flagMap.get(coord) == Box.FLAGED)
            flagMap.set(coord, Box.NOBOMB)
    }

    fun getCountOfFlagedBoxesAround(coord: Coord): Int {
        var count = 0
        Ranges.getCoordsAround(coord).forEach { around ->
            if (flagMap.get(around) == Box.FLAGED)
                count++
        }
        return count
    }
}