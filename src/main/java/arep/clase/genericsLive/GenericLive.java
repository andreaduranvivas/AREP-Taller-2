package arep.clase.genericsLive;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class GenericLive {

    public class Main {
        public void main(String[] args) {
            List<Integer> intList = new MyLinkedList<Integer>();
            intList.add(0);
            intList.add(1);

            Integer x = intList.iterator().next();

            printCollection(intList);
            printCollectionGenerics(intList);

            String[] strArray = {"Hola", "Mundo"};
            List<String> strList = new MyLinkedList<String>();

            fromArrayToCollection(strArray, strList);
            System.out.println(strList);
        }

        void printCollection(Collection c) {
            Iterator i = c.iterator();
            for (int k = 0; k < c.size(); k++) {
                System.out.println(i.next());
            }
        }

        void printCollectionGenerics(Collection<?> c)
        {
            for (Object e : c) {
                System.out.println(e);
            }
        }

        <T> void fromArrayToCollection(T[] a, Collection<T> c) {
            for (T o : a) {
                c.add(o); // compile-time error
            }
        }
    }
}
