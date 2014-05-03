package eu.lestard.colorpuzzlefx.core;

import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class ColorProfile {

    private Map<Colors, Color> profile = new EnumMap<>(Colors.class);

    public ColorProfile(){
        profile.put(Colors.Color1, Color.LIGHTBLUE);
        profile.put(Colors.Color2, Color.INDIANRED);
        profile.put(Colors.Color3, Color.YELLOW);
        profile.put(Colors.Color4, Color.GREENYELLOW);
        profile.put(Colors.Color5, Color.ORANGE);
        profile.put(Colors.Color6, Color.DARKCYAN);
    }

    public Map<Colors, Color> getProfile(){
        return Collections.unmodifiableMap(profile);
    }

}
