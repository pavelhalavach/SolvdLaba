package main.java.org.solvd.structure;

import java.util.*;

public class CustomLinkedList<E> implements List<E>, Iterable<E> {

    private Element<E> firstElement;
    private Element<E> lastElement;
    private int size;

    public CustomLinkedList() {
        size = 0;
    }

    private static class Element<E>{
        private E value;
        private Element<E> previous;
        private Element<E> next;

        public Element(E value, Element<E> previous, Element<E> next){
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        if (size == 0){
            return "[]";
        }
        String res = "[";
        Element<E> currEl = firstElement;
        for (int i = 0; i < size; i++){
            res = res.concat(currEl.value.toString() + ", ");
            currEl = currEl.next;
        }
        res += "]";
        res = res.replace(", ]", "]");
        return res;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CustomLinkedList<?> that = (CustomLinkedList<?>) object;
        Iterator<E> it = iterator();
        while(it.hasNext()){
            Iterator<?> it2 = that.iterator();
            Object copy = it.next();
            boolean indicator = false;
            while(it2.hasNext()){
                if (copy.equals(it2.next())){
                    indicator = true;
                }
            }
            if (!indicator){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int value = 0;
        Iterator<E> it = iterator();
        while(it.hasNext()){
            value += it.next().hashCode();
        }
        return value;
    }

    @Override
    public boolean contains(Object o) {
        if (size == 0) {
            return false;
        }
        Element<E> currEl;
        for (Element<E> e = firstElement; e != null; e = currEl.next){
            currEl = e;
            if (e.value.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator<?> it = c.iterator();
        while(it.hasNext()){
            if (!this.contains(it.next())){
                return false;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        Element<E> currEl;
        for (Element<E> e = firstElement; e != null; e = currEl.next){
            currEl = e;
            e.previous = null;
            e.value = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int index) {
        if (index >= size){
            throw new IndexOutOfBoundsException();
        }
        Element<E> currEl = firstElement;
        for (int i = 0; i < index; i++){
            currEl = currEl.next;
        }
        return currEl.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomLinkedListIterator();
    }

    private class CustomLinkedListIterator implements Iterator<E> {
        private int currentIndex;
        public CustomLinkedListIterator(){
            this.currentIndex = 0;
        }
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            Element<E> currEl = firstElement;
            for (int i = 0; i < currentIndex; i++){
                currEl = currEl.next;
            }
            currentIndex++;
            return currEl.value;
        }
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public void add(int index, E e) {
        if (size == 0) {
            System.out.println("LinkedList is empty");
        } else if (index > size){
            throw new IndexOutOfBoundsException();
        } else {
            Element<E> currEl = firstElement;
            for (int i = 0; i < index; i++){
                currEl = currEl.next;
            }
            currEl.previous.next = new Element<>(e, currEl.previous, currEl);
            currEl.previous = new Element<>(e, currEl.previous, currEl);
            size++;
        }
    }

    public void addFirst(E e){
        if (size == 0){
            firstElement.value = e;
        }
        else {
            Element<E> newEl = new Element<>(e, null, firstElement);
            firstElement.previous = newEl;
            firstElement = newEl;
        }
        size++;
    }

    public void addLast(E e){
        if (size == 0){
            firstElement = new Element<>(e, null, null);
        }
        else if(size == 1){
            lastElement = new Element<>(e, firstElement, null);
            firstElement.next = lastElement;
        }
        else {
            Element<E> newEl = new Element<>(e, lastElement, null);
            lastElement.next = newEl;
            lastElement = newEl;
        }
        size++;
    }

    @Override
    public boolean addAll(Collection<? extends E> c){
        try{
            Iterator<? extends E> it = c.iterator();
            while(it.hasNext()){
                this.add(it.next());
            }
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public E removeFirst(){
        if (size == 0){
            return null;
        }
        if (size == 1){
            this.clear();
            return null;
        }
        Element<E> currEl = firstElement.next;
        currEl.previous = null;
        firstElement = currEl;
        size--;
        return firstElement.value;
    }

    public E removeLast() {
        if (size == 0){
            return null;
        }
        if (size == 1){
            this.clear();
            return null;
        }
        Element<E> currEl = lastElement.previous;
        currEl.next = null;
        lastElement = currEl;
        size--;
        return lastElement.value;
    }

    @Override
    public boolean remove(Object o) {
        Element<E> currEl;
        for (Element<E> e = firstElement; e != null; e = currEl.next){
            currEl = e;
            if (e.value.equals(o)) {
                if (e == firstElement){
                    this.removeFirst();
                } else if (e == lastElement) {
                    this.removeLast();
                } else {
                    e.previous.next = e.next;
                    e.next.previous = e.previous;
                    size--;
                    e.previous = null;
                    e.value = null;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeAll(Collection<?> c){
        try{
            Iterator<?> it = c.iterator();
            while(it.hasNext()){
                Object copy = it.next();
                while(this.contains(copy)){
                    this.remove(copy);
                }
            }
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }





    @Override
    public E set(int index, E element) {
        return null;
    }



    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }


}
