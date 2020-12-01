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

package org.taktik.icure.services.external.rest.v1.dto.gui.type.primitive;



import org.taktik.icure.services.external.rest.v1.dto.gui.type.Data;

import java.io.Serializable;

/**
 * Created by aduchate on 19/11/13, 10:38
 */
public class PrimitiveNumber extends Data implements Primitive {

	String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void initWithString(String value) {
	}

	@Override
	public Serializable getPrimitiveValue() {
		return null;
	}

	@Override
	public void setPrimitiveValue(Serializable value) {
	}

//
//    java.lang.Number value;
//
//
//
//    public PrimitiveNumber() {
//    }
//
//    public PrimitiveNumber(java.lang.Number value) {
//        this.value = value;
//    }
//
//
//    public PrimitiveNumber(String value) {
//    	 initWithString(value);
//    }
//
//    public java.lang.Number getValue() {
//        return value;
//    }
//
//    public void setValue(java.lang.Number value) {
//        this.value = value;
//    }
//
//    public void setValue(String value) {
//        initWithString(value);
//    }
//
//
//    @Override
//    public void initWithString(String value) {
////        try {
////            setValue(decimalFormat.parse(value));
//            setValue(Double.valueOf(value));
////        } catch (ParseException e) {
////            throw new IllegalArgumentException(e);
////        }
//    }
//
//    @Override
//    public Serializable getPrimitiveValue() {
//        return getValue();
//    }
//
//    @Override
//    public void setPrimitiveValue(Serializable value) {
//        setValue((java.lang.Number) value);
//    }
}
