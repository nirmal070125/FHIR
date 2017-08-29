/**
 * (C) Copyright IBM Corp. 2017,2018,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watsonhealth.fhir.server.test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.testng.annotations.Test;

import com.ibm.watsonhealth.fhir.client.FHIRParameters;
import com.ibm.watsonhealth.fhir.client.FHIRRequestHeader;
import com.ibm.watsonhealth.fhir.client.FHIRResponse;
import com.ibm.watsonhealth.fhir.core.MediaType;
import com.ibm.watsonhealth.fhir.model.Bundle;
import com.ibm.watsonhealth.fhir.model.Observation;
import com.ibm.watsonhealth.fhir.model.OperationOutcome;
import com.ibm.watsonhealth.fhir.model.Patient;

public class SearchTest extends FHIRServerTestBase {
    private String patientId;
    private String observationId;
    private Boolean compartmentSearchSupported = null;
    

    /**
     * Retrieve the server's conformance statement to determine the status of certain runtime options.
     * 
     * @throws Exception
     */
    @Test
    public void retrieveConfig() throws Exception {
        compartmentSearchSupported = isComparmentSearchSupported();
        System.out.println("Compartment-based search supported?: " + compartmentSearchSupported.toString());
    }
    
    @Test(groups = { "server-search" })
    public void testCreatePatient() throws Exception {
        WebTarget target = getWebTarget();

        // Build a new Patient and then call the 'create' API.
        Patient patient = readResource(Patient.class, "Patient_JohnDoe.json");
        
        patient.setGender(objFactory.createCode().withValue("male"));
        Entity<Patient> entity = Entity.entity(patient, MediaType.APPLICATION_JSON_FHIR);
        Response response = target.path("Patient").request().post(entity, Response.class);
        assertResponse(response, Response.Status.CREATED.getStatusCode());

        // Get the patient's logical id value.
        patientId = getLocationLogicalId(response);

        // Next, call the 'read' API to retrieve the new patient and verify it.
        response = target.path("Patient/" + patientId).request(MediaType.APPLICATION_JSON_FHIR).get();
        assertResponse(response, Response.Status.OK.getStatusCode());
        Patient responsePatient = response.readEntity(Patient.class);
        assertResourceEquals(patient, responsePatient);
    }

    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreatePatient" })
    public void testSearchPatientWithGivenName() {
        WebTarget target = getWebTarget();
        Response response = target.path("Patient").queryParam("given", "John").request(MediaType.APPLICATION_JSON_FHIR).get();
        assertResponse(response, Response.Status.OK.getStatusCode());
        Bundle bundle = response.readEntity(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreatePatient" })
    public void test_SearchPatientWithGivenName() throws Exception {
        FHIRParameters parameters = new FHIRParameters();
        parameters.searchParam("given", "John");
        FHIRResponse response = client._search("Patient", parameters);
        assertResponse(response.getResponse(), Response.Status.OK.getStatusCode());
        Bundle bundle = response.getResource(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }

    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreatePatient" })
    public void testSearchPatientWithID() {
        WebTarget target = getWebTarget();
        Response response = target.path("Patient").queryParam("_id", patientId).request(MediaType.APPLICATION_JSON_FHIR).get();
        assertResponse(response, Response.Status.OK.getStatusCode());
        Bundle bundle = response.readEntity(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreatePatient" })
    public void test_SearchPatientWithID() throws Exception {
        FHIRParameters parameters = new FHIRParameters();
        parameters.searchParam("_id", patientId);
        FHIRResponse response = client._search("Patient", parameters);
        assertResponse(response.getResponse(), Response.Status.OK.getStatusCode());
        Bundle bundle = response.getResource(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }

    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreatePatient" })
    public void testSearchPatientWithBirthDate() {
        WebTarget target = getWebTarget();
        Response response = target.path("Patient").queryParam("birthdate", "1970-01-01").request(MediaType.APPLICATION_JSON_FHIR).get();
        assertResponse(response, Response.Status.OK.getStatusCode());
        Bundle bundle = response.readEntity(Bundle.class);

        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreatePatient" })
    public void test_SearchPatientWithBirthDate() throws Exception {
        FHIRParameters parameters = new FHIRParameters();
        parameters.searchParam("birthdate", "1970-01-01");
        FHIRResponse response = client._search("Patient", parameters);
        assertResponse(response.getResponse(), Response.Status.OK.getStatusCode());
        Bundle bundle = response.getResource(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }

    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreatePatient" })
    public void testSearchPatientWithLTBirthDate() {
        WebTarget target = getWebTarget();
        Response response = target.path("Patient").queryParam("birthdate", "lt1971-01-01").request(MediaType.APPLICATION_JSON_FHIR).get();
        assertResponse(response, Response.Status.OK.getStatusCode());
        Bundle bundle = response.readEntity(Bundle.class);

        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreatePatient" })
    public void test_SearchPatientWithLTBirthDate() throws Exception {
        FHIRParameters parameters = new FHIRParameters();
        parameters.searchParam("birthdate", "lt1971-01-01");
        FHIRResponse response = client._search("Patient", parameters);
        assertResponse(response.getResponse(), Response.Status.OK.getStatusCode());
        Bundle bundle = response.getResource(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }

    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreatePatient" })
    public void testSearchPatientWithGTBirthDate() {
        WebTarget target = getWebTarget();
        Response response = target.path("Patient").queryParam("birthdate", "gt1950-08-13").request(MediaType.APPLICATION_JSON_FHIR).get();
        assertResponse(response, Response.Status.OK.getStatusCode());
        Bundle bundle = response.readEntity(Bundle.class);

        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreatePatient" })
    public void test_SearchPatientWithGTBirthDate() throws Exception {
        FHIRParameters parameters = new FHIRParameters();
        parameters.searchParam("birthdate", "gt1950-08-13");
        FHIRResponse response = client._search("Patient", parameters);
        assertResponse(response.getResponse(), Response.Status.OK.getStatusCode());
        Bundle bundle = response.getResource(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }

    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreatePatient" })
    public void testSearchPatientWithGender() {
        WebTarget target = getWebTarget();
        Response response = target.path("Patient").queryParam("gender", "male").request(MediaType.APPLICATION_JSON_FHIR).get();
        assertResponse(response, Response.Status.OK.getStatusCode());
        Bundle bundle = response.readEntity(Bundle.class);

        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreatePatient" })
    public void test_SearchPatientWithGender() throws Exception {
        FHIRParameters parameters = new FHIRParameters();
        parameters.searchParam("gender", "male");
        FHIRResponse response = client._search("Patient", parameters);
        assertResponse(response.getResponse(), Response.Status.OK.getStatusCode());
        Bundle bundle = response.getResource(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }

    @Test(groups = { "server-search" })
    public void testCreateObservationWithRange() throws Exception {
        WebTarget target = getWebTarget();

        Observation observation = buildObservation(patientId, "Observation5.json");
        Entity<Observation> entity = Entity.entity(observation, MediaType.APPLICATION_JSON_FHIR);
        Response response = target.path("Observation").request()
                .header("X-FHIR-TENANT-ID", "tenant1")
                .post(entity, Response.class);
        assertResponse(response, Response.Status.CREATED.getStatusCode());

        // Get the patient's logical id value.
        String observationId = getLocationLogicalId(response);

        // Next, call the 'read' API to retrieve the new patient and verify it.
        response = target.path("Observation/" + observationId).request(MediaType.APPLICATION_JSON_FHIR).get();
        assertResponse(response, Response.Status.OK.getStatusCode());
        Observation responseObservation = response.readEntity(Observation.class);

        // use it for serach
        observationId = responseObservation.getId().getValue();
        assertResourceEquals(observation, responseObservation);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreateObservationWithRange" })
    public void testSearchObservationObservationWithRange() {
        WebTarget target = getWebTarget();
        Response response = target.path("Observation").queryParam("value-range", "5.0|http://loinc.org|v15074-8")
                .request(MediaType.APPLICATION_JSON_FHIR)
                .header("X-FHIR-TENANT-ID", "tenant1")
                .get();
        assertResponse(response, Response.Status.OK.getStatusCode());
        Bundle bundle = response.readEntity(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreateObservationWithRange" })
    public void test_SearchObservationObservationWithRange() throws Exception {
        FHIRParameters parameters = new FHIRParameters();
        parameters.searchParam("value-range", "5.0|http://loinc.org|v15074-8");
        FHIRRequestHeader header = new FHIRRequestHeader("X-FHIR-TENANT-ID", "tenant1");
        FHIRResponse response = client._search("Observation", parameters, header);
        assertResponse(response.getResponse(), Response.Status.OK.getStatusCode());
        Bundle bundle = response.getResource(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreatePatient" })
    public void testCreateObservation() throws Exception {
        WebTarget target = getWebTarget();

        Observation observation = buildObservation(patientId, "Observation1.json");
        Entity<Observation> entity = Entity.entity(observation, MediaType.APPLICATION_JSON_FHIR);
        Response response = target.path("Observation").request()
                .header("X-FHIR-TENANT-ID", "tenant1")
                .post(entity, Response.class);
        assertResponse(response, Response.Status.CREATED.getStatusCode());

        // Get the patient's logical id value.
        String observationId = getLocationLogicalId(response);

        // Next, call the 'read' API to retrieve the new patient and verify it.
        response = target.path("Observation/" + observationId).request(MediaType.APPLICATION_JSON_FHIR).get();
        assertResponse(response, Response.Status.OK.getStatusCode());
        Observation responseObservation = response.readEntity(Observation.class);

        // use it for serach
        observationId = responseObservation.getId().getValue();
        assertResourceEquals(observation, responseObservation);
    }
    

    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreateObservation" })
    public void testSearchObservationWithID() {
        WebTarget target = getWebTarget();
        Response response = target.path("Observation").queryParam("_id", observationId)
                .request(MediaType.APPLICATION_JSON_FHIR)
                .header("X-FHIR-TENANT-ID", "tenant1")
                .get();
        assertResponse(response, Response.Status.OK.getStatusCode());
        Bundle bundle = response.readEntity(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreateObservation" })
    public void test_SearchObservationWithID() throws Exception {
        FHIRParameters parameters = new FHIRParameters();
        parameters.searchParam("_id", observationId);
        FHIRRequestHeader header = new FHIRRequestHeader("X-FHIR-TENANT-ID", "tenant1");
        FHIRResponse response = client._search("Observation", parameters, header);
        assertResponse(response.getResponse(), Response.Status.OK.getStatusCode());
        Bundle bundle = response.getResource(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }

    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreateObservation" })
    public void testSearchObservationWithSubject() {
        WebTarget target = getWebTarget();
        Response response = target.path("Observation").queryParam("subject", "Patient/" + patientId)
                .request(MediaType.APPLICATION_JSON_FHIR)
                .header("X-FHIR-TENANT-ID", "tenant1")
                .get();
        assertResponse(response, Response.Status.OK.getStatusCode());
        Bundle bundle = response.readEntity(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreateObservation", "retrieveConfig" })
    public void testSearchObservationWithPatientCompartment() {
        assertNotNull(compartmentSearchSupported);
        if (!compartmentSearchSupported.booleanValue()) {
            return;
        }
        
        WebTarget target = getWebTarget();
        String targetUri = "Patient/" + patientId + "/Observation";
        Response response = target.path(targetUri)
                .request(MediaType.APPLICATION_JSON_FHIR)
                .header("X-FHIR-TENANT-ID", "tenant1")
                .get();
        assertResponse(response, Response.Status.OK.getStatusCode());
        Bundle bundle = response.readEntity(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }

    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreateObservation" })
    public void test_SearchObservationWithSubject() throws Exception {
        FHIRParameters parameters = new FHIRParameters();
        parameters.searchParam("subject", "Patient/" + patientId);
        FHIRRequestHeader header = new FHIRRequestHeader("X-FHIR-TENANT-ID", "tenant1");
        FHIRResponse response = client._search("Observation", parameters, header);
        assertResponse(response.getResponse(), Response.Status.OK.getStatusCode());
        Bundle bundle = response.getResource(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }

    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreateObservation" })
    public void testSearchObservationWithPatient() {
        WebTarget target = getWebTarget();
        Response response = target.path("Observation").queryParam("patient", "Patient/" + patientId)
                .request(MediaType.APPLICATION_JSON_FHIR)
                .header("X-FHIR-TENANT-ID", "tenant1")
                .get();
        assertResponse(response, Response.Status.OK.getStatusCode());
        Bundle bundle = response.readEntity(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreateObservation" })
    public void test_SearchObservationWithPatient() throws Exception {
        FHIRParameters parameters = new FHIRParameters();
        parameters.searchParam("patient", "Patient/" + patientId);
        FHIRRequestHeader header = new FHIRRequestHeader("X-FHIR-TENANT-ID", "tenant1");
        FHIRResponse response = client._search("Observation", parameters, header);
        assertResponse(response.getResponse(), Response.Status.OK.getStatusCode());
        Bundle bundle = response.getResource(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreateObservation" })
    public void testSearchObservationCodeSystem() {
        WebTarget target = getWebTarget();
        Response response = target.path("Observation").queryParam("component-value-quantity", "125.0||mmHg")
                .request(MediaType.APPLICATION_JSON_FHIR)
                .header("X-FHIR-TENANT-ID", "tenant1")
                .get();
        assertResponse(response, Response.Status.OK.getStatusCode());
        Bundle bundle = response.readEntity(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreateObservation" })
    public void test_SearchObservationCodeSystem() throws Exception {
        FHIRParameters parameters = new FHIRParameters();
        parameters.searchParam("component-value-quantity", "125.0||mmHg");
        FHIRRequestHeader header = new FHIRRequestHeader("X-FHIR-TENANT-ID", "tenant1");
        FHIRResponse response = client._search("Observation", parameters, header);
        assertResponse(response.getResponse(), Response.Status.OK.getStatusCode());
        Bundle bundle = response.getResource(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreateObservation" })
    public void testSearchObservationCodeLTSystem() {
        WebTarget target = getWebTarget();
        Response response = target.path("Observation").queryParam("component-value-quantity", "le126.0||mmHg")
                .request(MediaType.APPLICATION_JSON_FHIR)
                .header("X-FHIR-TENANT-ID", "tenant1")
                .get();
        assertResponse(response, Response.Status.OK.getStatusCode());
        Bundle bundle = response.readEntity(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreateObservation" })
    public void test_SearchObservationCodeLTSystem() throws Exception {
        FHIRParameters parameters = new FHIRParameters();
        parameters.searchParam("component-value-quantity", "le126.0||mmHg");
        FHIRRequestHeader header = new FHIRRequestHeader("X-FHIR-TENANT-ID", "tenant1");
        FHIRResponse response = client._search("Observation", parameters, header);
        assertResponse(response.getResponse(), Response.Status.OK.getStatusCode());
        Bundle bundle = response.getResource(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreateObservation" })
    public void testSearchObservationCodeGTSystem() {
        WebTarget target = getWebTarget();
        Response response = target.path("Observation").queryParam("component-value-quantity", "gt123.0||mmHg")
                .request(MediaType.APPLICATION_JSON_FHIR)
                .header("X-FHIR-TENANT-ID", "tenant1")
                .get();
        assertResponse(response, Response.Status.OK.getStatusCode());
        Bundle bundle = response.readEntity(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreateObservation" })
    public void test_SearchObservationCodeGTSystem() throws Exception {
        FHIRParameters parameters = new FHIRParameters();
        parameters.searchParam("component-value-quantity", "gt123.0||mmHg");
        FHIRRequestHeader header = new FHIRRequestHeader("X-FHIR-TENANT-ID", "tenant1");
        FHIRResponse response = client._search("Observation", parameters, header);
        assertResponse(response.getResponse(), Response.Status.OK.getStatusCode());
        Bundle bundle = response.getResource(Bundle.class);
        assertNotNull(bundle);
        assertTrue(bundle.getEntry().size() >= 1);
    }
    
    @Test(groups = { "server-search" }, dependsOnMethods = { "testCreateObservation" })
    public void test_SearchObservationFilteredSearchParameter1() throws Exception {
        // 'category' search parameter is filtered out for tenant1.
        FHIRParameters parameters = new FHIRParameters();
        parameters.searchParam("category", "foo");
        FHIRRequestHeader header = new FHIRRequestHeader("X-FHIR-TENANT-ID", "tenant1");
        FHIRResponse response = client._search("Observation", parameters, header);
        assertResponse(response.getResponse(), Response.Status.BAD_REQUEST.getStatusCode());
        assertExceptionOperationOutcome(response.getResource(OperationOutcome.class), "FHIRSearchException:");
    }
}