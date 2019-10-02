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

package org.taktik.icure.dto.gui.editor;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.taktik.icure.dto.gui.Editor;

/**
 * Created by aduchate on 19/11/13, 15:28
 */
@XStreamAlias("DateTimeEditor")
public class DateTimeEditor extends Editor {
	boolean displayTime;
	boolean showPicker;
	boolean webAgenda;
	boolean fullDateMode;

	public boolean isDisplayTime() {
		return displayTime;
	}

	public void setDisplayTime(boolean displayTime) {
		this.displayTime = displayTime;
	}

	public boolean isShowPicker() {
		return showPicker;
	}

	public void setShowPicker(boolean showPicker) {
		this.showPicker = showPicker;
	}

	public boolean isWebAgenda() {
		return webAgenda;
	}

	public void setWebAgenda(boolean webAgenda) {
		this.webAgenda = webAgenda;
	}

	public boolean isFullDateMode() { return fullDateMode; }

	public void setFullDateMode(boolean fullDateMode) {
		this.fullDateMode = fullDateMode;
	}

}
