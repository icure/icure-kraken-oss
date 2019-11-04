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

package org.taktik.icure.dao;


import org.ektorp.support.View;
import org.taktik.icure.db.PaginatedList;
import org.taktik.icure.db.PaginationOffset;
import org.taktik.icure.entities.AccessLog;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public interface AccessLogDAO extends GenericDAO<AccessLog> {

	PaginatedList<AccessLog> list(PaginationOffset paginationOffset, boolean descending);
	PaginatedList<AccessLog> findByUserAfterDate(String userId, String accessType, Instant startDate, PaginationOffset<List<Object>> pagination, boolean descending);
	List<AccessLog> findByHCPartySecretPatientKeys(String hcPartyId, List<String> secretPatientKeys);
}
