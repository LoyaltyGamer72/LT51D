package trab2;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Métodos utilitários
 */
public class Utils {

    /**
     * Procura o item com a chave passada em key, no mapa passado em m,se existir corre a função passada em add,
     * se não existir adiciona o valor passado com a chave
     * @param m
     * @param key
     * @param value
     * @param add
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> boolean actualize(Map<K, V> m,
                                    Supplier<K> key, Supplier<V> value,
                                    Function<V, Boolean> add) {
        K k = key.get( );
        V c = m.get( k );
        if (c != null)
            return add.apply( c );
        m.put(k, value.get( ));
        return true;
    }

    /**
     * Aplicar a ação a todos os valores contidos nas coleções associadas às chaves.
     * @param m contentor que associa a cada chave uma coleção de valores.
     * @param action ação a executar sobre cada valor V
     * @param <K> tipo da chave do contentor associativo.
     * @param <V> tipo do valor de cada elemento da coleção associada.
     * @return
     */
    public static < K, V >  void foreachV(Map<K, ? extends Collection<V> > m, Consumer<V> action) {
        m.forEach((k, vs)-> vs.forEach(action));
    }

    /**
     * Num contentor associativo obter a coleção de chaves cujos valores associados
     * são os maiores segundo um determinado comparador.
     * @param m contntor associativo
     * @param cmp comparador
     * @return
     */
    public static <K,V> Collection< K > greater( Map<K,V> m, Comparator<V> cmp  ) {
        //todo
        throw new UnsupportedOperationException("Utils::greater not implements");
    }

}
