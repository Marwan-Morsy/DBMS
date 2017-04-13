package classes;
import java.util.LinkedList;


abstract public class DBNode {
    private String name ;
    private LinkedList<Object> column;

    public DBNode(final String name) {
        // TODO Auto-generated constructor stub
        this.name=name;
        column = new LinkedList<Object>();
    }

  public void setColumn(final LinkedList<Object> column){
        this.column = column;
    }

  public String getName (){
    return name;
  }

  public LinkedList<Object> getColumn (){
        return column;
      }
}