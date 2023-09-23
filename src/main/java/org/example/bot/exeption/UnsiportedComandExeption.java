package org.example.bot.exeption;

public class UnsiportedComandExeption extends RuntimeException{
    public UnsiportedComandExeption(String message){
        super(message);
    }

    public static UnsiportedComandExeption from(String command){
        return new UnsiportedComandExeption("UNSUPORTED command - " + command);
    }
}
