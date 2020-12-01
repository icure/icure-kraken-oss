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






import java.io.Serializable;

/**
 * Created by aduchate on 03/12/13, 16:27
 */

public class FormPlanning implements Serializable{
    Boolean planninfForAnyDoctor;
    Boolean planningForDelegate;
    Boolean planningForPatientDoctor;
    Boolean planningForMe;

    Integer codedDelayInDays;
    Integer repetitions;
    Integer repetitionsUnit;

    String descr;

	public FormPlanning() {
	}

	public Boolean getPlanninfForAnyDoctor() {
        return planninfForAnyDoctor;
    }

    public void setPlanninfForAnyDoctor(Boolean planninfForAnyDoctor) {
        this.planninfForAnyDoctor = planninfForAnyDoctor;
    }

    public Boolean getPlanningForDelegate() {
        return planningForDelegate;
    }

    public void setPlanningForDelegate(Boolean planningForDelegate) {
        this.planningForDelegate = planningForDelegate;
    }

    public Boolean getPlanningForPatientDoctor() {
        return planningForPatientDoctor;
    }

    public void setPlanningForPatientDoctor(Boolean planningForPatientDoctor) {
        this.planningForPatientDoctor = planningForPatientDoctor;
    }

    public Boolean getPlanningForMe() {
        return planningForMe;
    }

    public void setPlanningForMe(Boolean planningForMe) {
        this.planningForMe = planningForMe;
    }

    public Integer getCodedDelayInDays() {
        return codedDelayInDays;
    }

    public void setCodedDelayInDays(Integer codedDelayInDays) {
        this.codedDelayInDays = codedDelayInDays;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }

    public Integer getRepetitionsUnit() {
        return repetitionsUnit;
    }

    public void setRepetitionsUnit(Integer repetitionsUnit) {
        this.repetitionsUnit = repetitionsUnit;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
