package edu.aitu.oop3.entities;

public class Room{
    private int id ;
    private String roomType;
    private double price;
    private boolean available;
    public Room(int id,String roomType,double price,boolean available){
        this.id=id;
        this.roomType=roomType;
        this.price=price;
        this.available=available;
    }

    public Room(String roomType,double price,boolean available){
        this.roomType=roomType;
        this.price=price;
        this.available=available;
    }

    public int getId() {return id;}
    public String getRoomType(){ return roomType; }
    public double getPrice(){ return price; }
    public boolean isAvailable(){ return available; }
}
// Room entity by sultan

