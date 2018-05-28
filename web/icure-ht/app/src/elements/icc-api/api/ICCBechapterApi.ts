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

export class iccBechapterApi {
    host : string
    headers : Array<XHR.Header>
    constructor(host: string, headers: any) {
        this.host = host
        this.headers = Object.keys(headers).map(k => new XHR.Header(k,headers[k]))
    }


    handleError(e: XHR.Data) {
        if (e.status == 401) throw Error('auth-failed')
        else throw Error('api-error'+ e.status)
    }


    agreementDetailsFromDocument(documentId: string) : Promise<models.AgreementTransaction|any> {
        let _body = null
        
        
        const _url = this.host+"/be_chapter4/paragraphs/{documentId}".replace("{documentId}", documentId+"") + "?ts=" + (new Date).getTime() 

        return XHR.sendCommand('GET', _url , this.headers, _body )
                .then(doc =>  new models.AgreementTransaction(doc.body as JSON))
                .catch(err => this.handleError(err))


    }
    agreementRequestsConsultation(token: string, patientId?: string, startOfAgreement?: number, endOfAgreement?: number, reference?: string, paragraph?: string, version?: string) : Promise<models.AgreementResponse|any> {
        let _body = null
        
        
        const _url = this.host+"/be_chapter4/consult/{token}".replace("{token}", token+"") + "?ts=" + (new Date).getTime()  + (patientId ? "&patientId=" + patientId : "") + (startOfAgreement ? "&startOfAgreement=" + startOfAgreement : "") + (endOfAgreement ? "&endOfAgreement=" + endOfAgreement : "") + (reference ? "&reference=" + reference : "") + (paragraph ? "&paragraph=" + paragraph : "") + (version ? "&version=" + version : "")

        return XHR.sendCommand('GET', _url , this.headers, _body )
                .then(doc =>  new models.AgreementResponse(doc.body as JSON))
                .catch(err => this.handleError(err))


    }
    cancelAgreement(token: string, patientId?: string, decisionReference?: string, ioRequestReference?: string) : Promise<models.AgreementResponse|any> {
        let _body = null
        
        
        const _url = this.host+"/be_chapter4/cancel/{token}".replace("{token}", token+"") + "?ts=" + (new Date).getTime()  + (patientId ? "&patientId=" + patientId : "") + (decisionReference ? "&decisionReference=" + decisionReference : "") + (ioRequestReference ? "&ioRequestReference=" + ioRequestReference : "")

        return XHR.sendCommand('PUT', _url , this.headers, _body )
                .then(doc =>  new models.AgreementResponse(doc.body as JSON))
                .catch(err => this.handleError(err))


    }
    closeAgreement(token: string, patientId?: string, decisionReference?: string) : Promise<models.AgreementResponse|any> {
        let _body = null
        
        
        const _url = this.host+"/be_chapter4/close/{token}".replace("{token}", token+"") + "?ts=" + (new Date).getTime()  + (patientId ? "&patientId=" + patientId : "") + (decisionReference ? "&decisionReference=" + decisionReference : "")

        return XHR.sendCommand('PUT', _url , this.headers, _body )
                .then(doc =>  new models.AgreementResponse(doc.body as JSON))
                .catch(err => this.handleError(err))


    }
    findParagraphs(searchString?: string, language?: string) : Promise<Array<models.ParagraphPreview>|any> {
        let _body = null
        
        
        const _url = this.host+"/be_chapter4" + "?ts=" + (new Date).getTime()  + (searchString ? "&searchString=" + searchString : "") + (language ? "&language=" + language : "")

        return XHR.sendCommand('GET', _url , this.headers, _body )
                .then(doc => (doc.body as Array<JSON>).map(it=>new models.ParagraphPreview(it)))
                .catch(err => this.handleError(err))


    }
    findParagraphsWithCnk(cnk: number, language: string) : Promise<Array<models.ParagraphPreview>|any> {
        let _body = null
        
        
        const _url = this.host+"/be_chapter4/paragraphs/{cnk}/{language}".replace("{cnk}", cnk+"").replace("{language}", language+"") + "?ts=" + (new Date).getTime() 

        return XHR.sendCommand('GET', _url , this.headers, _body )
                .then(doc => (doc.body as Array<JSON>).map(it=>new models.ParagraphPreview(it)))
                .catch(err => this.handleError(err))


    }
    getAddedDocuments(chapterName?: string, paragraphName?: string) : Promise<models.AgreementResponse|any> {
        let _body = null
        
        
        const _url = this.host+"/be_chapter4/documents" + "?ts=" + (new Date).getTime()  + (chapterName ? "&chapterName=" + chapterName : "") + (paragraphName ? "&paragraphName=" + paragraphName : "")

        return XHR.sendCommand('GET', _url , this.headers, _body )
                .then(doc =>  new models.AgreementResponse(doc.body as JSON))
                .catch(err => this.handleError(err))


    }
    getAppendixPrototype(path?: string, mimeType?: string, verseSeq?: number, documentSeq?: number) : Promise<models.Appendix|any> {
        let _body = null
        
        
        const _url = this.host+"/be_chapter4/appendix/prototype" + "?ts=" + (new Date).getTime()  + (path ? "&path=" + path : "") + (mimeType ? "&mimeType=" + mimeType : "") + (verseSeq ? "&verseSeq=" + verseSeq : "") + (documentSeq ? "&documentSeq=" + documentSeq : "")

        return XHR.sendCommand('GET', _url , this.headers, _body )
                .then(doc =>  new models.Appendix(doc.body as JSON))
                .catch(err => this.handleError(err))


    }
    requestAgreementExtension(token: string, body?: models.AgreementRequest) : Promise<models.AgreementResponse|any> {
        let _body = null
        _body = body
        
        const _url = this.host+"/be_chapter4/extension/{token}".replace("{token}", token+"") + "?ts=" + (new Date).getTime() 

        return XHR.sendCommand('POST', _url , this.headers, _body )
                .then(doc =>  new models.AgreementResponse(doc.body as JSON))
                .catch(err => this.handleError(err))


    }
    requestComplementaryAppendix(token: string, patientId?: string, incomplete?: boolean, decisionReference?: string, ioRequestReference?: string, paragraph?: string, version?: string, body?: Array<models.Appendix>) : Promise<models.AgreementResponse|any> {
        let _body = null
        _body = body
        
        const _url = this.host+"/be_chapter4/complementary/{token}".replace("{token}", token+"") + "?ts=" + (new Date).getTime()  + (patientId ? "&patientId=" + patientId : "") + (incomplete ? "&incomplete=" + incomplete : "") + (decisionReference ? "&decisionReference=" + decisionReference : "") + (ioRequestReference ? "&ioRequestReference=" + ioRequestReference : "") + (paragraph ? "&paragraph=" + paragraph : "") + (version ? "&version=" + version : "")

        return XHR.sendCommand('POST', _url , this.headers, _body )
                .then(doc =>  new models.AgreementResponse(doc.body as JSON))
                .catch(err => this.handleError(err))


    }
    requestNewAgreement(token: string, body?: models.AgreementRequest) : Promise<models.AgreementResponse|any> {
        let _body = null
        _body = body
        
        const _url = this.host+"/be_chapter4/new/{token}".replace("{token}", token+"") + "?ts=" + (new Date).getTime() 

        return XHR.sendCommand('POST', _url , this.headers, _body )
                .then(doc =>  new models.AgreementResponse(doc.body as JSON))
                .catch(err => this.handleError(err))


    }
}

