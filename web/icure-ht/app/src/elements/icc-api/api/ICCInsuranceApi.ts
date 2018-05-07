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

import {XHR} from "./XHR"
import * as models from '../model/models';

export class iccInsuranceApi {
    host : string
    headers : XHR.Header
    constructor(host: string, headers: any) {
        this.host = host
        this.headers = new XHR.Header('Authorization',headers.Authorization)
    }


    handleError(e: XHR.Data) {
        if (e.status == 401) throw Error('auth-failed')
        else throw Error('api-error'+ e.status)
    }


    createInsurance(body?: models.InsuranceDto) : Promise<models.AccessLogDto|any> {
        let _body = null
        _body = body
        
        const _url = this.host+"/insurance" + "?ts=" + (new Date).getTime() 

        return XHR.sendCommand('POST', _url , [this.headers], _body )
                .then(doc =>  new models.AccessLogDto(doc.body as JSON))
                .catch(err => this.handleError(err))


    }
    deleteInsurance(insuranceId: string) : Promise<Boolean|any> {
        let _body = null
        
        
        const _url = this.host+"/insurance/{insuranceId}".replace("{insuranceId}", insuranceId+"") + "?ts=" + (new Date).getTime() 

        return XHR.sendCommand('DELETE', _url , [this.headers], _body )
                .then(doc => true)
                .catch(err => this.handleError(err))


    }
    getInsurance(insuranceId: string) : Promise<models.InsuranceDto|any> {
        let _body = null
        
        
        const _url = this.host+"/insurance/{insuranceId}".replace("{insuranceId}", insuranceId+"") + "?ts=" + (new Date).getTime() 

        return XHR.sendCommand('GET', _url , [this.headers], _body )
                .then(doc =>  new models.InsuranceDto(doc.body as JSON))
                .catch(err => this.handleError(err))


    }
    getInsurances(body?: models.ListOfIdsDto) : Promise<Array<models.InsuranceDto>|any> {
        let _body = null
        _body = body
        
        const _url = this.host+"/insurance/byIds" + "?ts=" + (new Date).getTime() 

        return XHR.sendCommand('POST', _url , [this.headers], _body )
                .then(doc => (doc.body as Array<JSON>).map(it=>new models.InsuranceDto(it)))
                .catch(err => this.handleError(err))


    }
    listInsurancesByCode(insuranceCode: string) : Promise<Array<models.InsuranceDto>|any> {
        let _body = null
        
        
        const _url = this.host+"/insurance/byCode/{insuranceCode}".replace("{insuranceCode}", insuranceCode+"") + "?ts=" + (new Date).getTime() 

        return XHR.sendCommand('GET', _url , [this.headers], _body )
                .then(doc => (doc.body as Array<JSON>).map(it=>new models.InsuranceDto(it)))
                .catch(err => this.handleError(err))


    }
    listInsurancesByName(insuranceName: string) : Promise<Array<models.InsuranceDto>|any> {
        let _body = null
        
        
        const _url = this.host+"/insurance/byName/{insuranceName}".replace("{insuranceName}", insuranceName+"") + "?ts=" + (new Date).getTime() 

        return XHR.sendCommand('GET', _url , [this.headers], _body )
                .then(doc => (doc.body as Array<JSON>).map(it=>new models.InsuranceDto(it)))
                .catch(err => this.handleError(err))


    }
    modifyInsurance(body?: models.InsuranceDto) : Promise<models.InsuranceDto|any> {
        let _body = null
        _body = body
        
        const _url = this.host+"/insurance" + "?ts=" + (new Date).getTime() 

        return XHR.sendCommand('PUT', _url , [this.headers], _body )
                .then(doc =>  new models.InsuranceDto(doc.body as JSON))
                .catch(err => this.handleError(err))


    }
}

