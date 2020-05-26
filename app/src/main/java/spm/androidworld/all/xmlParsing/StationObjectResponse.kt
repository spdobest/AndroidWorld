package spm.androidworld.all.xmlParsing

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

//@Parcelize
@Root(strict = false, name = "ArrayOfObjStation")
data class StationObjectResponse constructor(
    @field:ElementList(
        required = false,
        name = "objStation",
        entry = "objStation",
        inline = true,
        empty = true
    )
    var cardsSummaryList: MutableList<ObjStationData>? = null
)/*: Parcelable*/