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

package org.taktik.icure.services.external.rest.v1.dto.gui;





import org.taktik.icure.dto.gui.FormLifecycle;

import java.io.Serializable;

/**
 * Created by aduchate on 03/12/13, 17:22
 */
public class Formula  implements Serializable{
    String value;
    private FormLifecycle lifecycle;

	public Formula() {
	}

	public FormLifecycle getLifecycle() {
		return lifecycle;
	}

	public void setLifecycle(FormLifecycle lifecycle) {
		this.lifecycle = lifecycle;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
