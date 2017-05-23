
package dao;

/**
 *
 * @author federico
 */
public interface WeatherDAO <t>{
    
    public void insert(t o);
    public t update(int id, t o);
    public t select(int id);
    public t delete(int id);
    
}
