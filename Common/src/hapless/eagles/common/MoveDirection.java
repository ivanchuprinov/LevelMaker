package hapless.eagles.common;

import javafx.scene.input.KeyCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The cardinal movement directions.
 * Created by Kneesnap on 2/17/19.
 */
@Getter
@AllArgsConstructor
public enum MoveDirection {
    UP(0, -1, KeyCode.UP),
    DOWN(0, 1, KeyCode.DOWN),
    LEFT(-1, 0, KeyCode.LEFT),
    RIGHT(1, 0, KeyCode.RIGHT);

    private final int xDelta;
    private final int yDelta;
    private final KeyCode keyCode;

    public MoveDirection getOpposite() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                throw new RuntimeException("Unknown opposite for:" + this);
        }
    }
}
