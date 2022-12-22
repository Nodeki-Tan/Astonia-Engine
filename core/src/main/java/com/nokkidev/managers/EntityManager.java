package com.nokkidev.managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.nokkidev.entities.DebugSquareEntity;
import com.nokkidev.entities.Entity;

public class EntityManager {

	public static List<Entity> entityList = new ArrayList<Entity>();
	public static List<DebugSquareEntity> debugSquareEntityList = new ArrayList<DebugSquareEntity>();
	
	public static void tick(float delta) {
		for (int i = 0; i < entityList.size(); i++) {
			entityList.get(i).tick(delta);
		}

		//System.out.println("entities " + entityList.size());
	}
	
	public static void cleanUp() {
		entityList.clear();
		debugSquareEntityList.clear();
	}
	
}
