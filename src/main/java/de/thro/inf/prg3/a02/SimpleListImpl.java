package de.thro.inf.prg3.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable {
    private static class Element {
        private Object item;

        private Element next;

        private final Object getItem() {
            return item;
        }

        public void setItem(Object item) {
            this.item = item;
        }

        public final Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }

        public Element() {
            this(null);
        }

        public Element(Object item) {
            setItem(item);
            setNext(null);
        }
    }

    /* Do not make it static to prevent collisions when using multiple instances of SimpleListImpl */
    private class SimpleIteratorImpl implements Iterator {
        private Element currentElement = head;

        @Override
        public boolean hasNext() {
            return currentElement != null;
        }

        @Override
        public Object next() {
            Element result = currentElement;
            currentElement = currentElement.getNext();
            return result.getItem();
        }
    }

    private Element head = null;

    private int size = 0;

    @Override
    public void add(Object o) {
        if (head == null) {
            head = new Element(o);
        } else {
            Element temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new Element(o));
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

        if (head == null) {
            return new SimpleListImpl();
        }

        Element temp = head;
        while (temp.getNext() != null) {
            if (filter.include(temp.getItem())) {
                result.add(temp.getItem());
            }
            temp = temp.getNext();
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return new SimpleIteratorImpl();
    }
}
