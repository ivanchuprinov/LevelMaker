package hapless.eagles.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Kneesnap on 2/17/19.
 */
@Getter
@AllArgsConstructor
public enum MoveDirection {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int xDelta;
    private final int yDelta;
}
