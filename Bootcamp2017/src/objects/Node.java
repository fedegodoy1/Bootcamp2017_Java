package objects;

/**
 *
 * @author federico
 */
public class Node {
    private Day info;
    private Node next;
   
    public Node ( )
    {
    }

    public Node (Day x, Node p)
    {
     info = x;
     next = p;
    }

    public Node getNext()
    {
     return next;
    }

    public void setNext(Node p)
    {
     next = p;
    }

    public Day getInfo()
    {
     return info;
    }

    public void setInfo(Day p)
    {
     if( p != null ) info = p;
    }

    @Override
    public String toString()
    {
     return info.toString();   
    }
}
