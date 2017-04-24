package objects;

import java.util.NoSuchElementException;

/**
 *
 * @author federico
 */
public class Forecast {
    
    private Node frente;
    private int cantidad;
      
      
    public Forecast ()
    {
        frente = null;
        cantidad = 0;
    }
      
    private boolean isHomogeneus (Day x)
    {
        if ( x == null ){
            return false;
        }
        if ( frente != null && x.getClass() != frente.getInfo().getClass() ) return false;
        return true;
    }
      
    public void add( int index, Day x )
    {
        if( isHomogeneus( x ) ) {
        } else {
          throw new ClassCastException( "Objeto incompatible" );
        }

        if( index < 0 || index > size() ) {
            throw new IndexOutOfBoundsException( "Indice fuera del rango" );
        }

        Node nuevo = new Node( x, frente );
        if( index == 0 ){
          frente = nuevo;
        }
        else
        {
        Node p = frente;
        for( int i = 0; i < index - 1; i++ ) 
        { 
            p = p.getNext(); 
        }
        nuevo.setNext( p.getNext() );
        p.setNext( nuevo );
        }         

        cantidad++;
    }
      
    public void addFirst( Day x )
    {
        if ( isHomogeneus( x ) ) {
        } else {
            return;
        }
        Node p = new Node(x, frente);
        frente = p;
        cantidad++;
    }  
      
    public Day search (String dateDay)
    {
        for ( Node p = frente; p != null; p = p.getNext() )
        {
            String a = p.getInfo().getName();
            String b = dateDay;
            if( b.equals(a) ) return p.getInfo();
        }
        return null;
    }
    
    public Day remove( int index )
    {
        if( index < 0 || index >= size() ) {
            throw new IndexOutOfBoundsException( "Indice fuera del rango" );
        }

        Node p = frente, q = null;
        for( int i = 0; i < index; i++ ) 
        {
            q = p;
            p = p.getNext();
        }

        Day x = p.getInfo();
        if( q == null ) {
            frente = p.getNext();
        }
        else {
            q.setNext( p.getNext() );
        }

        cantidad--;
        return x; 
    }
    
    public Day set( int index, Day x )
    {
        if( isHomogeneus( x ) ) {
        } else {
                throw new ClassCastException( "Objeto incompatible" );
        }
        if( index < 0 || index >= size() ) throw new NoSuchElementException( "Indice fuera del rango" );

        Node p = frente;
        for( int i = 0; i < index; i++ ) { 
            p = p.getNext();
        }

        Day ant = p.getInfo();
        p.setInfo( x );
        return ant;
    }

      
    public int size()
    {
        return cantidad;
    }
    
    @Override
    public String toString()
    {
        StringBuilder res = new StringBuilder( "\n[ " );
        for( Node p = frente; p != null;  p = p.getNext() )
        {
            res.append( p.toString() );
            if ( p.getNext() != null ) res.append( " - " );
        }
        res.append( " ]" );
        return res.toString();
    }
}
