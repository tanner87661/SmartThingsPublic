/**
 *  Generic HTTP Device v1.0.20170108
 *  Source code can be found here: https://github.com/JZ-SmartThings/SmartThings/blob/master/Devices/Generic%20HTTP%20Device/GenericHTTPDevice.groovy
 *  Copyright 2017 JZ
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 */

import groovy.json.JsonSlurper

metadata {
	definition (name: "Light Chain WS2811", author: "HRT", namespace:"HRT") {
        capability "Switch"
		capability "Illuminance Measurement"
		capability "Color Control"
		capability "Refresh"

		attribute "LightPattern", "string"
        attribute "color", "string"
//        attribute "hue", "string"
//        attribute "saturation", "string"
//        attribute "Brightness", "string"
        
		command "SelectUSA"
        command "SelectSwitzerland"
        command "SelectColombia"
        command "SelectRainbow"
        command "SelectHalloween"
        command "SelectXMas"
        command "SelectSteady"
        command "SelectValentinesDay"
        command "SelectFire2012"
        command "SelectAllOff"
		command "RefreshTrigger"
		command "RebootNow"
		command "ResetTiles"
		command "ClearTiles"
        command "SelectRGB"
        command "SplitChain"
        command "InsideOut"
        command "SelectFrameRate"
        command "SelectShiftRate"
        command "SelectSparks"
	}

	preferences {
		input("DeviceIP", "string", title:"Device IP Address", description: "Please enter your device's IP Address", required: true, displayDuringSetup: true)
		input("DevicePort", "string", title:"Device Port", description: "Empty assumes port 80.", required: false, displayDuringSetup: true)
		input("DevicePath", "string", title:"URL Path", description: "Rest of the URL, include forward slash.", displayDuringSetup: true)
		input(name: "DevicePostGet", type: "enum", title: "POST or GET", options: ["POST","GET"], defaultValue: "POST", required: false, displayDuringSetup: true)
		input("UseJSON", "bool", title:"Use JSON instead of HTML?", description: "", defaultValue: false, required: false, displayDuringSetup: true)
	}
	
	simulator {
	}

	tiles(scale: 2) {
		valueTile("displayName", "device.displayName", width: 6, height: 1, icon: "st.Outdoor.outdoor22", canChangeIcon: true, canChangeBackground: true, decoration: "flat") {
			state("default", label: '${currentValue}', backgroundColor:"#DDDDDD")
		}

 		standardTile("SelectUSA", "device.switchUSA", width: 2, height: 2, decoration: "flat") {
			state "off", label:'USA' , action: "SelectUSA", icon: "st.switches.switch.off", backgroundColor:"#ff0000" , nextState: "trying"
			state "on", label: 'USA',  icon: "st.switches.switch.on", backgroundColor: "#00ff00" , nextState: "trying"
			state "trying", label: 'trying', action: "ResetTiles", icon: "st.switches.switch.off", backgroundColor: "#FFAA33"
		}      
        
		standardTile("SelectSwitzerland", "device.switchSwitzerland", width: 2, height: 2, decoration: "flat") {
			state "off", label:'CH' , action: "SelectSwitzerland", icon: "st.switches.switch.off", backgroundColor:"#ff0000" , nextState: "trying"
			state "on", label: 'CH',  icon: "st.switches.switch.on", backgroundColor: "#00ff00" , nextState: "trying"
			state "trying", label: 'trying', action: "ResetTiles", icon: "st.switches.switch.off", backgroundColor: "#FFAA33"
		}
		standardTile("SelectColombia", "device.switchColombia", width: 2, height: 2, decoration: "flat") {
			state "off", label:'Col' , action: "SelectColombia", icon: "st.switches.switch.off", backgroundColor:"#ff0000" , nextState: "trying"
			state "on", label: 'Col',  icon: "st.switches.switch.on", backgroundColor: "#00ff00" , nextState: "trying"
			state "trying", label: 'trying', action: "ResetTiles", icon: "st.switches.switch.off", backgroundColor: "#FFAA33"
		}
		standardTile("SelectRainbow", "device.switchRainbow", width: 2, height: 2, canChangeIcon: true, decoration: "flat") {
			state "off", label:'Rain', action: "SelectRainbow", icon: "st.switches.switch.off", backgroundColor:"#ff0000", nextState: "trying"
			state "on", label: 'Rain', icon: "st.switches.switch.on", backgroundColor: "#00ff00", nextState: "trying"
			state "trying", label: 'trying', action: "ResetTiles", icon: "st.switches.switch.off", backgroundColor: "#FFAA33"
		}
		standardTile("SelectHalloween", "device.switchHalloween", width: 2, height: 2, canChangeIcon: true, decoration: "flat") {
			state "off", label:'Hal', action: "SelectHalloween", icon: "st.switches.switch.off", backgroundColor:"#ff0000", nextState: "trying"
			state "on", label: 'Hal', icon: "st.switches.switch.on", backgroundColor: "#00ff00", nextState: "trying"
			state "trying", label: 'trying', action: "ResetTiles", icon: "st.switches.switch.off", backgroundColor: "#FFAA33"
		}
		standardTile("SelectXMas", "device.switchXMAS", width: 2, height: 2, canChangeIcon: true, decoration: "flat") {
			state "off", label:'XMas', action: "SelectXMas", icon: "st.switches.switch.off", backgroundColor:"#ff0000", nextState: "trying"
			state "on", label: 'XMas', icon: "st.switches.switch.on", backgroundColor: "#00ff00", nextState: "trying"
			state "trying", label: 'trying', action: "ResetTiles", icon: "st.switches.switch.off", backgroundColor: "#FFAA33"
		}
		standardTile("SelectSteady", "device.switchSteady", width: 2, height: 2, canChangeIcon: true, decoration: "flat") {
			state "off", label:'Stdy', action: "SelectSteady", icon: "st.switches.switch.off", backgroundColor:"#ff0000", nextState: "trying"
			state "on", label: 'Stdy', icon: "st.switches.switch.on", backgroundColor: "#00ff00", nextState: "trying"
			state "trying", label: 'trying', action: "ResetTiles", icon: "st.switches.switch.off", backgroundColor: "#FFAA33"
		}
		standardTile("SelectValentinesDay", "device.switchValentinesDay", width: 2, height: 2, canChangeIcon: true, decoration: "flat") {
			state "off", label:'Val', action: "SelectValentinesDay", icon: "st.switches.switch.off", backgroundColor:"#ff0000", nextState: "trying"
			state "on", label: 'Val', icon: "st.switches.switch.on", backgroundColor: "#00ff00", nextState: "trying"
			state "trying", label: 'trying', action: "ResetTiles", icon: "st.switches.switch.off", backgroundColor: "#FFAA33"
		}
		standardTile("SelectFire2012", "device.switchFire2012", width: 2, height: 2, canChangeIcon: true, decoration: "flat") {
			state "off", label:'Fire', action: "SelectFire2012", icon: "st.switches.switch.off", backgroundColor:"#ff0000", nextState: "trying"
			state "on", label: 'Fire', icon: "st.switches.switch.on", backgroundColor: "#00ff00", nextState: "trying"
			state "trying", label: 'trying', action: "ResetTiles", icon: "st.switches.switch.off", backgroundColor: "#FFAA33"
		}
		standardTile("SelectAllOff", "device.switchAllOff", width: 2, height: 2, canChangeIcon: true, decoration: "flat") {
			state "off", label:'OFF', action: "SelectAllOff", icon: "st.switches.switch.off", backgroundColor:"#ff0000", nextState: "trying"
			state "on", label: 'OFF', icon: "st.switches.switch.on", backgroundColor: "#00ff00", nextState: "trying"
			state "trying", label: 'trying', action: "ResetTiles", icon: "st.switches.switch.off", backgroundColor: "#FFAA33"
		}
        
		controlTile("SelectRGB", "device.colorRGB", "color", height: 6, width: 6, inactiveLabel: false) {
    		state "color", action: "SelectRGB", label: "RGB"
		}
        
		controlTile("SelectFrameRate", "device.frameRate", "slider", height: 1, width: 2, inactiveLabel: false, range:"(20..80)") {
		    state "level", action:"SelectFrameRate"
		}
		controlTile("SelectShiftRate", "device.shiftRate", "slider", height: 1, width: 2, inactiveLabel: false, range:"(0..50)") {
		    state "level", action:"SelectShiftRate"
		}
		standardTile("RefreshTrigger", "device.refreshswitch", width: 1, height: 1, decoration: "flat") {
			state "default", label:'REFRESH', action: "refresh.refresh", icon: "st.secondary.refresh-icon", backgroundColor:"#53a7c0", nextState: "refreshing"
			state "refreshing", label: 'refreshing', action: "ResetTiles", icon: "st.secondary.refresh-icon", backgroundColor: "#FF6600", nextState: "default"
		}

		standardTile("SplitChain", "device.splitChain", width: 1, height: 1, decoration: "flat") {
			state "off", label:'Split', action: "SplitChain", icon: "st.switches.switch.off", backgroundColor:"#ff0000", nextState: "trying"
			state "on", label: 'Split', action: "SplitChain", icon: "st.switches.switch.on", backgroundColor: "#00ff00", nextState: "trying"
			state "trying", label: 'trying', action: "ResetTiles", icon: "st.switches.switch.off", backgroundColor: "#FFAA33"
		}
		standardTile("InsideOut", "device.insideOut", width: 1, height: 1, decoration: "flat") {
			state "off", label:'Reverse', action: "InsideOut", icon: "st.switches.switch.off", backgroundColor:"#ff0000", nextState: "trying"
			state "on", label: 'Reverse', action: "InsideOut", icon: "st.switches.switch.on", backgroundColor: "#00ff00", nextState: "trying"
			state "trying", label: 'trying', action: "ResetTiles", icon: "st.switches.switch.off", backgroundColor: "#FFAA33"
		}
		standardTile("SelectSparks", "device.selectSparks", width: 1, height: 1, decoration: "flat") {
			state "off", label:'Glitter', action: "SelectSparks", icon: "st.switches.switch.off", backgroundColor:"#ff0000", nextState: "trying"
			state "on", label: 'Glitter', action: "SelectSparks", icon: "st.switches.switch.on", backgroundColor: "#00ff00", nextState: "trying"
			state "trying", label: 'trying', action: "ResetTiles", icon: "st.switches.switch.off", backgroundColor: "#FFAA33"
		}

		valueTile("upTime", "device.upTime", width: 2, height: 1, decoration: "flat") {
			state("default", label: 'UpTime\r\n ${currentValue}', backgroundColor:"#ffffff")
		}
		standardTile("RebootNow", "device.rebootnow", width: 1, height: 1, decoration: "flat") {
			state "default", label:'REBOOT' , action: "RebootNow", icon: "st.Seasonal Winter.seasonal-winter-014", backgroundColor:"#ff0000", nextState: "rebooting"
			state "rebooting", label: 'REBOOTING', action: "ResetTiles", icon: "st.Office.office13", backgroundColor: "#FF6600", nextState: "default"
		}
		main "displayName"
        details(["displayName", "SelectUSA", "SelectSwitzerland", "SelectColombia", "SelectRainbow", "SelectHalloween", "SelectXMas", "SelectSteady", "SelectValentinesDay", "SelectFire2012", "SelectAllOff", "InsideOut", "SplitChain", "SelectSparks", "SelectFrameRate", "SelectShiftRate", "SelectRGB", "RefreshTrigger", "upTime", "clearTiles", "RebootNow"])
	}
}

def refresh() {
	def FullCommand = 'Refresh='
//	if (DeviceMainPin) {FullCommand=FullCommand+"&MainPin="+DeviceMainPin} // else {FullCommand=FullCommand+"&MainPin=4"}
//	if (DeviceCustomPin) {FullCommand=FullCommand+"&CustomPin="+DeviceCustomPin} // else {FullCommand=FullCommand+"&CustomPin=21"}
//	if (UseJSON==true) { FullCommand=FullCommand+"&UseJSON=" }
	runCmd(FullCommand)
}

def SelectUSA() {
//	log.debug device.currentState("switchUSA").getValue() + " === customswitch state"
	def FullCommand = 'Pattern=0'
	runCmd(FullCommand)
}

def SelectSwitzerland() {
//	log.debug device.currentState("switchUSA").getValue() + " === customswitch state"
	def FullCommand = 'Pattern=1'
	runCmd(FullCommand)
}
def SelectColombia() {
//	log.debug device.currentState("switchColombia").getValue() + " === customswitch state"
	def FullCommand = 'Pattern=2'
	runCmd(FullCommand)
}

def SelectRainbow() {
//	log.debug device.currentState("switchUSA").getValue() + " === customswitch state"
	def FullCommand = 'Pattern=3'
	runCmd(FullCommand)
}

def SelectHalloween() {
//	log.debug device.currentState("switchUSA").getValue() + " === customswitch state"
	def FullCommand = 'Pattern=4'
	runCmd(FullCommand)
}
def SelectXMas() {
//	log.debug device.currentState("switchXMAS").getValue() + " === customswitch state"
	def FullCommand = 'Pattern=5'
	runCmd(FullCommand)
}

def SelectSteady() {
//	log.debug device.currentState("switchUSA").getValue() + " === customswitch state"
	def FullCommand = 'Pattern=6'
	runCmd(FullCommand)
}
def SelectValentinesDay() {
//	log.debug device.currentState("switchValentinesDay").getValue() + " === customswitch state"
	def FullCommand = 'Pattern=7'
	runCmd(FullCommand)
}
def SelectFire2012() {
//	log.debug device.currentState("switchFire2012").getValue() + " === customswitch state"
	def FullCommand = 'Pattern=8'
	runCmd(FullCommand)
}
def SelectAllOff() {
//	log.debug device.currentState("switchAllOff").on() + " === customswitch state"
	def FullCommand = 'Pattern=99'
	runCmd(FullCommand)
}
def SplitChain() {
//	log.debug device.currentState("splitChain").getValue()
    def FullCommand = ''
    if (device.currentState("splitChain")!=null && device.currentState("splitChain").getValue()=="on")
		FullCommand = 'SplitChain=false'
    else
		FullCommand = 'SplitChain=true'
	runCmd(FullCommand)
}

def InsideOut() {
    def FullCommand = ''
    if (device.currentState("insideOut")!=null && device.currentState("insideOut").getValue()=="on")
		FullCommand = 'InsideOut=false'
    else
		FullCommand = 'InsideOut=true'
	runCmd(FullCommand)
}

def SelectSparks() {
    def FullCommand = ''
    if (device.currentState("selectSparks")!=null && device.currentState("selectSparks").getValue()=="on")
		FullCommand = 'SelectSparks=false'
    else
		FullCommand = 'SelectSparks=true'
	runCmd(FullCommand)
}

def SelectFrameRate(java.lang.Integer CurrentValue) {
    def FullCommand = 'FrameRate=' + CurrentValue
//  	log.debug "Command: " + FullCommand
	runCmd(FullCommand)       
}

def SelectShiftRate(java.lang.Integer CurrentValue) {
    def FullCommand = 'ShiftRate=' + CurrentValue
//  	log.debug "Command: " + FullCommand
	runCmd(FullCommand)       
}

def SelectRGB(value) {
//    log.debug "Sending: "
//	log.debug device.currentState("device.colorRGB").getValue() + " === color value"
//	log.debug "setting color: $value"
//    if (value.hex) { sendEvent(name: "color", value: value.hex) }
//    if (value.hue) { sendEvent(name: "hue", value: value.hue) }
//    if (value.saturation) { sendEvent(name: "saturation", value: value.saturation) }
	def FullCommand = ''
    FullCommand= "&RGB=" + value.hex +  "&hue=" + value.hue + "&sat=" + value.saturation
	runCmd(FullCommand)
//    log.debug "Sending: " + FullCommand
}


def setSaturation(percent) {
	log.debug "Executing 'setSaturation'"
	sendEvent(name: "saturation", value: percent)
}

def setHue(percent) {
	log.debug "Executing 'setHue'"
	sendEvent(name: "hue", value: percent)
}


def RebootNow() {
	log.debug "Reboot Triggered!!!"
	runCmd('RebootNow=')
}

def ResetTiles() {
	//RETURN BUTTONS TO CORRECT STATE
	sendEvent(name: "switchUSA", value: "off", isStateChange: true)
	sendEvent(name: "switchSwitzerland", value: "off", isStateChange: true)
	sendEvent(name: "switchColombia", value: "off", isStateChange: true)
	sendEvent(name: "switchRainbow", value: "off", isStateChange: true)
	sendEvent(name: "switchHalloween", value: "off", isStateChange: true)
	sendEvent(name: "switchXMAS", value: "off", isStateChange: true)
	sendEvent(name: "switchSteady", value: "off", isStateChange: true)
	sendEvent(name: "switchValentinesDay", value: "off", isStateChange: true)
	sendEvent(name: "switchFire2012", value: "off", isStateChange: true)
	sendEvent(name: "switchAllOff", value: "off", isStateChange: true)
	sendEvent(name: "insideOut", value: "off", isStateChange: true)
	sendEvent(name: "splitChain", value: "off", isStateChange: true)
	sendEvent(name: "frameRate", value: "0", isStateChange: true)
	sendEvent(name: "shiftRate", value: "0", isStateChange: true)
	sendEvent(name: "refreshswitch", value: "default", isStateChange: true)
	sendEvent(name: "rebootnow", value: "default", isStateChange: true)
	sendEvent(name: "colorRGB", value: "default", isStateChange: true)
	log.debug "Resetting tiles."
}


def runCmd(String varCommand) {
	def host = DeviceIP
	def hosthex = convertIPtoHex(host).toUpperCase()
	def LocalDevicePort = ''
	if (DevicePort==null) { LocalDevicePort = "80" } else { LocalDevicePort = DevicePort }
	def porthex = convertPortToHex(LocalDevicePort).toUpperCase()
	device.deviceNetworkId = "$hosthex:$porthex"
	def userpassascii = "${HTTPUser}:${HTTPPassword}"
	def userpass = "Basic " + userpassascii.encodeAsBase64().toString()

	log.debug "The device id configured is: $device.deviceNetworkId"

	def headers = [:] 
	headers.put("HOST", "$host:$LocalDevicePort")
	headers.put("Content-Type", "application/x-www-form-urlencoded")
	if (HTTPAuth) {
		headers.put("Authorization", userpass)
	}
//	log.debug "The Header is $headers"

	def path = ''
	def body = ''
//	log.debug "Uses which method: $DevicePostGet"
	def method = "POST"
	try {
		if (DevicePostGet.toUpperCase() == "GET") {
			method = "GET"
			path = varCommand
			if (path.substring(0,1) != "/") { path = "/" + path }
//			log.debug "GET path is: $path"
		} else {
			path = DevicePath
			body = varCommand 
//			log.debug "POST body is: $body"
		}
//		log.debug "The method is $method"
	}
	catch (Exception e) {
		settings.DevicePostGet = "POST"
//		log.debug e
//		log.debug "You must not have set the preference for the DevicePOSTGET option"
	}

	try {
		def hubAction = new physicalgraph.device.HubAction(
			method: method,
			path: path,
			body: body,
			headers: headers
			)
		hubAction.options = [outputMsgToS3:false]
//		log.debug hubAction
		hubAction
	}
	catch (Exception e) {
		log.debug "Hit Exception $e on $hubAction"
	}
}

def parse(String description) {
//	log.debug "Parsing '${description}'"
	def whichTile = ''
	def map = [:]
	def retResult = []
	def descMap = parseDescriptionAsMap(description)
	def jsonlist = [:]
	def bodyReturned = ' '
	def headersReturned = ' '
	if (descMap["body"]) { bodyReturned = new String(descMap["body"].decodeBase64()) }
	if (descMap["headers"]) { headersReturned = new String(descMap["headers"].decodeBase64()) }
//	log.debug "BODY---" + bodyReturned
//	log.debug "HEADERS---" + headersReturned

	if (descMap["body"]) {
		if (headersReturned.contains("application/json")) {
			def body = new String(descMap["body"].decodeBase64())
			def slurper = new JsonSlurper()
			jsonlist = slurper.parseText(body)
			//log.debug "JSONLIST---" + jsonlist."CPU"
			jsonlist.put ("Date", new Date().format("yyyy-MM-dd h:mm:ss a", location.timeZone))
		} else 
        {
			jsonlist.put ("Date", new Date().format("yyyy-MM-dd h:mm:ss a", location.timeZone))
			def data=bodyReturned.eachLine { line ->
				if (line.contains('UpTime=')) { jsonlist.put ("UpTime", line.replace("UpTime=","")) }
//				if (line.contains('Refresh=Success')) { jsonlist.put ("Refresh", "Success") }
//				if (line.contains('Refresh=Failed : Authentication Required!')) { jsonlist.put ("Refresh", "Authentication Required!") }
				if (line.contains('RebootNow=Success')) { jsonlist.put ("RebootNow", "Success") }
				if (line.contains('RebootNow=Failed : Authentication Required!')) { jsonlist.put ("RebootNow", "Authentication Required!") }
				//ARDUINO CHECKS
				if (line.contains('LightPattern=0')) { jsonlist.put ("LightPattern", "0") } 
				if (line.contains('LightPattern=1')) { jsonlist.put ("LightPattern", "1") } 
				if (line.contains('LightPattern=2')) { jsonlist.put ("LightPattern", "2") } 
				if (line.contains('LightPattern=3')) { jsonlist.put ("LightPattern", "3") } 
				if (line.contains('LightPattern=4')) { jsonlist.put ("LightPattern", "4") } 
				if (line.contains('LightPattern=5')) { jsonlist.put ("LightPattern", "5") } 
				if (line.contains('LightPattern=6')) { jsonlist.put ("LightPattern", "6") } 
				if (line.contains('LightPattern=7')) { jsonlist.put ("LightPattern", "7") } 
				if (line.contains('LightPattern=8')) { jsonlist.put ("LightPattern", "8") } 
				if (line.contains('LightPattern=99')) { jsonlist.put ("LightPattern", "99") } 
				if (line.contains('InsideOut=true')) { jsonlist.put ("InsideOut", "true") } 
				if (line.contains('InsideOut=false')) { jsonlist.put ("InsideOut", "false") } 
				if (line.contains('SplitChain=true')) { jsonlist.put ("SplitChain", "true") } 
				if (line.contains('SplitChain=false')) { jsonlist.put ("SplitChain", "false") } 
				if (line.contains('SelectSparks=true')) { jsonlist.put ("SelectSparks", "true") } 
				if (line.contains('SelectSparks=false')) { jsonlist.put ("SelectSparks", "false") } 
				if (line.contains('FrameRate='))
                {
                	int strPos = line.indexOf('FrameRate=')
                    jsonlist.put ("FrameRate", line.substring(strPos+10, line.length()))
//                    log.debug "Value: " + line.substring(strPos+10, line.length()) + "Length: " + line.length()
                }
                
				if (line.contains('ShiftRate='))
                {
                	int strPos = line.indexOf('ShiftRate=')
                  	jsonlist.put ("ShiftRate", line.substring(strPos+10, line.length())) 
//                    log.debug "Value: " + line.substring(strPos+10, line.length()) + "Length: " + line.length()
                }
				if (line.contains('Brightness='))
                {
                	int strPos = line.indexOf('Brightness=')
                 	jsonlist.put ("Brightness", line.substring(strPos+11, line.length())) 
//                    log.debug "Value: Brightness " + line.substring(strPos+11, line.length()) + "Length: " + line.length()
                }
				if (line.contains('RGB='))
                {
                	int strPos = line.indexOf('RGB=')
                 	jsonlist.put ("RGB", line.substring(strPos+5, line.length())) 
//                    log.debug "Value: RGB " + line.substring(strPos+5, line.length()) + "Length: " + line.length()
                }
                
				if (line.contains('Refresh=')) { jsonlist.put ("Refresh", "Success") }
                log.debug line
		 	}
        }
	}
	if (descMap["body"]) {
		//putImageInS3(descMap)
		if (jsonlist."Refresh"=="Success") 
        {
			sendEvent(name: "refreshswitch", value: "default", isStateChange: true)
			whichTile = 'Refresh'
        } 
 		if (jsonlist."LightPattern"=="0") 
        {
			sendEvent(name: "switchUSA", value: "on", isStateChange: true)
			whichTile = 'selUSAOn'
//            log.debug "USA ON"
        } else 
        {	sendEvent(name: "switchUSA", value: "off", isStateChange: true)
			whichTile = 'selUSAOff'
//            log.debug "USA OFF"
        }
		
		if (jsonlist."LightPattern"=="1") 
        {
			sendEvent(name: "switchSwitzerland", value: "on", isStateChange: true)
			whichTile = 'selSwitzerlandOn'
        } 
        else 
        {
        	sendEvent(name: "switchSwitzerland", value: "off", isStateChange: true)
			whichTile = 'selSwitzerlandOff'
        }
		if (jsonlist."LightPattern"=="2") 
        {
			sendEvent(name: "switchColombia", value: "on", isStateChange: true)
			whichTile = 'selColombiaOn'
//            log.debug "Colombia ON"
        } 
        else 
        {
        	sendEvent(name: "switchColombia", value: "off", isStateChange: true)
			whichTile = 'selColombiaOff'
//            log.debug "Colombia OFF"
        }
		if (jsonlist."LightPattern"=="3") 
        {
			sendEvent(name: "switchRainbow", value: "on", isStateChange: true) 
			whichTile = 'selRainbowOn'
		}
        else 
        {
        	sendEvent(name: "switchRainbow", value: "off", isStateChange: true)
			whichTile = 'selRainbowOff'
        }
		if (jsonlist."LightPattern"=="4") 
        {
			sendEvent(name: "switchHalloween", value: "on", isStateChange: true) 
			whichTile = 'selHalloweenOn'
		}
        else 
        {
        	sendEvent(name: "switchHalloween", value: "off", isStateChange: true)
			whichTile = 'selHalloweenOff'
        }
		if (jsonlist."LightPattern"=="5") 
        {
			sendEvent(name: "switchXMas", value: "on", isStateChange: true) 
			whichTile = 'selXMasOn'
		}
        else 
        {
        	sendEvent(name: "switchXMas", value: "off", isStateChange: true)
			whichTile = 'selXMasOff'
        }
		if (jsonlist."LightPattern"=="6") 
        {
			sendEvent(name: "switchSteady", value: "on", isStateChange: true)
			whichTile = 'selSteadyOn'
        } 
        else 
        {
            sendEvent(name: "switchSteady", value: "off", isStateChange: true)
			whichTile = 'selSteadyOff'
		}
		if (jsonlist."LightPattern"=="7") 
        {
			sendEvent(name: "switchValentinesDay", value: "on", isStateChange: true)
			whichTile = 'selValentinesDayOn'
        } 
        else 
        {
            sendEvent(name: "switchValentinesDay", value: "off", isStateChange: true)
			whichTile = 'selValentinesDayOff'
		}
		if (jsonlist."LightPattern"=="8") 
        {
			sendEvent(name: "switchFire2012", value: "on", isStateChange: true)
			whichTile = 'selFire2012On'
        } 
        else 
        {
            sendEvent(name: "switchFire2012", value: "off", isStateChange: true)
			whichTile = 'selFire2012Off'
		}
		if (jsonlist."LightPattern"=="99") 
        {
			sendEvent(name: "switchAllOff", value: "on", isStateChange: true)
			whichTile = 'selAllOffOn'
        } 
        else 
        {
            sendEvent(name: "switchAllOff", value: "off", isStateChange: true)
			whichTile = 'selAllOffOff'
		}
		if (jsonlist."UpTime") {
			sendEvent(name: "upTime", value: jsonlist."UpTime".replace("=","\n"), unit: "")
		}
		if (jsonlist."RebootNow") {
			whichTile = 'RebootNow'
		}
        if (jsonlist."InsideOut"=="true")
        {
			sendEvent(name: "insideOut", value: "on", isStateChange: true)
			whichTile = 'selInsideOutOn'
        } 
        if (jsonlist."InsideOut"=="false")
        {
			sendEvent(name: "insideOut", value: "off", isStateChange: true)
			whichTile = 'selInsideOutOff'
        } 
        if (jsonlist."SplitChain"=="true")
        {
			sendEvent(name: "splitChain", value: "on", isStateChange: true)
			whichTile = 'selSplitChainOn'
        } 
        if (jsonlist."SplitChain"=="false")
        {
			sendEvent(name: "splitChain", value: "off", isStateChange: true)
			whichTile = 'selSplitChainOff'
        } 
        if (jsonlist."SelectSparks"=="true")
        {
			sendEvent(name: "selectSparks", value: "on", isStateChange: true)
			whichTile = 'selSelectSparksOn'
        } 
        if (jsonlist."SelectSparks"=="false")
        {
			sendEvent(name: "selectSparks", value: "off", isStateChange: true)
			whichTile = 'selSelectSparksOff'
        } 
		if (jsonlist."FrameRate") {
			sendEvent(name: "frameRate", value: jsonlist."FrameRate".replace("=","\n"), unit: "")
		}
		if (jsonlist."ShiftRate") {
			sendEvent(name: "shiftRate", value: jsonlist."ShiftRate".replace("=","\n"), unit: "")
		}
		if (jsonlist."Brightness") {
 //           sendEvent(name: "colorRGB", value: "saturation.${jsonlist."Brightness".replace("=","\n")}", displayed: true)
//           	log.debug 'updating brightness: ' + jsonlist."Brightness".replace("=","\n")
		}
		if (jsonlist."RGB") {
//       	    if (device.currentState("colorRGB")!=null 
//              device.currentState("insideOut").getValue()=="on")
//            sendEvent(name: "colorRGB", value: "hex.${jsonlist."RGB".replace("=","\n")}", displayed: true)
//           	log.debug 'updating RGB: ' + jsonlist."RGB".replace("=","\n")
		} 
	}

//	log.debug jsonlist

	//RESET THE DEVICE ID TO GENERIC/RANDOM NUMBER. THIS ALLOWS MULTIPLE DEVICES TO USE THE SAME ID/IP
	device.deviceNetworkId = "ID_WILL_BE_CHANGED_AT_RUNTIME_" + (Math.abs(new Random().nextInt()) % 99999 + 1)

	//CHANGE NAME TILE
	sendEvent(name: "displayName", value: DeviceIP, unit: "")

	//RETURN BUTTONS TO CORRECT STATE
//	log.debug 'whichTile: ' + whichTile
    switch (whichTile) {
        case 'Refresh':
			sendEvent(name: "refreshswitch", value: "default", isStateChange: true)
			def result = createEvent(name: "refreshswitch", value: "default", isStateChange: true)
			//log.debug "refreshswitch returned ${result?.descriptionText}"
			return result
        case 'selUSAOn':
			sendEvent(name: "switchUSA", value: "on", isStateChange: true)
			def result = createEvent(name: "switchUSA", value: "on", isStateChange: true)
			return result
        case 'selUSAOff':
			sendEvent(name: "switchUSA", value: "off", isStateChange: true)
			def result = createEvent(name: "switchUSA", value: "off", isStateChange: true)
			return result
         case 'selSwitzerlandOn':
			sendEvent(name: "switchSwitzerland", value: "on", isStateChange: true)
			def result = createEvent(name: "switchSwitzerland", value: "on", isStateChange: true)
			return result
        case 'selSwitzerlandOff':
			sendEvent(name: "switchSwitzerland", value: "off", isStateChange: true)
			def result = createEvent(name: "switchSwitzerland", value: "off", isStateChange: true)
			return result
        case 'selColombiaOn':
			sendEvent(name: "switchColombia", value: "on", isStateChange: true)
			def result = createEvent(name: "switchColombia", value: "on", isStateChange: true)
			return result
        case 'selColombiaOff':
			sendEvent(name: "switchColombia", value: "off", isStateChange: true)
			def result = createEvent(name: "switchColombia", value: "off", isStateChange: true)
			return result
        case 'selRainbowOn':
			sendEvent(name: "switchRainbow", value: "on", isStateChange: true)
			def result = createEvent(name: "switchRainbow", value: "on", isStateChange: true)
			return result
        case 'selRainbowOff':
			sendEvent(name: "switchRainbow", value: "off", isStateChange: true)
			def result = createEvent(name: "switchRainbow", value: "off", isStateChange: true)
			return result
        case 'selHalloweenOn':
			sendEvent(name: "switchHalloween", value: "on", isStateChange: true)
			def result = createEvent(name: "switchHalloween", value: "on", isStateChange: true)
			return result
        case 'selHalloweenOff':
			sendEvent(name: "switchHalloween", value: "off", isStateChange: true)
			def result = createEvent(name: "switchHalloween", value: "off", isStateChange: true)
			return result
        case 'selXMasOn':
			sendEvent(name: "switchXMas", value: "on", isStateChange: true)
			def result = createEvent(name: "switchXMas", value: "on", isStateChange: true)
			return result
        case 'selXMasOff':
			sendEvent(name: "switchXMas", value: "off", isStateChange: true)
			def result = createEvent(name: "switchXMas", value: "off", isStateChange: true)
			return result
        case 'selSteadyOn':
			sendEvent(name: "switchSteady", value: "on", isStateChange: true)
			def result = createEvent(name: "switchSteady", value: "on", isStateChange: true)
			return result
        case 'selSteadyOff':
			sendEvent(name: "switchSteady", value: "off", isStateChange: true)
			def result = createEvent(name: "switchSteady", value: "off", isStateChange: true)
			return result
        case 'selValentinesDayOn':
			sendEvent(name: "switchValentinesDay", value: "on", isStateChange: true)
			def result = createEvent(name: "switchValentinesDay", value: "on", isStateChange: true)
			return result
        case 'selValentinesDayOff':
			sendEvent(name: "switchValentinesDay", value: "off", isStateChange: true)
			def result = createEvent(name: "switchValentinesDay", value: "off", isStateChange: true)
			return result
        case 'selFire2012On':
			sendEvent(name: "switchFire2012", value: "on", isStateChange: true)
			def result = createEvent(name: "switchFire2012", value: "on", isStateChange: true)
			return result
        case 'selFire2012Off':
			sendEvent(name: "switchFire2012", value: "off", isStateChange: true)
			def result = createEvent(name: "switchFire2012", value: "off", isStateChange: true)
			return result
        case 'selAllOffOn':
			sendEvent(name: "switchAllOff", value: "on", isStateChange: true)
			def result = createEvent(name: "switchAllOff", value: "on", isStateChange: true)
			return result
        case 'selAllOffOff':
			sendEvent(name: "switchAllOff", value: "off", isStateChange: true)
			def result = createEvent(name: "switchAllOff", value: "off", isStateChange: true)
			return result
        case 'RebootNow':
			sendEvent(name: "rebootnow", value: "default", isStateChange: true)
			def result = createEvent(name: "rebootnow", value: "default", isStateChange: true)
			return result
        case 'selSplitChainOn':
			sendEvent(name: "splitChain", value: "on", isStateChange: true)
			def result = createEvent(name: "splitChain", value: "on", isStateChange: true)
			return result
        case 'selSplitChainOff':
			sendEvent(name: "splitChain", value: "off", isStateChange: true)
			def result = createEvent(name: "splitChain", value: "off", isStateChange: true)
			return result
        case 'selInsideOutOn':
			sendEvent(name: "insideOut", value: "on", isStateChange: true)
			def result = createEvent(name: "insideOut", value: "on", isStateChange: true)
			return result
        case 'selInsideOutOff':
			sendEvent(name: "insideOut", value: "off", isStateChange: true)
			def result = createEvent(name: "insideOut", value: "off", isStateChange: true)
			return result
        case 'selSelectSparksOn':
			sendEvent(name: "selectSparks", value: "on", isStateChange: true)
			def result = createEvent(name: "selectSparks", value: "on", isStateChange: true)
			return result
        case 'selSelectSparksOff':
			sendEvent(name: "selectSparks", value: "off", isStateChange: true)
			def result = createEvent(name: "selectSparks", value: "off", isStateChange: true)
			return result

        default:
			sendEvent(name: "refreshswitch", value: "default", isStateChange: true)
			def result = createEvent(name: "refreshswitch", value: "default", isStateChange: true)
			//log.debug "refreshswitch returned ${result?.descriptionText}"
			return result
    }
}

def parseDescriptionAsMap(description) {
	description.split(",").inject([:]) { map, param ->
	def nameAndValue = param.split(":")
	map += [(nameAndValue[0].trim()):nameAndValue[1].trim()]
	}
}
private String convertIPtoHex(ipAddress) { 
	String hex = ipAddress.tokenize( '.' ).collect {  String.format( '%02x', it.toInteger() ) }.join()
	//log.debug "IP address entered is $ipAddress and the converted hex code is $hex"
	return hex
}
private String convertPortToHex(port) {
	String hexport = port.toString().format( '%04x', port.toInteger() )
	//log.debug hexport
	return hexport
}
private Integer convertHexToInt(hex) {
	Integer.parseInt(hex,16)
}
private String convertHexToIP(hex) {
	//log.debug("Convert hex to ip: $hex") 
	[convertHexToInt(hex[0..1]),convertHexToInt(hex[2..3]),convertHexToInt(hex[4..5]),convertHexToInt(hex[6..7])].join(".")
}
private getHostAddress() {
	def parts = device.deviceNetworkId.split(":")
	//log.debug device.deviceNetworkId
	def ip = convertHexToIP(parts[0])
	def port = convertHexToInt(parts[1])
	return ip + ":" + port
}