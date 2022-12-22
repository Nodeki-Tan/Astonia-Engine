package com.nokkidev.managers;

import com.nokkidev.core.states.State;

public class StateManager {
	
	private static State currentState = null;

	public static State getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(State currentState) {

		if(StateManager.currentState != null){
			StateManager.currentState.dispose();
		}

		StateManager.currentState = currentState;
		currentState.init();
	}

}
