package DataAccessObject;
import java.util.List;

public interface IBaseDAO<T> {
    public boolean Create(T input);
    public T Read(String id);  
    public List<T> ReadAll();  
    public boolean Update(T input);  
    public boolean Delete(String id);  
}