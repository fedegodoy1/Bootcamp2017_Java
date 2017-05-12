package Main;


/**
 *
 * @author federico
 */
public class Node {
    private Object info;
    private Node next;
   
    public Node ( )
    {
    }

    public Node (Object x, Node p)
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

    public Object getInfo()
    {
     return info;
    }

    public void setInfo(Object p)
    {
     if( p != null ) info = p;
    }

    @Override
    public String toString()
    {
     return info.toString();   
    }
}
