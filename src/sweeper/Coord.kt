package sweeper

class Coord(val x: Int, val y: Int) {
    override fun equals(other: Any?) = if (other is Coord) other.x == x && other.y == y else false
    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}