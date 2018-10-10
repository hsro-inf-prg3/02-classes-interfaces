package de.thro.inf.prg3.a02;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable {
    private static class Element {
        Object item;
        Element next;
    }

    /* Do not make it static to prevent collisions when using multiple instances of SimpleListImpl */
    private class SimpleIteratorImpl implements Iterator {
        private int currentIndex = 0;

        private Element currentElement = head;

        @Override
        public boolean hasNext() {
            return currentIndex < size && currentElement != null;
        }

        @Override
        public Object next() {
            Element result = currentElement;
            ++currentIndex;
            currentElement = currentElement.next;
            return result;
        }
    }

    private Element head = null;

    private int size = 0;

    @Override
    public void add(Object o) {
        if (head == null) {
            head = new Element();
            head.item = o;
            head.next = null;
        } else {
            Element temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = new Element();
            Element newElement = temp.next;
            newElement.next = null;

            newElement.item = o;
        }
        ++size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleListImpl result = new SimpleListImpl();
        for (Object o : result) {
            if (filter.include(o)) {
                result.add(o);
            }
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return new SimpleIteratorImpl();
    }

    // TODO: Implement the required methods.

}
