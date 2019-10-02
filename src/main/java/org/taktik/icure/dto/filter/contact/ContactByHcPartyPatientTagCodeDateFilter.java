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

package org.taktik.icure.dto.filter.contact;

import java.util.List;

import org.taktik.icure.dto.filter.Filter;
import org.taktik.icure.entities.Contact;

public interface ContactByHcPartyPatientTagCodeDateFilter extends Filter<String,Contact> {
	String getHealthcarePartyId();
	@Deprecated
    String getPatientSecretForeignKey();
    List<String> getPatientSecretForeignKeys();
	String getTagType();
	String getTagCode();
	String getCodeType();
	String getCodeCode();
	Long getStartServiceValueDate();
	Long getEndServiceValueDate();
}
