package net.hamzasaddouki.Models;

import net.hamzasaddouki.Wrappers.PokemonsDAOMySQL;

import java.sql.SQLException;
import java.util.List;

public class Lluita
{
    private List<Pokemon> Equip1;
    private List<Pokemon> Equip2;
    private PokemonsDAOMySQL bdd;
    private static final float ATAC_BASE = 7;

    public Lluita(List<Pokemon> Equip1, List<Pokemon> Equip2, PokemonsDAOMySQL bdd)
    {
        this.Equip1 = Equip1;
        this.Equip2 = Equip2;
        this.bdd = bdd;
    }

    public void realitzarLluita() throws SQLException
    {
        int count = 0;

        List<Tipus> Atacant;
        List<Tipus> Atacat;
        float efecte;
        while (count < 4)
        {
            for (Pokemon PokemonEquip1 : this.Equip1)
            {
                for (Pokemon PokemonEquip2 : this.Equip2) {
                    // Comprobacio per que cadascu ataqui al pokemon que li toca.
                    if (Equip1.indexOf(PokemonEquip1) != Equip2.indexOf(PokemonEquip2) && this.Equip1.size() == this.Equip2.size())
                    {
                        continue;
                    }
                    if (PokemonEquip1.getPes() < PokemonEquip2.getPes())
                    {
                        Atacant = PokemonEquip1.getTipus();
                        Atacat = PokemonEquip2.getTipus();
                        efecte = comprobarEfectesLluita(Atacant, Atacat);
                        PokemonEquip2.setPunts_de_vida(PokemonEquip2.getPunts_de_vida() - (ATAC_BASE  * efecte));
                        System.out.println(getTexteBatalla(PokemonEquip1, PokemonEquip2, efecte));

                        Atacant  = PokemonEquip2.getTipus();
                        Atacat = PokemonEquip1.getTipus();
                        efecte = comprobarEfectesLluita(Atacant, Atacat);
                        PokemonEquip1.setPunts_de_vida(PokemonEquip1.getPunts_de_vida() - (ATAC_BASE  * efecte));
                        System.out.println(getTexteBatalla(PokemonEquip2, PokemonEquip1, efecte));
                    } else {
                        Atacant  = PokemonEquip2.getTipus();
                        Atacat = PokemonEquip1.getTipus();
                        efecte = comprobarEfectesLluita(Atacant, Atacat);
                        PokemonEquip1.setPunts_de_vida(PokemonEquip1.getPunts_de_vida() - (ATAC_BASE  * efecte));
                        System.out.println(getTexteBatalla(PokemonEquip2, PokemonEquip1, efecte));

                        Atacant = PokemonEquip1.getTipus();
                        Atacat = PokemonEquip2.getTipus();
                        efecte = comprobarEfectesLluita(Atacant, Atacat);
                        PokemonEquip2.setPunts_de_vida(PokemonEquip2.getPunts_de_vida() - (ATAC_BASE  * efecte));
                        System.out.println(getTexteBatalla(PokemonEquip1, PokemonEquip2, efecte));
                    }
                    break;
                }
            }
            count++;
        }
    }

    public float comprobarEfectesLluita(List<Tipus> TipusAtacant, List<Tipus> TipusAtacat) throws SQLException
    {
        float efecte = 1;
        for (Tipus Atacant : TipusAtacant)
        {
            for (Tipus Atacat : TipusAtacat)
            {
                efecte += this.bdd.getEfecte(Atacant.getTipusId(), Atacat.getTipusId());
            }
        }
        return efecte;
    }

    public String getTexteBatalla(Pokemon Atacant, Pokemon Atacat, float efecte)
    {
        return Atacant.getNom() + " Ataca a: " + Atacat.getNom() + " i li treu " + (ATAC_BASE  * efecte) + " punts de vida, ara " + Atacat.getNom() + " té " + Atacat.getPunts_de_vida() + " punts de vida";
    }
}