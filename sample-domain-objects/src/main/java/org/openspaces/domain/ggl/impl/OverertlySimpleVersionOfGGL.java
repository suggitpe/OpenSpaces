package org.openspaces.domain.ggl.impl;

import org.openspaces.domain.ggl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Really simple version of the GGL.
 * <p/>
 * User: suggitpe
 * Date: 27/07/11
 * Time: 16:26
 */

public final class OverertlySimpleVersionOfGGL implements GGL {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( OverertlySimpleVersionOfGGL.class );

    @Override
    public void collectDataAndProcessBatch() throws GGLProcessingException {
        LOG.debug("Doing stuff" );
    }

    @Override
    public RegulatoryReportingBean extractRegulatoryReportingData() throws GGLProcessingException {
        return new RegulatoryReportingBean();
    }

    @Override
    public PnlReportingBean extractPnlReportingData() throws GGLProcessingException {
        return new PnlReportingBean();
    }

    @Override
    public TreasuryReportingBean extractTreasuryReportingData() throws GGLProcessingException {
        return new TreasuryReportingBean();
    }
}
