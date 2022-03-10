package com.musalasoft.server.enumeration;

public enum DroneModel {
   // (Lightweight, Middleweight, Cruiserweight, Heavyweight)

   LIGHTWEIGHT("LIGHTWEIGHT"),
   MIDDLEWEIGHT("MIDDLEWEIGHT"),
   CRUISERWEIGHT("CRUISERWEIGHT"),
   HEAVYWEIGHT("HEAVYWEIGHT");
  

   private final String droneModel;

   DroneModel(String droneModel){
       this.droneModel = droneModel;
   }

   public String getStatus(){
       return droneModel;
   }
    
}
