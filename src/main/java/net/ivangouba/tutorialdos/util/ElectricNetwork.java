package net.ivangouba.tutorialdos.util;

import net.ivangouba.tutorialdos.block.entity.CableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import java.util.*;

public class ElectricNetwork {

    private final Map<BlockPos, CableBlockEntity> nodes = new HashMap<>();
    private final Map<BlockPos, List<BlockPos>> connections = new HashMap<>();

    public void addNode(BlockPos pos, CableBlockEntity node) {
        nodes.put(pos, node);
        updateConnections(pos);
    }

    public void removeNode(BlockPos pos) {
        nodes.remove(pos);
        connections.remove(pos);
        for(List<BlockPos> list : connections.values()) {
            list.remove(pos);
        }
    }

    private void updateConnections(BlockPos pos) {
        List<BlockPos> connectedNodes = new ArrayList<>();
        for(Direction dir : Direction.values()) {
            BlockPos neighborPos = pos.relative(dir);
            if(nodes.containsKey(neighborPos)) {
                connectedNodes.add(neighborPos);
                connections.computeIfAbsent(neighborPos, k -> new ArrayList<>()).add(pos);
            }
        }
        connections.put(pos, connectedNodes);

        System.out.println("Nodo en " + pos + " conectado a: " + connectedNodes);
    }

    public List<BlockPos> getConnections(BlockPos pos) {
        return connections.getOrDefault(pos, new ArrayList<>());
    }

    public Collection<CableBlockEntity> getNodes() {
        return nodes.values();
    }

    public boolean contains(BlockPos pos) {
        return nodes.containsKey(pos);
    }

}
