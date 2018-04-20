package net.hamzasaddouki.Interfaces;

import net.hamzasaddouki.Exceptions.PokemonsException;
import net.hamzasaddouki.Exceptions.TipusException;
import net.hamzasaddouki.Models.Pokemon;
import net.hamzasaddouki.Models.Tipus;

import java.sql.SQLException;
import java.util.List;

public interface PokemonsDAO {
    List<Pokemon> getPokemons() throws SQLException, PokemonsException, TipusException;
    List<Tipus> getTipus(int id_pokemon) throws SQLException, TipusException;
}
