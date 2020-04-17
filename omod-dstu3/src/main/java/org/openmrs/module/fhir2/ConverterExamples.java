package org.openmrs.module.fhir2;

import org.hl7.fhir.exceptions.FHIRException;

public class ConverterExamples {

	@SuppressWarnings("unused")
	public void c1020() throws FHIRException {
		// Create an input resource to convert
		org.hl7.fhir.r4.model.Observation input = new org.hl7.fhir.r4.model.Observation();
		input.setEncounter(new org.hl7.fhir.r4.model.Reference("Encounter/123"));

// Convert the resource
		org.hl7.fhir.dstu3.model.Observation output = org.hl7.fhir.convertors.conv40_50.Observation.convertObservation(input);
		String context = output.getContext().getReference();


		// Create an input resource to convert
		org.hl7.fhir.dstu2.model.Observation input = new org.hl7.fhir.dstu2.model.Observation();
		input.setEncounter(new org.hl7.fhir.dstu2.model.Reference("Encounter/123"));
		
		// Convert the resource
		org.hl7.fhir.dstu3.model.Observation output = Observation10_30.convertObservation(input);
		String context = output.getContext().getReference();
	//END SNIPPET: 1020
	}
	
	@SuppressWarnings("unused")
	public void c1420() throws FHIRException {
		// Create a resource to convert
		org.hl7.fhir.dstu2016may.model.Questionnaire input = new org.hl7.fhir.dstu2016may.model.Questionnaire();
		input.setTitle("My title");
		
		// Convert the resource
		org.hl7.fhir.dstu3.model.Questionnaire output = Questionnaire14_30.convertQuestionnaire(input);
		String context = output.getTitle();
	}

}
