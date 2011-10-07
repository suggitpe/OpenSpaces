package org.openspaces.domain.ggl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 27/07/11
 * Time: 15:18
 */

public final class GGLProcessingException extends Exception {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( GGLProcessingException.class );

    public GGLProcessingException() {
        super();
    }

    public GGLProcessingException( String aReason ) {
        super( aReason );
    }

    public GGLProcessingException( Throwable aThrowable ) {
        super( aThrowable );
    }

    public GGLProcessingException( String aReason, Throwable aThrowable ) {
        super( aReason, aThrowable );
    }


}
