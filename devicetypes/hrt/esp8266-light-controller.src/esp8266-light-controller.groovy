import groovy.json.JsonSlurper

preferences {
		input("ip", "string", title:"Device IP Address", description: "Please enter your device's IP Address", required: true, displayDuringSetup: true)
		input("port", "string", title:"Device Port", description: "Empty assumes port 80.", defaultValue: 80, required: false, displayDuringSetup: true)
        input("mac", "text", title: "MAC Addr", description: "mac", required: false, displayDuringSetup: true)
		input(name: "DevicePostGet", type: "enum", title: "POST or GET", options: ["POST","GET"], defaultValue: "POST", required: true, displayDuringSetup: true)
		input("UseJSON", "bool", title:"Use JSON instead of HTML?", description: "", defaultValue: true, required: false, displayDuringSetup: true)
}
 
 metadata {
    definition (name: "ESP8266 Light Controller", namespace: "HRT", author: "HRT") {
        capability "Refresh"
        capability "Light"
        capability "Motion Sensor"
        capability "Temperature Measurement"
        capability "Sensor"
		capability "Illuminance Measurement"
		capability "Color Control"
        
		attribute "LightPattern", "Number"
        attribute "Temperature", "Number"
        attribute "Humidity", "Number"
        attribute "Illuminance", "Number"
        attribute "Motion", "Number"
        attribute "color", "String"
        attribute "hue", "String"
        attribute "saturation", "String"
        attribute "Brightness", "String"

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
        command "SplitChain"
        command "InsideOut"
        command "SelectSparks"
        command "SelectShiftRate"

        
    }
 
    // simulator metadata
    simulator {}
 
    // UI tile definitions
    tiles (scale: 2) {
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
			state "on", label:'OFF', action: "off", icon: "st.switches.switch.off", backgroundColor:"#ff0000", nextState: "trying"
			state "off", label: 'LAST', action: "on", icon: "st.switches.switch.on", backgroundColor: "#00ff00", nextState: "trying"
			state "trying", label: 'trying', action: "ResetTiles", icon: "st.switches.switch.off", backgroundColor: "#FFAA33"
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
		standardTile("RefreshTrigger", "device.refreshswitch", width: 1, height: 1, decoration: "flat") {
			state "default", label:'REFRESH', action: "refresh.refresh", icon: "st.secondary.refresh-icon", backgroundColor:"#53a7c0", nextState: "refreshing"
			state "refreshing", label: 'refreshing', action: "ResetTiles", icon: "st.secondary.refresh-icon", backgroundColor: "#FF6600", nextState: "default"
		}
        standardTile("restart", "device.backdoor", inactiveLabel: false, decoration: "flat") {
           state("default", label:"", action:"restart.restart", icon:"st.secondary.restart")
        }
		controlTile("SliderShiftRate", "device.shiftRate", "slider", height: 1, width: 2, inactiveLabel: false, range:"(0..50)") {
		    state "level", action:"SelectShiftRate"
		}
        standardTile("dummy", "device.backdoor", height: 1, width: 2, inactiveLabel: false, decoration: "flat") {
           state("default", label:"")
        }
        
       
        valueTile("temperature", "device.Temperature", width: 1, height: 1) {
			state("temperature", label:'${currentValue}°C', icon:"st.Weather.weather2", unit:"C",
				backgroundColors:[
					[value: 0, color: "#153591"],
					[value: 7, color: "#1e9cbb"],
					[value: 14, color: "#90d2a7"],
					[value: 21, color: "#44b621"],
					[value: 28, color: "#f1d801"],
					[value: 35, color: "#d04e00"],
					[value: 42, color: "#bc2323"]
				] ) }

        valueTile("humidity", "device.Humidity", width: 1, height: 1) {
			state("humidity", label:'${currentValue}%r', icon:"st.Weather.weather12", unit:"%",
				backgroundColors:[
					[value: 0, color: "#153591"],
					[value: 20, color: "#1e9cbb"],
					[value: 40, color: "#90d2a7"],
					[value: 60, color: "#44b621"],
					[value: 80, color: "#f1d801"],
					[value: 100, color: "#d04e00"]
				] ) }

        valueTile("daylight", "device.Illuminance", width: 1, height: 1) {
			state("daylight", label:'${currentValue}', icon:"st.Weather.weather14", unit:"Lux",
				backgroundColors:[
					[value: 0, color: "#153591"],
					[value: 200, color: "#1e9cbb"],
					[value: 400, color: "#90d2a7"],
					[value: 600, color: "#44b621"],
					[value: 800, color: "#f1d801"],
					[value: 1000, color: "#d04e00"]
				] ) }

/*        valueTile("motion", "device.Motion", width: 1, height: 1) {
			state("motion", label:'${currentValue}', icon:"st.Health & Wellness.health12", unit:"",
				backgroundColors:[
					[value: no, color: "#153591"],
					[value: yes, color: "#d04e00"]
				] ) }
*/
        valueTile("motion", "device.Motion", width: 1, height: 1) {
			state("motion", label:'${currentValue}', icon:"st.Health & Wellness.health12", 
				backgroundColors:[
					[value: 0, color: "#153591"],
					[value: 20, color: "#1e9cbb"],
					[value: 40, color: "#90d2a7"],
					[value: 60, color: "#44b621"],
					[value: 80, color: "#f1d801"],
					[value: 100, color: "#d04e00"]
				] ) }

		main "displayName"
    	details(["displayName", "SelectUSA", "SelectSwitzerland", "SelectColombia", "SelectRainbow", "SelectHalloween", "SelectXMas", "SelectSteady", "SelectValentinesDay", "SelectFire2012", "SelectAllOff", "InsideOut", "SplitChain", "SelectSparks", "RefreshTrigger", "SliderShiftRate", "dummy", "temperature", "humidity", "daylight", "motion"]) //, "SelectFrameRate", "SelectRGB", "upTime", "clearTiles", "RebootNow"])
 }
}

def ResetTiles(newPattern) {
	//RETURN BUTTONS TO CORRECT STATE
    if (newPattern == 0)
  		sendEvent(name: "switchUSA", value: "on", isStateChange: true)
    else
  		sendEvent(name: "switchUSA", value: "off", isStateChange: true)
    if (newPattern == 1)
		sendEvent(name: "switchSwitzerland", value: "on", isStateChange: true)
    else
		sendEvent(name: "switchSwitzerland", value: "off", isStateChange: true)
    if (newPattern == 2)
		sendEvent(name: "switchColombia", value: "on", isStateChange: true)
    else
		sendEvent(name: "switchColombia", value: "off", isStateChange: true)
    if (newPattern == 3)
		sendEvent(name: "switchRainbow", value: "on", isStateChange: true)
    else
		sendEvent(name: "switchRainbow", value: "off", isStateChange: true)
    if (newPattern == 4)
		sendEvent(name: "switchHalloween", value: "on", isStateChange: true)
    else
		sendEvent(name: "switchHalloween", value: "off", isStateChange: true)
    if (newPattern == 5)
		sendEvent(name: "switchXMAS", value: "on", isStateChange: true)
    else
		sendEvent(name: "switchXMAS", value: "off", isStateChange: true)
    if (newPattern == 6)
		sendEvent(name: "switchSteady", value: "on", isStateChange: true)
    else
		sendEvent(name: "switchSteady", value: "off", isStateChange: true)
    if (newPattern == 7)
		sendEvent(name: "switchValentinesDay", value: "on", isStateChange: true)
    else
		sendEvent(name: "switchValentinesDay", value: "off", isStateChange: true)
    if (newPattern == 8)
		sendEvent(name: "switchFire2012", value: "on", isStateChange: true)
    else
		sendEvent(name: "switchFire2012", value: "off", isStateChange: true)
    if (newPattern == 99)
		sendEvent(name: "switchAllOff", value: "off", isStateChange: true)
    else
  		sendEvent(name: "switchAllOff", value: "on", isStateChange: true)
            
//	sendEvent(name: "insideOut", value: "off", isStateChange: true)
//	sendEvent(name: "splitChain", value: "off", isStateChange: true)
//	sendEvent(name: "frameRate", value: "0", isStateChange: true)
//	sendEvent(name: "shiftRate", value: "0", isStateChange: true)
//	sendEvent(name: "rebootnow", value: "default", isStateChange: true)
//	sendEvent(name: "colorRGB", value: "default", isStateChange: true)
	log.debug "Resetting tiles."
}

// parse events into attributes
def parse(String description) 
{
//	log.debug(description)
//	def hub = location.hubs[0]
//    log.debug("call parse $description")
//	log.debug "type: ${hub.type}"

//    log.debug "name: ${hub.name}"
//    log.debug "firmwareVersionString: ${hub.firmwareVersionString}"
//    log.debug "localIP: ${hub.localIP}"
//    log.debug "localSrvPortTCP: ${hub.localSrvPortTCP}"
    
    def msg = parseLanMessage(description)

    def result = []
    def bodyString = msg.body
    def name = ""
    def value = ""
	log.debug(bodyString)
    if (bodyString) {
        def json = msg.json;
    	if (json?.IP != NULL)
    	{
			name = "displayName"
            value = json?.IP
//            log.debug("IP received: $value")
            sendEvent(name: name, value: value)
   		}
        if (json?.pattern != NULL)
        {
        	value = json?.pattern as Integer 
//            log.debug("Pattern received: $value")
    		ResetTiles(value)
        }
        if( json?.split != NULL)
        {
			name = "splitChain"
            if ((json?.split as Integer) == 1)
              value = "on"
            else
              value = "off"
//            log.debug("Split Chain received: $value")
			sendEvent(name: name, value: value, isStateChange: true)
        } 
        if( json?.sparks != NULL)
        {
			name = "selectSparks"
            if ((json?.sparks as Integer) == 1)
              value = "on"
            else
              value = "off"
//            log.debug("Sparks received: $value")
			sendEvent(name: name, value: value, isStateChange: true)
        } 
        if( json?.insideout != NULL)
        {
			name = "insideOut"
            if ((json?.insideout as Integer) == 1)
              value = "on"
            else
              value = "off"
//            log.debug("Inside Out received: $value")
			sendEvent(name: name, value: value, isStateChange: true)
        } 
        if( json?.shiftrate != NULL)
        {
			name = "shiftRate"
            value = (json?.shiftrate as Integer)
//            log.debug("Shift Rate received: $value")
			sendEvent(name: name, value: value, isStateChange: true)
        } 
        if (json?.temp != NULL)
        {
			name = "Temperature"
            value = (json?.temp as Float).round(2)
//            log.debug("Temperature received: $value")
			sendEvent(name: name, value: value, isStateChange: true)
        }
        if (json?.hum != NULL)
        {
			name = "Humidity"
            value = (json?.hum as Float ).round(2)
//            log.debug("Humidity received: $value")
			sendEvent(name: name, value: value, isStateChange: true)
        }
        if (json?.daylight != NULL)
        {
			name = "Illuminance"
            value = json?.daylight as Integer 
//            log.debug("Daylight received: $value")
			sendEvent(name: name, value: value, isStateChange: true)
        }
        if (json?.irmd != NULL) 
        {
            name = "Motion"
            value = (100 * (json?.irmd as Float)).round()
//            log.debug("IR Motion detected: $value")
			sendEvent(name: name, value: value)
        }
        else if (json?.radar != NULL) //can't be IRMD and radar at the same time
        {
            name = "Motion"
            value = (100 * (json?.radar as Float)).round()
//            log.debug("Radar Motion detected: $value")
			sendEvent(name: name, value: value)
        }
	}

	result << createEvent(name: "refreshswitch", value: "default", isStateChange: true)
   	return result
}
 
def updated() {
    log.debug("Updated with settings: $settings")
    state.dni = ""
    updateDNI()
    updateSettings()
}
 
private getHostAddress() {
    def ip = settings.ip
    def port = settings.port
 
    log.debug "Using ip: ${ip} and port: ${port} for device: ${device.id}"
    return ip + ":" + port
}
 
private def updateDNI() {
    if (!state.dni || state.dni != device.deviceNetworkId || (state.mac && state.mac != device.deviceNetworkId)) {
        device.setDeviceNetworkId(createNetworkId(settings.ip, settings.port))
        state.dni = device.deviceNetworkId
    }
//    log.debug("Update DNI: $mac")
//    log.debug("Update DNI: $state.mac")
//    log.debug("Update DNI: $state.dni")
}
 
private String createNetworkId(ipaddr, port) {
    if (state.mac) {
        return state.mac
    }
    def hexIp = ipaddr.tokenize('.').collect {
        String.format('%02X', it.toInteger())
    }.join()
    def hexPort = String.format('%04X', port.toInteger())
    return "${hexIp}:${hexPort}"
}
 
def updateSettings() {
    def headers = [:]
    headers.put("HOST", getHostAddress())
    headers.put("Content-Type", "application/json")
    groovy.json.JsonBuilder json = new groovy.json.JsonBuilder ()
    def map = json {
        hubIp device.hub.getDataValue("localIP")
        hubPort device.hub.getDataValue("localSrvPortTCP").toInteger()
        deviceName device.name
    }    
//	log.debug("Update Settings")
//	log.debug(json.toString())
    return new physicalgraph.device.HubAction(
        method: "POST",
        path: "/ajax_inputs",
        body: json.toString(),
        headers: headers
    )
}
 
def refresh() {
    log.debug "Executing 'refresh' ${getHostAddress()}"
    updateDNI()
    return new physicalgraph.device.HubAction(
        method: "GET",
        path: "/ajax_inputs",
        headers: [
                HOST: "${getHostAddress()}"
        ]
    )
}

def selectPattern(patternID) 
{
	sendCommand("Pattern=" + patternID)
}

def sendCommand(thisCommand)
{
    log.debug "Executing  $thisCommand ${getHostAddress()}"
    updateDNI()
    return new physicalgraph.device.HubAction(
        method: "GET",
        path: "/ajax_inputs&" + thisCommand,
        headers: [
                HOST: "${getHostAddress()}"
        ]
    )
}

def SelectUSA()
{
	selectPattern(0)
}
def SelectSwitzerland()
{
	selectPattern(1)
}
def SelectColombia()
{
	selectPattern(2)
}
def SelectRainbow() {
	selectPattern(3)
}
def SelectHalloween() {
	selectPattern(4)
}
def SelectXMas() {
	selectPattern(5)
}
def SelectSteady() {
	selectPattern(6)
}
def SelectValentinesDay() {
	selectPattern(7)
}
def SelectFire2012() {
	selectPattern(8)
}
def off() {
	selectPattern(99)
}
def on() {
	selectPattern(98)
}

def SplitChain() {
//    log.debug(device.currentState("splitChain"))
//    log.debug(device.currentState("splitChain").getValue())
    if (device.currentState("splitChain")!=null && device.currentState("splitChain").getValue()=="on")
		sendCommand("SplitChain=false")
    else
		sendCommand("SplitChain=true")
}

def InsideOut() {
    if (device.currentState("insideOut")!=null && device.currentState("insideOut").getValue()=="on")
		sendCommand("InsideOut=false")
    else
		sendCommand("InsideOut=true")
}

def SelectSparks() {
    if (device.currentState("selectSparks")!=null && device.currentState("selectSparks").getValue()=="on")
		sendCommand("SelectSparks=false")
    else
		sendCommand("SelectSparks=true")
}

def SelectShiftRate(java.lang.Integer CurrentValue) {
//  	log.debug("ShiftRate= $CurrentValue")
    sendCommand("ShiftRate=" + CurrentValue)
}

def restart() {
    log.debug "Executing 'restart' ${getHostAddress()}"
    updateDNI()
    return new physicalgraph.device.HubAction(
        method: "GET",
        path: "/ajax_inputs&RebootNow",
        headers: [
                HOST: "${getHostAddress()}"
        ]
    )
}