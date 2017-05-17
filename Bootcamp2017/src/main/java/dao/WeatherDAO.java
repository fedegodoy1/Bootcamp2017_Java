
package dao;

/**
 *
 * @author federico
 */
public interface WeatherDAO{
    
    public void insert(Object o);
    public Object update(int id, Object o);
    public Object select(int id);
    public Object delete(int id);
    
}
