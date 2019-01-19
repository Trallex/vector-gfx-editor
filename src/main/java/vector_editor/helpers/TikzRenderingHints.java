package vector_editor.helpers;

import java.awt.*;
import java.util.Arrays;
import java.util.Map;

/**
 * @author Alexei Drummond
 */
public class TikzRenderingHints extends RenderingHints {

    public TikzRenderingHints(Map<Key, ?> keyMap) {
        super(keyMap);
    }

    /**
     * This key determines how TikzGraphics2D anchors the nodes created by drawString method.
     */
    public static final Key KEY_NODE_ANCHOR;


    public static final Key KEY_FONT_SIZE;


    static {
        KEY_NODE_ANCHOR = new Key(667) {
            @Override
            public boolean isCompatibleValue(Object o) {
                return Arrays.asList(VALUES_NODE_ANCHOR).contains(o);
            }
        };

        KEY_FONT_SIZE = new Key(668) {
            @Override
            public boolean isCompatibleValue(Object o) {
                return Arrays.asList(VALUES_FONT_SIZE).contains(o);
            }
        };
    }

    public static final Object VALUE_CENTER = "center";
    public static final Object VALUE_MID = "mid";
    public static final Object VALUE_BASE = "base";
    public static final Object VALUE_NORTH = "north";
    public static final Object VALUE_SOUTH = "south";
    public static final Object VALUE_WEST = "west";
    public static final Object VALUE_EAST = "east";
    public static final Object VALUE_NORTH_WEST = "north west";
    public static final Object VALUE_NORTH_EAST = "north east";
    public static final Object VALUE_SOUTH_EAST = "south east";
    public static final Object VALUE_SOUTH_WEST = "south west";

    public static final Object[] VALUES_NODE_ANCHOR = {
            VALUE_CENTER, VALUE_MID, VALUE_BASE, VALUE_NORTH, VALUE_SOUTH, VALUE_WEST, VALUE_EAST, VALUE_NORTH_WEST, VALUE_NORTH_EAST, VALUE_SOUTH_EAST, VALUE_SOUTH_WEST
    };

    public static final Object VALUE_tiny = "\\tiny";
    public static final Object VALUE_scriptsize = "\\scriptsize";
    public static final Object VALUE_footnotesize = "\\footnotesize";
    public static final Object VALUE_small = "\\small";
    public static final Object VALUE_normalsize = "\\normalsize";
    public static final Object VALUE_large = "\\large";
    public static final Object VALUE_Large = "\\Large";
    public static final Object VALUE_LARGE = "\\LARGE";
    public static final Object VALUE_huge = "\\huge";
    public static final Object VALUE_Huge = "\\Huge";


    public static final Object[] VALUES_FONT_SIZE = {
            VALUE_tiny, VALUE_scriptsize, VALUE_footnotesize, VALUE_small, VALUE_normalsize, VALUE_large, VALUE_Large, VALUE_LARGE, VALUE_huge, VALUE_Huge
    };


}