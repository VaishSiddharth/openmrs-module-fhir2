/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.fhir2.api.translators.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.emrapi.conditionslist.Condition;
import org.openmrs.module.fhir2.FhirConstants;

public class ConditionClinicalStatusTranslatorImpl_2_0Test {
	
	private static final String ACTIVE = "ACTIVE";
	
	private static final String INACTIVE = "INACTIVE";
	
	private static final String HISTORY_OF = "HISTORY_OF";
	
	private ConditionClinicalStatusTranslatorImpl_2_0 clinicalStatusTranslator;
	
	@Before
	public void setUp() {
		this.clinicalStatusTranslator = new ConditionClinicalStatusTranslatorImpl_2_0();
	}
	
	@Test
	public void shouldConvertOpenMrsActiveToFhirType() {
		CodeableConcept codeableConcept = clinicalStatusTranslator.toFhirResource(Condition.Status.ACTIVE);
		assertThat(codeableConcept, notNullValue());
		assertThat(codeableConcept.getCodingFirstRep().getCode(), equalTo(ACTIVE));
		assertThat(codeableConcept.getCodingFirstRep().getDisplay(), equalTo(ACTIVE));
		assertThat(codeableConcept.getCodingFirstRep().getSystem(), equalTo(FhirConstants.OPENMRS_URI));
	}
	
	@Test
	public void shouldConvertOpenMrsInActiveToFhirType() {
		CodeableConcept codeableConcept = clinicalStatusTranslator.toFhirResource(Condition.Status.INACTIVE);
		assertThat(codeableConcept, notNullValue());
		assertThat(codeableConcept.getCodingFirstRep().getCode(), equalTo(INACTIVE));
		assertThat(codeableConcept.getCodingFirstRep().getDisplay(), equalTo(INACTIVE));
		assertThat(codeableConcept.getCodingFirstRep().getSystem(), equalTo(FhirConstants.OPENMRS_URI));
	}
	
	@Test
	public void shouldConvertOpenMrsHistoryOfToFhirType() {
		CodeableConcept codeableConcept = clinicalStatusTranslator.toFhirResource(Condition.Status.HISTORY_OF);
		assertThat(codeableConcept, notNullValue());
		assertThat(codeableConcept.getCodingFirstRep().getCode(), equalTo(HISTORY_OF));
		assertThat(codeableConcept.getCodingFirstRep().getDisplay(), equalTo(HISTORY_OF));
		assertThat(codeableConcept.getCodingFirstRep().getSystem(), equalTo(FhirConstants.OPENMRS_URI));
	}
	
	@Test
	public void shouldConvertFhirActiveToOpenMrsType() {
		CodeableConcept codeableConcept = new CodeableConcept();
		Coding coding = new Coding();
		coding.setCode(ACTIVE);
		coding.setDisplay(ACTIVE);
		coding.setSystem(FhirConstants.OPENMRS_URI);
		codeableConcept.addCoding(coding);
		assertThat(clinicalStatusTranslator.toOpenmrsType(codeableConcept), is(Condition.Status.ACTIVE));
	}
	
	@Test
	public void shouldConvertFhirInActiveToOpenMrsType() {
		CodeableConcept codeableConcept = new CodeableConcept();
		Coding coding = new Coding();
		coding.setCode(INACTIVE);
		coding.setDisplay(INACTIVE);
		coding.setSystem(FhirConstants.OPENMRS_URI);
		codeableConcept.addCoding(coding);
		assertThat(clinicalStatusTranslator.toOpenmrsType(codeableConcept), is(Condition.Status.INACTIVE));
	}
	
	@Test
	public void shouldConvertFhirHistoryToOpenMrsType() {
		CodeableConcept codeableConcept = new CodeableConcept();
		Coding coding = new Coding();
		coding.setCode(HISTORY_OF);
		coding.setDisplay(HISTORY_OF);
		coding.setSystem(FhirConstants.OPENMRS_URI);
		codeableConcept.addCoding(coding);
		assertThat(clinicalStatusTranslator.toOpenmrsType(codeableConcept), is(Condition.Status.HISTORY_OF));
	}
	
}