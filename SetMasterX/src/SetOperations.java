import java.util.*;

public class SetOperations {

    public static Set<Integer> union(Set<Integer> A, Set<Integer> B) {

        Set<Integer> resultado = new HashSet<>(A);

        resultado.addAll(B);

        return resultado;
    }

    public static Set<Integer> interseccion(Set<Integer> A, Set<Integer> B) {

        Set<Integer> resultado = new HashSet<>(A);

        resultado.retainAll(B);

        return resultado;
    }

    public static Set<Integer> diferencia(Set<Integer> A, Set<Integer> B) {

        Set<Integer> resultado = new HashSet<>(A);

        resultado.removeAll(B);

        return resultado;
    }

    public static Set<Integer> diferenciaSimetrica(Set<Integer> A, Set<Integer> B) {

        Set<Integer> union = union(A, B);

        Set<Integer> inter = interseccion(A, B);

        union.removeAll(inter);

        return union;
    }
}