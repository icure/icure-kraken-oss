/**
 * Api Documentation
 * Api Documentation
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import * as models from "./models"

export class Telecom  {
  constructor(json: JSON | any) {
    Object.assign(this as Telecom, json)
  }
  telecomNumber?: string

  telecomType?: Telecom.TelecomTypeEnum
}
export namespace Telecom {
  export enum TelecomTypeEnum {
    Mobile = <any>"mobile",
    Phone = <any>"phone",
    Email = <any>"email",
    Fax = <any>"fax",
    Skype = <any>"skype",
    Im = <any>"im",
    Medibridge = <any>"medibridge",
    Ehealthbox = <any>"ehealthbox",
    Apicrypt = <any>"apicrypt",
    Web = <any>"web",
    Print = <any>"print",
    Disk = <any>"disk"
  }
}
