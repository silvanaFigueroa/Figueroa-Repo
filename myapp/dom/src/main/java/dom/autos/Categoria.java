package dom.autos;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.DescribedAs;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.annotation.Resolve;
import org.apache.isis.applib.util.TitleBuffer;

import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Resolve.Type;
import org.apache.isis.applib.filter.Filter;
import org.apache.isis.core.objectstore.jdo.applib.annotations.Auditable;

import com.google.common.collect.Lists;



import dom.todo.ToDoItem;
import dom.todo.ToDoItems;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@javax.jdo.annotations.Query(name="listado", language="JDQL",value="SELECT * FROM dom.autos.Categoria")
@javax.jdo.annotations.Version(strategy=VersionStrategy.VERSION_NUMBER, column="VERSION")





public class Categoria 
{

//	public static enum aireAcondicionado

	private String categorias;
	
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence="1")
	public String getCategorias()
	{
		return categorias;
	}
	
	public void setCategorias(String categorias)
	{
		this.categorias=categorias;
	}	

    private int cantPuertas ;
	
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence="2")
	public int getCantPuertas()
	{
		return cantPuertas;
	}
	
	public void setCantPuertas(int cantPuertas)
	{
		this.cantPuertas=cantPuertas;
	}	
     
//-----------------------------------------
	private int cantPlazas ;
	
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence="3")
	public int getCantPlazas()
	{
		return cantPlazas;
	}
	
	public void setCantPlazas(int cantPlazas)
	{
		this.cantPlazas=cantPlazas;
	}
 //--------------------------------------------
	
	
	
	
	public String title() 
	{
		final TitleBuffer buf = new TitleBuffer();
        buf.append(getCategorias());
       
            
        
        return buf.toString();
	}
	





    

    private SortedSet<Categoria> dependencies = new TreeSet<Categoria>();

    @Disabled
    @MemberOrder(sequence = "1")
    @Resolve(Type.EAGERLY)
    public SortedSet<Categoria> getDependencies()
    {
        return dependencies;
    }

    public void setDependencies(final SortedSet<Categoria> dependencies) 
    {
        this.dependencies = dependencies;
    }
   
    public Categoria Add(final Categoria toDoItem) 
    {
       getDependencies().add(toDoItem);
        return this;
    }
	


    @SuppressWarnings("unused")
    private DomainObjectContainer container;

    public void setDomainObjectContainer(final DomainObjectContainer container) 
    {
        this.container = container;
    }
    // }}
    
    
    

}
