package interfaces;

import java.util.ArrayList;


public interface SqlFactoryIF {

    /**
     * This is the main function which is responsible of doing all the operations
     * 
     *  @param
     *          input : the command from user
     * */
    public ArrayList<ArrayList<String>> execute(String input) throws Exception;

}
