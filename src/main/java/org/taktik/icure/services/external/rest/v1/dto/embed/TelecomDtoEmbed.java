/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.icure.services.external.rest.v1.dto.embed;

import java.io.Serializable;

/**
 * Created by aduchate on 21/01/13, 14:47
 */
public class TelecomDtoEmbed implements Serializable {
	
    protected TelecomType telecomType;
    protected String telecomNumber;

	public TelecomDtoEmbed() {
	}

	public TelecomDtoEmbed(TelecomType telecomType, String telecomNumber) {
		this.telecomType = telecomType;
		this.telecomNumber = telecomNumber;
	}

	public TelecomType getTelecomType() {
        return telecomType;
    }

    public void setTelecomType(TelecomType telecomType) {
        this.telecomType = telecomType;
    }

    public String getTelecomNumber() {
        return telecomNumber;
    }

    public void setTelecomNumber(String telecomNumber) {
        this.telecomNumber = telecomNumber;
    }
}
