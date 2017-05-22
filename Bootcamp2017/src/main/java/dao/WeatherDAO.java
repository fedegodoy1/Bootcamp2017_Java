
package dao;

/**
 *
 * @author federico
 */
public interface WeatherDAO <t>{
    
    public void insert(t o);
    public Object update(int id, t o);
    public Object select(int id);
    public Object delete(int id);
    
}
