/**
 * (C) Copyright IBM Corp. 2017,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watsonhealth.fhir.persistence.test;

import com.ibm.watsonhealth.fhir.persistence.FHIRPersistence;
import com.ibm.watsonhealth.fhir.persistence.exception.FHIRPersistenceException;


/**
 * Mock persistence factory for use during testing.
 */
public class NotAPersistenceFactory {

    public FHIRPersistence getInstance() throws FHIRPersistenceException {
        return new MockPersistenceImpl();
    }
}
