package spm.androidworld.all.xmlParsing

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

//@Parcelize
@Root(name = "objStation", strict = false)
data class ObjStationData(
    @field:Element(name = "StationDesc", required = true) var stationDesc: String = "",
    @field:Element(name = "StationAlias", required = false) var stationAlias: String = "",
    @field:Element(name = "StationLatitude", required = true) var stationLatitude: Float,
    @field:Element(name = "StationLongitude", required = true) var stationLongitude: Float,
    @field:Element(name = "StationCode", required = true) var stationCode: String = "",
    @field:Element(name = "StationId", required = true) var stationId: String = ""
)/*:Parcelable*/