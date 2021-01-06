package trab2;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Date;

/**
 * Agenda.
 */
public class NoteBook {
    // Contentor associativo, associa o nome ao contacto.
    // A chave é o nome do contacto.
    // Não podem existir dois contactos com o mesmo nome.
    private Map< String, Contact> contacts = new TreeMap<>();
    // Contentor associativo de número de telefones.
    // A chave é o número de telefone o valor associado são os contactos que têm o mesmo número de telefone.
    private Map< String, SortedSet< Contact > > telephones ; //todo - Instanciar
    // Contentor associativo ordenado por datas de nascimento de contactos cujo
    // aniversário é no mesmo dia/mes.
    // A chave data de nascimento o valor associado são os contactos que fazem anos no mesmo número de telefone.
    private SortedMap<java.util.Date, SortedSet<Contact> > birthdays; // todo - Instanciar

    /**
     * Adiciona um contacto ao contentor associativo de contactos contact.
     * - Caso não exista um contacto com o mesmo nome adiciona-o.
     * - Caso já exista um contacto com o mesmo nome e data de nascimento,
     *   adiciona os números de telefone ao contacto já existente.
     * - Caso já exista um contacto com o mesmo nome e data de nascimento
     *   diferente não adiciona.
     * Actualiza o contentor de aniversários com os telefones deste contacto.
     * Actualiza o contentor de telefones com os telefones deste contacto.
     *
     * @param contact contacto a adicionar
     * @return true caso tenha adicionado ou atualizado as estruturas.
     */
    public boolean add( Contact contact ) {
        // todo
        throw new UnsupportedOperationException("NoteBook::add not implements");
    }

    /**
     * Adicionar todos os contactos doutra agenda.
     * Não adiciona caso já exista um contacto com o mesmo nome e data de nascimento, neste caso
     * adiciona os numeros de telefone ao contacto já existente.
     * @param nb agenda
     */
    public void add( NoteBook nb ) {
        // todo
        throw new UnsupportedOperationException("NoteBook::add not implements");
    }

    /**
     * Remove um contacto com determinado nome dos contactos,
     * dos telefones e das data de nascimento.
     * @param name nome do contacto
     */
    public boolean remove( String name ) {
        // todo
        throw new UnsupportedOperationException("NoteBook::remove not implements");
    }

    /**
     * Remove todos os contactos.
     */
    public void clear(  ) {
        contacts.clear();
        telephones.clear();
        birthdays.clear();
    }

    /*********************************************************************
     * Métodos de consulta
     */
    /**
     * Obter o contacto dado o nome.
     * @param name nome do contacto
     * @return o contacto ou null caso não exista.
     */
    public Contact getContact( String name ) {
        // todo
        throw new UnsupportedOperationException("NoteBook::getContact not implements");
    }

    /**
     * Obter os contactos.
     * @return uma coleção de contactos inalterável.
     */
    public Iterable<Contact> getAllContacts() {
        return contacts.values();
    }

    /**
     * Obter os contactos que fazem anos num determinado dia/mes.
     * @param day  dia
     * @param month  mês
     * @return sequencia de contactos ordenada por data.
     */
    public Iterable<Contact> getBirthdays(int day, int month ) {
        return birthdays.get( new Date(day, month, 0) );
    }

    /**
     * Obter os contactos que têm determinado numero de telefone.
     * @param phone número de telefone
     * @return sequencia de contactos.
     */
    public Iterable<Contact> getContactsOf( String phone ) {
        // todo
        throw new UnsupportedOperationException("NoteBook::getContactsOf not implements");
   }

    /**
     * Obter os contactos que fazem anos num determinado mes.
    * @param month  mês
     * @return sequencia de contactos ordenada por data.
     */
    public Iterable<Contact> getBirthdays( int month ) {
        // todo - USAR O MÉTODO Utils.foreachV
        throw new UnsupportedOperationException("NoteBook::getBirthdays not implements");
    }


    /*********************************************************************
     * Leitura e escrita em ficheiro
     */
    /**
     * Lê os contactos do ficheiro de texto para a agenda.
     * @param file ficheiro de leitura.
     * @throws IOException
     */
    public void read( File file ) throws IOException {
        throw new UnsupportedOperationException("NoteBook::read not implements");
    }

    /**
     * Escreve todos os contactos da agenda num ficheiro de texto.
     * @param file ficheiro de escrita.
     * @throws IOException
     */
    public void write( File file ) throws  IOException {
        throw new UnsupportedOperationException("NoteBook::write not implements");
    }

    /*********************************************************************
     * Métodos para obter os contactos com maior numero de telefones, os
     * telefones com maior número de contactos ou as datas que existem mais
     * mais aniversários
     */
    // todo - USAR O MÉTODO Utils.greater

}