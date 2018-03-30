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

package org.taktik.icure.be.drugs.dto;
// Generated Feb 27, 2008 11:38:04 AM by Hibernate Tools 3.2.0.CR1



/**
 * AtcId generated by hbm2java
 */
public class AtcId  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
     private String mppId;
     private String lang;

    public AtcId() {
    }

    public AtcId(String mppId, String lang) {
       this.mppId = mppId;
       this.lang = lang;
    }
   
    public String getMppId() {
        return this.mppId;
    }
    
    public void setMppId(String mppId) {
        this.mppId = mppId;
    }
    public String getLang() {
        return this.lang;
    }
    
    public void setLang(String lang) {
        this.lang = lang;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AtcId) ) return false;
		 AtcId castOther = ( AtcId ) other; 
         
		 return ( (this.getMppId()==castOther.getMppId()) || ( this.getMppId()!=null && castOther.getMppId()!=null && this.getMppId().equals(castOther.getMppId()) ) )
 && ( (this.getLang()==castOther.getLang()) || ( this.getLang()!=null && castOther.getLang()!=null && this.getLang().equals(castOther.getLang()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMppId() == null ? 0 : this.getMppId().hashCode() );
         result = 37 * result + ( getLang() == null ? 0 : this.getLang().hashCode() );
         return result;
   }   


}


