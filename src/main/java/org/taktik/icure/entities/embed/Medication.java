/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.icure.entities.embed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Medication implements Serializable {
	public static final String REIMBURSED = "REIMBURSED";

	protected String compoundPrescription;
	protected Substanceproduct substanceProduct;
	protected Medicinalproduct medicinalProduct;

	Integer numberOfPackages;

	String batch;

	String instructionForPatient;
	String commentForDelivery;

	Long beginMoment;
	Long endMoment;

	Duration duration;

	Boolean knownUsage;

	List<RegimenItem> regimen;
	String posology; // replace structured posology by text

	Map<String, Content> options;
	Map<String, ParagraphAgreement> agreements;

	String MedicationSchemeIdOnSafe;
	Integer MedicationSchemeSafeVersion;
	Long MedicationSchemeTimeStampOnSafe;
	String safeIdName; //can be: vitalinkuri, RSWID, RSBID
	String idOnSafes; //medicationschemeelement : value of vitalinkuri, RSBID, RSWID
	Long timestampOnSafe; //transaction date+time
	String medicationUse; //free text
	String beginCondition; //free text
	String endCondition; //free text
	String origin; // regularprocess, recorded

	public Map<String, Content> getOptions() {
		return options;
	}

	public void setOptions(Map<String, Content> options) {
		this.options = options;
	}

	public @Nullable String getCompoundPrescription() {
		return compoundPrescription;
	}

	public void setCompoundPrescription(String compoundPrescription) {
		this.compoundPrescription = compoundPrescription;
	}

	public @Nullable Integer getNumberOfPackages() {
		return numberOfPackages;
	}

	public void setNumberOfPackages(Integer numberOfPackages) {
		this.numberOfPackages = numberOfPackages;
	}

	public @Nullable String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public @Nullable Boolean getKnownUsage() {
		return knownUsage;
	}

	public void setKnownUsage(Boolean knownUsage) {
		this.knownUsage = knownUsage;
	}

	public @Nullable String getInstructionForPatient() {
		return instructionForPatient;
	}

	public void setInstructionForPatient(String instructionForPatient) {
		this.instructionForPatient = instructionForPatient;
	}

	public @Nullable String getCommentForDelivery() {
		return commentForDelivery;
	}

	public void setCommentForDelivery(String commentForDelivery) {
		this.commentForDelivery = commentForDelivery;
	}

	public @Nullable Long getBeginMoment() {
		return beginMoment;
	}

	public void setBeginMoment(Long beginMoment) {
		this.beginMoment = beginMoment;
	}

	public @Nullable Long getEndMoment() {
		return endMoment;
	}

	public void setEndMoment(Long endMoment) {
		this.endMoment = endMoment;
	}

	public List<RegimenItem> getRegimen() {
		return regimen;
	}

	public void setRegimen(List<RegimenItem> regimen) {
		this.regimen = regimen;
	}

	public @Nullable String getPosology() { return posology; }

	public void setPosology(String posology) { this.posology = posology; }

	public @Nullable Substanceproduct getSubstanceProduct() {
		return substanceProduct;
	}

	public void setSubstanceProduct(Substanceproduct substanceProduct) {
		this.substanceProduct = substanceProduct;
	}

	public @Nullable Medicinalproduct getMedicinalProduct() {
		return medicinalProduct;
	}

	public void setMedicinalProduct(Medicinalproduct medicinalProduct) {
		this.medicinalProduct = medicinalProduct;
	}

	public @Nullable Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public Map<String, ParagraphAgreement> getAgreements() {
		return agreements;
	}

	public void setAgreements(Map<String, ParagraphAgreement> agreements) {
		this.agreements = agreements;
	}

	public String getMedicationSchemeIdOnSafe() { return MedicationSchemeIdOnSafe; }

	public void setMedicationSchemeIdOnSafe(String medicationSchemeIdOnSafe) { MedicationSchemeIdOnSafe = medicationSchemeIdOnSafe; }

	public Integer getMedicationSchemeSafeVersion() { return MedicationSchemeSafeVersion; }

	public void setMedicationSchemeSafeVersion(Integer medicationSchemeSafeVersion) { MedicationSchemeSafeVersion = medicationSchemeSafeVersion; }

	public Long getMedicationSchemeTimeStampOnSafe() { return MedicationSchemeTimeStampOnSafe; }

	public void setMedicationSchemeTimeStampOnSafe(Long medicationSchemeTimeStampOnSafe) { MedicationSchemeTimeStampOnSafe = medicationSchemeTimeStampOnSafe; }

	public @Nullable String getSafeIdName() { return safeIdName; }

	public void setSafeIdName(@Nullable String safeIdName) { this.safeIdName = safeIdName; }

	public @Nullable String getIdOnSafes() {
		return idOnSafes;
	}

	public void setIdOnSafes(@Nullable String idOnSafes) {
		this.idOnSafes = idOnSafes;
	}

	public @Nullable Long getTimestampOnSafe() {
		return timestampOnSafe;
	}

	public void setTimestampOnSafe(@Nullable Long timestampOnSafe) {
		this.timestampOnSafe = timestampOnSafe;
	}

	public  @Nullable String getMedicationUse() {
		return medicationUse;
	}

	public void setMedicationUse( @Nullable String medicationUse) {
		this.medicationUse = medicationUse;
	}

	public  @Nullable String getBeginCondition() {
		return beginCondition;
	}

	public void setBeginCondition( @Nullable String beginCondition) {
		this.beginCondition = beginCondition;
	}

	public  @Nullable String getEndCondition() {
		return endCondition;
	}

	public void setEndCondition( @Nullable String endCondition) {
		this.endCondition = endCondition;
	}

	public @Nullable String getOrigin() { return origin; }

	public void setOrigin(@Nullable String origin) { this.origin = origin; }

	public String toString() {
		String result = String.format("%s, %s", this.compoundPrescription!=null?this.compoundPrescription:this.substanceProduct!=null?this.substanceProduct:this.medicinalProduct, getPosologyText());
		if (this.numberOfPackages != null && this.numberOfPackages>0) {
			result = String.format("%s packages of %s",this.numberOfPackages,result);
		}
		if (this.duration != null) {
			result = String.format("%s during %s", result, this.duration);
		}
		return result;
	}

	@JsonIgnore
	public @Nullable String getPosologyText() {
		if (regimen == null || regimen.size()==0) {
			return this.posology;
		}

		String unit = regimen.get(0).getAdministratedQuantity() == null ? null: regimen.get(0).getAdministratedQuantity().getAdministrationUnit() != null ? regimen.get(0).getAdministratedQuantity().getAdministrationUnit().getCode() : regimen.get(0).getAdministratedQuantity().getUnit();
		Double quantity = regimen.get(0).getAdministratedQuantity() == null ? null: regimen.get(0).getAdministratedQuantity().getQuantity();

		for (RegimenItem ri : regimen.subList(1,regimen.size())) {
			String oUnit = ri.getAdministratedQuantity() == null ? null: ri.getAdministratedQuantity().getAdministrationUnit() != null ? ri.getAdministratedQuantity().getAdministrationUnit().getCode() : ri.getAdministratedQuantity().getUnit();
			Double oQuantity = ri.getAdministratedQuantity() == null ? null: ri.getAdministratedQuantity().getQuantity();

			if (!StringUtils.equals(unit,oUnit)) {
				unit = "take(s)";
			}
			if ((quantity == null && oQuantity != null) || (quantity != null && oQuantity == null) || (quantity!= null && !quantity.equals(oQuantity))) {
				quantity = -1d;
			}
		}

		return String.format("%s, %d x %s, %s",quantity == null || quantity == -1 ? "x" : quantity.toString(), regimen.size(), "daily", Joiner.on(", ").skipNulls().join(regimen.stream().map(RegimenItem::toString).collect(Collectors.toList())));
	}

	@JsonIgnore
	public @Nullable String getFullPosologyText() {
		String poso = getPosologyText();
		if(this.instructionForPatient != null && !StringUtils.isEmpty(this.instructionForPatient)) {
			poso = poso + ". " + this.instructionForPatient;
		}
		return poso;
	}
}
