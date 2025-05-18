package BusinessLogic;
import java.util.List;

public interface IBaseBL<T> {
    public boolean Registrar(T input);
    public T Leer(String id);  
    public List<T> Listar();  
    public boolean Actualizar(T input);  
    public boolean Eliminar(String id); 
}
