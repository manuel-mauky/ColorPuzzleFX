package eu.lestard.colorpuzzlefx.core;

import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class ColorProfile {

    private Map<Colors, Color> profile = new EnumMap<>(Colors.class);

    public ColorProfile(){
        profile.put(Colors.Color1, Color.rgb(135,206,250)); // light blue
        profile.put(Colors.Color2, Color.rgb(155,205,50)); // green
        profile.put(Colors.Color3, Color.rgb(238,221,130)); // yellow
        profile.put(Colors.Color4, Color.rgb(244,164,96)); // orange
        profile.put(Colors.Color5, Color.rgb(255,99,71)); // red
        profile.put(Colors.Color6, Color.rgb(160,82,45)); // brown
        profile.put(Colors.Color7, Color.rgb(139,136,120)); // grey
    }

    public Map<Colors, Color> getProfile(){
        return Collections.unmodifiableMap(profile);
    }

}
