package com.musalasoft.server.enumeration;

public enum DroneState {
   // (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).
   IDLE("IDLE"),
   LOADING("LOADING"),
   LOADED("LOADED"),
   DELIVERING("DELIVERING"),
   DELIVERED("DELIVERED"),
   RETURNING("RETURNING");

   private final String droneState;

   DroneState(String droneState){
       this.droneState = droneState;
   }

   public String getStatus(){
       return droneState;
   }



    
}
