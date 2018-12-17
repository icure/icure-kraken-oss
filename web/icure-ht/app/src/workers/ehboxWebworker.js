import * as fhcApi from 'fhc-api/dist/fhcApi'
import * as iccApi from 'icc-api/dist/icc-api/iccApi'
import * as iccXApi from 'icc-api/dist/icc-x-api/index'
import {UtilsClass} from "icc-api/dist/icc-x-api/crypto/utils"

import moment from 'moment/src/moment'

onmessage = e => {
    if(e.data.action === "loadEhboxMessage"){
        const iccHost           = e.data.iccHost
        const iccHeaders        = JSON.parse(e.data.iccHeaders)

        const fhcHost           = e.data.fhcHost
        const fhcHeaders        = JSON.parse(e.data.fhcHeaders)
        const hcpartyBaseApi    = e.data.hcpartyBaseApi
		const fhcCrypto			= e.data.fhcCrypto

        const tokenId           = e.data.tokenId
        const keystoreId        = e.data.keystoreId
        const user              = e.data.user
        const ehpassword        = e.data.ehpassword
        const boxIds             = e.data.boxId
		const alternateKeystores= e.data.alternateKeystores

        const ehboxApi          = new fhcApi.fhcEhboxcontrollerApi(fhcHost, fhcHeaders)

        const docApi            = new iccApi.iccDocumentApi(iccHost, iccHeaders)
        const msgApi            = new iccApi.iccMessageApi(iccHost, iccHeaders)
        const beResultApi       = new iccApi.iccBeresultimportApi(iccHost, iccHeaders)


        const iccHcpartyApi     = new iccApi.iccHcpartyApi(iccHost, iccHeaders)
        const iccPatientApi     = new iccApi.iccPatientApi(iccHost, iccHeaders)
		const iccContactApi		= new iccApi.iccContactApi(iccHost, iccHeaders)
		const iccFormApi		= new iccApi.iccFormApi(iccHost, iccHeaders)
        const iccCryptoXApi     = new iccXApi.IccCryptoXApi(iccHost, iccHeaders, iccHcpartyApi)
        const iccDocumentXApi   = new iccXApi.IccDocumentXApi(iccHost, iccHeaders, iccHcpartyApi)
		const iccContactXApi	= new iccXApi.IccContactXApi(iccHost, iccHeaders,iccCryptoXApi)
		const iccFormXApi		= new iccXApi.IccFormXApi(iccHost, iccHeaders,iccCryptoXApi)

        const iccUtils          = new UtilsClass()

        //Avoid the hit to the local storage to load the key pair
        iccCryptoXApi.cacheKeyPair(e.data.keyPair, user.healthcarePartyId)

        const docxApi           = new iccXApi.IccDocumentXApi(iccHost, iccHeaders, iccCryptoXApi)
        const iccMessageXApi    = new iccXApi.IccMessageXApi(iccHost, iccHeaders, iccCryptoXApi)

		const textType = (uti, utis) =>{
			return (uti && [uti] || []).concat(utis && utis.value || []).map(u => iccDocumentXApi.mimeType(u)).find(m => m === 'text/plain');
		}

        const removeMsg = (msg) => {
            if (msg) {
                const thisBox = msg.transportGuid.substring(0,msg.transportGuid.indexOf(':'))
                const delBox = thisBox == 'INBOX' ? 'BININBOX' : thisBox =='SENTBOX' ? 'BINSENTBOX' : 'BININBOX'
                const idOfMsg = msg.transportGuid.substring(msg.transportGuid.indexOf(':')+1)
                console.log('remove',idOfMsg,thisBox,delBox)
                if (!msg.transportGuid.startsWith("BIN")) {
                    console.log('remove:move to bin',idOfMsg,thisBox,delBox)
                    ehboxApi.moveMessagesUsingPOST(keystoreId, tokenId, ehpassword, [idOfMsg], thisBox, delBox)
                        .then(()=>{
                            console.log('remove:move to bin done, modify',idOfMsg,thisBox,delBox)
                            msg.transportGuid = delBox + msg.transportGuid.substring(msg.transportGuid.indexOf(':'))
                            msgApi.modifyMessage(msg).then(()=> {
                                console.log('remove:move to bin: modify done',idOfMsg,thisBox,delBox)

                            } )
                        })
                        .catch(err => {
                            console.log('ERROR: remove:move to bin',idOfMsg,thisBox,delBox, err)
                        })
                } else {
                    console.log('remove: delete from bin',idOfMsg,thisBox,delBox)
                    ehboxApi.deleteMessagesUsingPOST(this.api.keystoreId, this.api.tokenId, this.api.credentials.ehpassword, [idOfMsg], delBox)
                }
                const target = this.parentElement.parentNode.children[1]
                target.dispatchEvent(new CustomEvent('selection-messages-change', { detail: { selection: { item: null } } }));
            }
        }

		const assignAttachment = (messageId,docInfo,document) => {
            if (textType(document.mainUti, document.otherUtis)) {
                return iccPatientApi.findByNameBirthSsinAuto(user.healthcarePartyId, docInfo.lastName + " " + docInfo.firstName, null, null, 100, "asc").then(patients => {
                    if (patients && patients.rows && patients.rows.length === 1) {
                        return iccContactXApi.newInstance(user, patients.rows[0], {
                            groupId: messageId,
                            created: new Date().getTime(),
                            modified: new Date().getTime(),
                            author: user.healthcarePartyId,
                            responsible: user.healthcarePartyId,
                            openingDate: moment().format('YYYYMMDDhhmmss') || '',
                            closingDate: moment().format('YYYYMMDDhhmmss') || '',
                            encounterType: {
                                type: docInfo.codes.type,
                                version: docInfo.codes.version,
                                code: docInfo.codes.code
                            },
                            descr: docInfo.labo,
                            subContacts: []
                        }).then(c => {
                             console.log('newInstance',c)
                            return iccContactApi.createContact(c)
                        }).then(c => {
                            console.log('createContact',c)
                            return iccFormXApi.newInstance(user, patients.rows[0], {
                                contactId: c.id,
                                descr: "Lab " + new Date().getTime(),
                            }).then(f => {
                                return iccFormXApi.createForm(f).then(f =>
                                    iccHcpartyApi.getHealthcareParty(user.healthcarePartyId).then(hcp =>
                                        beResultApi.doImport(document.id, user.healthcarePartyId, hcp.languages.find(e => !!e) || "en", docInfo.protocol, f.id, null, c)
                                    )
                                )
                            })
                        }).then(c => {
                            console.log("Contact id " + c.id);
                            return {id: c.id, protocolId: docInfo.protocol}
                        }).catch(err => {
                            console.log("error:" + err)
                        })
                    } else {
                        console.log("pat not found:", docInfo.lastName + " " + docInfo.firstName)
                        return Promise.resolve({})
                    }
                })
            } else {
                console.log("message not text type")
                return Promise.resolve({})
            }
        }

        const treatMessage =  (message,boxId) => ehboxApi.getFullMessageUsingGET(keystoreId, tokenId, ehpassword, boxId, message.id)
            .then(fullMessage => msgApi.findMessagesByTransportGuid(boxId+":"+message.id, null, null, 1).then(existingMess => [fullMessage, existingMess]))
            .then(([fullMessage, existingMess]) => {
                if (existingMess.rows.length > 0) {
                     console.log("Message found",existingMess.rows)

                    const existingMessage = existingMess.rows[0]

                    return (existingMessage.created !== null && existingMessage.created < (Date.now() - (24 * 3600000))) ? fullMessage.id : null
                } else {
                     console.log('fullMessage',fullMessage)
                    let createdDate = moment(fullMessage.publicationDateTime, "YYYYMMDD").valueOf()
                    let receivedDate = new Date().getTime()

                    let newMessage = {
                        created: createdDate,
                        fromAddress: getFromAddress(fullMessage.sender),
                        subject: (fullMessage.document && fullMessage.document.title) || fullMessage.errorCode + " " + fullMessage.title,
                        metas: fullMessage.customMetas,
                        toAddresses: [boxId],
                        transportGuid: boxId + ":" + fullMessage.id,
                        fromHealthcarePartyId: fullMessage.fromHealthcarePartyId ? fullMessage.fromHealthcarePartyId : fullMessage.sender.id,
                        received: receivedDate
                    }
                     console.log('Unknown message : ',newMessage)

                    return iccMessageXApi.newInstance(user, newMessage)
                        .then(messageInstance => msgApi.createMessage(messageInstance))
                        .then(createdMessage => {
                             console.log('createdMessage',createdMessage)
                            return Promise.all((fullMessage.document ? [fullMessage.document] : []).concat(fullMessage.annex || []).map(a => a &&
                                //console.log("mime:" + docxApi.uti(a.mimeType, a.filename && a.filename.replace(/.+\.(.+)/,'$1'))) &&
									docxApi.newInstance(user, createdMessage, {
										documentLocation:   (fullMessage.document && a.content === fullMessage.document.content) ? 'body' : 'annex',
										documentType:       'result', //Todo identify message and set type accordingly
										//mainUti:            docxApi.uti(a.mimeType, a.filename && a.filename.replace(/.+\.(.+)/,'$1')),
                                        mainUti: "public.plainText",
										name:               a.title
									})
										.then(d => docApi.createDocument(d))
										.then(createdDocument => {
										     console.log('createdDocument',createdDocument)
											let byteContent = iccUtils.base64toArrayBuffer(a.content);
											return [createdDocument, byteContent]
										})
										.then(([createdDocument, byteContent]) => docApi.setAttachment(createdDocument.id, null, byteContent)
                                            .then(att => {
                                                if (createdDocument.documentLocation !== "body" && textType(createdDocument.mainUti, createdDocument.otherUtis)) {
                                                    return beResultApi.getInfos(createdDocument.id)
                                                        .then(docInfos => docInfos ? [docInfos, Promise.all(docInfos.map(docInfo => {
                                                            console.log('will assignAttachment', fullMessage.id, docInfo, createdDocument)
                                                            return assignAttachment(fullMessage.id, docInfo, createdDocument)
                                                        }))] : [null, null])
                                                        .then(([docInfos, assignedAttachments]) => {
                                                            console.log('assignedAttachments', assignedAttachments)
                                                            return assignedAttachments && assignedAttachments.then(data => {
                                                                let assignedMap = {}
                                                                data.forEach(datum => {
                                                                    assignedMap[datum.id] = datum.protocolId
                                                                })
                                                                createdMessage.unassignedResults = docInfos.filter(docinfo => (data.map(p => p.protocolId) || []).indexOf(docinfo.protocol) === -1);
                                                                createdMessage.assignedResults = assignedMap
                                                                return msgApi.modifyMessage(createdMessage).then(msg => {
                                                                    if(createdMessage.unassignedResults.length == 0) {
                                                                        return removeMsg(msg)
                                                                    }
                                                                    return Promise.resolve()
                                                                });
                                                            })
                                                        })
                                                } else {
                                                    console.log("annex is body or not text file: " + createdDocument.documentLocation + ": " + [createdDocument.mainUti, createdDocument.otherUtis])
                                                    return att
                                                }
                                            })
										)
								)
                            )
                        })
                        .then(() => null) //DO NOT RETURN A MESSAGE ID TO BE DELETED
                } // else end
            })

        boxIds && boxIds.forEach(boxId =>{
            // console.log('boxids foreach',keystoreId, tokenId, ehpassword, boxId, 100, alternateKeystores)
            ehboxApi.loadMessagesUsingPOST(keystoreId, tokenId, ehpassword, boxId, 100, alternateKeystores)
                .then(messages => {
                    let p = Promise.resolve([])
                    messages.forEach(m => {
                        p = p.then(acc => {
                            return treatMessage(m, boxId).then(id => {
                                return id ? acc.concat([id]) : acc
                            }).catch(e => {console.log("Error loading message "+m.id); return acc})
                        })
                    })
                    return p
                })
                .then(toBeDeletedIds =>
                    (!boxId.startsWith("BIN")) ? Promise.all(toBeDeletedIds.map(id => ehboxApi.moveMessagesUsingPOST(keystoreId, tokenId, ehpassword, [id], boxId, "BIN" + boxId))) : Promise.resolve([])
                )
                .catch(err => console.log("Error while fetching messages: " + err))
        })
    }
};

function isUnread(m) {
    return ((m.status & (1 << 1)) !== 0)
}
function isImportant(m) {
    return ((m.status & (1 << 2)) !== 0)
}
function isCrypted(m) {
    return ((m.status & (1 << 3)) !== 0)
}
function hasAnnex(m) {
    return ((m.status & (1 << 4)) !== 0)
}

function getFromAddress(sender){
    if (!sender) { return "" }
	return (sender.lastName ? sender.lastName : "") +
        (sender.firstName ? ' '+sender.firstName : "") +
        (sender.organizationName ? ' '+sender.organizationName : "") +
        (' ' + sender.identifierType.type + ':' + sender.id)

}
