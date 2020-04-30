package sweeper

class Game(cols: Int, rows: Int, bombs: Int) {

    private val bomb: Bomb
    private val flag: Flag
    private val gameOver: Boolean
        get() {
            if (state == GameState.PLAYED) return false
            start()
            return true
        }
    lateinit var state: GameState
        private set

    init {
        Ranges.setSize(Coord(cols, rows))
        bomb = Bomb(bombs)
        flag = Flag()
    }

    fun start() {
        bomb.start()
        flag.start()
        state = GameState.PLAYED
    }

    fun getBox(coord: Coord) = if(flag.get(coord) == Box.OPENED) bomb.get(coord) else flag.get(coord)

    private fun checkWinner() {
        if (state == GameState.PLAYED)
            if (flag.countOfClosedBoxes == bomb.totalBombs)
                state = GameState.WINNER
    }

    private fun openBox(coord: Coord) {
        when(flag.get(coord)) {
            Box.OPENED -> setOpenedToClosedBoxesAroundNumber(coord)
            Box.CLOSED -> when(bomb.get(coord)) {
                Box.ZERO -> openBoxesAround(coord)
                Box.BOMB -> openBombs(coord)
                else -> flag.setOpenedToBox(coord)
            }
            else -> return
        }
    }

    private fun openBombs(bombed: Coord) {
        state = GameState.BOMBED
        flag.setBombedToBox(bombed)
        Ranges.isAllCoords.forEach { coord ->
            if (bomb.get(coord) == Box.BOMB)
                flag.setOpenedToClosedBombBox(coord)
            else
                flag.setNobombToFlagedSafeBox(coord)
        }
    }

    private fun openBoxesAround(coord: Coord) {
        flag.setOpenedToBox(coord)
        Ranges.getCoordsAround(coord).forEach { around ->
            openBox(around)
        }
    }

    fun pressRightButton(coord: Coord) {
        if (gameOver) return
        flag.toggleFlagedToBox(coord)
    }

    fun pressLeftButton(coord: Coord) {
        if (gameOver) return
        openBox(coord)
        checkWinner()
    }

    private fun setOpenedToClosedBoxesAroundNumber(coord: Coord) {
        if (bomb.get(coord) != Box.BOMB)
            if (flag.getCountOfFlagedBoxesAround(coord) == bomb.get(coord)?.number)
                Ranges.getCoordsAround(coord).forEach { around ->
                    if (flag.get(around) == Box.CLOSED)
                        openBox(around)
                }
    }
}