package net.ivangouba.tutorialdos.util;

import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

public class ElectricNetworkManager {

    private static final Map<Level, ElectricNetwork> networks = new HashMap<>();

    public static ElectricNetwork get (Level level) {
        return networks.computeIfAbsent(level, k -> new ElectricNetwork());
    }

}
