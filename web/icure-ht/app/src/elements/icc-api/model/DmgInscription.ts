/**
 * 
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0.2
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

import * as models from './models';

export class DmgInscription {
 constructor(json: JSON| any) {
    Object.assign(this as DmgInscription, json)
 }
    complete?: boolean;

    errors?: Array<models.Error>;

    io?: string;

    reference?: string;

    valueHash?: string;

    inss?: string;

    firstName?: string;

    lastName?: string;

    birthday?: number;

    deceased?: number;

    sex?: string;

    regNrWithMut?: string;

    mutuality?: string;

    from?: number;

    to?: number;

    payment1Amount?: number;

    payment1Currency?: string;

    payment1Date?: number;

    payment1Ref?: string;

    payment2Amount?: number;

    payment2Currency?: string;

    payment2Date?: number;

    payment2Ref?: string;

    hcParty?: models.HcParty;

}
