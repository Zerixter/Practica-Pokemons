package net.hamzasaddouki.Wrappers;

import net.hamzasaddouki.Exceptions.PokemonsException;
import net.hamzasaddouki.Exceptions.TipusException;
import net.hamzasaddouki.Models.Pokemon;
import net.hamzasaddouki.Interfaces.PokemonsDAO;
import net.hamzasaddouki.Models.Tipus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonsDAOMySQL implements PokemonsDAO {

    private static final String SELECCIONAR_POKEMONS = "SELECT * FROM POKEMONS ORDER BY RAND() LIMIT 4";
    private static final String SELECCIONAR_TIPUS = "SELECT * FROM POKETIPUS WHERE POKEMON_ID = ?";
    private static final String SELECCIONAR_EFECTE = "SELECT * FROM TIPUS_ATAC WHERE TIPUS_ATACANT_ID = ? AND TIPUS_ATACAT_ID = ?";

    private Connection conn;

    public PokemonsDAOMySQL() throws SQLException
    {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pk", "hamza", "hamza");
    }

    public List<Pokemon> getPokemons() throws SQLException, PokemonsException, TipusException
    {
        Statement x = conn.createStatement();
        ResultSet rs = x.executeQuery(SELECCIONAR_POKEMONS);
        List<Pokemon> Pokemons = new ArrayList<Pokemon>();
        while (rs.next())
        {
            Pokemon pokemon = new Pokemon();
            pokemon.setId(rs.getInt("POKEMON_ID"));
            pokemon.setNom(rs.getString("NOM"));
            pokemon.setPes(rs.getFloat("PES"));
            pokemon.setTipus(getTipus(pokemon.getId()));
            Pokemons.add(pokemon);
        }

        if (Pokemons.size() < 3)
        {
            throw new PokemonsException("No hi ha els suficients resultats");
        }
        return Pokemons;
    }

    public List<Tipus> getTipus(int id_pokemon) throws SQLException, TipusException
    {
        List<Tipus> TipusList = new ArrayList<Tipus>();
        PreparedStatement pe = conn.prepareStatement(SELECCIONAR_TIPUS);
        pe.setInt(1, id_pokemon);
        ResultSet rs = pe.executeQuery();

        while(rs.next())
        {
            Tipus tipus = new Tipus();
            tipus.setTipusId(rs.getInt("TIPUS_ID"));
            TipusList.add(tipus);
        }

        if (TipusList.size() == 0)
        {
            throw new TipusException("No s'ha trobat cap resultat de tipus per aquest pokemon.");
        }

        return TipusList;
    }

    public float getEfecte(int id_atacant, int id_atacat) throws SQLException
    {
        PreparedStatement pe = conn.prepareStatement(SELECCIONAR_EFECTE);
        pe.setInt(1, id_atacant);
        pe.setInt(2, id_atacat);
        ResultSet rs = pe.executeQuery();
        float efecte = 1;

        while (rs.next())
        {
            efecte = rs.getFloat("EFECTE");
        }

        return efecte;
    }
}
