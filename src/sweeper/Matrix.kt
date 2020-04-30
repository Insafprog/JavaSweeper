package sweeper

internal class Matrix(defaultBox: Box) {
    private val matrix = Array(Ranges.size.x) {
        Array(Ranges.size.y) {
            defaultBox
        }
    }

    fun get (coord: Coord): Box? {
        if (Ranges.inRange(coord))
            return matrix[coord.x][coord.y]
        return null
    }
    fun set (coord: Coord, box: Box) {
        if (Ranges.inRange(coord))
            matrix[coord.x][coord.y] = box
    }
}