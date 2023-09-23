package org.example.bot.enums;

import org.example.bot.exeption.UnsiportedComandExeption;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Comand {
    COMAND_START("/start"),
    COMAND_HELP("/help"),
    COMAND_SETTINGS("/settings");

    private static final Map<String, Comand> comands = Arrays.stream(Comand.values())
            .collect(Collectors.toMap(comand -> comand.value, comand -> comand));

    public final String value;

    Comand(String value) {
        this.value = value;
    }

    public static Comand of(final String command){
        Comand c = comands.get(command);
        if (c != null){
            return c;
        }
        throw UnsiportedComandExeption.from(command);
    }
}
