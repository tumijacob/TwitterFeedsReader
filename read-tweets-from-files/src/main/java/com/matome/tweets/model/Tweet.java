package com.matome.tweets.model;


public class Tweet {
   private final String owner;
   private final String message;

   public Tweet(String owner, String message) {
       this.owner = owner;
       this.message = message;
   }

   public String getOwner() {
       return owner;
   }

   public String getMessage() {
       return message;
   }
}
