package org.openspaces.tdd;

/**
 * Interface to a service that will allow us to retrieve prices for stock symbols.
 * <p/>
 * User: suggitpe
 * Date: 08/09/11
 * Time: 19:18
 */
public interface PriceService {

    int getPriceFor( String aSymbol );
}
