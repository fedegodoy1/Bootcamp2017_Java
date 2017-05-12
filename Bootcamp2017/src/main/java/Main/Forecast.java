package Main;


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
      
    private boolean isHomogeneus (Object x)
    {
        if ( x == null ){
            return false;
        }
        if ( frente != null && x.getClass() != frente.getInfo().getClass() ) return false;
        return true;
    }
      
    public void add( int index, Object x )
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
      
    public void addFirst( Object x )
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
        Day d;
        for ( Node p = frente; p != null; p = p.getNext() )
        {
            d = (Day) p.getInfo();
            String a = d.getName();
            String b = dateDay;
            if( b.equals(a) ) {
                return d;
            }
        }
        return null;
    }
    
    public Object get( int index )
      {
          if( index < 0 || index >= size() ) {
              throw new IndexOutOfBoundsException( "Indice fuera del rango" );
          }
         
          Node p = frente;
          for( int i = 0; i < index; i++ ) 
          { 
              p = p.getNext(); 
          }
          
          return p.getInfo();
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

        Day x = (Day) p.getInfo();
        if( q == null ) {
            frente = p.getNext();
        }
        else {
            q.setNext( p.getNext() );
        }

        cantidad--;
        return x; 
    }
    
    public Day set( int index, Object x )
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

        Day ant = (Day) p.getInfo();
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
    
    public float currentTempProm(){
        float res = 0;
        Day d;
        for( Node p = frente; p != null;  p = p.getNext() ){
            d = (Day)p.getInfo();
            res += d.getTemp().getCurrentTemperature();
        }
        res = res / size();
        return res;
    }
}
