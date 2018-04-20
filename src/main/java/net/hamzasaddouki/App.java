package net.hamzasaddouki;

import net.hamzasaddouki.Exceptions.PokemonsException;
import net.hamzasaddouki.Exceptions.TipusException;
import net.hamzasaddouki.Interfaces.PokemonsDAO;
import net.hamzasaddouki.Models.Lluita;
import net.hamzasaddouki.Models.Pokemon;
import net.hamzasaddouki.Wrappers.PokemonsDAOMySQL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try
        {
            PokemonsDAO bdd = new PokemonsDAOMySQL();
            List<Pokemon> Pokemons = bdd.getPokemons();
            List<Pokemon> Equip1 = new ArrayList<Pokemon>();
            List<Pokemon> Equip2 = new ArrayList<Pokemon>();
            Equip1.add(Pokemons.get(0));
            Equip1.add(Pokemons.get(1));
            Equip2.add(Pokemons.get(2));
            Equip2.add(Pokemons.get(3));
            Lluita Lluita = new Lluita(Equip1, Equip2, (PokemonsDAOMySQL) bdd);
            Lluita.realitzarLluita();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } catch (PokemonsException e) {
            e.printStackTrace();
        } catch (TipusException e) {
            e.printStackTrace();
        }
    }
}
