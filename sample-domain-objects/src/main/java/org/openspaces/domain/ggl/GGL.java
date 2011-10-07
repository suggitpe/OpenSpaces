package org.openspaces.domain.ggl;

/**
 * High level behaviour of the GGL function.
 * <p/>
 * User: suggitpe
 * Date: 27/07/11
 * Time: 15:17
 */
public interface GGL {

    void collectDataAndProcessBatch() throws GGLProcessingException;

    RegulatoryReportingBean extractRegulatoryReportingData() throws GGLProcessingException;

    PnlReportingBean extractPnlReportingData() throws GGLProcessingException;

    TreasuryReportingBean extractTreasuryReportingData() throws GGLProcessingException;

}
