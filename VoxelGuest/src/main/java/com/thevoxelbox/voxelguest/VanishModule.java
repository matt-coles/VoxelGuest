/*
 * VoxelGuest
 *
 * Copyright (C) 2011, 2012 psanker and contributors

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package com.thevoxelbox.voxelguest;

import java.util.TreeSet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class VanishManager {
    
    protected static TreeSet<String> vanished = new TreeSet<String>();
    protected static TreeSet<String> safeList = new TreeSet<String>();
    
    public static void hidePlayer(Player hidden) {
        if (!vanished.contains(hidden.getName())) {
            vanished.add(hidden.getName());
            
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                if (!safeList.contains(p.getName()))
                    p.hidePlayer(hidden);
            }
        }
    }
    
    public static void revealPlayer(Player hidden) {
        if (vanished.contains(hidden.getName())) {
            vanished.remove(hidden.getName());
            
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                if (!safeList.contains(p.getName()))
                    p.showPlayer(hidden);
            }
        }
    }
    
    public static void resetHiddenPlayer(Player hidden) {
        if (vanished.contains(hidden.getName())) {
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                if (!safeList.contains(p.getName()))
                    p.hidePlayer(hidden);
            }
        }
    }
    
    public static void addMemberToSafeList(Player p) {
        if (!safeList.contains(p.getName()))
            safeList.add(p.getName());
    }
    
    public static boolean isOnSafeList(Player p) {
        return safeList.contains(p.getName());
    }
    
    public static boolean isVanished(Player p) {
        return vanished.contains(p.getName());
    }
}