
open class SmartDevice(val name: String, val category: String) {
    var deviceStatus = "off"
    protected open val deviceType = "unknown"
    
    open fun turnOn() {
        deviceStatus = "on"
        println("$name is turned on.")
    }
    
    open fun turnOff() {
        deviceStatus = "off"
        println("$name is turned off.")
    }
    
   
    fun printDeviceInfo() {
        println("Device name: $name, category: $category, type: $deviceType")
    }
}


class SmartTvDevice(name: String, category: String) : SmartDevice(name, category) {
    override val deviceType = "Smart TV"
    private var volume = 20
    private var channel = 1
    
    override fun turnOn() {
        super.turnOn()
        println("Smart TV is ready to watch.")
    }
    
    override fun turnOff() {
        super.turnOff()
        println("Smart TV is now off.")
    }
    
    fun increaseVolume() {
        if (deviceStatus == "on") {
            volume++
            println("Volume increased to $volume")
        } else {
            println("Cannot increase volume. TV is off.")
        }
    }
    
    
    fun decreaseVolume() {
        if (deviceStatus == "on") {
            if (volume > 0) {
                volume--
                println("Volume decreased to $volume")
            } else {
                println("Volume is already at minimum (0)")
            }
        } else {
            println("Cannot decrease volume. TV is off.")
        }
    }
    
    fun nextChannel() {
        if (deviceStatus == "on") {
            channel++
            println("Channel changed to $channel")
        } else {
            println("Cannot change channel. TV is off.")
        }
    }
    
    
    fun previousChannel() {
        if (deviceStatus == "on") {
            if (channel > 1) {
                channel--
                println("Channel changed to previous channel: $channel")
            } else {
                println("Already on the first channel (1)")
            }
        } else {
            println("Cannot change channel. TV is off.")
        }
    }
}


class SmartLightDevice(name: String, category: String) : SmartDevice(name, category) {
    override val deviceType = "Smart Light"
    private var brightness = 50
    
    override fun turnOn() {
        super.turnOn()
        println("Smart Light is now on.")
    }
    
    override fun turnOff() {
        super.turnOff()
        println("Smart Light is now off.")
    }
    
    fun increaseBrightness() {
        if (deviceStatus == "on") {
            if (brightness < 100) {
                brightness += 10
                println("Brightness increased to $brightness%")
            } else {
                println("Brightness is already at maximum (100%)")
            }
        } else {
            println("Cannot increase brightness. Light is off.")
        }
    }
   
    fun decreaseBrightness() {
        if (deviceStatus == "on") {
            if (brightness > 0) {
                brightness -= 10
                println("Brightness decreased to $brightness%")
            } else {
                println("Brightness is already at minimum (0%)")
            }
        } else {
            println("Cannot decrease brightness. Light is off.")
        }
    }
}


class SmartHome {
    private val smartTv = SmartTvDevice("Living Room TV", "Entertainment")
    private val smartLight = SmartLightDevice("Kitchen Light", "Lighting")
    private var deviceTurnOnCount = 0
    
    fun turnOnTv() {
        if (smartTv.deviceStatus != "on") {
            smartTv.turnOn()
            deviceTurnOnCount++
        } else {
            println("TV is already on.")
        }
    }
    
    fun turnOffTv() {
        smartTv.turnOff()
    }
    
    fun turnOnLight() {
        if (smartLight.deviceStatus != "on") {
            smartLight.turnOn()
            deviceTurnOnCount++
        } else {
            println("Light is already on.")
        }
    }
    
    fun turnOffLight() {
        smartLight.turnOff()
    }
    
    fun getDeviceTurnOnCount(): Int {
        return deviceTurnOnCount
    }
    
    
    fun decreaseTvVolume() {
        smartTv.decreaseVolume()
    }
    
    fun changeTvChannelToPrevious() {
        smartTv.previousChannel()
    }
    
    fun printSmartTvInfo() {
        smartTv.printDeviceInfo()
        println("Current status: ${smartTv.deviceStatus}")
    }
    
    fun printSmartLightInfo() {
        smartLight.printDeviceInfo()
        println("Current status: ${smartLight.deviceStatus}")
    }
    
    fun decreaseLightBrightness() {
        smartLight.decreaseBrightness()
    }
}

fun main() {
    val smartHome = SmartHome()
    
    println("SMART HOME SYSTEM \n")
    
    
    println(" Initial Device Info:")
    smartHome.printSmartTvInfo()
    smartHome.printSmartLightInfo()
    println()
    
    
    println(" Turning on devices:")
    smartHome.turnOnTv()    
    smartHome.turnOnLight()  
    println("Devices turned on: ${smartHome.getDeviceTurnOnCount()}\n")
    
    println("TV Operations:")
    smartHome.decreaseTvVolume()        
    smartHome.decreaseTvVolume()        
    smartHome.changeTvChannelToPrevious() 
    println()
    
    
    println(" Light Operations:")
    smartHome.decreaseLightBrightness()  
    smartHome.decreaseLightBrightness()  
    println()
    
    
    println(" Updated Device Info:")
    smartHome.printSmartTvInfo()
    smartHome.printSmartLightInfo()
    println()
    
    
    println("Turning off devices:")
    smartHome.turnOffTv()
    smartHome.turnOffLight()
    println("Total devices turned on during session: ${smartHome.getDeviceTurnOnCount()}\n")
    
    
    println("  Testing with devices off:")
    smartHome.decreaseTvVolume()         
    smartHome.decreaseLightBrightness()  
    println()
    
 
    println(" Turning on TV again:")
    smartHome.turnOnTv()  
    smartHome.decreaseTvVolume()
    println("Total devices turned on: ${smartHome.getDeviceTurnOnCount()}")
}