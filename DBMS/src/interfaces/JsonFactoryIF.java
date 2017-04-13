package interfaces;

import classes.TabelImp;

public interface JsonFactoryIF {
	public TabelImp ReadJson(final String TableName) throws Exception;

	public void writeJson(final TabelImp Table) throws Exception;

}
