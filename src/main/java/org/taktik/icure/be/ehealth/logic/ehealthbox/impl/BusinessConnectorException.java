/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.icure.be.ehealth.logic.ehealthbox.impl;

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 14/03/13
 * Time: 10:25
 * To change this template use File | Settings | File Templates.
 */
public class BusinessConnectorException extends Exception {
    private final String message;
    private final String statusCode;

    public BusinessConnectorException(String message, String statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
